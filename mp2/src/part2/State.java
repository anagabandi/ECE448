package part2;

import java.util.ArrayList;

public class State 
{
	ArrayList<Integer> available;
	ArrayList<Integer> zeroSquares;
	ArrayList<Integer> oneSquares;
	int zeroScore;
	int oneScore;
	
	public State(int[][] cells, int dim)
	{
		available= new ArrayList<Integer>();
		zeroSquares= new ArrayList<Integer>();
		oneSquares= new ArrayList<Integer>();
		
		for(int i=0; i<dim*dim; i++)
		{
			available.add(i);
		}
		zeroScore=0;
		oneScore=0;
	}
	
}
