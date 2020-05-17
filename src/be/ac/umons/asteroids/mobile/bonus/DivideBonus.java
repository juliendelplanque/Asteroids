/**
 * Class DivideBonus.
 * Create the DivideBonus object.
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

public class DivideBonus extends ABonus
{
	/**
	 * Build a Bonus object.
	 *
     * @param pos the inital position of the bonus
	 * @param _vecDir the inital direction of the bonus
     * @param _speed the inital speed of the bonus
     * @param _life the inital life of the bonus
     *
	 */
	public DivideBonus(Point2D.Double pos, Point2D.Double _vecDir, double _speed, double _life)
	{
		super(pos,_vecDir, _speed, _life);
		image = GetImage.getDivideBonusImage();
	}
	
	@Override	
    public void draw(Graphics g)
	{
		g.drawImage(image,(int)position.x-radius,(int)position.y-radius,null);
	}
	
	/**
	 * Launch the effect of the DivideBonus.
	 *
	 * @param e a Engine
	 *
	 */
	public static void effect(Engine e)
	{
		e.divideAll();
	}
}
