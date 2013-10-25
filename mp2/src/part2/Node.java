package part2;

import java.util.ArrayList;

public class Node 
{
	ArrayList<Node> children= new ArrayList<Node>();
	int value;
	ArrayList<Integer> available;

	public Node(int newLoc, int[][] cells2, ArrayList<Integer> availPrev, int dim) 
	{
		available= new ArrayList<Integer>();
		for(int i=0; i<availPrev.size(); i++)
		{
			this.available.add(availPrev.get(i));
		}
		
		int x= newLoc%dim;
		int y= newLoc/dim;
		value= cells2[x][y];
		
		
		int i= this.available.indexOf(newLoc);
		this.available.remove(i);
	}
	
}
