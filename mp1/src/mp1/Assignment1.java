package mp1;

public class Assignment1 {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String file = "trickySearch.lay";
//	
//		Search bfs = new BreadthFirstSearch();
//		bfs.loadMap(file);
//		bfs.findPath();
//		bfs.printMap();
////		
////		Search dfs = new DepthFirstSearch();
////		dfs.loadMap(file);
////		dfs.findPath();
////		dfs.printMap();
////		
//		Search gbfs = new GreedyBestFirstSearch();
//		gbfs.loadMap(file);
//		gbfs.findPath();
//		gbfs.printMap();
//
//		Search astar = new AStarSearch();
//		astar.loadMap(file);
//		astar.findPath();
//		astar.printMap();
	
//		Search ucs = new UniformCostSearch();
//		ucs.loadMap(file);
//		ucs.findPath();
//		ucs.printMap();
		
//		Search mdfs = new MultipleDepthFirstSearch();
//		mdfs.loadMap(file);
//		mdfs.findPath();
//		mdfs.printMap();
////		
//		Search mbfs = new MultipleBreadthFirstSearch();
//		mbfs.loadMap(file);
//		mbfs.findPath();
//		mbfs.printMap();
		
//		Search ss = new SuboptimalSearch();
//		ss.loadMap(file);
//		ss.findPath();
//		ss.printMap();
		
		Search mastar = new MultipleAStarSearch();
		mastar.loadMap(file);
		mastar.findPath();
		mastar.printMap();
		

	
		
	}

}
