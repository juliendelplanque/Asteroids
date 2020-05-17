/**
 * Class Asteroid.
 * Create the asteroid object.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-06-03
 */
 
package be.ac.umons.asteroids.mobile.asteroid;

import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Image;
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

public class Asteroid extends ComputerMobile
{

    // Public fields
    public boolean isSelect;
    
    // Private fields
    private double mass;
	private Image image;
	

    /**
     * Build an asteroid object.
     *
     * @param pos the initial position of the Asteroid
     * @param _vecDir the initial direction of the Asteroid
     * @param _speed the initial speed of the Asteroid
     * @param _radius the radius of Asteroid
     *
     */
    public Asteroid(Point2D.Double pos, Point2D.Double _vecDir, double _speed, int _radius)
    {
		super(pos, _vecDir, _speed, _radius);
        mass = (4*Math.PI*Math.pow(super.radius, 3))*3517 / 3;
		image = GetImage.getAsteroidImage();
		isSelect = false;
    }
    
    
    @Override	
    public  void draw(Graphics g)
	{
		Graphics2D  g2 = (Graphics2D)g;
		AffineTransform at = new AffineTransform();
		at.translate((int)position.x-radius,(int)position.y-radius);
		at.scale((double)radius*2/100,(double)radius*2/100);
		g2.drawImage(image, at, null);
		if(isSelect == true)
		{
			g.setColor(Color.yellow);
			int x = (int)position.x-radius;
			int y = (int)position.y-radius;
			g.drawLine(x,y,x+radius/4,y);
			g.drawLine(x,y,x,y+radius/4);
			g.drawLine(x,y+2*radius,x,y+2*radius-radius/4);
			g.drawLine(x,y+2*radius,x+radius/4,y+2*radius);
			g.drawLine(x+2*radius,y,x+2*radius-radius/4,y);
			g.drawLine(x+2*radius,y,x+2*radius,y+radius/4);
			g.drawLine(x+2*radius,y+2*radius,x+2*radius,y+2*radius-radius/4);
			g.drawLine(x+2*radius,y+2*radius,x+2*radius-radius/4,y+2*radius);
		}
	}
	
	/**
	 * Get the mass of the asteroid.
	 *
	 * @return the mass of the asteroid
	 *
	 */
    public double getMass()
    {
        return mass;
    }
    
	/**
	 * Set the mass of the asteroid.
	 *
	 * @param newMass a double new mass of the asteroid
	 *
	 */
    public void setMass(double newMass)
    {
        mass = newMass;
    }
	
	/**
	 * Return true if the asteroid is selected by a homing missile.
	 *
	 * @return true if select else false
	 *
	 */
	public boolean isSelect()
	{
		return isSelect;
	}
	
	/**
	 * Set if the asteroid is selected by a missile or not.
	 *
	 * @param b a boolean
	 *
	 */
	public void select(boolean b)
	{
		isSelect = b;
	}
}
