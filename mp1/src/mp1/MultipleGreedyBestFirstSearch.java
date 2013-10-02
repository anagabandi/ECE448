package mp1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class MultipleGreedyBestFirstSearch extends Search {
	private int startX, startY;
	private ArrayList<Cell> goals;
	private ArrayList<Cell> visited = new ArrayList<Cell>();
	private ArrayList<Cell> solution = new ArrayList<Cell>();
	int total=0;
	
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
		System.out.println("Solving GreedyBestFirstSearch");
		setInititalStates();
		
		PriorityQueue<AStarState> pq = new PriorityQueue<AStarState>(10, new Comparator<AStarState>() {
			@Override
			public int compare(AStarState n1, AStarState n2) {
				if(n1.getHeuristic() > n2.getHeuristic()) {
					return +1;
				}
				else if (n1.getHeuristic() < n2.getHeuristic()) {
					return -1;
				}
				else {
					return 0;
				}
			}
		});
		
		int x = startX;
		int y = startY;
		
		AStarState a = new AStarState(cells[x][y], 0);
		a.setPathLength(0);
		pq.add(a);
		
		while(!goals.isEmpty()) {
			AStarState state = pq.remove();
			int currentPathLength = state.getPathLength();
			
			if(!visited.contains(state.getCell())) {
				
				visited.add(state.getCell());
				x = state.getCell().getX();
				y = state.getCell().getY();
				
				numberOfNodesExpanded++;
				
				if(goals.contains(state.getCell())) {
					
					visited = new ArrayList<Cell>(); //clear visited
					visited.add(state.getCell());
					goals.remove(state.getCell());
					solution.add(state.getCell());
					total+=currentPathLength;
					currentPathLength=0;
					
					while(!pq.isEmpty()) {
						pq.remove();
					}
					
				}
				if(canTravel(x, y + 1)) { // right
					Cell tmp = cells[x][y+1];
					AStarState toAdd = new AStarState(tmp, heuristic(x, y+1, currentPathLength));
					toAdd.setPathLength(currentPathLength+1);
					pq.add(toAdd);
				}
				
				if(canTravel(x - 1, y)) { //up
						
					Cell tmp = cells[x-1][y];
					AStarState toAdd = new AStarState(tmp, heuristic(x-1, y, currentPathLength));
					toAdd.setPathLength(currentPathLength+1);
					pq.add(toAdd);
				}
				
				if(canTravel(x, y - 1)) { // left
					Cell tmp = cells[x][y-1];
					AStarState toAdd = new AStarState(tmp, heuristic(x, y-1, currentPathLength));
					toAdd.setPathLength(currentPathLength+1);
					pq.add(toAdd);
				}
				
				if(canTravel(x + 1, y)) { // down	
					Cell tmp = cells[x+1][y];
					AStarState toAdd = new AStarState(tmp, heuristic(x+1, y, currentPathLength));
					toAdd.setPathLength(currentPathLength+1);
					pq.add(toAdd);
				}
			}
		}
		System.out.println("Done");		
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
		
		System.out.println();
		System.out.println("Number of expanded nodes:" + numberOfNodesExpanded);
		System.out.println("Path Length:"+ total);
		System.out.println();
	}

	private double heuristic(int x, int y, int pathLength) {
		// TODO Auto-generated method stub
		double dist = Double.MAX_VALUE;
		
		for(Cell q : goals) {
			if(distance(x, y, q.getX(), q.getY()) < dist) {
				dist = distance(x, y, q.getX(), q.getY());
			}
			
		}
		return dist;
	}
	
	private double distance(int x1, int y1, int x2, int y2) {
		//return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2), .5);
		return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
	}
}
