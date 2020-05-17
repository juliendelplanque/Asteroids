/**
 * Class RecordManager.
 * Manage the record mode.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-04-30
 */
package be.ac.umons.asteroids.manager;

import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.* ;
import java.awt.Dimension;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public class RecordManager 
{
	protected String[] players;
	protected String[] colors;
	protected SpaceShip[] shipTab;
	protected int width;
	protected int height;
	protected int index;
	protected int remainingMatches;
	
	/**
	 * Build a RecordManager object.
	 *
	 */
	public RecordManager(String[] _players, String[] _colors, int _width, int _height)
	{
		players = _players;
		colors = _colors;
		width = _width;
		height = _height;
		createShipsTab();
		index = 0;
		remainingMatches = shipTab.length;
	}
	
	/**
	 * Return the next SpaceShip that have to play.
	 *
	 * @return a SpaceShip
	 *
	 */
	public SpaceShip getNextShip()
	{
        SpaceShip ship = shipTab[index];
		index++;
		remainingMatches--;
		return ship;
	}
	
	/**
	 * Return the remaining matches for the current record mode's game.
	 *
	 * @return the remaining matches
	 *
	 */
	public int getRemainingMatches()
	{
		return remainingMatches;
	}
	
	/**
	 * Display the scores frame.
	 *
	 */
	public void displayScoresFrame()
	{
		final JFrame frame = new JFrame("Scores");
		frame.setSize(new Dimension(200, 160));
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(frame.getParent());
		
		sortShipsTab();
		
		JList list = new JList(shipTab);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setSize(new Dimension(200, 160));
		frame.add(scrollPane, BorderLayout.NORTH);
		scrollPane.setVisible(true);
		frame.setVisible(true);
	}
	
	/**
	 * Create the SpaceShip tab from the colors tab and the name tab.
	 *
	 */
	protected void createShipsTab()
	{
		shipTab = new SpaceShip[players.length];
		for(int i = 0; i<shipTab.length; i++)
		{
			SpaceShip s = new SpaceShip(new Point2D.Double((int)width/2,(int)height/2),new Point2D.Double(0,-1),0,100, players[i]);
			s.setInvulnerability(true, 1000);
			if(colors[i].equals("Green ship"))
				s.setGreenSkin();
			else if(colors[i].equals("Orange ship"))
				s.setOrangeSkin();
			else if(colors[i].equals("Blue ship"))
				s.setBlueSkin();
			shipTab[i] = s;
		}
	}
	
	/**
	 * Sort the SpaceShip tab.
	 *
	 */
	protected void sortShipsTab()
	{
		Arrays.sort(shipTab, new Comparator<SpaceShip>()
		{
			public int compare(SpaceShip s1, SpaceShip s2)
			{
				if(s1.getScore() > s2.getScore())
					return -1;
				else if(s1.getScore() == s2.getScore())
					return 0;
				else
					return 1;
			}
			
			public boolean equals(SpaceShip s1, SpaceShip s2)
			{
				if(s1.getScore() == s2.getScore())
					return true;
				else
					return false;
			}
		});
	}
}
