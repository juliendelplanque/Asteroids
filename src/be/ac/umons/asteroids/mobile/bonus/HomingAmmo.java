/**
 * Class HomingAmmo.
 * Create an HomingAmmo object.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-08-04
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

public class HomingAmmo extends ABonus implements ISpaceShipBonus
{
	/**
	 * Build an HomingAmmo object.
	 *
	 * @param pos a Point2D.Double position of the HomingAmmo.
	 * @param _life a double tha represent the life of the HomingAmmo.
	 *
	 */
	public HomingAmmo(Point2D.Double pos, double _life)
	{
		super(pos, new Point2D.Double(0,0), 0, _life);
		image = GetImage.getHomingAmmoImage();
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image,(int)position.x-radius,(int)position.y-radius,null);
	}
	
	/**
	 * Increase the number of homing of the ship.
	 *
	 * @param s a SpaceShip
	 *
	 */
	public void effect(SpaceShip s)
	{
		s.reloadHoming();
	}
	
	/**
	 * Only refresh the life of the HomingAmmo because an ammo don't move.
	 *
	 */
	@Override
	public void refreshPosition()
	{
		life -= time;
	}
}
