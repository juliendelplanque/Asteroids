/**
* Class SpaceShip.
* Calculs of movements for the ship.
* @author Delplanque Julien - Giammello Alessandro
* @version 1.0, 2013-03-04
*/
package be.ac.umons.asteroids.mobile;

import java.awt.geom.Point2D;
import java.awt.Polygon;
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

public class SpaceShip extends AMobile implements Cloneable
{

    // Private fields
    private int acceleration;
	private Image image,image2;
	private int angle;
    private int limitWidth, limitHeight;
	private int radius;
	private int life;
	private String name;
	private boolean isInvulnerable;
	private int invulnerableTime;
	private int score;
	private boolean multiFire, rateB, missileLifeB;
	private int homing,pulse,claymore;
	private boolean homingB, pulseB, claymoreB;
	private int missileLife;
    private double den = Math.pow(10, 10);
	private Color color;
	private int lifeTime;
	private int fireRate;
	private int rate = 15*Main.GAMEDELAY;
    
    
    // Public fields
	public static final int HOMING = 10;
	public static final int PULSE = 15;
	public static final int CLAYMORE = 5;
    
    
	/**
	 * Build a spaceship object
	 *
     * @param pos the initial position of the SpaceShip object
     * @param vecOr the initial orientation of the SpaceShip
     * @param speed the initial speed of the SpaceShip
     * @param _life the initial life of the SpaceShip
	 * 
	 */    
    public SpaceShip(Point2D.Double pos, Point2D.Double vecOr, double speed, int _life)
    {
		super(pos, vecOr, speed);
        acceleration = 0;
		angle = 0;
		radius = 25;
		life = _life;
		isInvulnerable = false;
		invulnerableTime = 10000;
		score = 0;
		multiFire = false;
		rateB = false;
		missileLifeB = false;
		homing = HOMING;
		pulse = PULSE;
		claymore = CLAYMORE;
		homingB = false; 
		pulseB = true;
		claymoreB = false;
		missileLife = 300;
		fireRate = rate;
		name = "";
    }
	
	/**
	 * Build a spaceship object.
	 *
     * @param pos the initial position of the SpaceShip object
     * @param vecOr the initial orientation of the SpaceShip
     * @param speed the initial speed of the SpaceShip
     * @param _life the initial life of the SpaceShip
     * @param _name the name of the SpacheShip
	 * 
	 */    
    public SpaceShip(Point2D.Double pos, Point2D.Double vecOr, double speed, int _life, String _name)
    {
		super(pos, vecOr, speed);
        acceleration = 0;
		angle = 0;
		radius = 25;
		life = _life;
		name = _name;
		isInvulnerable = false;
		invulnerableTime = 10000;
		score = 0;
		multiFire = false;
		rateB = false;
		missileLifeB = false;
		homing = HOMING;
		pulse = PULSE;
		claymore = CLAYMORE;
		homingB = false; 
		pulseB = true;
		claymoreB = false;
		missileLife = 300;
		fireRate = rate;
		name = _name;
    }
    
    /**
     * Return a new SpaceShip.
     *
     * @return a SpaceShip object with entire life and no inital speed at the center of the game zone
     *
     */
	public SpaceShip clone()
	{
		SpaceShip s = new SpaceShip(new Point2D.Double(limitWidth/2,limitHeight/2), new Point2D.Double(0,-1), 0, 100, name);
		s.setSkin(color);
		return s;
	}
	
	@Override
	public String toString()
	{
		return getName() + " : " + getScore();
	}
	
    @Override
    public void draw(Graphics g)
    {
		Graphics2D  g2 = (Graphics2D)g;
        
        AffineTransform saved = g2.getTransform();
		AffineTransform at = new AffineTransform();

		at.translate((int)position.x, (int)position.y);
		at.rotate(Math.toRadians(angle));
        
        g2.setTransform(at);
        
		if(acceleration > 0)
			g2.drawImage(image2, -25, -25, null);
		else
			g2.drawImage(image, -25, -25, null);
        
        g2.setTransform(saved);
    }
    
    /**
	 * Fire with the main weapon.
	 *
	 */
    public Missile[] fire(int t)
    {
    	fireRate -= t;
        Missile[] mis = null;
        
    	if(fireRate <= 0)
    	{
    		fireRate = rate;
			if(multiFire)
			{
				mis = multiFire();
			}
			else
			{
				mis = simpleFire();
			}
		}
        
        return mis;
		
    }
	
