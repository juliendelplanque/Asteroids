/**
 * Class MainTask.
 * Create the task used in the game timer
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-07-04
 */

package be.ac.umons.asteroids.manager;

import javax.swing.*;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Random;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public class MainTask
{

    // Public fields    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 640;
	public static PlayerListener listener = new PlayerListener();
	public static MenuBar menuBar = new MenuBar();
	public static DrawablePanel panel;
	public static MainFrame mainFrame;
	public static SpaceShip ship;
	public static Engine en;
	public static RecordManager recordManager;
	public static TournamentManager tournamentManager;
	public static MultiPlayerMenuFrame multiFrame;
    public static final int DELAY = 5000;
    
    // Private fields
    private static int menuAnswer, quitAnswer;
	private static GameGenerator g = new GameGenerator();
    private static ShipObserver shipObserver;
	private static int numberOfAst;
    private	static ArrayList<Asteroid> a;
	private	static ArrayList<Missile> m;
	private	static ArrayList<ABonus> b;
    private static ArrayList<Explosion> exp;
    private	static ArrayList<IDrawable> d;
    private static int currentDifficulty = 1;
    private static int panelHeight, panelWidth;
	
   

    /**
     *
     * Load all the images, create the panel and the frame of the game, create the menu.
     */
    public static void init()
    {
        GetImage.loadImages();
		panel = new DrawablePanel();
		mainFrame = new MainFrame(WIDTH,HEIGHT,listener,panel,menuBar);
        panelWidth = WIDTH;
        panelHeight = HEIGHT - (mainFrame.getJMenuBar().getHeight());
        panel.setSize(panelWidth, panelHeight);
		g.createMenuAnimation(WIDTH, HEIGHT);
        en = g.getEngine();
    }
    
    /**
     * Task use to go to the menu
     *
     */
    public static void menuTask()
    {
    	UserManager.stopAboutAndHelpTask();
        if(menuBar.getStartBool() == false)
        {
            panel.setMenu(true);
            UserManager.exitGame(mainFrame, listener, menuBar);
            UserManager.recordGame(menuBar, multiFrame);
            UserManager.tournamentGame(menuBar,multiFrame);
            UserManager.keysHelp(menuBar, panel, listener);
            UserManager.aboutHelp(menuBar, panel, listener);
            
            if(multiFrame != null &&  multiFrame.getMode() == false && multiFrame.getGoButton())
            {
                recordManager = new RecordManager(multiFrame.getNamesTab(), multiFrame.getColorsTab(), WIDTH, HEIGHT);
                multiFrame.setGoButton(false);
                multiFrame.dispose();
                Main.menuTimer.stop();
                panel.setMenu(false);
                Main.recordTimer.start();
            }
            else if(multiFrame != null &&  multiFrame.getMode() == true && multiFrame.getGoButton())
            {
                recordManager = new TournamentManager(multiFrame.getNamesTab(), multiFrame.getColorsTab(), WIDTH, HEIGHT);
                multiFrame.setGoButton(false);
                multiFrame.dispose();
                Main.menuTimer.stop();
                panel.setMenu(false);
                Main.recordTimer.start();
            }
            en.bounceBetweenAsteroids();
            
            d = new ArrayList<IDrawable>(0);
            
            d.addAll(en.getAsteroidList());
            panel.remplaceDrawables(d);
            
            panel.repaint();
        }
        else
        {
            numberOfAst = 5;
            Integer temp = new Integer(menuBar.getGameDifficulty());
            currentDifficulty = temp.intValue();
            switch(menuBar.getGameDifficulty())
            {
                case 0: g.createEasyGame(WIDTH,HEIGHT,numberOfAst);
                    break;
                case 1: g.createMediumGame(WIDTH,HEIGHT,numberOfAst);
                    break;
                case 2: g.createHardGame(WIDTH,HEIGHT,numberOfAst);
                    break;
                case 3: g.createBrutalGame(WIDTH,HEIGHT,numberOfAst);
                    break;
            }
            
            en = g.getEngine();
            ship = g.getSpaceShip();
            
            switch(menuBar.getShipSkin())
            {
                case 0: ship.setGreenSkin();
                    break;
                case 1: ship.setOrangeSkin();
                    break;
                case 2: ship.setBlueSkin();
                    break;
            }
            Main.menuTimer.stop();
            panel.setMenu(false);
            Main.gameTimer.start();
        }
    }// End menuTask()
    
    /**
     * Task use to play the solo game.
     *
     */
    public static void gameTask()
    {
        panel.addAllDrawable(en.getAsteroidList());
        
        ship.setLimitWidth(panelWidth);
        ship.setLimitHeight(panelHeight);
        shipObserver = new ShipObserver(ship);
        
        panel.addDrawable(ship);
        
        if(ship.getLife() > 0)
        {
            
            UserManager.checkAll(ship, listener, en, mainFrame, panel, menuBar);
            
            
            if(en.getAsteroidList().size() == 0)
            {
                m = en.getMissileList();
                b = en.getBonusList();
                exp = en.getExplosionList();
                numberOfAst++;
                
                switch(currentDifficulty)
                {
                    case 0: g.createEasyGame(WIDTH,HEIGHT,numberOfAst);
                        break;
                    case 1: g.createMediumGame(WIDTH,HEIGHT,numberOfAst);
                        break;
                    case 2: g.createHardGame(WIDTH,HEIGHT,numberOfAst);
                        break;
                    case 3: g.createBrutalGame(WIDTH,HEIGHT,numberOfAst);
                        break;
                }
				
                en = g.getEngine();
                en.setMissileList(m);
                en.setBonusList(b);
                en.setExplosionList(exp);
            }
            
            ship.refreshPosition();
            ship.refreshInvulnerability(Main.GAMEDELAY);
            en.refresh(ship);
            
            a = en.getAsteroidList();
            m = en.getMissileList();
            b = en.getBonusList();
            exp = en.getExplosionList();
            d = new ArrayList<IDrawable>(0);
            
            d.addAll(a);
            d.addAll(m);
            d.addAll(b);
            d.addAll(exp);
            d.add(ship);
            d.add(shipObserver);
            
            panel.remplaceDrawables(d);
            
            panel.repaint();
            
        }
        else
        {
            panel.removeDrawable(ship);
            if(panel.getGameOver() == false)
            	en.finalExplosion(ship);
            panel.setGameOver(true);
            UserManager.menuGame(mainFrame, listener, menuBar,panel);
            UserManager.exitGame(mainFrame, listener, menuBar);
            en.bounceBetweenAsteroids();
            en.refreshExplosion();
            
            d = new ArrayList<IDrawable>(0);
            
            d.addAll(en.getAsteroidList());
            d.addAll(en.getExplosionList());
            panel.remplaceDrawables(d);
            panel.repaint();
        }
        
        
    }// End gameTask()
    
    /**
     * Task use to play multiplayer game
     *
     */
    public static void multiPlayerTask()
    {
        panel.addAllDrawable(en.getAsteroidList());
        
        ship.setLimitWidth(panelWidth);
        ship.setLimitHeight(panelHeight);
        shipObserver = new ShipObserver(ship);
        
        panel.addDrawable(ship);
        if(ship.getLife() > 0)
        {
            
            UserManager.checkAll(ship, listener, en, mainFrame, panel, menuBar);
            
            
            if(en.getAsteroidList().size() == 0)
            {
                m = en.getMissileList();
                b = en.getBonusList();
                exp = en.getExplosionList();
                numberOfAst++;
                Integer temp = new Integer(menuBar.getGameDifficulty());
            	currentDifficulty = temp.intValue();
                switch(currentDifficulty)
                {
                    case 0: g.createEasyGame(WIDTH,HEIGHT,numberOfAst);
                        break;
                    case 1: g.createMediumGame(WIDTH,HEIGHT,numberOfAst);
                        break;
                    case 2: g.createHardGame(WIDTH,HEIGHT,numberOfAst);
                        break;
                    case 3: g.createBrutalGame(WIDTH,HEIGHT,numberOfAst);
                        break;
                }
				
                en = g.getEngine();
                en.setMissileList(m);
                en.setBonusList(b);
                en.setExplosionList(exp);
            }
            
            ship.refreshPosition();
            ship.refreshInvulnerability(Main.GAMEDELAY);
            en.refresh(ship);
            
            a = en.getAsteroidList();
            m = en.getMissileList();
            b = en.getBonusList();
            exp = en.getExplosionList();
            d = new ArrayList<IDrawable>(0);
            
            d.addAll(a);
            d.addAll(m);
            d.addAll(b);
            d.addAll(exp);
            d.add(ship);
            d.add(shipObserver);
            
            panel.remplaceDrawables(d);
            
            panel.repaint();
        }
        else
        {
            panel.removeDrawable(ship);
            if(panel.getGameOver() == false)
                en.finalExplosion(ship);
            if(recordManager != null && recordManager.getRemainingMatches()<=0 && panel.getGameOver() == false)
                recordManager.displayScoresFrame();
            if(tournamentManager != null && tournamentManager.getRemainingMatches()<=0 && panel.getGameOver() == false)
                tournamentManager.displayScoresFrame();
            panel.setGameOver(true);
            UserManager.menuGame(mainFrame, listener, menuBar,panel);
            UserManager.exitGame(mainFrame, listener, menuBar);
            en.bounceBetweenAsteroids();
            en.refreshExplosion();
            
            d = new ArrayList<IDrawable>(0);
            
            d.addAll(en.getAsteroidList());
            d.addAll(en.getExplosionList());
            panel.remplaceDrawables(d);
            panel.repaint();

            if(recordManager != null && recordManager.getRemainingMatches() > 0)
            {
                Main.delayMulti -= Main.GAMEDELAY;
                if(Main.delayMulti <= 0)
                {
                    Main.delayMulti = DELAY;
                    panel.setGameOver(false);
                    panel.repaint();
                    Main.recordTimer.start();
                    Main.multiTimer.stop();
                }
            }
            if(tournamentManager != null && tournamentManager.getRemainingMatches() > 0)
            {
                Main.delayMulti -= Main.GAMEDELAY;
                if(Main.delayMulti <= 0)
                {
                    Main.delayMulti = DELAY;
                    panel.setGameOver(false);
                    panel.repaint();
                    Main.tournamentTimer.start();
                    Main.multiTimer.stop();
                }
            }
            
        }
    } // End multiPlayerTask()
    
    /**
     * Task use to set the parameters of record game.
     *
     */
    public static void recordTask()
    {
        if(recordManager.getRemainingMatches() > 0)
        {
            numberOfAst = 5;
            Integer temp = new Integer(menuBar.getGameDifficulty());
            currentDifficulty = temp.intValue();
            switch(currentDifficulty)
            {
                case 0: g.createEasyGame(WIDTH,HEIGHT,numberOfAst);
                    break;
                case 1: g.createMediumGame(WIDTH,HEIGHT,numberOfAst);
                    break;
                case 2: g.createHardGame(WIDTH,HEIGHT,numberOfAst);
                    break;
                case 3: g.createBrutalGame(WIDTH,HEIGHT,numberOfAst);
                    break;
            }
            
            en = g.getEngine();
            ship = recordManager.getNextShip();
            
            Main.recordTimer.stop();
            Main.multiTimer.start();
        }
        else
        {
            Main.recordTimer.stop();
            Main.multiTimer.stop();
        }
    }// end recordTask()
    
    /**
     * Task use to set the parameters of tournament game.
     *
     */
    public static void tournamentTask()
    {
        if(tournamentManager.getRemainingMatches() > 0)
        {
            numberOfAst = 5;
            Integer temp = new Integer(menuBar.getGameDifficulty());
            currentDifficulty = temp.intValue();
            switch(currentDifficulty)
            {
                case 0: g.createEasyGame(WIDTH,HEIGHT,numberOfAst);
                    break;
                case 1: g.createMediumGame(WIDTH,HEIGHT,numberOfAst);
                    break;
                case 2: g.createHardGame(WIDTH,HEIGHT,numberOfAst);
                    break;
                case 3: g.createBrutalGame(WIDTH,HEIGHT,numberOfAst);
                    break;
            }
            
            en = g.getEngine();
            ship = tournamentManager.getNextShip();
            
            Main.tournamentTimer.stop();
            Main.multiTimer.start();
        }
        
    } // end tournamentTask()
    
    /**
     * Task use to set the parameters of the keys help.
     *
     */
    public static void helpTask()
    {
    	UserManager.keysHelp(menuBar,panel, listener);
    	UserManager.aboutHelp(menuBar, panel, listener);
    	UserManager.exitGame(mainFrame, listener, menuBar);
    	en.bounceBetweenAsteroids();
            
        d = new ArrayList<IDrawable>(0);
            
        d.addAll(en.getAsteroidList());
        panel.remplaceDrawables(d);
        panel.repaint();
    } // end helpTask()
    
    /**
     * Task use to set the parameters of the about help.
     *
     */
    public static void aboutTask()
    {
    	UserManager.keysHelp(menuBar, panel, listener);
    	UserManager.aboutHelp(menuBar,panel, listener);
    	UserManager.exitGame(mainFrame, listener, menuBar);
    	en.bounceBetweenAsteroids();
        
        d = new ArrayList<IDrawable>(0);
            
        d.addAll(en.getAsteroidList());
        panel.remplaceDrawables(d);
        panel.repaint();
    } // end aboutTask()
    
}
