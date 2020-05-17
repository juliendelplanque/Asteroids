/**
 * Abstract class Bonus.
 * Creates the basic functionalities of an abstract bonus and some methods to generate bonus randomly.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-07-04
 */

package be.ac.umons.asteroids.mobile.bonus;

import java.awt.geom.Point2D;
import java.util.Random;
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

public abstract class ABonus extends Asteroid
{

    // Protected fields
    protected double life;
	protected int time;
	protected Image image;
    
    // Public fields
	public static final int BONUSLIFE = 7500;
	public static final double BONUSSPEED = 0.5;
	
	/**
     * Build an ABonus object.
     *
     * @param pos the initial position of the Asteroid
     * @param _vecDir the initial direction of the Asteroid
     * @param _speed the initial speed of the Asteroid
     * @param _life the life of the bonus
     *
     */
	protected ABonus(Point2D.Double pos, Point2D.Double _vecDir, double _speed, double _life)
	{
		super(pos,_vecDir, _speed, 12);
        life = _life;
	}
	
	@Override
	public void refreshPosition()
	{
		super.refreshPosition();
		life -= time;
	}
	
    /**
     * Return true if the bonus is still alive else return false.
     *
     * @return the state of the bonus
     *
     */
    public boolean isAlive()
    {
        if(life > 0)
            return true;
        else
            return false;
    }

    /**
     * Returns the remaining life of the bonus.
     *
     * @return the remaining life of the bonus
     *
     */  
    public double timeToLive()
    {
        return life;
    }
	
	/**
	 * Determinate if the player will get a Bonus randomly.
	 *
	 * @return true if the player will get a bonus, else false
	 *
	 */
	public static boolean willGetABonus()
	{
		Random rd = new Random();
		int r = rd.nextInt(60);
		if(r == 1)
			return true;
		else 
			return false;
	}
	
	/**
	 * Static method that decide with a Random object if an AmmoBonus will appear after the destruction of an asteroid.
	 *
	 * @return true if an asteroid will apear else false
	 *
	 */
	public static boolean willGetAmmo()
	{
		Random rd = new Random();
		int r = rd.nextInt(25);
		if(r == 1)
			return true;
		else 
			return false;
	}
	
	/**

	 * Static method that decide with a Random object if an MainWeaponBonus will appear after the destruction of an asteroid.
	 *
	 * @return true if an asteroid will apear else false
	 *
	 */
	public static boolean willGetMainWeaponBonus()
	{
		Random rd = new Random();
		int r = rd.nextInt(60);
		if(r == 1)
			return true;
		else 
			return false;
	}
	/**
	 * Return a random Bonus.
	 *
	 * @param a an Asteroid
	 *
	 * @return a Bonus
	 *
	 */
	public static ABonus randomBonus(Asteroid a)
	{
		Random r = new Random();
		int bonus = r.nextInt(3);
        ABonus b = null;
        switch(bonus)
        {
            case 0:	LifeBonus lifeB = new LifeBonus(a.getPosition(),a.getVecDir(),BONUSSPEED,BONUSLIFE);
                    lifeB.setTime(Main.GAMEDELAY);
                    b = lifeB;
                    break;
            case 1:	DivideBonus divideB = new DivideBonus(a.getPosition(),a.getVecDir(),BONUSSPEED,BONUSLIFE);
                    divideB.setTime(Main.GAMEDELAY);
                    b = divideB;
                    break;
            case 2:	InvulnerabilityBonus invB = new InvulnerabilityBonus(a.getPosition(),a.getVecDir(),BONUSSPEED,BONUSLIFE);
                    invB.setTime(Main.GAMEDELAY);
                    b = invB;
                    break;
        }
        
        return b;
	}
	
	/**
	 * Return a random Ammo.
	 *
	 * @param a an Asteroid
	 *
	 * @return an Ammo
	 *
	 */
	public static ABonus randomAmmo(Asteroid a)
	{
		Random r = new Random();
		int ammo = r.nextInt(3);
        ABonus b = null;
        
        switch(ammo)
        {
            case 0:	PulseAmmo pulse = new PulseAmmo(a.getPosition(),BONUSLIFE);
                    pulse.setTime(Main.GAMEDELAY);
                    b = pulse;
                    break;
            case 1:	HomingAmmo homing = new HomingAmmo(a.getPosition(),BONUSLIFE);
                    homing.setTime(Main.GAMEDELAY);
                    b = homing;
                    break;
            case 2:	ClaymoreAmmo claymore = new ClaymoreAmmo(a.getPosition(),BONUSLIFE);
                    claymore.setTime(Main.GAMEDELAY);
                    b = claymore;
                    break;
        }
        
        return b;

	}
	
    /**
     * Return a weapon bonus.
     *
     * @param a an Asteroid (the bonus appear if this Asteroid is exploded)
     * @param s the SpaceShip
     *
     * @return a weapon bonus
     *
     */
	public static ABonus randomMainWeaponBonus(Asteroid a, SpaceShip s)
	{
		Random r = new Random();
		int ammo = r.nextInt(3);
        ABonus b = null;
        if(ammo == 0 && s.isMissileLifeSet() == false)
        {
            MissileLifeBonus missileLife = new MissileLifeBonus(a.getPosition(),a.getVecDir(),BONUSSPEED,BONUSLIFE);
            missileLife.setTime(Main.GAMEDELAY);
            b = missileLife;
        }
        else if(ammo == 1 && s.isMultiFireSet() == false)
        {   
            MultiFireBonus multiFire = new MultiFireBonus(a.getPosition(),a.getVecDir(),BONUSSPEED,BONUSLIFE);
            multiFire.setTime(Main.GAMEDELAY);
            b = multiFire;
        }
        else if(ammo == 2 && s.isFireRateIncreased() == false)
        {    
            FireRateBonus fireRate = new FireRateBonus(a.getPosition(),a.getVecDir(),BONUSSPEED,BONUSLIFE);
            fireRate.setTime(Main.GAMEDELAY);
            b = fireRate;
        }
        return b;
	}
	
	/**
     * Get how many time is substracted from the life of the bonus.
     *
     * @return the substracted time
     *
     */ 
    public int getTime()
    {
        return time;
    }
	
	/**
     * Set how many time is substracted from the life of the bonus.
     *
     * @param newTime a int time to substract
     *
     */ 
    public void setTime(int newTime)
    {
        time = newTime;
    }
}
