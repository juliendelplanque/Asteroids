/**
 * Class Missile.
 * Create the missile object.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-19-03
 */
package be.ac.umons.asteroids.mobile.weapon;

import java.awt.geom.Point2D;
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

public class Missile extends ComputerMobile
{
    protected double life;

    /**
     * Build a missile object.
     *
     * @param pos the initial position of the Missile
     * @param _vecOr the inital orientation of the Missile
     * @param _speed the inital speed of the Missile
     * @param _radius the radius of the Missile
     * @param _life the initial life of the Missile
     *
     */    
    public Missile(Point2D.Double pos, Point2D.Double _vecOr, int _speed, int _radius, double _life)
    {
		super(pos,_vecOr,_speed, _radius);  
        life = _life;
    }
    
	@Override
    public void draw(Graphics g)
    {
		g.setColor(Color.green);
        g.drawOval((int)position.x-super.radius,(int)position.y-super.radius,super.radius*2,super.radius*2);
    }
	
	/**
	 * Refresh the position of the missile an decrease it life of the time elapsed since the last call of this method.
	 *
	 */
	@Override
	public void refreshPosition()
	{
		super.refreshPosition();
		life -= Main.GAMEDELAY;
	}

    /**
     * Return true if the missile is still alive else return false.
     *
     * @return the state of the missile.
     *
     */    
    public boolean isAlive()
    {
        if(life > 0)
            return true;
        else
            return false;
    }

    /**
     * Returns the remaining life of the asteroid.
     *
     * @return the remaining life of the asteroid
     *
     */    
    public double timeToLive()
    {
        return life;
    }
}
