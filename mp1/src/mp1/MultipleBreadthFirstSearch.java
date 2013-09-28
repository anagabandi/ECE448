package mp1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class MultipleBreadthFirstSearch extends Search {
	private int startX, startY;
	private ArrayList<Cell> goals;
	private List<Cell> shortestOrder;
	private int nodesExpanded = 0;
	
	
	private void setInititalStates() {
		pathLength = Integer.MAX_VALUE;
		goals = new ArrayList<Cell>();

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
		System.out.println("Solving Multiple BFS");
		setInititalStates();
		
		int x = startX;
		int y = startY;
				
		Queue<MultipleState> qu = new LinkedList<MultipleState>();
		
		MultipleState ms = new MultipleState(cells[x][y], new ArrayList<Cell>(), new ArrayList<Cell>());
		
		qu.add(ms);
		
		while(!qu.isEmpty()) {
			MultipleState state = qu.remove();
			
			
			Cell c = state.getCell();
			List<Cell> reachedGoals = new ArrayList<Cell>();
			
			for(Cell q : state.getReachedGoals()) {
				reachedGoals.add(q);
			}
			
			List<Cell> visited = new ArrayList<Cell>();
			for(Cell q : state.getVisited()) {
				visited.add(q);
			}
			
			int nextPathCost = state.pathCost + 1;
			
			//if(!c.isHasVisited()) { // Only "expands" the node if we haven't been there already
				
				x = c.getX();
				y = c.getY();
				
				//System.out.print("Popped: ");
				nodesExpanded++;
				
//				System.out.print(c.getY() + " ");
//				for(Cell q : reachedGoals) {
//					System.out.print(q.getY() + " ");
//				}
//				System.out.println();

				//cells[x][y].markAsVisited();
				visited.add(cells[x][y]);
				
				for(Cell i : goals) {
					if(i.getX() == x && i.getY() == y && !reachedGoals.contains(i)) {
						reachedGoals.add(i);
						clearVisited();
						visited = new ArrayList<Cell>();
						//cells[x][y].markAsVisited();
						visited.add(cells[x][y]);
					}
				}
				
				
				// Add nearby to frontier
				if(reachedGoals.size() != goals.size()) {
					if(canTravel(x - 1, y) && !visited.contains(cells[x-1][y])) { //up
						
						Cell tmp = cells[x-1][y];
						tmp.height = c.height + 1;
						if(maxHeight < tmp.height) maxHeight = tmp.height;
						tmp.parentX = c.getX();
						tmp.parentY = c.getY();
						
//						System.out.print("Pushed: ");
//						System.out.print(tmp.getY() + " ");
//						for(Cell q : reachedGoals) {
//							System.out.print(q.getY() + " ");
//						}
//						System.out.println();
						
						qu.add(new MultipleState(tmp, reachedGoals, visited, nextPathCost));
					}
					
					if(canTravel(x, y - 1) && !visited.contains(cells[x][y-1])) { // left
						Cell tmp = cells[x][y-1];
						tmp.height = c.height + 1;
						if(maxHeight < tmp.height) maxHeight = tmp.height;
						tmp.parentX = c.getX();
						tmp.parentY = c.getY();
						
//						System.out.print("Pushed: ");
//						System.out.print(tmp.getY() + " ");
//						for(Cell q : reachedGoals) {
//							System.out.print(q.getY() + " ");
//						}
//						System.out.println();
						
						qu.add(new MultipleState(tmp, reachedGoals, visited, nextPathCost));
		
					}
					
					
					if(canTravel(x + 1, y) && !visited.contains(cells[x + 1][y])) { // down
						
						Cell tmp = cells[x+1][y];
						tmp.height = c.height + 1;
						if(maxHeight < tmp.height) maxHeight = tmp.height;
						tmp.parentX = c.getX();
						tmp.parentY = c.getY();

//						System.out.print("Pushed: ");
//						System.out.print(tmp.getY() + " ");
//						for(Cell q : reachedGoals) {
//							System.out.print(q.getY() + " ");
//						}
//						System.out.println();
						
						qu.add(new MultipleState(tmp, reachedGoals, visited, nextPathCost));
					}
					
					if(canTravel(x, y + 1) && !visited.contains(cells[x][y+1])) { // right
						
						Cell tmp = cells[x][y+1];
						tmp.height = c.height + 1;
						if(maxHeight < tmp.height) maxHeight = tmp.height;
						tmp.parentX = c.getX();
						tmp.parentY = c.getY();
						
//						System.out.print("Pushed: ");
//						System.out.print(tmp.getY() + " ");
//						for(Cell q : reachedGoals) {
//							System.out.print(q.getY() + " ");
//						}
//						System.out.println();
						
						qu.add(new MultipleState(tmp, reachedGoals, visited, nextPathCost));
					}
				} else {
					int pathCost = nextPathCost - 1;
					if(pathCost < pathLength) {
						pathLength = pathCost;
						shortestOrder = reachedGoals;
					}
				}
			//}
		}
		
		System.out.println("Done.");
	}

	
	private void clearVisited() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				cells[i][j].markAsUnvisited();
			}
		}
	}
	
	// Figures out if the path can be traveled or not.
	
	protected boolean canTravel(int x, int y) {
			if(x < 0 || x > (rows - 1)) return false;
			if(y < 0 || y > (columns - 1)) return false;
			if(cells[x][y].isWall()) return false;
			//if(cells[x][y].isHasVisited()) return false;
			return true;
	} 
	
	@Override
	public void printMap() {
		// TODO Auto-generated method stub
		// Prints out the map matrix to System.out
		String [][] printCells = new String[this.rows][this.columns];
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				printCells[i][j] = cells[i][j].toChar();
			}
		}
		
		Integer order = 1;
		for(Cell q : shortestOrder) {
			printCells[q.getX()][q.getY()] = order.toString();
			order++;
		}
		
		for(int i = 0; i < this.rows; i++) {
			System.out.println();
			for(int j = 0; j < this.columns; j++) {
				System.out.print(printCells[i][j]);
			}
		}
		System.out.println();
		System.out.println("Nodes Expanded: " + nodesExpanded);
	}
	


}
