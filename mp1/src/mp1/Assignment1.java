package mp1;

import java.util.PriorityQueue;

public class Assignment1 {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String file = "mediumMaze.lay";
//	
		Search bfs = new BreadthFirstSearch();
		bfs.loadMap(file);
		bfs.findPath();
		bfs.printMap();
//		
//		Search dfs = new DepthFirstSearch();
//		dfs.loadMap(file);
//		dfs.findPath();
//		dfs.printMap();
//		
//		Search gbfs = new GreedyBestFirstSearch();
//		gbfs.loadMap(file);
//		gbfs.findPath();
//		gbfs.printMap();
//		
//		Search astar = new AStarSearch();
//		astar.loadMap(file);
//		astar.findPath();
//		astar.printMap();
		
		Search ucs = new UniformCostSearch();
		ucs.loadMap(file);
		ucs.findPath();
		ucs.printMap();
		

	
		
	}

}