	/**
	 * Fire a missile.
	 *
	 * @return a Missile
	 *
	 */
	public Missile[] simpleFire()
	{
			Missile missile = new Missile(Geom.add(position,vecDir), vecDir, (int)speed+10, 1, (double)missileLife);
			Missile[] tab = {missile};
			return tab;		
	}
	
	/**
	 * Fire three missiles.
	 *
	 * @return a missile tab
	 *
	 */
	public Missile[] multiFire()
	{
		Missile missile = new Missile(Geom.add(position,vecDir), vecDir, (int)speed+10, 1, (double)missileLife);
		Missile missile1 = new Missile(Geom.add(position,vecDir), Geom.rotate(vecDir,30), (int)speed+10, 1, (double)missileLife);
		Missile missile2 = new Missile(Geom.add(position,vecDir), Geom.rotate(vecDir,-30), (int)speed+10, 1, (double)missileLife);
		Missile[] tab = {missile,missile1,missile2};
		return tab;
	}
	
	/**
	 * Fire an homing.
	 *
	 * @return an Homing
	 *
	 */
	public Homing homingWeapon(Engine e) throws NoMunitionException
	{
		if(homing > 0)
		{
			homing--;
			Homing homing = new Homing(Geom.add(position,vecDir), vecDir, (int)speed+10, 5, (double)1000,e);
			return homing;	
		}
		else
		{
			throw new NoMunitionException();
		}
	}
	
	/**
	 * Fire a pulse.
	 *
	 * @return a Pulse
	 *
	 */
	public Pulse pulseWeapon() throws NoMunitionException
	{
		if(pulse > 0)
		{
			pulse--;
			Pulse pulse = new Pulse(position, vecDir, (int)speed+10, 5);
			return pulse;
		}
		else
		{
			throw new NoMunitionException();
		}
	}
	
	/**
	 * Drop a Claymore at the back of the ship.
	 *
	 * @return a Claymore
	 *
	 */
	public Claymore dropClaymore() throws NoMunitionException
	{
		if(claymore > 0)
		{
			claymore--;
			Claymore claymore = new Claymore(Geom.add(position,Geom.normalize(Geom.opposite(vecOr),27)));
			return claymore;
		}
		else
		{
			throw new NoMunitionException();
		}
	}

	/**
	 * Refresh the position of the ship.
	 *
	 */    
    public void refreshPosition()
    {
        Point2D.Double newDir = Geom.normalize(Geom.add(vecDir,vecOr), 1);
        Point2D.Double vecSpeed = Geom.normalize(newDir, speed);
        Point2D.Double newPos = Geom.add(super.position, vecSpeed);
        if(position.x > limitWidth)
            setPosition(new Point2D.Double(0, position.y));
        else if(position.y > limitHeight)
            setPosition(new Point2D.Double(position.x, 0));
        else if(position.x < 0)
            setPosition(new Point2D.Double(limitWidth, position.y));
        else if(position.y < 0)
            setPosition(new Point2D.Double(position.x, limitHeight));
        else
            setPosition(newPos);
    }
	
	/**
	 * Refresh the invulnerablility time of the ship.
	 *
	 */
	public void refreshInvulnerability(int time)
	{
		if(isInvulnerable())
		{
			invulnerableTime -= time;
			if(invulnerableTime <= 0)
			{
				setInvulnerability(false);
				invulnerableTime = 10000;
			}
		}
		lifeTime += time;
	}

	/**
	 * Increase of one unity the speed of the ship.
     *
	 */    
    public void increaseSpeed(long tm)
    {
        
        speed = acceleration*(tm/den);
    }

	/**
	 * Decrease of one unity the speed of the ship.
     *
	 */    
    public void decreaseSpeed(long tm)
    {
        speed += acceleration*(tm/den);
        if (speed < 0)
            speed = 0;
    }

	/**
	 * Return the acceleration of the ship.
     *
	 * @return the acceleration of the ship
     *
	 */       
    public int getAcceleration()
    {       
        return acceleration;    
    }
    
	/**
	 * Set the acceleration of the ship.
     *
	 * @param newAcceleration an int new acceleration
     *
	 */    
    public void setAcceleration(int newAcceleration)
    {
        acceleration = newAcceleration;        
    }
    
