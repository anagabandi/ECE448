package part2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import javax.swing.JPanel;


public class Visual 
{
	int [][] cells;
	JFrame window;
	JPanel panel;
	int scoreZero;
	int scoreOne;
	JFrame frame;
	Label zeroScore;
	Label oneScore;
	JButton[][] grid;
	int dim;
	
	public Visual(int[][] oldcells, int size)
	{
		dim=size;
        scoreZero=0;
        scoreOne=0;
		cells=new int[dim][dim];
		for(int i=0; i<dim; i++)
		{
			for(int j=0; j<dim; j++)
			{
				this.cells[j][i]= oldcells[j][i];
				System.out.println(this.cells[j][i]);
			}
		}
		
		frame= new JFrame("War Game");
		frame.setLayout(new GridLayout(9,dim)); 
        grid=new JButton[dim][dim]; 
        for(int y=0; y<dim; y++)
        { 
                for(int x=0; x<dim; x++)
                {
                        grid[x][y]=new JButton(" "+cells[x][y]+" ");   
                        frame.add(grid[x][y]); 
                }
        }

        
        zeroScore= new Label(""+scoreZero);
        oneScore= new Label(""+scoreOne);
        
        frame.add(new Label(""));
        frame.add(new Label(""));
        frame.add(new Label(""));
        frame.add(new Label(""));
        frame.add(new Label(""));
        frame.add(new Label(""));
        frame.add(new Label("Blue: "));
        frame.add(zeroScore);
        frame.add(new Label(""));
        frame.add(new Label(""));
        frame.add(new Label(""));
        frame.add(new Label(""));
        frame.add(new Label("Green: "));
        frame.add(oneScore);
        frame.add(new Label(""));
        frame.add(new Label(""));
        frame.add(new Label(""));
        frame.add(new Label(""));
      
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.pack(); 
        frame.setVisible(true);
       
	}
	
	
	public void updateMove(int player, int location)
	{
		int x= location%dim;
		int y= location/dim;
		
		if(player==0)
		{
			frame.remove(grid[x][y]);
			grid[x][y].setBackground(Color.BLUE);
			grid[x][y].setOpaque(true);
            frame.add(grid[x][y], location);
            frame.setVisible(false);
            frame.setVisible(true);
		}
		else
		{
			frame.remove(grid[x][y]);
			grid[x][y].setBackground(Color.GREEN);
			grid[x][y].setOpaque(true);
            frame.add(grid[x][y], location);
            frame.setVisible(false);
            frame.setVisible(true);
		}
	}
	
	public void updateScores(int score0, int score1)
	{
        //frame.setVisible(false);
        
        frame.remove(zeroScore);
        scoreZero=score0;
        zeroScore= new Label(""+scoreZero);
        frame.add(zeroScore, 43);
        
        frame.remove(oneScore);
        scoreOne=score1;
        oneScore= new Label(""+scoreOne);
        frame.add(oneScore, 49);
        
        //frame.setVisible(true);
		
	}
	
	public void goToEndScreen()
	{
		
	}
	
}



