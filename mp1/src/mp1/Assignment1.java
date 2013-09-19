package mp1;

public class Assignment1 {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String file = "openMaze.lay";
		
		Search bfs = new BreadthFirstSearch();
		bfs.loadMap(file);
		bfs.printMap();
		bfs.findPath();
		bfs.printMap();
		
		Search dfs = new DepthFirstSearch();
		dfs.loadMap(file);
		dfs.printMap();
		dfs.findPath();
		dfs.printMap();
		
	}

}
