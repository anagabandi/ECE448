package mp1;

public class Assignment1 {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		test1();
		test2();
		//testAStar();
		//testMultipleDFS();
		//testSuboptimalSearch();
		
	}
	
	private static void test1() throws Exception {
		System.out.println("!!!!TESTING MP1.1!!!!");
		String [] mazes = {"smallMaze.lay", "mediumMaze.lay", "bigMaze.lay", "openMaze.lay"};
		Search [] searches = {new DepthFirstSearch(), new BreadthFirstSearch(), new GreedyBestFirstSearch(), new AStarSearch()};
		
		execute(mazes, searches);
		
		
	}
	
	private static void test2() throws Exception {
		System.out.println("!!!!TESTING MP1.2!!!!");
		String [] mazes = {"mediumMaze.lay"};
		Search [] searches = {new UniformCostSearch(1), new UniformCostSearch(2)}; 
		
		execute(mazes, searches);
	}
	
	private static void execute(String [] layouts, Search [] searches) throws Exception {
		for(String layout : layouts) {
			for(Search search : searches) {
				search.loadMap(layout);
				search.findPath();
				search.printMap();
				
				System.out.println("--------------------------------");
				System.out.println();
			}
			
		}
	}
	
	private static void testAStar() throws Exception {
		System.out.println("!!!!TESTING A*!!!!");
		String [] mazes = {"openMaze.lay", "smallMaze.lay", "mediumMaze.lay", "bigMaze.lay"};
		Search [] searches = {new AStarSearch()};
		
		execute(mazes, searches);
	}
	
	private static void testMultipleDFS() throws Exception {
		System.out.println("!!!!TESTING Multiple DFS!!!!");
		String [] layouts = {"tinySearch.lay", "mediumSearch.lay", "bigSearch.lay", "trickySearch.lay"};
		Search [] searches = {new MultipleDepthFirstSearch()};
		execute(layouts, searches);
		
	}
	
	private static void testSuboptimalSearch() throws Exception {
		System.out.println("!!!!TESTING Suboptimal Search!!!!");
		String [] layouts = {"tinySearch.lay", "mediumSearch.lay", "bigSearch.lay", "trickySearch.lay"};
		Search [] searches = {new SuboptimalSearch()};
		execute(layouts, searches);
		
	}

}