    /**
     * Return the max height of the SpaceShip zone.
     *
     * @return the height of the SpaceShip zone
     *
     */
    public int getLimitHeight()
    {
        return limitHeight;
    }
    
    /**
     * Return the max width of the SpaceShip zone
     *
     * @return the width of the SpaceShip zone
     *
     */
    public int getLimitWidth()
    {
        return limitWidth;
    }
    
    /**
     * Set the max width of the SpaceShip zone.
     *
     * @param newWidthLimit the new width of the SpaceShip zone
     *
     */
    public void setLimitWidth(int newWidthLimit)
    {
        limitWidth = newWidthLimit;
    }
    
    /**
     * Set the max height of the SpaceShip zone.
     *
     * @param newHeightLimit the new height of the SpaceShip zone
     *
     */
    public void setLimitHeight(int newHeightLimit)
    {
        limitHeight = newHeightLimit;
    }
	
    /**
     * Increase the angle value (use to rotate the SpaceShip).
     *
     */
	public void increaseAngle()
	{
		setVecOr(Geom.rotate(vecOr,-3));
		angle = (angle-3)%360;
	}
    
    /**
     * Decrease the angle value (use to rotate the SpaceShip).
     *
     */
	public void decreaseAngle()
	{
		setVecOr(Geom.rotate(vecOr,3));
		angle = (angle+3)%360;
	}
	
    /**
     * Return the radius of the SpaceShip's hide box.
     *
     * @return the radius of the SpaceShip's hide box
     *
     */
	public int getRadius()
	{
		return radius;
	}
	
    /**
     * Set the SpaceShip's life.
     *
     * @param _life the new life of the SpaceShip
     *
     */
	public void setLife(int _life)
	{
		life = _life;
		if(life < 100)
			life = 100;
		else if(life < 0)
			life = 0;
	}
	
    /**
     * Return the SpaceShip's life.
     *
     * @return the SpaceShip's life
     *
     */
	public int getLife()
	{
		return life;
	}
	
    /**
     * Increase the SpaceShip's life.
     *
     */
	public void increaseLife()
	{
		life +=10;
		if(life > 100)
			life = 100;
	}
	
    /**
     * Decrease the SpaceShip's life
     *
     */
	public void decreaseLife()
	{
		if(!isInvulnerable())
		{
			life -= 10;
			if(life < 0)
				life = 0;
			setInvulnerability(true,1000);
		}
	}
	
    /**
     * Return the Invulnerability state.
     *
     * @return true if the SpaceShip is invulnerable, otherwise false
     *
     */
	public boolean isInvulnerable()
	{
		return isInvulnerable;
	}
	
    /**
     * Set the Invulnerability state.
     *
     * @param b true to activate the invulnerability, false to desactivate
     *
     */
	public void setInvulnerability(boolean b)
	{
		isInvulnerable = b;
	}
	
    /**
     * Set the Invulnerability state.
     * 
     * @param b true to activate the invulnerability, false to deactivate
     * @param time the duration of the invulnerability if it's true
     *
     */
	public void setInvulnerability(boolean b,int time)
	{
		isInvulnerable = b;
		if(isInvulnerable)
			invulnerableTime = time;
		else
			invulnerableTime = 10000;
	}
	
    /**
     * Increase de Score.
     *
     * @param s the value to add to the Score
     *
     */
	public void increaseScore(int s)
	{
		score += s;
	}
	
    /**
     * Return the score.
     *
     * @return the score
     *
     */
	public int getScore()
	{
		return score+(lifeTime/1000);
	}
	
    /**
     * Set the green color to the SpaceShip.
     *
     */
	public void setGreenSkin()
	{
		color = Color.green;
		Image[] tab = GetImage.getGreenShipImage();
		image = tab[0];
		image2 = tab[1];
	}
	
    /**
     * Set the orange color to the SpaceShip.
     *
     */
	public void setOrangeSkin()
	{
		color = color.orange;
		Image[] tab = GetImage.getOrangeShipImage();
		image = tab[0];
		image2 = tab[1];
	}
	
    /**
     * Set the blue color to the SpaceShip.
     *
     */
	public void setBlueSkin()
	{
		color = color.blue;
		Image[] tab = GetImage.getBlueShipImage();
		image = tab[0];
		image2 = tab[1];
	}
    
