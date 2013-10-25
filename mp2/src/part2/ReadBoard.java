package part2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadBoard 
{	
	private String fileName;
	int[][] cells;
	int dim;
	
	public ReadBoard(String boardName, int size)
	{
		dim=size;
		this.fileName= boardName;
		read();
	}
	public void read()
	{
		cells= new int[dim][dim];
		
		InputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        
    	for(int i=0; i<dim; i++)
    	{
    		try {
				line= br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		String [] indiv = line.split("[^\\d]+");
    		
    		for(int j=0; j<dim; j++)
    		{
    			cells[j][i]= Integer.parseInt(indiv[j]);
    		}
    	}
    	
    	///// print out cells array
    	for(int i=0; i<dim; i++)
    	{
        	for(int j=0; j<dim; j++)
        	{
        		System.out.print(cells[j][i]);
        	}
        	System.out.println();
    	}
    	
	}

}
