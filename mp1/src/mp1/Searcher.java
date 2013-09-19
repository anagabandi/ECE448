package mp1;

public interface Searcher {
	void loadMap(String fileName) throws Exception;
	void printMap();
	void findPath();
}
