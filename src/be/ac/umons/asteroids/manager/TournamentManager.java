/**
 * Class TournamentManager.
 * Manage the tournament mode.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-04-30
 */
 
package be.ac.umons.asteroids.manager;

import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
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

public class TournamentManager extends RecordManager
{
	private ArrayList<SpaceShip[]> tabList;
	
	/**
	 * Build a TournamentManager object.
	 *
	 */
	public TournamentManager(String[] _players, String[] _colors, int _width, int _height)
    {
        super(_players, _colors, _width, _height);
		remainingMatches = calculRemainingMatches();
		tabList= new ArrayList<SpaceShip[]>();
    }
	
	/**
	 * Return the next SpaceShip that have to play.
	 *
	 * @return a SpaceShip
	 *
	 */
	@Override
	public SpaceShip getNextShip()
	{
		if(shipTab.length > index)
		{
			SpaceShip ship = shipTab[index];
			ship.setLife(100);
			index++;
			remainingMatches--;
			return ship;
		}
		else
		{
			jumpStage();
			index = 0;
			remainingMatches--;
			return shipTab[index];
		}
	}
	
	/**
	 * Jump in higher stage of the "pyramid".
	 *
	 */
	public void jumpStage()
	{
		if(shipTab.length/2 != 1)
		{
			tabList.add(shipTab);
			SpaceShip[] tab = new SpaceShip[shipTab.length/2];
			int y = 0;
			for(int x = 0; x<shipTab.length; x+=2)
			{
				if(shipTab[x].getScore() > shipTab[x+1].getScore())
					tab[y] = shipTab[x].clone();
				else if(shipTab[x].getScore() < shipTab[x+1].getScore())
					tab[y] = shipTab[x+1].clone();
				y++;
			}
			shipTab = tab;
		}
	}
	
	/**
	 * Set the SpaceShip that win the tournament on the top of the "binary tree".
	 *
	 */
	private void setTheWinner()
	{
			if(shipTab[0].getScore() > shipTab[1].getScore())
			{
				SpaceShip[] tab = new SpaceShip[1];
				tab[0] = shipTab[0];
				tabList.add(tab);
			}
			else if(shipTab[0].getScore() < shipTab[1].getScore())
			{
				SpaceShip[] tab = new SpaceShip[1];
				tab[0] = shipTab[1];
				tabList.add(tab);
			}

	}
	
	/**
	 * Calcul the number of matches that remains before the end of the tournament.
	 *
	 * @return int the number of matches that remains
	 *
	 */
	private int calculRemainingMatches()
	{
		int rM = 0;
		int i = 1;
		while((int)Math.pow(2,i) <= shipTab.length)
		{
			rM += (int)Math.pow(2,i);
			i++;
		}
		return rM;
	}
	
	/**
	 * Sort the SpaceShip tab.
	 *
	 */
	protected void sortShipsTab()
	{
	}
}
