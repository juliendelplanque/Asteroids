/**
 * Class AMobile.
 * Abstract class for the mobile objects.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-04-30
 */

package be.ac.umons.asteroids.mobile;

import java.awt.geom.Point2D;
import java.awt.Graphics;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public abstract class AMobile implements IDrawable
{
    protected double speed;
	protected Point2D.Double vecOr, vecDir, vecSpeed;
    protected Point2D.Double position;
    protected boolean deletable = false;
	
	/**
	 * Build a Mobile object.
	 *
	 * @param pos the inital position of the Mobile
     * @param _vecOr the inital orientation of the Mobile
     * @param _speed the inital speed of the Mobile
     *
	 */
	public AMobile(Point2D.Double pos, Point2D.Double _vecOr, double _speed)
	{
		position = pos;
        vecOr = _vecOr;
        vecDir = Geom.normalize(vecOr);
        speed = _speed;
        vecSpeed = Geom.normalize(vecDir, speed);
	}
	
	/**
	 * Set the position of a Mobile object.
	 *
	 * @param newPos a Point2D.Double new position
	 *
	 */
	public void setPosition(Point2D.Double newPos)
	{
		position = newPos;
	}
	
	/**
	 * Get the position of a Mobile object.
	 *
	 * @return the position of the mobile.
	 *
	 */
	public Point2D.Double getPosition()
	{
		return position;
	}
    
    /**
	 * Set the speed of a Mobile object.
	 *
	 * @param newSpeed the new speed value
	 *
	 */
    public void setSpeed(double newSpeed)
    {
        speed = newSpeed;
        setVecSpeed(vecDir, speed); 
    }
    
    /**
	 * Get the speed of a Mobile object.
	 *
	 * @return the speed of the mobile.
	 *
	 */
    public double getSpeed()
    {
        return speed;
    }
    
    /**
	 * Set the orientation of a Mobile object.
	 *
	 * @param newVecOr the new orientation (Point2D.Double)
	 *
	 */
    public void setVecOr(Point2D.Double newVecOr)
    {
        vecOr = newVecOr;
        vecDir = Geom.normalize(vecOr);
        vecSpeed = Geom.normalize(vecDir, speed);
    }
    
    /**
	 * Get the orientation of a Mobile object.
	 *
	 * @return the orientation of the mobile.
	 *
	 */
    public Point2D.Double getVecOr()
    {
        return vecOr;
    }
    
    /**
	 * Set the speed vector of a Mobile object.
	 *
	 * @param newVecSpeed the new speed vector (Point2D.Double)
	 *
	 */
    public void setVecSpeed(Point2D.Double newVecSpeed)
    {
        vecSpeed = newVecSpeed;
        vecDir = Geom.normalize(newVecSpeed);
        speed = Geom.magnitude(newVecSpeed);
        vecOr = Geom.normalize(vecDir, Geom.magnitude(vecOr));
    }
    
    /**
	 * Set the speed vector of a Mobile object.
	 *
	 * @param newVecDir the new direction (Point2D.Double)
	 * @param newSpeed the new speed (double)
     *
	 */
    public void setVecSpeed(Point2D.Double newVecDir, double newSpeed)
    {
        vecSpeed = Geom.normalize(newVecDir, newSpeed);
        vecDir = newVecDir;
        vecOr = Geom.normalize(newVecDir, Geom.magnitude(vecOr));
        speed = newSpeed;
    }
    
    
    /**
	 * Get the speed vector of a Mobile object.
	 *
	 * @return the speed vector of the mobile.
	 *
	 */
    public Point2D.Double getVecSpeed()
    {
        return vecSpeed;
    }
    
    /**
	 * Set the direction vector of a Mobile object.
	 *
	 * @param newVecDir the new direction (Point2D.Double)
     *
	 */
    public void setVecDir(Point2D.Double newVecDir)
    {
        vecDir = Geom.normalize(newVecDir);
        vecSpeed = Geom.normalize(newVecDir, speed);
        vecOr = Geom.normalize(newVecDir, Geom.magnitude(vecOr));
    }
    
    /**
	 * Get the direction vector of a Mobile object.
	 *
	 * @return the direction vector of the mobile (Point2D.Double).
	 *
	 */
    public Point2D.Double getVecDir()
    {
        return vecDir;
    }
    
    /**
     * This method will be used to refresh the positions of the objects that extends this class.
     *
     */
	abstract void refreshPosition();
    
    @Override
    public void draw(Graphics g) {}
    
    @Override
    public boolean isDeletable()
    {
        return deletable;
    }
    
    @Override
    public void setDeletable(boolean newValue)
    {
        deletable = newValue;
    }
}
