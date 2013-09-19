package mp1;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UniformCostSearch extends Search {
private int startX, startY, goalX, goalY;
	
	private double cost1(int x, int y, double currentCost) {
		return Math.pow(1/2, y);
		//return Math.pow(2, y);

	}
	
	private double cost2(int x, int y, double currentCost) {
		return Math.pow(2, x) + currentCost;
	}
	
	private void setInititalStates() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(cells[i][j].cellType.equals(CellType.START)) {
					startX = i;
					startY = j;
				} else if(cells[i][j].cellType.equals(CellType.GOAL)) {
					goalX = i;
					goalY = j;
				}
			}
		}
	}
	
	@Override
	public void findPath() {
		PriorityQueue<Cell> pq = new PriorityQueue<Cell>(10, new Comparator<Cell>() {
			@Override
			public int compare(Cell n1, Cell n2) {
				if(n1.getCost() > n2.getCost()) {
					return +1;
				}
				else if (n1.getCost() < n2.getCost()) {
					return -1;
				}
				else {
					return 0;
				}
			}
		});
		
		System.out.println("Solving UCS");
		setInititalStates();
		boolean foundSolution = false;
		
		int x = startX;
		int y = startY;
		
		cells[x][y].setCost(0);
		pq.add(cells[x][y]);
		
		while(!pq.isEmpty() && !foundSolution) {
			if(maxFrontierSize < pq.size()) maxFrontierSize = pq.size();
			
			Cell c = pq.remove();
			
			if(!c.isHasVisited()) {
				x = c.getX();
				y = c.getY();
				
				cells[x][y].markAsVisited();
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
					
					tmp.setCost(cost1(x-1, y, tmp.getCost()));
					pq.add(tmp);
				}
				
				if(canTravel(x, y - 1)) { // left
					Cell tmp = cells[x][y-1];
					tmp.height = c.height + 1;
					if(maxHeight < tmp.height) maxHeight = tmp.height;
					tmp.parentX = c.getX();
					tmp.parentY = c.getY();
					
					tmp.setCost(cost1(x, y-1, tmp.getCost()));
					pq.add(tmp);
	
				}
				
				if(canTravel(x + 1, y)) { // down
					
					Cell tmp = cells[x+1][y];
					tmp.height = c.height + 1;
					if(maxHeight < tmp.height) maxHeight = tmp.height;
					tmp.parentX = c.getX();
					tmp.parentY = c.getY();
					
					tmp.setCost(cost1(x+1, y, tmp.getCost()));
					pq.add(tmp);
				}
				
				if(canTravel(x, y + 1)) { // right
					
					Cell tmp = cells[x][y+1];
					tmp.height = c.height + 1;
					if(maxHeight < tmp.height) maxHeight = tmp.height;
					tmp.parentX = c.getX();
					tmp.parentY = c.getY();
					
					
					tmp.setCost(cost1(x, y+1, tmp.getCost()));
					pq.add(tmp);
				}

			}
						
		}
		// Print solution
					x = goalX;
					y = goalY;

					while(x != startX || y != startY) {
						pathLength++;
						Cell tmp = cells[x][y];
						tmp.markAsPath();
						x = tmp.parentX;
						y = tmp.parentY;
						
					}

	}
}
