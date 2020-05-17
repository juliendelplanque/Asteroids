/**
 * Class Claymore.
 * Create a claymore object.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-08-04
 */

package be.ac.umons.asteroids.mobile.weapon;

import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Image;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public class Claymore extends Missile
{

    // Private fields
	private Image image;
	
	/**
	 * Build a ClayMore object.
	 *
	 * @param pos the position of the Claymore
	 *
	 */
	public Claymore(Point2D.Double pos)
	{
		super(pos,new Point2D.Double(0,0),0,20,1);
		image = GetImage.getClaymoreImage();
	}
	
	@Override
    public void draw(Graphics g)
    {
		g.drawImage(image,(int)position.x-25,(int)position.y-25,null);
    }
	
	/**
	 * The position of a Claymore don't need to be refresh because a Claymore doesn't move.
	 *
	 */
	@Override
	public void refreshPosition(){}	
}
