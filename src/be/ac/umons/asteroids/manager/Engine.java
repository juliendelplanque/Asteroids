/**
 * Class Engine.
 * Manage physicals events of the game.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-06-03
 */

package be.ac.umons.asteroids.manager;

import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.lang.Thread;
import java.util.Random;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public class Engine
{
    private int minSize, reductionFactor;
    private Point2D.Double mapSize, collisionPoint;
    private ArrayList<Asteroid> asteroidList;
	private ArrayList<Missile> missileList;
	private ArrayList<ABonus> bonusList;
	private ArrayList<Explosion> expList;
    
    /**
     * Build an engine object.
     *
     * @param _mapSize the size of the game zone
     * @param _minSize the minimum size of the Asteroid
     *
     */
    public Engine(Point2D.Double _mapSize, int _minSize)
    {
        minSize = _minSize;
        reductionFactor = 2;
        mapSize = _mapSize;
        asteroidList = new ArrayList<Asteroid>(0);
		missileList = new ArrayList<Missile>(0);
		bonusList = new ArrayList<ABonus>(0);
		expList = new ArrayList<Explosion>(0);
    }
    
    /**
     * Add an asteroid in the arraylist.
     *
     * @param a an Asteroid
     *
     */
    public void add(Asteroid a)
    {
        asteroidList.add(a);
    }

    /**
     * Add an missile in the arraylist.
     *
     * @param m a Missile
     *
     */
    public void add(Missile m)
    {
        missileList.add(m);
    }
	
    /**
     * Add an bonus in the arraylist.
     *
     * @param b a Bonus
     *
     */
    public void add(ABonus b)
    {
        bonusList.add(b);
    }
    
    /**
     * Divide an asteroid.
     *
     * @param a an Asteroid to divide
     * @param b a boolean true if this methode have to delete the asteroid from the list else false
     *
     * @return an asteroid list of length 2
     *
     */
    private Asteroid[] divide(Asteroid a, boolean b)
	{
		Random rdAngle = new Random();
		int angle = rdAngle.nextInt(45)+45;
		Point2D.Double mainDir = a.getVecDir();
		Point2D.Double horizontalVector = new Point2D.Double(1, 0);
		double mainAngle = Geom.angle(horizontalVector, mainDir);
		double angle1 = mainAngle + angle;
		double angle2 = mainAngle - angle;
		Point2D.Double dir1 = Geom.rotate(Geom.opposite(mainDir), angle1);
		Point2D.Double dir2 = Geom.rotate(Geom.opposite(mainDir), angle2);
		Point2D.Double pos1 = Geom.add(a.getPosition(), Geom.normalize(dir1, 20));
		Point2D.Double pos2 = Geom.add(a.getPosition(), Geom.normalize(dir2, 20));
		double currentSpeed = a.getSpeed();
		int newRadius = a.getRadius()/reductionFactor;
		Asteroid newAsteroid1 = new Asteroid(pos1, dir1, currentSpeed, newRadius);
		Asteroid newAsteroid2 = new Asteroid(pos2, dir2, currentSpeed, newRadius);
		if(b == true)
			asteroidList.remove(a);
		Asteroid [] tab = {newAsteroid1,newAsteroid2};
		return tab;
	}
    
    /**
     * Divide the asteroids after a collision with a missile. 
     *
     * @param s the SpaceShip 
     *
     */
    public void division(SpaceShip s)
    {
		if(missileList.size() > 0)
		{
			for(int i = 0; i < missileList.size(); i++)
			{
				if(missileList.get(i).isAlive())
				{
					if(missileList.get(i) instanceof Pulse)
					{
                        for(int j = 0; j < asteroidList.size(); j++)
                        {
                            Asteroid currentAs = asteroidList.get(j);
                            Point2D.Double currentAsPosition = currentAs.getPosition();
                            Missile currentMi = missileList.get(i);
                            Point2D.Double currentMiPosition = currentMi.getPosition();
                            double collisionDist = Geom.magnitude(Geom.vector(currentAsPosition,currentMiPosition));
                            if(collisionDist <= currentAs.getRadius()+currentMi.getRadius())
                            {
								currentAs.setVecOr(Geom.vector(currentMiPosition,currentAsPosition));

                            }
                        }
					}
					else
					{
						for(int j = 0; j < asteroidList.size(); j++)
						{
							Asteroid currentAs = asteroidList.get(j);
							Point2D.Double currentAsPosition = currentAs.getPosition();
							Missile currentMi = missileList.get(i);
							Point2D.Double currentMiPosition = currentMi.getPosition();
							double collisionDist = Geom.magnitude(Geom.vector(currentAsPosition,currentMiPosition));
							if(collisionDist <= currentAs.getRadius()+currentMi.getRadius())
							{
								s.increaseScore(100*1/currentAs.getRadius());
                                expList.add(new Explosion(currentMiPosition));
								currentAs.setDeletable(true);
								asteroidList.remove(j);
								if (currentMi instanceof Homing)
								{
									Homing homing = (Homing) missileList.get(i);;
									homing.deselectAst();
								}
								currentMi.setDeletable(true);
								missileList.remove(i);
                                
								if(ABonus.willGetABonus())
									bonusList.add(ABonus.randomBonus(currentAs));
								
								if(ABonus.willGetAmmo())
									bonusList.add(ABonus.randomAmmo(currentAs));
								
								if(ABonus.willGetMainWeaponBonus())
								{
									ABonus b = ABonus.randomMainWeaponBonus(currentAs,s);
									if(b != null)
										bonusList.add(b);
								}
								if(currentAs.getRadius() >= minSize)
								{
									Asteroid[] tab = divide(currentAs,true);
									add(tab[0]);
									add(tab[1]);
								}
								break;
							}
						}
					}
				}
				else
				{
                    missileList.get(i).setDeletable(true);
					missileList.remove(i);
				}				
			}
		}
    }
    
    /**
     * Calcul the collision between two asteroids and refresh the position of 
	 * the asteroids.
	 *
     * @param s the SpaceShip
     *
     */
    public void bounceBetweenAsteroids(SpaceShip s)
    {
        for(int x = 0; x < asteroidList.size(); x++)
        {
            Asteroid as1 = asteroidList.get(x);
			double dist = Geom.magnitude(Geom.vector(s.getPosition(),as1.getPosition()));
			if(dist <= (as1.getRadius()+s.getRadius()))
			{
				s.decreaseLife();
				Point2D.Double newVecOr = Geom.vector(s.getPosition(),as1.getPosition());
				as1.setVecOr(newVecOr);
			}
            as1.refreshPosition();
            double xCoord = as1.getPosition().x;
            double yCoord = as1.getPosition().y;    
            if(xCoord > mapSize.x) 
            {
                as1.setPosition(new Point2D.Double(0 ,yCoord));
            }
            else if(xCoord < 0)
            {
                as1.setPosition(new Point2D.Double(mapSize.x - as1.getRadius(),yCoord));
            }
            if(yCoord > mapSize.y)
            {
                as1.setPosition(new Point2D.Double(xCoord, 0));
            }
            else if(yCoord < 0)
            {
                as1.setPosition(new Point2D.Double(xCoord, mapSize.y - as1.getRadius()));
            }
            for(int y = x+1; y < asteroidList.size(); y++)
            {
                Asteroid as2 = asteroidList.get(y);
                Point2D.Double pos1 = as1.getPosition();
                Point2D.Double pos2 = as2.getPosition();
                double astDist = Geom.magnitude(Geom.vector(pos1,pos2));
                int radius1 = as1.getRadius();
                int radius2 = as2.getRadius();

                if (astDist <= (radius1 + radius2))
                {
                    Point2D.Double vecSpeed1 = as1.getVecSpeed();
                    Point2D.Double vecSpeed2 = as2.getVecSpeed();
                    Point2D.Double vecDir1 = as1.getVecDir();
                    Point2D.Double vecDir2 = as2.getVecDir();
                    double speed1 = as1.getSpeed();
                    double speed2 = as2.getSpeed();
                    double mass1 = as1.getMass();
                    double mass2 = as2.getMass();
                    Point2D.Double horizontalVector = new Point2D.Double(1, 0);
                    Point2D.Double mainVector = Geom.vector(pos1, pos2);
                    double collisionAngle = Geom.angle(mainVector, horizontalVector);
					
					//set new direction
					
                    Point2D.Double tempMainVec = Geom.rotate(mainVector, -collisionAngle);
                    Point2D.Double tempVec1 = Geom.opposite(tempMainVec);
                    Point2D.Double tempVec2 = Geom.opposite(tempVec1);
                    Point2D.Double finalVec1 = Geom.rotate(tempVec1, collisionAngle);
                    Point2D.Double finalVec2 = Geom.rotate(tempVec2, collisionAngle);
                        
                    as1.setVecDir(finalVec1);
                    as2.setVecDir(finalVec2);
                        
                    // set news speed
                    
                    double finalSpeed1 = (((mass1-mass2)*speed1) + ((2*mass2)*speed2)) / (mass1+mass2);
                    double finalSpeed2 = (((mass2-mass1)*speed2) + ((2*mass1)*speed1)) / (mass1+mass2);
                      
                    as1.setSpeed(finalSpeed1);
                    as2.setSpeed(finalSpeed2);                       
				}
            }
        }
    } 
	
    /**
     * Calcul the collision between two asteroids and refresh the position of 
	 * the asteroids.
     *
     */
    public void bounceBetweenAsteroids()
    {
        for(int x = 0; x < asteroidList.size(); x++)
        {
            Asteroid as1 = asteroidList.get(x);
            as1.refreshPosition();
            double xCoord = as1.getPosition().x;
            double yCoord = as1.getPosition().y;    
            if(xCoord > mapSize.x) 
            {
                as1.setPosition(new Point2D.Double(0 ,yCoord));
            }
            else if(xCoord < 0)
            {
                as1.setPosition(new Point2D.Double(mapSize.x - as1.getRadius(),yCoord));
            }
            if(yCoord > mapSize.y)
            {
                as1.setPosition(new Point2D.Double(xCoord, 0));
            }
            else if(yCoord < 0)
            {
                as1.setPosition(new Point2D.Double(xCoord, mapSize.y - as1.getRadius()));
            }
            for(int y = x+1; y < asteroidList.size(); y++)
            {
                Asteroid as2 = asteroidList.get(y);
                Point2D.Double pos1 = as1.getPosition();
                Point2D.Double pos2 = as2.getPosition();
                double astDist = Geom.magnitude(Geom.vector(pos1,pos2));
                int radius1 = as1.getRadius();
                int radius2 = as2.getRadius();

                if (astDist <= (radius1 + radius2))
                {
                    Point2D.Double vecSpeed1 = as1.getVecSpeed();
                    Point2D.Double vecSpeed2 = as2.getVecSpeed();
                    Point2D.Double vecDir1 = as1.getVecDir();
                    Point2D.Double vecDir2 = as2.getVecDir();
                    double speed1 = as1.getSpeed();
                    double speed2 = as2.getSpeed();
                    double mass1 = as1.getMass();
                    double mass2 = as2.getMass();
                    Point2D.Double horizontalVector = new Point2D.Double(1, 0);
                    Point2D.Double mainVector = Geom.vector(pos1, pos2);
                    double collisionAngle = Geom.angle(mainVector, horizontalVector);
					
					//set new direction
					
                    Point2D.Double tempMainVec = Geom.rotate(mainVector, -collisionAngle);
                    Point2D.Double tempVec1 = Geom.opposite(tempMainVec);
                    Point2D.Double tempVec2 = Geom.opposite(tempVec1);
                    Point2D.Double finalVec1 = Geom.rotate(tempVec1, collisionAngle);
                    Point2D.Double finalVec2 = Geom.rotate(tempVec2, collisionAngle);
                        
                    as1.setVecDir(finalVec1);
                    as2.setVecDir(finalVec2);
                        
                    // set news speed
                    
                    double finalSpeed1 = (((mass1-mass2)*speed1) + ((2*mass2)*speed2)) / (mass1+mass2);
                    double finalSpeed2 = (((mass2-mass1)*speed2) + ((2*mass1)*speed1)) / (mass1+mass2);
                      
                    as1.setSpeed(finalSpeed1);
                    as2.setSpeed(finalSpeed2);                       
				}
            }
        }
    }
    
    /**
     * Refresh the position of all the Missile in the Missile list
     *
     */
	public void refreshMissilePositions()
	{
		for(int i = 0; i<missileList.size(); i++)
		{
			if(missileList.get(i).isAlive())
			{
				missileList.get(i).refreshPosition();
			}
			else
			{
				if (missileList.get(i) instanceof Homing)
				{
					Homing homing = (Homing) missileList.get(i);;
					homing.deselectAst();
				}
				missileList.get(i).setDeletable(true);
				missileList.remove(i);
			}
			
		}
	}
	
    /**
     * Refresh the explosions.
     *
     */
	public void refreshExplosion()
	{
		for(int i = 0; i<expList.size(); i++)
		{
			if(expList.get(i).isDeletable() == true)
			{
				expList.remove(i);
			}
		}
	}
	
    /**
     * Refresh the position of Bonus in the Bonus list.
     *
     */
	public void refreshBonusPositions(SpaceShip s)
	{
		for(int i = 0; i<bonusList.size(); i++)
		{
			if(bonusList.get(i).isAlive() == true)
			{
				double dist = Geom.magnitude(Geom.vector(s.getPosition(),bonusList.get(i).getPosition()));
				if(dist <= (s.getRadius()+bonusList.get(i).getRadius()))
				{
					if(bonusList.get(i) instanceof ISpaceShipBonus)
					{
						ISpaceShipBonus b = (ISpaceShipBonus) bonusList.get(i);
						b.effect(s);
					}
					
					else if(bonusList.get(i) instanceof DivideBonus)
					{
						DivideBonus b = (DivideBonus) bonusList.get(i);
						b.effect(this);
					}

					bonusList.remove(i);
				}
				else
					bonusList.get(i).refreshPosition();
			}
			else
				bonusList.remove(i);
		}
	}
   
    /**
     * Check all the colisions.
     *
     * @param s the spaceShip
     *
     */
	public void refresh(SpaceShip s)
	{
		division(s);
		refreshExplosion();
		bounceBetweenAsteroids(s); //check if ship is in collision as well
		refreshBonusPositions(s);
		refreshMissilePositions();
	}
	
    
    /**
     * Divide all the Asteroid
     *
     */
	public void divideAll()
	{
		ArrayList<Asteroid> newAstList = new ArrayList<Asteroid>();
		
		for(int i = 0; i < asteroidList.size(); i++)
		{
			expList.add(new Explosion(asteroidList.get(i).getPosition()));
			if(asteroidList.get(i).getRadius() >= minSize)
			{
				Asteroid [] tab = divide(asteroidList.get(i),false);
				newAstList.add(tab[0]);
				newAstList.add(tab[1]);
			}
		}
		asteroidList = newAstList;
		newAstList = null;
	}
	
    /**
     * Set the minimal size of an asteroid.
     *
     * @param newMinSize an int new minimal size
     *
     */
    public void setMinSize(int newMinSize)
    {
        minSize = newMinSize;
    }
    
    /**
     * Set the reduction factor.
     *
     * @param newFactor an int new reduction factor
     *
     */
    public void setReductionFactor(int newFactor)
    {
        reductionFactor = newFactor;
    }
    
    /**
    *Set the map's size.
    *
    *@param newMapSize a Point2D.Double new map size
    *
    */
    public void setMapSize(Point2D.Double newMapSize)
    {
        mapSize = newMapSize;
    }
    
    /**
     * Get the minimal size of an asteroid.
     *
     * @return the minimal size
     *
     */
    public int getMinSize()
    {
        return minSize;
    }
    
    /**
     * Get the reduction factor.
     *
     * @return the reduction factor
     *
     */
    public int getReductionFactor()
    {
        return reductionFactor;
    }
    
    /**
     * Get the map's size.
     *
     * @return the map's size
     *
     */
    public Point2D.Double getMapSize()
    {
        return mapSize;
    }
	
    /**
    * Get the list of Asteroids.
    *
    * @return the list of Asteroid
    */
	public ArrayList<Asteroid> getAsteroidList()
	{
		return asteroidList;
	}
	
    /**
     * Set the list of Asteroids.
     *
     * @param a the news list of Asteroid
     *
     */
	public void setAsteroidList(ArrayList<Asteroid> a)
	{
		asteroidList = a;
	}
	
    /**
     * Remove the Asteroid at the i index of the Asteroids list.
     *
     * @param i the index of the Asteroid to remove
     */
	public void removeAsteroid(int i)
	{
		asteroidList.remove(i);
	}
	
    /**
     * Return the list of Missiles.
     *
     * @return the ArrayList that contains the Asteroid
     */
	public ArrayList<Missile> getMissileList()
	{
		return missileList;
	}
	
    /**
     * Set a new list of Missiles.
     *
     * @param m the new ArrayList that contains Missile
     *
     */
	public void setMissileList(ArrayList<Missile> m)
	{
		missileList = m;
	}
	
    /**
     * Remove a Missile from the Missile list.
     *
     * @param i the index of the Missile to remove
     *
     */
	public void removeMissile(int i)
	{
		missileList.remove(i);
	}
    
    /**
     * Return the list of Bonus.
     *
     * @return the ArrayList that contains Bonus
     *
     */
	public ArrayList<ABonus> getBonusList()
	{
		return bonusList;
	}
	
    /**
     * Set the list of Bonus.
     *
     * @param b the news ArrayList of Bonus
     *
     */
	public void setBonusList(ArrayList<ABonus> b)
	{
		bonusList = b;
	}
	
    /**
     * Return the list of Explosions.
     *
     * @return the ArrayList that contains Explosion
     *
     */
	public ArrayList<Explosion> getExplosionList()
	{
		return expList;
	}
	
    /**
     * Set the list of Explosions.
     *
     * @param e the new ArrayList that contains Explosion
     *
     */
	public void setExplosionList(ArrayList<Explosion> e)
	{
		expList = e;
	}
	
    
    /**
     * Remove a Bonus in the Bonus list.
     *
     * @param i the index of the Bonus to remove
     *
     */
	public void removeBonus(int i)
	{
		bonusList.remove(i);
	}
	
    /**
     * Create the Explosion when the game is over.
     *
     * @param ship the SpaceShip who explode
     */
	public void finalExplosion(SpaceShip ship)
	{
		expList.add(new Explosion(ship.getPosition()));
	}
}
