package mp1;

import java.util.List;

public class MultipleState {
	private Cell cell;
	private List<Cell> reachedGoals;
	private List<Cell> visited;
	private List<Cell> solution;
	public int pathCost = 0;
	private double heuristic = 0;
	
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
	
	public MultipleState(Cell c, List<Cell> s, List<Cell> v, List<Cell> sol, int pc, double h) {
		this.cell = c;
		this.reachedGoals = s;
		this.visited = v;
		this.pathCost = pc;
		this.solution = sol;
		this.heuristic = h;
	}
	
	public MultipleState(Cell c, List<Cell> s, int pc, double h) {
		this.cell = c;
		this.reachedGoals = s;
		this.pathCost = pc;
		this.heuristic = h;
	}
	
	public double getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}
	public List<Cell> getSolution() {
		return solution;
	}
	public void setSolution(List<Cell> solution) {
		this.solution = solution;
	}
	
	
	
	
	
	
}
