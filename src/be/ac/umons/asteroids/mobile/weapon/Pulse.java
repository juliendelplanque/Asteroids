/**
 * Class Pulse.
 * Pulse weapon.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-05-02
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

public class Pulse extends Missile
{
	/**
	 * Build a Pulse Weapon with a constant life of 100.
	 *
	 * @param pos the initial position of the Missile
     * @param _vecDir the initial direction of the Missile
     * @param _speed the initial speed of the Missile
     * @param _radius the radius of the Missible
     *
	 */
	public Pulse(Point2D.Double pos, Point2D.Double _vecDir, int _speed, int _radius)
	{
		super(pos,_vecDir,_speed, _radius, 100);
	}

	@Override
    public void draw(Graphics g)
    {
		g.setColor(Color.blue);
        g.drawOval((int)position.x-super.radius,(int)position.y-super.radius,super.radius*2,super.radius*2);
    }
	
	/**
	 * Refresh the state of the pulse, don't make it move just grow it up.
	 *
	 */
	@Override
	public void refreshPosition()
	{
		setRadius(getRadius()+10);
		life -= Main.GAMEDELAY;
	}
}
