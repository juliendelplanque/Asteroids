/**
 * Class Explosion.
 * Create the Explosion.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-06-03
 */

package be.ac.umons.asteroids.explosion;

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

public class Explosion implements IDrawable
{

    // Private fields
	private Image[] explosion;
	private int step;
    private Point2D.Double position;
    private boolean deletable = false;

	/**
	 * Build an Explosion object.
	 *
	 * @param pos a Point2D.Double position
	 *
	 */
	public Explosion(Point2D.Double pos)
	{
		position = pos;
		step = 0;
		explosion = GetImage.getExplosionImages();
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(explosion[step],(int)position.x-50,(int)position.y-50,null);
		if(step < 15)
			step ++;
		if(step == 15)
		{
			setDeletable(true);
		}
	}
	
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
