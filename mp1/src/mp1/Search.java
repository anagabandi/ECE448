package mp1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Search implements Searcher {
	Cell [][] cells;
	int rows, columns;
	int startX, startY;
	protected int maxHeight = 0, maxFrontierSize = 0;
	
	@Override
	public void loadMap(String fileName) throws Exception {
		// TODO Auto-generated method stub
		String line;
		InputStream fis = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		while ((line = br.readLine()) != null) {
		    // Deal with the line
			this.rows++;
			if(this.columns == 0) {
				this.columns = line.length();
			}	
		}
		
		System.out.println("Rows: " + rows);
		System.out.println("Columns: " + columns);

		cells = new Cell[rows][columns];
		
		br.close();
		br = null;
		fis = null;
		
		fis = new FileInputStream(fileName);
		br = new BufferedReader(new InputStreamReader(fis));
		
		for(int i = 0; i < this.rows; i++) {
			line = br.readLine();
			for(int j = 0; j < this.columns; j++) {
				cells[i][j] = new Cell(i, j, line.charAt(j));
			}
		}
		
		// Done with the file
		br.close();
		br = null;
		fis = null;
		
				
	}

	@Override
	public void printMap() {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.rows; i++) {
			System.out.println();
			for(int j = 0; j < this.columns; j++) {
				System.out.print(cells[i][j].toChar());
			}
		}
		System.out.println();
		System.out.println("Max Height: " + maxHeight);
		System.out.println("Max Frontier Size: " + maxFrontierSize);
	}

	@Override
	public void findPath() {
		// TODO Auto-generated method stub
		
	}
	
	

}