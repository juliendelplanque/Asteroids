/**
 * Class LifeBonus.
 * Create the LifeBonus object.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-07-04
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

public class LifeBonus extends ABonus implements ISpaceShipBonus
{
	/**
	 * Build a LifeBonus object.
	 *
	 * @param pos the initial position of the Bonus
     * @param _vecDir the initial direction of the Bonus
     * @param _speed the inital speed of the Bonus
     * @param _life the initial life of the Bonus
     *
	 */
	public LifeBonus(Point2D.Double pos, Point2D.Double _vecDir, double _speed, double _life)
	{
		super(pos,_vecDir, _speed, _life);
		image = GetImage.getLifeBonusImage();
	}
	
	@Override	
    public void draw(Graphics g)
	{
		g.drawImage(image,(int)position.x-radius,(int)position.y-radius,null);
	}
	
	/**
	 * Launch the effect of the LifeBonus.
	 *
	 * @param s a SpaceShip
	 *
	 */
	public void effect(SpaceShip s)
	{
		s.increaseLife();
	}
}
