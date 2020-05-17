/**
 * Class MultiFireBonus.
 * Create a MultiFireBonus.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-06-04
 */
 
package be.ac.umons.asteroids.mobile.bonus;

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

public class MultiFireBonus extends ABonus implements ISpaceShipBonus
{
	/**
	 * Build a MultiFireBonus.
	 *
	 * @param pos a Point2D.Double position
	 * @param _vecDir a Point2D.Double direction vector
	 * @param _speed a double speed
	 * @param _life a double life time
	 *
	 */
	public MultiFireBonus(Point2D.Double pos, Point2D.Double _vecDir, double _speed, double _life)
	{
		super(pos,_vecDir, _speed, _life);
		image = GetImage.getMultiFireBonusImage();
	}
	
	@Override	
    public void draw(Graphics g)
	{
		g.drawImage(image,(int)position.x-radius,(int)position.y-radius,null);
	}
	
	/**
	 * Launch the effect of the MultiFireBonus.
	 *
	 * @param s a SpaceShip
	 *
	 */
	public void effect(SpaceShip s)
	{
		s.setMultiFire();
	}
}
