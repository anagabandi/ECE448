package mp1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SuboptimalSearch extends Search {
	private int startX, startY;
	private ArrayList<Cell> goals;
	private ArrayList<Cell> solution;

	private int heuristic(int x, int y, int goalX, int goalY) {
		return Math.abs(goalX - x) + Math.abs(goalY - y);
	}
	
	private void setInititalStates() {
		goals = new ArrayList<Cell>();
		solution = new ArrayList<Cell>();

		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(cells[i][j].cellType.equals(CellType.START)) {
					startX = i;
					startY = j;
				} else if(cells[i][j].cellType.equals(CellType.GOAL)) {
					goals.add(cells[i][j]);
				}
			}
		}
	}
	
	@Override
	public void findPath() {
		
		System.out.println("Solving Suboptimal Search");
		setInititalStates();
		
		int x = startX;
		int y = startY;
		int goalX = -1;
		int goalY = -1;
		
		
		while(!goals.isEmpty()) {
			int currentStartX = x;
			int currentStartY = y;
			double minDistance = Double.MAX_VALUE;
			for(Cell q : goals) {
				if(distance(x, y, q.getX(), q.getY()) < minDistance) {
					minDistance = distance(x, y, q.getX(), q.getY());
					goalX = q.getX();
					goalY = q.getY();
				}
			}
			
			PriorityQueue<Cell> pq = new PriorityQueue<Cell>(10, new Comparator<Cell>() {
				@Override
				public int compare(Cell n1, Cell n2) {
					if(n1.getDistance() > n2.getDistance()) {
						return +1;
					}
					else if (n1.getDistance() < n2.getDistance()) {
						return -1;
					}
					else {
						return 0;
					}
				}
			});
			
			cells[x][y].setDistance(heuristic(x, y, goalX, goalY));
			pq.add(cells[x][y]);
			
			boolean foundSolution = false;
			while(!pq.isEmpty() && !foundSolution) {
				if(maxFrontierSize < pq.size()) maxFrontierSize = pq.size();
				
				Cell c = pq.remove();
				
				if(!c.isHasVisited()) {
					x = c.getX();
					y = c.getY();
					
					cells[x][y].markAsVisited();
					if(goals.contains(cells[x][y])) {
						goals.remove(cells[x][y]);
						solution.add(cells[x][y]);
					}
					numberOfNodesExpanded++;
									
					
					if(x == goalX && y == goalY) {
						foundSolution = true;
					}
					
					// Add nearby to frontier
					
					if(canTravel(x - 1, y)) { //up
						
						Cell tmp = cells[x-1][y];
						tmp.height = c.height + 1;
						if(maxHeight < tmp.height) maxHeight = tmp.height;
						tmp.parentX = c.getX();
						tmp.parentY = c.getY();
						
						tmp.setDistance(heuristic(x-1, y, goalX, goalY));
						pq.add(tmp);
					}
					
					if(canTravel(x, y - 1)) { // left
						Cell tmp = cells[x][y-1];
						tmp.height = c.height + 1;
						if(maxHeight < tmp.height) maxHeight = tmp.height;
						tmp.parentX = c.getX();
						tmp.parentY = c.getY();
						
						tmp.setDistance(heuristic(x, y-1, goalX, goalY));
						pq.add(tmp);
		
					}
					
					if(canTravel(x + 1, y)) { // down
						
						Cell tmp = cells[x+1][y];
						tmp.height = c.height + 1;
						if(maxHeight < tmp.height) maxHeight = tmp.height;
						tmp.parentX = c.getX();
						tmp.parentY = c.getY();
						
						tmp.setDistance(heuristic(x+1, y, goalX, goalY));
						pq.add(tmp);
					}
					
					if(canTravel(x, y + 1)) { // right
						
						Cell tmp = cells[x][y+1];
						tmp.height = c.height + 1;
						if(maxHeight < tmp.height) maxHeight = tmp.height;
						tmp.parentX = c.getX();
						tmp.parentY = c.getY();
						
						
						tmp.setDistance(heuristic(x, y+1, goalX, goalY));
						pq.add(tmp);
					}

				}
							
			}
			int tmp_x = x;
			int tmp_y = y;
			
			while(tmp_x != currentStartX || tmp_y != currentStartY) {
				pathLength++;
				Cell tmp = cells[tmp_x][tmp_y];
				tmp_x = tmp.parentX;
				tmp_y = tmp.parentY;
				
			}
			
			if(goals.contains(cells[x][y])) {
				goals.remove(cells[x][y]);
				solution.add(cells[x][y]);
			}
			clearVisited();

			
		}
	}
	
	private double distance(int x1, int y1, int x2, int y2) {
		//return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2), .5);
		return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
	}
	
	private void clearVisited() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				cells[i][j].markAsUnvisited();
			}
		}
	}
	
	@Override
	public void printMap() {
		// TODO Auto-generated method stub
		// Prints out the map matrix to System.out
		
		if(solution.size() < 36) {
			String [][] printCells = new String[this.rows][this.columns];
			for(int i = 0; i < this.rows; i++) {
				for(int j = 0; j < this.columns; j++) {
					printCells[i][j] = cells[i][j].toChar();
				}
			}
			
			Integer order = 0;
			for(Cell q : solution) {
				printCells[q.getX()][q.getY()] = display[order];
				order++;
			}
			
			for(int i = 0; i < this.rows; i++) {
				System.out.println();
				for(int j = 0; j < this.columns; j++) {
					System.out.print(printCells[i][j]);
				}
			}
		} else {
			System.out.println("\nToo many goals to display...");
		}
		System.out.println();
		System.out.println("Shortest Path: " + pathLength);
		System.out.println("Nodes Expanded: " + numberOfNodesExpanded);
		System.out.println();
	}

}
