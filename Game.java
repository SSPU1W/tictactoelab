import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;

public class Game
{
	private int[][] table;
	private int turn;
	private int player1Wins;
	private int player2Wins;
	private Font font;
	private Font font2;
	private boolean computerPlayed;

	public Game()
	{
		table = new int[3][3];
		turn = 1;
		player1Wins = 0;
		player2Wins = 0;
		computerPlayed = false;
		font = new Font("Heltevica", Font.BOLD, 100);
		font2 = new Font("Heltevica", Font.BOLD, 20);
	}

	public void printTable()
	{
		for(int r = 0; r < table.length; r++)
		{
			for(int c = 0; c < table[r].length; c++)
			{
				System.out.print(table[r][c] + "\t");
			}
			System.out.println();
		}
    System.out.println();
	}

	public void insertXO(int num1, int num2) 
	{
    if(turn%2 != 0 && table[num1][num2] == 0)
    {
      table[num1][num2] = 1;
    }
    else if(turn%2 == 0 && table[num1][num2] == 0)
    {
      table[num1][num2] = 2;
    }
    else if(table[num1][num2] != 0)
    {
      turn--;
    }
    turn++;
	}



	public int getTurn()
	{
    return turn % 2;
	}

  public int isTicTacToe()
  {
  	for(int r = 0; r < table.length; r++)
  	{
  		if(table[r][0] == table[r][1] && table[r][1] == table[r][2])
  		  return table[r][0];
  	}
    for(int r = 0; r < table.length; r++)
    {
    	for(int c = 0; c < table[r].length; c++)
      {
	    	if(table[0][c] == table[1][c] && table[1][c] == table[2][c])
	    		return table[0][c];
	    }
    }
    if(table[0][0] == table[1][1] && table[1][1] == table[2][2])
    	return table[0][0];
    else if (table[2][0] == table[1][1] && table[1][1] == table[0][2])
    	return table[2][0];
    return 0;
  }

  public void computer()
  {
    for(int r = 0; r < table.length; r++)
  	{
    	if(table[r][0] == table[r][1] && getTurn() == 0 && (table[r][0] == 1 || table[r][0] == 2 ))
    	{
        if(table[r][2] == 0)
        {
    	    table[r][2] = 2;
          turn++;
        }
    	} 
    	else if(table[r][1] == table[r][2] && getTurn() == 0 && (table[r][1] == 1 || table[r][1] == 2 ))
    	{
        if(table[r][0] == 0)
        {
          table[r][0] = 2;
          turn++;
        }
    	}
    	else if(table[r][0] == table[r][2] && getTurn() == 0 && (table[r][0] == 1 || table[r][0] == 2 ))
    	{
        if(table[r][1] == 0)
        {
          table[r][1] = 2;
          turn++;
        }
    	}
    }
  	if(table[1][1] == 0 && getTurn() == 0)
  	{
      table[1][1] = 2;
      turn++;
  	}
    else if(table[0][0] == table[1][1] && getTurn() == 0)
  	{
      if(table[2][2] == 0)
      {
        table[2][2] = 2;
        turn++;
      }
    }
    else if(table[1][1] == table[2][2] && getTurn() == 0)
    {
      if(table[0][0] == 0)
      {
        table[0][0] = 2;
        turn++;
      }
    }
    else if(table[2][0] == table[1][1] && getTurn() == 0)
    {
      if(table[0][2] == 0)
      {
        table[0][2] = 2;
        turn++;
      }
    }
    else if(table[1][1] == table[0][2] && getTurn() == 0)
    {
      if(table[2][0] == 0)
      {
        table[2][0] = 2;
        turn++;
      }
    }
    else if(getTurn() == 0)
    {
    	for(int r = 0; r < table.length; r++)
      {
        for(int c = 0; c < table[r].length; c++)
        {
          if(table[r][c] == 0)
          {
            table[r][c] = 2;
            turn++;
            return;
          }
        }
      }
  	}
  }
  
	public boolean checkFull()
	{
		boolean isFull = true;
    for(int r = 0; r < table.length; r++)
    {
      for(int c = 0; c < table[r].length; c++)
	    {
	    	if(table[r][c] == 0)
        {
          isFull = false;
          return isFull;
	    	}
      }
		}
	  return isFull;
	}

  public void reset()
  {
    for(int r = 0; r < table.length; r++)
    {
    	for(int c = 0; c < table[r].length; c++)
    	{
    		table[r][c] = 0;
  		}
  	}
    turn = 1;
  }

  public void incrementWins1()
  {
  	player1Wins++;
  }

  public void incrementWins2()
  {
  	player2Wins++;
  }

  public void computerTurn()
  {
  	computerPlayed = false;
  }

  public void drawMe(Graphics g)
  {
	g.setColor(Color.BLUE);
	g.fillRect(0,0,800,600);
    g.setFont(font);
  	g.setColor(Color.WHITE);
    g.drawRect(150, 150, 450, 25);
    g.drawRect(150, 310, 450, 25);
    g.drawRect(265, 50, 25, 400);
    g.drawRect(455, 50, 25, 400);
	

  
    for(int r = 0; r < table.length; r++)
		{
  		for(int c = 0; c < table[r].length; c++)
  		{
  			if(table[r][c] == 1)
  			{
  				g.drawString("X", c * 200 + 150, r * 150 + 140);
  			}
  			else if(table[r][c] == 2)
  			{
  				g.drawString("O", c * 200 + 150, r * 150 + 140);
  			}
  		}
  	}

  	g.setFont(font2);
  	g.drawString("Player 1 Has: " + player1Wins, 150, 475);
  	g.drawString("Player 2 Has: " + player2Wins, 455, 475);
  }
}