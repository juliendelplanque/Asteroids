/**
 * Class GameGenerator.
 * Create a game with a somme difficulty.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-06-03
 */

package be.ac.umons.asteroids.manager;

import java.util.Random;
import java.awt.geom.Point2D;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public class GameGenerator
{
	private static Engine engine = null;
	private static SpaceShip ship = null;
	
    /**
     * Create a game with specific conditions.
     *
     * @param width the width of the game zone
     * @param height the height of the game zone
     * @param shipLife the ship life at the begining of the game
     * @param minSize the mininimum size of Asteroid
     * @param maxSize the maximum size of Asteroid
     * @param minSpeed the minimum speed of Asteroid
     * @param numberOfAst the number of Asteroid at the begining of the game
     *
     */
	private void createGame(int width, int height, int shipLife, int minSize, int maxSize, double minSpeed, int numberOfAst)
	{
		engine = new Engine(new Point2D.Double(width,height),minSize);
		ship = new SpaceShip(new Point2D.Double((int)width/2,(int)height/2),new Point2D.Double(0,-1),0,shipLife);
        
        ship.setInvulnerability(true, 1000);
        
		for(int i = 0; i< numberOfAst; i++)
		{
            Random posX = new Random();
            Random posY = new Random();
            Random dirX = new Random();
            Random dirY = new Random();
            Random speed = new Random();
            Random size = new Random();
            Point2D.Double pos = new Point2D.Double((double)posX.nextInt(width), (double)posY.nextInt(height));
            Point2D.Double dir = new Point2D.Double(dirX.nextDouble(), dirY.nextDouble());
            Asteroid newAs = new Asteroid(pos, dir, (double)speed.nextDouble()+minSpeed, size.nextInt(maxSize)+minSize);
            engine.add(newAs);
		}
	}
	
    /**
     * Create a game with an easy difficulty.
     *
     * @param width the width of the game zone
     * @param height the height of the game zone
     * @param numberOfAst the number of Asteroid at the begining
     *
     */
	public void createEasyGame(int width, int height, int numberOfAst)
	{
		createGame(width, height,100,30,50,0.5, numberOfAst);
	}
	
    /**
     * Create a game with a medium difficulty.
     *
     * @param width the width of the game zone
     * @param height the height of the game zone
     * @param numberOfAst the number of Asteroid at the begining
     *
     */
	public void createMediumGame(int width, int height, int numberOfAst)
	{
		createGame(width, height,100,20,50,1, numberOfAst);
	}
	
    /**
     * Create a game with a hard difficulty.
     *
     * @param width the width of the game zone
     * @param height the height of the game zone
     * @param numberOfAst the number of Asteroid at the begining
     *
     */
	public void createHardGame(int width, int height, int numberOfAst)
	{
		createGame(width, height,100,15,50,2, numberOfAst);
	}
	
    /**
     * Create a game with a brutal difficulty.
     *
     * @param width the width of the game zone
     * @param height the height of the game zone
     * @param numberOfAst the number of Asteroid at the begining
     *
     */
	public void createBrutalGame(int width, int height, int numberOfAst)
	{
		createGame(width, height,100,10,50,2, numberOfAst);
	}
	
    /**
     * Create the Asteroid animation behind the menu screen.
     *
     * @param width the width of the game zone
     * @param height the height of the game zone
     *
     */
	public void createMenuAnimation(int width, int height)
	{
		createGame(width, height,100,5,75,0.5, 7);
	}
	
    /**
     * Return the Engine used in the game generated.
     *
     * @return the Engine used in the game
     */
	public Engine getEngine()
	{
		return engine;
	}
	
    /**
     * Return the SpaceShip used in the game generated.
     *
     * @return the SpaceShip used in the game
     */
	public SpaceShip getSpaceShip()
	{
		return ship;
	}
	
}
