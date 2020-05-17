/**
 * Class MenuBar.
 * Create menu bar of the game.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-08-04
 */

package be.ac.umons.asteroids.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color; 

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;

public class MenuBar extends JMenuBar
{
	private int gameDifficulty = 1; //medium
	private int shipSkin = 0; // green
	private boolean startB = false;
	private boolean exitB = false;
	private boolean aboutB = false;
	private boolean keysB = false;
	private boolean menuB = false;
	private boolean pauseB = false;
	private boolean recordB = false;
	private boolean tournamentB = false;
	private JMenuItem easy;
	private JMenuItem medium;
	private JMenuItem hard;
	private JMenuItem brutal;
	private JMenuItem green;
	private JMenuItem orange;
	private JMenuItem blue;
	
	/**
	 * Build a MenuBar object.
	 *
	 */
	public MenuBar()
	{
		super();
		JMenu game = new JMenu("Game");
		JMenu help = new JMenu("Help");
		
		JMenu solo = new JMenu("Solo");
		
		JMenuItem start = new JMenuItem("Start");
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				startB = true;
			}});
		
		JMenu setSkin = new JMenu("Set skin");
		
		solo.add(start);
		solo.add(setSkin);
		
		JMenu multi = new JMenu("Multiplayer");
		
		JMenuItem record = new JMenuItem("Record");
		record.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				recordB = true;
			}});
		
		JMenuItem tournament = new JMenuItem("Tournament");
		tournament.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				tournamentB = true;
			}});
		
		multi.add(record);
		multi.add(tournament);
		
		JMenu setDifficultyLevel = new JMenu("Set Difficulty");
		
		JMenuItem menu = new JMenuItem("Return to menu");
		menu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				menuB = true;
			}});
		
		JMenuItem pause = new JMenuItem("Pause");
		pause.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				pauseB = true;
			}});
			
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				exitB = true;
			}});
		
		easy = new JMenuItem("Easy");
		easy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				gameDifficulty = 0;
				easy.setBackground(Color.orange);
				medium.setBackground(Color.white);
				hard.setBackground(Color.white);
				brutal.setBackground(Color.white);
			}});
			
		medium = new JMenuItem("Medium");
		medium.setBackground(Color.orange);
		medium.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				gameDifficulty = 1;
				easy.setBackground(Color.white);
				medium.setBackground(Color.orange);
				hard.setBackground(Color.white);
				brutal.setBackground(Color.white);
			}});
			
		hard = new JMenuItem("Hard");
		hard.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				gameDifficulty = 2;
				easy.setBackground(Color.white);
				medium.setBackground(Color.white);
				hard.setBackground(Color.orange);
				brutal.setBackground(Color.white);
			}});
			
		brutal = new JMenuItem("Brutal");
		brutal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				gameDifficulty = 3;
				easy.setBackground(Color.white);
				medium.setBackground(Color.white);
				hard.setBackground(Color.white);
				brutal.setBackground(Color.orange);
			}});
		
		green = new JMenuItem("Green ship");
		green.setBackground(Color.orange);
		green.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				shipSkin = 0;
				green.setBackground(Color.orange);
				orange.setBackground(Color.white);
				blue.setBackground(Color.white);
			}});
			
		orange = new JMenuItem("Orange ship");
		orange.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				shipSkin = 1;
				green.setBackground(Color.white);
				orange.setBackground(Color.orange);
				blue.setBackground(Color.white);
			}});
			
		blue = new JMenuItem("Blue ship");
		blue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				shipSkin = 2;
				green.setBackground(Color.white);
				orange.setBackground(Color.white);
				blue.setBackground(Color.orange);
			}});
		
		JMenuItem about = new JMenuItem("About the game");
		about.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				aboutB = true;
			}});
		
		JMenuItem keys = new JMenuItem("Keys");
		keys.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a) 
			{
				keysB = true;
			}});
		
		setDifficultyLevel.add(easy);
		setDifficultyLevel.add(medium);
		setDifficultyLevel.add(hard);
		setDifficultyLevel.add(brutal);
		
		setSkin.add(green);
		setSkin.add(orange);
		setSkin.add(blue);
		
		game.add(solo);
		game.add(multi);
		game.add(setDifficultyLevel);
		game.add(pause);
		game.add(menu);
		game.add(exit);
		
		help.add(about);
		help.add(keys);
		
		add(game);
		add(help);
        game.getPopupMenu().setLightWeightPopupEnabled(false);
        help.getPopupMenu().setLightWeightPopupEnabled(false);
	}
	
	/**
	 * Return the game difficulty as an int.
	 *
	 * @return the difficulty : 0 = easy, 1 = medium, 2 = hard, 3 = brutal
	 *
	 */
	public int getGameDifficulty()
	{
		return gameDifficulty;
	}
	
	/**
	 * Return the ship color as an int.
	 *
	 * @return the difficulty : 0 = green, 1 = orange, 2 = blue
	 *
	 */
	public int getShipSkin()
	{
		return shipSkin;
	}
	
	/**
	 * Return the state of the start button in the MenuBar.
	 *
	 * @return true if the button has been click else false
	 *
	 */
	public boolean getStartBool()
	{
		return startB;
	}
	
	/**
	 * Return the state of the exit button in the MenuBar.
	 *
	 * @return true if the button has been click else false
	 *
	 */
	public boolean getExitBool()
	{
		return exitB;
	}
	
	/**
	 * Return the state of the about button in the MenuBar.
	 *
	 * @return true if the button has been click else false
	 *
	 */
	public boolean getAboutBool()
	{
		return aboutB;
	}
	
	/**
	 * Return the state of the keys button in the MenuBar.
	 *
	 * @return true if the button has been click else false
	 *
	 */
	public boolean getKeysBool()
	{
		return keysB;
	}
	
	/**
	 * Return the state of the menu button in the MenuBar.
	 *
	 * @return true if the button has been click else false
	 *
	 */
	public boolean getMenuBool()
	{
		return menuB;
	}
	
	/**
	 * Return the state of the pause button in the MenuBar.
	 *
	 * @return true if the button has been click else false
	 *
	 */
	public boolean getPauseBool()
	{
		return pauseB;
	}
	
	/**
	 * Return the state of the tournament button in the MenuBar.
	 *
	 * @return true if the button has been click else false
	 *
	 */
	public boolean getTournamentBool()
	{
		return tournamentB;
	}
	
	/**
	 * Return the state of the record button in the MenuBar.
	 *
	 * @return true if the button has been click else false
	 *
	 */
	public boolean getRecordBool()
	{
		return recordB;
	}
	
	/**
	 * Set all the buttons booleans in the MenuBar at false.
	 *
	 */
	public void setAllFalse()
	{
		startB = false;
		exitB = false;
		aboutB = false;
		keysB = false;
		menuB = false;
		pauseB = false;
		recordB = false;
		tournamentB = false;
	}
}
