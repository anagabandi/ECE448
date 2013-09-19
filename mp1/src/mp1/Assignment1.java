package mp1;

public class Assignment1 {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		bfs.loadMap("openMaze.lay");
		bfs.printMap();
		bfs.findPath();
		bfs.printMap();
		
	}

}
