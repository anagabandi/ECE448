package part2;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MiniMax 
{
    private int[][] cells;
    State state;
    Visual v;
	int dim;
    
	public MiniMax(String boardName, int size) 
    {
		dim=size;
		ReadBoard b= new ReadBoard(boardName, dim);
        this.cells = b.cells;
        state= new State(cells, dim);
        v= new Visual(cells, dim);
    }
	
	public void play()
	{
		int count=0;
		while(!state.available.isEmpty())
		{
			//Node first= new Node(-1, cells, state.available);
			if(count%2==0)
				state= makeMove(0, state);
			else
				state= makeMove(1, state);
			count++;
		}
	}
	
	public State makeMove(int player, State currState)
	{

		int minlevel2Child;
		
		int other=1;
		if(player==1)
			other=0;
		State state= currState;
		int bestOption=-1;
		int moveToChoose=-10;
		
		for(int i=0; i<state.available.size(); i++)
		{
			int newLoc= state.available.get(i);
			
		
			/*for(int j=0; j<state.available.size(); j++)
			{
				System.out.println("avail "+state.available.get(j));
			}*/
			Node n= new Node(newLoc, cells, state.available, dim);
			minlevel2Child= 200*40;
			
			State tempState= new State(cells, dim);
			tempState.available= new ArrayList<Integer>();
			for(int b=0; b<state.available.size(); b++)
			{
				tempState.available.add(state.available.get(b));
			}
			tempState.zeroSquares= state.zeroSquares;
			tempState.oneSquares= state.oneSquares;
			tempState.zeroScore= state.zeroScore;
			tempState.oneScore= state.oneScore;
			
			//System.out.println("no");
			updateScore(player, newLoc, tempState); 
			
			for(int j=0; j<tempState.available.size(); j++)
			{
				if(tempState.available.isEmpty())
					break;
				int newLoc2= tempState.available.get(j);
				Node level2Child= new Node(newLoc2, cells, tempState.available, dim);
				n.children.add(level2Child);

				
				//make tempState to play with scores
					//did i copy right????
				
				//System.out.println("wat??");
				updateScore(player, newLoc2, tempState);
				if(tempState.available.isEmpty())
					break;
				
				int maxlevel3Child=-1;
				
				
				for(int k=0; k<tempState.available.size(); k++)
				{
					int newLoc3= tempState.available.get(k);
					Node level3Child= new Node(newLoc3, cells, tempState.available, dim);
					level2Child.children.add(level3Child);
					
					//calculate value you get if you take this path
						//player goes to newLoc, then other goes to newLoc2, then player goes to newLoc3

					updateScore(player, newLoc3, tempState);
					
					
					int level3val=tempState.oneScore;
					if(player==0)
						level3val= tempState.zeroScore;
					//is ^ this really what we want?????????
					
					if(level3val<0)
						level3val=0;
					//keep track of max of those values
					
					if(level3val>maxlevel3Child)
					{
						maxlevel3Child=level3val;

					}
				}
				
				//miniMax value of level2Child is maxlevel3Child
					//get min of these lev2child miniMaxvals 

				if(maxlevel3Child< minlevel2Child)
				{
					minlevel2Child=maxlevel3Child;
				}
			}
			//miniMax value of 1st level nodes is minlevel2Child
				//get max of these to choose as where you move
			
			if(minlevel2Child>bestOption)
			{
				bestOption= minlevel2Child;
				moveToChoose= newLoc;
			}
		
		}
		
		updateScore(player, moveToChoose, state);
		System.out.println("Player " + player+ " moved to "+moveToChoose); //change format
		v.updateScores(state.zeroScore, state.oneScore);
		v.updateMove(player, moveToChoose);
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return state;
	}
	
	
	//removes from available, adds to player lists, update scores
	public void updateScore(int player, int location, State state)
	{
		//System.out.println("called updateScore with location "+location);
		/*for(int j=0; j<state.available.size(); j++)
		{
			System.out.print(" avail "+state.available.get(j));
		}
		System.out.println();*/
		
		int i= state.available.indexOf(location);
		//System.out.println("removed location "+ location);
		state.available.remove(i);
		
		int x= location%dim;
		int y= location/dim;
		
		if(player==0) //player 0's turn to move
		{
			state.zeroSquares.add(location);
			state.zeroScore+=cells[x][y];
			
			//if it's next to its own pieces--- it's a blitz 			
			if(((x+1<dim)&& state.zeroSquares.contains(y*dim+x+1)) || ((y-1)>=0 &&(state.zeroSquares.contains((y-1)*dim+x))) || ((x-1)>=0 && state.zeroSquares.contains(y*dim+x-1)) || ((y+1)<dim && state.zeroSquares.contains((y+1)*dim+x)))
			{
				//if it's also next player 1's piece, take that and adjust scores
				if((x+1<dim)&& state.oneSquares.contains(y*dim+x+1)) //right
				{
					state.zeroScore+=cells[x+1][y];
					state.oneScore-=cells[x+1][y];
					
					state.zeroSquares.add(y*dim+x+1);
					int a= state.oneSquares.indexOf(y*dim+x+1);
					state.oneSquares.remove(a);
				}
				if ((y-1)>=0 &&(state.oneSquares.contains((y-1)*dim+x))) //above
				{
					state.zeroScore+=cells[x][y-1];
					state.oneScore-=cells[x][y-1];
					
					state.zeroSquares.add((y-1)*dim+x);
					int a= state.oneSquares.indexOf((y-1)*dim+x);
					state.oneSquares.remove(a);
				}
				if((x-1)>=0 && state.oneSquares.contains(y*dim+x-1)) //left
				{
					state.zeroScore+=cells[x-1][y];
					state.oneScore-=cells[x-1][y];
					
					state.zeroSquares.add(y*dim+x-1);
					int a= state.oneSquares.indexOf(y*dim+x-1);
					state.oneSquares.remove(a);
				}
				if((y+1)<dim && state.oneSquares.contains((y+1)*dim+x)) //below
				{
					state.zeroScore+=cells[x][y+1];
					state.oneScore-=cells[x][y+1];
					
					state.zeroSquares.add((y+1)*dim+x);
					int a= state.oneSquares.indexOf((y+1)*dim+x);
					state.oneSquares.remove(a);
				}
			}
		}
		else //player 1's turn to move
		{
			state.oneSquares.add(location);
			state.oneScore+=cells[x][y];
			
			
			
			//if it's next to its own pieces--- it's a blitz 			
			if(((x+1<dim)&& state.oneSquares.contains(y*dim+x+1)) || ((y-1)>=0 &&(state.oneSquares.contains((y-1)*dim+x))) || ((x-1)>=0 && state.oneSquares.contains(y*dim+x-1)) || ((y+1)<dim && state.oneSquares.contains((y+1)*dim+x)))
			{
				//if it's also next player 0's piece, take that and adjust scores
				if((x+1<dim)&& state.zeroSquares.contains(y*dim+x+1)) //right
				{
					state.zeroScore-=cells[x+1][y];
					state.oneScore+=cells[x+1][y];
					
					int a= state.zeroSquares.indexOf(y*dim+x+1);
					state.zeroSquares.remove(a);
					state.oneSquares.add(y*dim+x+1);
				}
				if ((y-1)>=0 &&(state.zeroSquares.contains((y-1)*dim+x))) //above
				{
					state.zeroScore-=cells[x][y-1];
					state.oneScore+=cells[x][y-1];
					
					int a= state.zeroSquares.indexOf((y-1)*dim+x);
					state.zeroSquares.remove(a);
					state.oneSquares.add((y-1)*dim+x);
				}
				if((x-1)>=0 && state.zeroSquares.contains(y*dim+x-1)) //left
				{
					state.zeroScore-=cells[x-1][y];
					state.oneScore+=cells[x-1][y];
					
					int a= state.zeroSquares.indexOf(y*dim+x-1);
					state.zeroSquares.remove(a);
					state.oneSquares.add(y*dim+x-1);
				}
				if((y+1)<dim && state.zeroSquares.contains((y+1)*dim+x)) //below
				{
					state.zeroScore-=cells[x][y+1];
					state.oneScore+=cells[x][y+1];
					
					int a= state.zeroSquares.indexOf((y+1)*dim+x);
					state.zeroSquares.remove(a);
					state.oneSquares.add((y+1)*dim+x);
				}
			}
		}
		
	}
}
