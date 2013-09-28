package mp1;

import java.util.List;

public class MultipleState {
	private Cell cell;
	private List<Cell> reachedGoals;
	private List<Cell> visited;
	public int pathCost = 0;
	
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	public List<Cell> getReachedGoals() {
		return reachedGoals;
	}
	public void setReachedGoals(List<Cell> reachedGoals) {
		this.reachedGoals = reachedGoals;
	}
	
	public List<Cell> getVisited() {
		return visited;
	}
	public void setVisited(List<Cell> visited) {
		this.visited = visited;
	}
	
	public MultipleState(Cell c, List<Cell> s, List<Cell> v) {
		this.cell = c;
		this.reachedGoals = s;
		this.visited = v;
	}
	
	public MultipleState(Cell c, List<Cell> s, List<Cell> v, int pc) {
		this.cell = c;
		this.reachedGoals = s;
		this.visited = v;
		this.pathCost = pc;
	}
	
	
	
	
	
}
