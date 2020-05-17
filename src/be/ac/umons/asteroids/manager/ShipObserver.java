/**
 * Class ShipObserver.
 * Draw all the informations about the ship.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-04-22
 */
 
package be.ac.umons.asteroids.manager;

import java.awt.Graphics;
import java.awt.Color;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public class ShipObserver implements IDrawable
{
	private SpaceShip ship;
	
	/**
	 * Build a ShipObserver object.
	 *
	 * @param _s the current SpaceShip
	 *
	 */
	public ShipObserver(SpaceShip _s)
	{
		ship = _s;
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(isInvulnerable())
		{
			g.setColor(Color.white);
			g.drawString("Invulnerable",10,10);		
			g.drawRect(10,15,201,5);
		}
		else
		{
			g.setColor(Color.red);
			g.drawString("Life",10,12);		
			g.drawRect(10,15,201,5);
		}
		g.fillRect(11,16,getLife()*2,4);
		g.setColor(Color.white);
		g.drawString("Score : "+getScore(),10,35);
		g.drawString(ship.getName(),10,50);
		
		if(ship.isPulse())
			g.setColor(Color.red);
		else
			g.setColor(Color.white);
		g.drawString("Pulse",660,17);
		
		if(ship.isHoming())
			g.setColor(Color.red);
		else
			g.setColor(Color.white);
		g.drawString("Homing",660,37);
		
		if(ship.isClaymore())
			g.setColor(Color.red);
		else
			g.setColor(Color.white);
		g.drawString("Claymore",660,57);
		
		g.setColor(Color.lightGray);
		int x = 0;
		for(int i = 1; i < 16; i++)
		{
			g.fillRect(720+x,5,2,15);
			if(i <= 10)
				g.fillRect(720+x,25,2,15);
			if(i <= 5)
				g.fillRect(720+x,45,2,15);
			x += 5;
		}
		x = 0;
		for(int j = 1; j < 16; j++)
		{
			if(j <= getPulseAmmo())
			{
				g.setColor(Color.blue);
				g.fillRect(720+x,5,2,15);
			}
			if(j <= getHomingAmmo())
			{
				g.setColor(Color.red);
				g.fillRect(720+x,25,2,15);
			}
			if(j <= getClaymoreAmmo())
			{
				g.setColor(Color.green);
				g.fillRect(720+x,45,2,15);
			}
			x += 5;
		}
		g.setColor(Color.white);
		g.drawString(getTime(),360,15);
	}
	
	@Override
	public boolean isDeletable()
	{
		return false;
	}
	
	@Override
    public void setDeletable(boolean newValue)
	{
	}
	
	/**
	 * Get the homing ammo.
	 *
	 * @return the homing ammo that left
	 *
	 */
	private int getHomingAmmo()
	{
		return ship.getHomingAmmo();
	}
	
	/**
	 * Get the claymore ammo.
	 *
	 * @return the claymore ammo that left
	 *
	 */
	private int getClaymoreAmmo()
	{
		return ship.getClaymoreAmmo();
	}
	
	/**
	 * Get the pulse ammo.
	 *
	 * @return the pulse ammo that left
	 *
	 */
	private int getPulseAmmo()
	{
		return ship.getPulseAmmo();
	}
	
	/**
	 * Get the ship life.
	 *
	 * @return the ship life
	 *
	 */
	private int getLife()
	{
		return ship.getLife();
	}
	
	/**
	 * Get the vulnaribility of the ship
	 *
	 * @return true if invulnerable else false
	 *
	 */
	private boolean isInvulnerable()
	{
		return ship.isInvulnerable();
	}
	
	/**
	 * Get the ship score.
	 *
	 * @return the ship score
	 *
	 */
	private int getScore()
	{
		return ship.getScore();
	}
	
	/**
	 * Get the ship time of life (String).
	 *
	 * @return the ship time of life in hour/min/sec
	 *
	 */
	private String getTime()
	{
		int time = ship.getTime();
		int s = (time/1000)%60;
		int m = (time/60000)%60;
		int h = (time/3600000)%24;
		String t = "";
		if(h < 10)
			t += "0"+h;
		else
			t += h;
		t += ":";
		if(m < 10)
			t += "0"+m;
		else
			t += m;
		t += ":";
		if(s < 10)
			t += "0"+s;
		else 
			t += s; 
		return t;
	}
}
