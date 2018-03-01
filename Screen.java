import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Screen extends JPanel implements MouseListener, ActionListener
{
	private Game game;
	private String notificationText;
	private Font font, font2;
	private JButton restartButton;
	private JButton twoPlayerButton;
	private JButton onePlayerButton;
	private boolean pointsAdded;
	private int oneTwoPlayer;
	public Screen()
	{
		this.setLayout(null);
		game = new Game();
		notificationText = "";
		addMouseListener(this);
		font = new Font("Helvetica", Font.BOLD, 50);
		font2 = new Font("Helvetica", Font.BOLD, 30);
		pointsAdded = false;
		oneTwoPlayer = 0;

		
		twoPlayerButton = new JButton("2 Player Game");
		twoPlayerButton.setBounds(400,200,125,60);
		twoPlayerButton.addActionListener(this);
		this.add(twoPlayerButton);
		twoPlayerButton.setVisible(true);
		twoPlayerButton.setVisible(true);
		twoPlayerButton.setBorderPainted(false);
		restartButton = new JButton("Restart");
		restartButton.setBounds(300,200,125,60);
		restartButton.addActionListener(this);
		this.add(restartButton);
		restartButton.setVisible(false);
		restartButton.setBorderPainted(false);

		onePlayerButton = new JButton("1 Player Game");
		onePlayerButton.setBounds(200,200,125,60);
		onePlayerButton.addActionListener(this);
		this.add(onePlayerButton);
		onePlayerButton.setVisible(true);
		onePlayerButton.setBorderPainted(false);





		this.setFocusable(true);
	}

	public Dimension getPreferredSize() 
	{
		//Sets the size of the panel
		return new Dimension(800,600);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		if(oneTwoPlayer == 1)
		{
			onePlayerButton.setVisible(false);
			twoPlayerButton.setVisible(false);
			g.setFont(font);
			game.printTable();
			if(game.isTicTacToe() == 0 && !game.checkFull())
			{
				game.drawMe(g);
			}
			else if(game.isTicTacToe() == 1)
			{
				g.setColor(Color.BLACK);
				g.setFont(font2);
				g.drawString("Player 1 Wins!", 300, 200);
				if(pointsAdded == false)
				{
					game.incrementWins1();
					pointsAdded = true;
					playSoundHitWin();
				}
				restartButton.setVisible(true);
			}
			else if(game.isTicTacToe() == 2)
			{
				g.setColor(Color.BLACK);
				g.setFont(font2);
				g.drawString("Player 2 Wins!", 300, 200);
				if(pointsAdded == false)
				{
					game.incrementWins2();
					pointsAdded = true;
					playSoundHitLose();
				}
				restartButton.setVisible(true);
			}
			else if(game.checkFull())
			{
				g.setColor(Color.BLACK);
				g.setFont(font2);
				g.drawString("Tie Game", 300, 200);
				restartButton.setVisible(true);
				if(pointsAdded == false)
				{
					playSoundHitTie();
					pointsAdded = true;
				}
			}
		}
		else if(oneTwoPlayer == 2)
		{
			onePlayerButton.setVisible(false);
			twoPlayerButton.setVisible(false);
			g.setFont(font);
			if(game.isTicTacToe() == 0 && !game.checkFull())
			{
				game.drawMe(g);
			}
			else if(game.isTicTacToe() == 1)
			{
				g.setColor(Color.BLACK);
				g.setFont(font2);
				g.drawString("Player 1 Wins!", 300, 200);
				if(pointsAdded == false)
				{
					game.incrementWins1();
					pointsAdded = true;
					playSoundHitWin();
				}
				restartButton.setVisible(true);
			}
			else if(game.isTicTacToe() == 2)
			{
				g.setColor(Color.BLACK);
				g.setFont(font2);
				g.drawString("Player 2 Wins!", 300, 200);
				if(pointsAdded == false)
				{
					game.incrementWins2();
					pointsAdded = true;
					playSoundHitWin();
				}
				restartButton.setVisible(true);
			}
			else if(game.checkFull())
			{
				g.setColor(Color.BLACK);
				g.setFont(font2);
				g.drawString("Tie Game!", 300, 200);
				restartButton.setVisible(true);
				if(pointsAdded == false)
				{
					playSoundHitTie();
					pointsAdded = true;
				}
			}
		}
	}

	public void playSoundHit()
	{
		try {
			URL url = this.getClass().getClassLoader().getResource("sounds/cartoon012.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
	
	public void playSoundHitTie()
	{
		try {
			URL url = this.getClass().getClassLoader().getResource("sounds/aircraft001.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	public void playSoundHitWin()
	{
		try {
			URL url = this.getClass().getClassLoader().getResource("sounds/comic001.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	public void playSoundHitLose()
	{
		try {
			URL url = this.getClass().getClassLoader().getResource("sounds/birds001.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}



	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == restartButton)
		{
			game.reset();
			restartButton.setVisible(false);
			pointsAdded = false;
			oneTwoPlayer = 0;
			onePlayerButton.setVisible(true);
			twoPlayerButton.setVisible(true);
		}
		else if(e.getSource() == onePlayerButton)
		{
			oneTwoPlayer = 1;
		}
		else if(e.getSource() == twoPlayerButton)
		{
			oneTwoPlayer = 2;
		}
		repaint();
	}

	public void mousePressed(MouseEvent e) 
	{	if(oneTwoPlayer != 0)
		{
			if (e.getX() >= 150 && e.getX() <= 250 && e.getY() >= 50 && e.getY() <= 150) 
	        {
	           	game.insertXO(0,0);
	            playSoundHit();
	        } 
	        else if (e.getX() >= 175 && e.getX() <= 425 && e.getY() >= 50 && e.getY() <= 150) 
	        {
	            game.insertXO(0,1);
	            playSoundHit();
	        } 
	        else if (e.getX() >= 450 && e.getX() <= 550 && e.getY() >= 50 && e.getY() <= 150) 
	        {
	            game.insertXO(0,2);
	            playSoundHit();
	        } 
	        else if (e.getX() >= 150 && e.getX() <= 250 && e.getY() >= 175 && e.getY() <= 325) 
	        {
	            game.insertXO(1,0);
	            playSoundHit();
	        } 
	        else if (e.getX() >= 275 && e.getX() <= 425 && e.getY() >= 175 && e.getY() <= 325) 
	        {
	            game.insertXO(1,1);
	            playSoundHit();
	        } 
	        else if (e.getX() >= 450 && e.getX() <= 550 && e.getY() >= 175 && e.getY() <= 325) 
	        {
	            game.insertXO(1,2);
	            playSoundHit();
	        } 
	        else if (e.getX() >= 150 && e.getX() <= 250 && e.getY() >= 335 && e.getY() <= 450) 
	        {
	            game.insertXO(2,0);
	            playSoundHit();
	        } 
	        else if (e.getX() >= 275 && e.getX() <= 425 && e.getY() >= 335 && e.getY() <= 450) 
	        {
	            game.insertXO(2,1);
	            playSoundHit();
	        } 
	        else if (e.getX() >= 450 && e.getX() <= 550 && e.getY() >= 335 && e.getY() <= 450) 
	        {
	            game.insertXO(2,2);
	            playSoundHit();
	        } 
	        else 
	        {
	            notificationText = "";
	        }
		} 
		if(oneTwoPlayer == 1)
		{
			System.out.println("Computer");
			System.out.println(game.getTurn());
	 		game.computer();	
		}   
        repaint();
    }
 
    public void mouseReleased(MouseEvent e) {}
 
    public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mouseClicked(MouseEvent e) {}
}