    /**
     * Set the SpaceShip's Color.
     *
     * @param c the new color of the SpaceShip
     *
     */
	private void setSkin(Color c)
	{
		if(c == Color.green)
			setGreenSkin();
		else if(c == Color.orange)
			setOrangeSkin();
		else if(c == Color.blue)
			setBlueSkin();
	}
	
	/**
	 * Return the number of homing that left.
	 *
	 * @return the number of homing
	 *
	 */
	public int getHomingAmmo()
	{
		return homing;
	}
	
	/**
	 * Return the number of claymore that left.
	 *
	 * @return the number of claymore
	 *
	 */
	public int getClaymoreAmmo()
	{
		return claymore;
	}
	
	/**
	 * Return the number of pulse that left.
	 *
	 * @return the number of pulse
	 *
	 */
	public int getPulseAmmo()
	{
		return pulse;
	}
	
	/**
	 * Increase the number of homing (factor 2).
	 *
	 */
	public void reloadHoming()
	{
		if(homing < HOMING)
			homing += 2;
	}
	
	/**
	 * Increase the number of pulse (factor 3).
	 *
	 */
	public void reloadPulse()
	{
		if(pulse < PULSE)
			pulse += 3;
	}
	
	/**
	 * Increase the number of claymore (factor 1).
	 *
	 */
	public void reloadClaymore()
	{
		if(claymore < CLAYMORE)
			claymore += 1;
	}
	
    /**
     * Activate the Homing bonus.
     *
     */
	public void setHoming()
	{
		homingB = true;
		pulseB = false;
		claymoreB = false;
	}
	
    /**
     * Activate the Pulse bonus.
     *
     */
	public void setPulse()
	{
		pulseB = true;
		claymoreB = false;
		homingB = false;
	}
	
    /**
     * Activate the Claymore bonus.
     *
     */
	public void setClaymore()
	{
		claymoreB = true;
		homingB = false;
		pulseB = false;
	}
	
    /**
     * Return the state of Homing bonus.
     *
     * @return true if the bonus Homing is activated, otherwise false
     *
     */
	public boolean isHoming()
	{
		return homingB;
	}
    
    /**
     * Return the state of Pulse bonus.
     *
     * @return true if the bonus Pulse is activated, otherwise false
     *
     */
	public boolean isPulse()
	{
		return pulseB;
	}
    
    /**
     * Return the state of Claymore bonus.
     *
     * @return true if the bonus Claymore is activated, otherwise false
     *
     */
	public boolean isClaymore()
	{
		return claymoreB;
	}
	
    /**
     * Return the name of the SpaceShip.
     *
     * @return the name of the spaceShip
     *
     */
	public String getName()
	{
		return name;
	}
	
    /**
     * Set the name of the SpaceShip.
     *
     * @param newName the new name of the SpaceShip
     *
     */
	public void setName(String newName)
	{
		name = newName;
	}
	
	public int getTime()
	{
		return lifeTime;
	}
	
    /**
     * Return the life of Missile.
     *
     * @return the life of Missile
     *
     */
	public int getMissileLife()
	{
		return missileLife;
	}
	
    /**
     * Increase the missile life (by a factor 2).
     *
     */
	public void increaseMissileLife()
	{
		missileLife += 1000;
		missileLifeB = true;
	}
	
    /**
     * Return if the multi fire is activated.
     *
     * @return true if the multi fire is activate, otherwise false
     *
     */
	public boolean isMultiFireSet()
	{
		return multiFire;
	}
	
    /**
     * Activate the multi fire.
     *
     */
	public void setMultiFire()
	{
		multiFire = true;
	}
	
    /**
     * Increase de fire rate of the SpaceShip.
     *
     */
	public void increaseFireRate()
	{
		rate -= 5*Main.GAMEDELAY;
		rateB = true;
		
	}
	
    /**
     * Return the fire rate.
     *
     * @return the fire rate of the SpaceShip
     *
     */
	public int getFireRate()
	{
		return fireRate;
	}
	
	/**
     * Return if the fire rate is activated.
     *
     * @return true if the fire rate is activate, otherwise false
     *
     */
	public boolean isFireRateIncreased()
	{
		return rateB;
	}
	
	/**
     * Return if the missile life bonus is activated.
     *
     * @return true if the missile life bonus is activate, otherwise false
     *
     */
	public boolean isMissileLifeSet()
	{
		return missileLifeB;
	}
}
