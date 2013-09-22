package mp1;

import java.util.Stack;

public class DepthFirstSearch extends Search {
	private int startX, startY, goalX, goalY;
	
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
		System.out.println("Solving DFS");
		setInititalStates();

		Stack<Cell> st = new Stack<Cell>();
		boolean foundSolution = false;
		
		int x = startX;
		int y = startY;
		
		st.add(cells[x][y]);
		
		while(!st.isEmpty() && !foundSolution) {
			if(maxFrontierSize < st.size()) maxFrontierSize = st.size();
			
			Cell c = st.pop();
			
			if(!c.isHasVisited()) { // Only "expands" the node if we haven't been there already
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
					
					st.push(tmp);
				}
				
				if(canTravel(x, y - 1)) { // left
					Cell tmp = cells[x][y-1];
					tmp.height = c.height + 1;
					if(maxHeight < tmp.height) maxHeight = tmp.height;
					tmp.parentX = c.getX();
					tmp.parentY = c.getY();
					
					st.push(tmp);
	
				}
				
				if(canTravel(x + 1, y)) { // down
					
					Cell tmp = cells[x+1][y];
					tmp.height = c.height + 1;
					if(maxHeight < tmp.height) maxHeight = tmp.height;
					tmp.parentX = c.getX();
					tmp.parentY = c.getY();
					
					st.push(tmp);
				}
				
				if(canTravel(x, y + 1)) { // right
					
					Cell tmp = cells[x][y+1];
					tmp.height = c.height + 1;
					if(maxHeight < tmp.height) maxHeight = tmp.height;
					tmp.parentX = c.getX();
					tmp.parentY = c.getY();
					
					st.push(tmp);
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
