package mp1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javax.xml.crypto.NodeSetData;


public class MultipleAStarSearch extends Search {
	private int startX, startY;
	private ArrayList<Cell> goals;
	private List<Cell> endVisited = new ArrayList<Cell>();
	private List<Cell> endSolution = new ArrayList<Cell>();
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
		System.out.println("Solving Multiple A*");
		setInititalStates();

		
		PriorityQueue<MultipleState> pq = new PriorityQueue<MultipleState>(10, new Comparator<MultipleState>() {
			@Override
			public int compare(MultipleState n1, MultipleState n2) {
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
		
		MultipleState ms = new MultipleState(cells[x][y], new ArrayList<Cell>(), new ArrayList<Cell>(), new ArrayList<Cell>(), 0 ,0);

		pq.add(ms);
		boolean foundSolution = false;
		
		while(!pq.isEmpty() && !foundSolution) { // end case test
			MultipleState state = pq.remove();
			int currentPathLength = state.pathCost;
			
			// deep copies for new states
			List<Cell> visited = new ArrayList<Cell>();
			for(Cell q : state.getVisited()) {
				visited.add(q);
			}
			
			List<Cell> reachedGoals = new ArrayList<Cell>();
			for(Cell q : state.getReachedGoals()) {
				reachedGoals.add(q);
			}
			
			List<Cell> solution = new ArrayList<Cell>();
			for(Cell q : state.getSolution()) {
				solution.add(q);
			}

			currentPathLength = state.pathCost;
			
			if(!visited.contains(state.getCell())) {
				visited.add(state.getCell());
				x = state.getCell().getX();
				y = state.getCell().getY();
				
				numberOfNodesExpanded++;
				
				List<Cell> goalsLeft = new ArrayList<Cell>();
				for(Cell q : goals) {
					if(!reachedGoals.contains(q)) goalsLeft.add(q);
				}
				
				if(goalsLeft.contains(state.getCell())) { // checks to see if we've reached a goal
					visited = new ArrayList<Cell>(); //clear visited
					visited.add(state.getCell());
					reachedGoals.add(state.getCell());
					
					solution.add(state.getCell());
														
				}
				
				// Adding nodes to search tree
				
				if(canTravel(x, y + 1)) { // right
					Cell tmp = cells[x][y+1];
					MultipleState toAdd = new MultipleState(tmp, reachedGoals, visited, solution, currentPathLength, heuristic(x, y+1, currentPathLength, reachedGoals));
					toAdd.pathCost = currentPathLength+1;
					pq.add(toAdd);
				}
				
				if(canTravel(x - 1, y)) { //up	
					Cell tmp = cells[x-1][y];
					MultipleState toAdd = new MultipleState(tmp, reachedGoals, visited, solution, currentPathLength, heuristic(x-1, y, currentPathLength, reachedGoals));
					toAdd.pathCost = currentPathLength+1;
					pq.add(toAdd);
				}
				
				if(canTravel(x, y - 1)) { // left
					Cell tmp = cells[x][y-1];
					MultipleState toAdd = new MultipleState(tmp, reachedGoals, visited, solution, currentPathLength, heuristic(x, y-1, currentPathLength, reachedGoals));
					toAdd.pathCost = currentPathLength+1;
					pq.add(toAdd);
				}
				
				if(canTravel(x + 1, y)) { // down	
					Cell tmp = cells[x+1][y];
					MultipleState toAdd = new MultipleState(tmp, reachedGoals, visited, solution, currentPathLength, heuristic(x + 1, y, currentPathLength, reachedGoals));
					toAdd.pathCost = currentPathLength+1;
					pq.add(toAdd);
				}
				
				if(reachedGoals.size() == goals.size()) { // determines if all the nodes have been hit
					foundSolution = true;
					endSolution = solution;
					total = state.pathCost + 1;
					
				}
			}
		}
		System.out.println("Done");		
	}
	
	@Override
	public void printMap() {
		// Prints out the map matrix to System.out
		if(endSolution.size() < 36) {
			String [][] printCells = new String[this.rows][this.columns];
			for(int i = 0; i < this.rows; i++) {
				for(int j = 0; j < this.columns; j++) {
					printCells[i][j] = cells[i][j].toChar();
				}
			}
			
			Integer order = 0;
			for(Cell q : endSolution) {
				printCells[q.getX()][q.getY()] = display[order];
				order++;
			}
			
			for(int i = 0; i < this.rows; i++) {
				System.out.println();
				for(int j = 0; j < this.columns; j++) {
					System.out.print(printCells[i][j]);
				}
			}
		}
		
		System.out.println();
		System.out.println("Number of expanded nodes:" + numberOfNodesExpanded);
		System.out.println("Path Length:"+ total);
		System.out.println();
	}

	private double heuristic(int x, int y, int pathLength, List<Cell> goalsFound) {
		double dist = Double.MAX_VALUE;
		List<Cell> goalsLeft = new ArrayList<Cell>();
		for(Cell q : goals) {
			if(!goalsFound.contains(q)) goalsLeft.add(q);
		}
		
		for(Cell q : goalsLeft) {
			if(distance(x, y, q.getX(), q.getY()) < dist) {
				dist = distance(x, y, q.getX(), q.getY()); //gets closest distance to nearest node
			}
			
		}
		return (dist+pathLength+1) / goalsFound.size(); // weights based on how many nodes have been found
	}
	
	private double distance(int x1, int y1, int x2, int y2) {
		return (Math.abs(x1 - x2) + Math.abs(y1 - y2)); // calcuates manhattan distance
	}
}
