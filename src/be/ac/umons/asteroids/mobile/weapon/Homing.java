/**
 * Class Homing.
 * Create an Homing object.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-08-04
 */
 
package be.ac.umons.asteroids.mobile.weapon;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
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


public class Homing extends Missile
{
	private Asteroid a;
	private boolean homing;
	private ArrayList<Asteroid> list;
	private Engine e;
	
	/**
	 * Build a Homing object.
	 *
	 * @param pos a Point2D.Double position
	 * @param _vecDir a Point2D.Double direction vector
	 * @param _speed a double speed
	 * @param _radius an int radius
	 * @param _life a double life time
	 * @param _e an Engine engine
	 *
	 */
	public Homing(Point2D.Double pos, Point2D.Double _vecDir, int _speed, int _radius, double _life,Engine _e)
	{
		super(pos,_vecDir,_speed, _radius, _life);
		e = _e;
		ArrayList<Asteroid> list = e.getAsteroidList();
		if(list.size() != 0)
		{
			a = getNearestAst(list);
			a.select(true);
			homing = true;
		}
		else
		{
			homing = false;
		}
	}

	@Override
    public void draw(Graphics g)
    {
		g.setColor(Color.red);
        g.drawOval((int)position.x-super.radius,(int)position.y-super.radius,super.radius*2,super.radius*2);
    }
	
	/**
	 * Override the refreshPosition method, the trajectory of the asteroid is recalculated every time this method
	 * is called, and it lock the nearest asteroid from it position.
	 *
	 */
	@Override
	public void refreshPosition()
	{
		list = e.getAsteroidList();
		if(homing = true && a != null && list.size() != 0)
		{	
			a.select(false);
			a = getNearestAst(list);
			a.select(true);
			super.refreshPosition();
			Point2D.Double oldOr = Geom.normalize((Point2D.Double)super.vecOr.clone(),2);
			Point2D.Double newVecOr = Geom.vector(super.position,a.getPosition());
			newVecOr = Geom.add(oldOr,newVecOr);
			super.setVecOr(newVecOr);
		}
		else
		{
			super.refreshPosition();;
		}
	}
	
	/**
	 * Return an Asteroid object that is the nearest Asteroid from the Homing.
	 * 
	 * @param astList an ArrayList<Asteroid> list of Asteroids
	 *
	 * @return the nearest Asteroid
	 *
	 */
	private Asteroid getNearestAst(ArrayList<Asteroid> astList)
	{
		double bestDist = 0;
		int i = 0;
		for(int j = 0; j<astList.size(); j++)
		{
			double dist = Geom.magnitude(Geom.vector(astList.get(j).getPosition(),position));
			if(j == 0)
				bestDist = dist;
			else
			{
				if (dist < bestDist)
				{
					bestDist = dist;
					i = j;
				}
			}
		}
		return astList.get(i);
	}
	
	/**
	 * Deselect the asteroid that the missile was following.
	 *
	 */
	public void deselectAst()
	{
		a.select(false);
	}
}
