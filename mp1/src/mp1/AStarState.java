package mp1;

public class AStarState {
	private Cell cell;
	private double heuristic;
	private int pathLength;
	
	public AStarState(Cell cell2, double i) {
		this.cell = cell2;
		this.heuristic = i;
	}
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	public double getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}
	public int getPathLength() {
		return pathLength;
	}
	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}
	
	
	
}
