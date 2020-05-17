/**
 * Class PulseAmmo.
 * Ammo for the pulse weapon.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-05-02
 */
 
package be.ac.umons.asteroids.mobile.bonus;

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

public class PulseAmmo extends ABonus implements ISpaceShipBonus
{
	/**
	 * Build a PulseAmmo object.
	 *
	 */
	public PulseAmmo(Point2D.Double pos, double _life)
	{
		super(pos, new Point2D.Double(0,0), 0, _life);
		image = GetImage.getPulseAmmoImage();
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image,(int)position.x-radius,(int)position.y-radius,null);
	}
	
	/**
	 * Increase the pulse ammos of the spaceship.
	 *
	 * @param s a Spaceship
	 *
	 */
	public void effect(SpaceShip s)
	{
		s.reloadPulse();
	}
	
	/**
	 * Just refresh the life of a pulse ammo, don't make it move.
	 *
	 */
	@Override
	public void refreshPosition()
	{
		life -= time;
	}
}
