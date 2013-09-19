package mp1;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends Search {
	public int startX, startY, goalX, goalY;
	
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
		setInititalStates();

		Queue<Cell> qu = new LinkedList<Cell>();
		boolean foundSolution = false;
		
		int x = startX;
		int y = startY;
		
		qu.add(cells[x][y]);
		cells[x][y].markAsVisited();
		
		while(!qu.isEmpty() && !foundSolution) {
			if(maxFrontierSize < qu.size()) maxFrontierSize = qu.size();
			
			Cell c = qu.remove();
			
			x = c.getX();
			y = c.getY();
			cells[x][y].markAsVisited();
			System.out.println(cells[x][y].isHasVisited());
			System.out.println("("+ x + ", " + y + ")");

			
			
			if(c.isGoal()) {
				foundSolution = true;
			}
			
			// Add nearby to frontier
			if(canTravel(x, y - 1)) {
				Cell tmp = cells[x][y-1];
				tmp.height = c.height + 1;
				if(maxHeight < tmp.height) maxHeight = tmp.height;
				tmp.parentX = c.getX();
				tmp.parentY = c.getY();
				
				qu.add(tmp);

			}
			
			if(canTravel(x + 1, y)) {
				
				Cell tmp = cells[x+1][y];
				tmp.height = c.height + 1;
				if(maxHeight < tmp.height) maxHeight = tmp.height;
				tmp.parentX = c.getX();
				tmp.parentY = c.getY();
				
				qu.add(tmp);
			}
			
			if(canTravel(x, y + 1)) {
				
				Cell tmp = cells[x][y+1];
				tmp.height = c.height + 1;
				if(maxHeight < tmp.height) maxHeight = tmp.height;
				tmp.parentX = c.getX();
				tmp.parentY = c.getY();
				
				qu.add(tmp);
			}
			
			if(canTravel(x - 1, y)) {
				
				Cell tmp = cells[x-1][y];
				tmp.height = c.height + 1;
				if(maxHeight < tmp.height) maxHeight = tmp.height;
				tmp.parentX = c.getX();
				tmp.parentY = c.getY();
				
				qu.add(tmp);
			}
			
			
		}
		
		// Print solution
		x = goalX;
		y = goalY;

		while(x != startX || y != startY) {
			System.out.println("("+ x + ", " + y + ")");
			Cell tmp = cells[x][y];
			tmp.markAsPath();
			x = tmp.parentX;
			y = tmp.parentY;
			
		}
	}
	
	protected boolean canTravel(int x, int y) {
		if(x < 0 || x > (rows - 1)) return false;
		if(y < 0 || y > (columns - 1)) return false;
		if(cells[x][y].isWall()) return false;
		if(cells[x][y].isHasVisited()) return false;
		return true;
	}

}
