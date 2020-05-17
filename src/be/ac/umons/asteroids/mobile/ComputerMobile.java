/**
 * Class Computer.
 * Mother class for all the mobile that are manage by the computer.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-04-30
 */

package be.ac.umons.asteroids.mobile;

import java.awt.geom.Point2D;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public class ComputerMobile extends AMobile
{
	protected int radius;
    
	/**
	 * Build a ComputerMobile object.
	 *
     * @param pos the inital position of the Mobile
     * @param _vecOr the inital orientation of the Mobile
     * @param _speed the inital speed of the Mobile
     * @param _radius the radius of the Mobile
	 *
	 */
	public ComputerMobile(Point2D.Double pos, Point2D.Double _vecOr, double _speed, int _radius)
	{
		super(pos, _vecOr, _speed);
        radius = _radius;
	}
    
    @Override
    public void refreshPosition()
    {
        setVecSpeed(vecDir, speed);
        Point2D.Double newPos = Geom.add(position, vecSpeed);
        setPosition(newPos);
    }
    
    /**
     * Return the Mobile's Radius.
     *
     * @return the Mobile's Radius
     */
    public int getRadius()
    {
        return radius;
    }
	
    /**
     * Set the radius of the Mobile.
     *
     * @param newRadius the new radius of the Mobile
     */
	public void setRadius(int newRadius)
	{
		radius = newRadius;
	}
}
