package mp1;

public class Cell {
	private boolean hasVisited;
	public CellType cellType;
	private int x, y;
	
	public int parentX, parentY;
	public int height;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isHasVisited() {
		return hasVisited;
	}

	
	public Cell(int x, int y, Character c) {
		if(c.equals('%')) this.cellType = CellType.WALL;
		if(c.equals(' ')) this.cellType = CellType.OPEN;
		if(c.equals('.')) this.cellType = CellType.GOAL;
		if(c.equals('P')) this.cellType = CellType.START;

		this.hasVisited = false;
		this.x = x;
		this.y = y;
		this.height = 0;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Character toChar() {
		if(this.cellType.equals(CellType.WALL)) return '%';
		if(this.cellType.equals(CellType.OPEN)) return ' ';
		if(this.cellType.equals(CellType.GOAL)) return '.';
		if(this.cellType.equals(CellType.START)) return 'P';
		if(this.cellType.equals(CellType.VISITED)) return '^';
		return ' ';
	}
	
	public void markAsVisited() {
		this.hasVisited = true;
		this.cellType = CellType.VISITED;

	}
	
	public void markAsPath() {
		this.cellType = CellType.GOAL;
	}
	
	public boolean isGoal() {
		if(this.cellType.equals(CellType.GOAL)) {
			return true;	
		}
		return false;
	}
	
	public boolean isWall() {
		if(this.cellType.equals(CellType.WALL)) {
			return true;	
		}
		return false;
	}
}
