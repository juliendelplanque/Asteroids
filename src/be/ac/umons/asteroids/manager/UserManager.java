/**
 * Class UserManager.
 * Manage the player actions.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-04-30
 */

package be.ac.umons.asteroids.manager;

import javax.swing.*;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public class UserManager
{
    private static int menuAnswer, quitAnswer;
    private static long initTime, finalTime;
	private static int repeatBKey = 0;
    private static int countIncrease = 0, countDecrease = 0;
    private static Timer menuTimer, helpTimer, aboutTimer, gameTimer, pauseTimer, multiTimer, recordTimer, tournamentTimer;
    private static boolean pauseGameFlag = false;
    private static boolean pauseMultiFlag = false;
    
    /**
     * Activate all listeners.
     *
     * @param ship the SpaceShip of the game
     * @param listener the listener of the game
     * @param en the engine generated for the game
     * @param mainFrame the main frame of the game
     * @param panel the main panel of the game
     * @param menuBar the menu bar
     *
     */
	public static void checkAll(SpaceShip ship, PlayerListener listener,  Engine en, MainFrame mainFrame, DrawablePanel panel, MenuBar menuBar)
	{
		UserManager.exitGame(mainFrame, listener, menuBar);
		UserManager.gamePlayer(ship, listener, en, mainFrame, panel, menuBar);
		UserManager.pauseGame(mainFrame, listener, menuBar, panel);		
		UserManager.menuGame(mainFrame, listener, menuBar, panel);
	}
	
    /**
     * Check if the player want quit the game.
     *
     * @param mainFrame the main frame of the game
     * @param listener the listener of the game
     * @param menuBar the menu bar 
     *
     */
    public static void exitGame(MainFrame mainFrame, PlayerListener listener, MenuBar menuBar)
    {
        if(menuBar.getExitBool() || listener.isKeyXPressed())
        {
            quitAnswer = JOptionPane.showConfirmDialog(mainFrame, "Do you really want to quit ?", "QUIT GAME ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, GetImage.getIconMessage());
            
            if(quitAnswer == JOptionPane.YES_OPTION)
                System.exit(0);
            
            menuBar.setAllFalse();
			listener.setKeyX(false);
        }
    }
    
    /**
     * Check if the player wants help.
     *
     * @param menuBar the menu bar
     * @param panel the main panel of the game
     * @param listener the listener of the game
     *
     */
    public static void keysHelp(MenuBar menuBar,DrawablePanel panel, PlayerListener listener)
    {
    	if(menuBar.getKeysBool())
        {
            menuBar.setAllFalse();
        	panel.clean();
        	panel.setKeys(true);
        	panel.repaint();
        	if(aboutTimer.isRunning() == true)
            {
                aboutTimer.stop();
                panel.setAbout(false);
            }
        	if(menuTimer.isRunning() == true)
        		menuTimer.stop();
        	helpTimer.start();
        }
        if(menuBar.getMenuBool() || listener.isKeyMTyped())
        {
        	menuBar.setAllFalse();
        	listener.setKeyM(false);
        	panel.clean();
        	panel.setMenu(true);
        	panel.repaint();
        	helpTimer.stop();
        	menuTimer.start();
        }
    }
    
    /**
     * Check if the player wants informations.
     *
     * @param menuBar the menu bar
     * @param panel the main panel of the game
     * @param listener the listener of the game
     *
     */
    public static void aboutHelp(MenuBar menuBar,DrawablePanel panel, PlayerListener listener)
    {
    	if(menuBar.getAboutBool())
        {
            menuBar.setAllFalse();
        	panel.clean();
        	panel.setAbout(true);
        	panel.repaint();
        	if(helpTimer.isRunning())
            {
        		helpTimer.stop();
                panel.setKeys(false);
            }
        	if(menuTimer.isRunning())
        		menuTimer.stop();
        	aboutTimer.start();
        }
        if(menuBar.getMenuBool() || listener.isKeyMTyped())
        {
        	menuBar.setAllFalse();
        	listener.setKeyM(false);
        	panel.clean();
        	panel.setMenu(true);
        	panel.repaint();
        	aboutTimer.stop();
        	menuTimer.start();
        }
    }
	
    /**
     * Check if the player wants to pause the game.
     *
     * @param mainFrame the main frame of the game
     * @param listener the listener of the game
     * @param menuBar the menu bar
     * @param panel the main panel of the game
     *
     */
	private static void pauseGame(MainFrame mainFrame, PlayerListener listener, MenuBar menuBar,DrawablePanel panel)
	{
        
		if(listener.isKeyPTyped() || menuBar.getPauseBool())
        {
            listener.setKeyP(false);
            panel.setPause(true);
            menuBar.setAllFalse();
            if(gameTimer.isRunning())
            {
                gameTimer.stop();
                pauseTimer.start();
                pauseGameFlag = true;
            }
            else if(multiTimer.isRunning())
            {
                multiTimer.stop();
                pauseTimer.start();
                pauseMultiFlag = true;
            }
            else
            {  
                pauseTimer.stop();
                panel.setPause(false);
                if(pauseGameFlag == true)
                {
                    gameTimer.start();
                    pauseGameFlag = false;
                }
                else //(pauseMultiFlag == true)
                {
                    multiTimer.start();
                    pauseMultiFlag = false;
                }
            }
        }
	}
	
    /**
     * Check if the player wants to go to the menu.
     *
     * @param mainFrame the main frame of the game
     * @param listener the listener of the game
     * @param menuBar the menu bar
     * @param panel the main panel of the game
     *
     */
	public static void menuGame(MainFrame mainFrame, PlayerListener listener, MenuBar menuBar,DrawablePanel panel)
	{
		if(listener.isKeyMTyped() || menuBar.getMenuBool())
        {
            menuAnswer = JOptionPane.showConfirmDialog(mainFrame, "Do you really want to return to the menu ?", "RETURN MENU ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, GetImage.getIconMessage());
            
            if(menuAnswer == JOptionPane.YES_OPTION)
            {
                if(gameTimer.isRunning())
                    gameTimer.stop();
				if(multiTimer.isRunning())
					multiTimer.stop();
                if(aboutTimer.isRunning())
                    aboutTimer.stop();
                if(helpTimer.isRunning())
                    helpTimer.stop();
                panel.clearDrawables();
                panel.setGameOver(false);
                panel.setKeys(false);
                menuTimer.restart();
                listener.reset();
            }
            
            menuBar.setAllFalse();
            listener.setKeyM(false);
            
        }
	}
	
    /**
     * Check if the player choose record game.
     *
     * @param menuBar the menu bar
     * @param multiFrame the multiplayer frame
     *
     */
	public static void recordGame(MenuBar menuBar, MultiPlayerMenuFrame multiFrame)
	{
		if(menuBar.getRecordBool() && multiFrame == null)
		{
			multiFrame = new MultiPlayerMenuFrame("Record",false);
			MainTask.multiFrame = multiFrame;
			menuBar.setAllFalse();
		}
		else if((multiFrame != null && multiFrame.getCancelButton()))
		{
			MainTask.multiFrame = null;
		}
		else if(multiFrame != null && multiFrame.isShowing()== false)
		{
			MainTask.multiFrame = null;
		}
	}
	
    /**
     * Check if the player choose tournament game.
     *
     * @param menuBar the menu bar
     * @param multiFrame the multiplayer frame
     *
     */
	public static void tournamentGame(MenuBar menuBar, MultiPlayerMenuFrame multiFrame)
	{
		if(menuBar.getTournamentBool() && multiFrame == null)
		{
			multiFrame = new MultiPlayerMenuFrame("Tournament",true);
			MainTask.multiFrame = multiFrame;
			menuBar.setAllFalse();
		}
		else if((multiFrame != null && multiFrame.getCancelButton()))
		{
			MainTask.multiFrame = null;
		}
		else if(multiFrame != null && multiFrame.isShowing()== false)
		{
			MainTask.multiFrame = null;
		}
	}
	

    /**
     * Key events manager during game.
     *
     * @param ship the SpaceShip of the game
     * @param listener the listener of the game
     * @param en the engine generated for the game
     * @param mainFrame the main frame of the game
     * @param panel the main panel of the game
     * @param menuBar the menu bar 
     *
     */
    private static void gamePlayer(SpaceShip ship, PlayerListener listener,  Engine en, MainFrame mainFrame, DrawablePanel panel, MenuBar menuBar)
    {
        if(listener.isKeyZPressed())
        {
            ship.setAcceleration(100);
            countDecrease = 0;
            countIncrease += 1;
            if(countIncrease == 1)
                initTime = System.nanoTime();
            
            finalTime = System.nanoTime() - initTime;
            
            if (ship.getSpeed() < 10)
                ship.increaseSpeed(finalTime);
        }
        
        if(listener.isKeySPressed() || !listener.isKeyZPressed())
        {
            ship.setAcceleration(-30);
            countDecrease += 1;
            if(countDecrease == 1)
                initTime = System.nanoTime();
            
            finalTime = System.nanoTime() - initTime;
            
            countIncrease = 0;
            ship.decreaseSpeed(finalTime);
        }
        
        if(listener.isKeyQPressed())
        {
            ship.increaseAngle();
        }
        
        if(listener.isKeyDPressed())
        {
            ship.decreaseAngle();
        }
        
        if(listener.isKeySpacePressed())
        {
            Missile[] tab = ship.fire(Main.GAMEDELAY);
            if(tab != null)
            {
                for(int i = 0; i < tab.length; i++)
                {
                    Missile mis = tab[i];
                    panel.addDrawable(mis);
                    en.add(mis);
                }
            }
        }
        
        if(listener.isKeyBTyped())
        {
        	listener.setKeyB(false);
            try
            {
                    Missile mis = null;
                    
                    if(listener.isKey1Typed())
                        mis = ship.pulseWeapon();
                    else if(listener.isKey2Typed())
                        mis = ship.homingWeapon(en);
                    else if(listener.isKey3Typed())
                        mis = ship.dropClaymore();
                    
                    if(mis != null)
                    {
                        panel.addDrawable(mis);
                        en.add(mis);
                    }
                    
            }catch(NoMunitionException noMun){}
        }
        
        if(listener.isKey1Typed())
            ship.setPulse();
        
        if(listener.isKey2Typed())
            ship.setHoming();
        
        if(listener.isKey3Typed())
            ship.setClaymore();
    }
    
    /**
     * Set the menu timer.
     *
     * @param timer the menu timer
     *
     */
    public static void setMenuTimer(Timer timer)
    {
        menuTimer = timer;
    }
    
    /**
     * Set the help timer (use to display help).
     *
     * @param timer the help timer
     *
     */
    public static void setHelpTimer(Timer timer)
    {
        helpTimer = timer;
    }
    
    /**
     * Set the game timer.
     *
     * @param timer the game timer
     *
     */
    public static void setGameTimer(Timer timer)
    {
        gameTimer = timer;
    }
    
    
    /**
     * Set the pause timer.
     *
     * @param timer the pause timer
     *
     */
    public static void setPauseTimer(Timer timer)
    {
        pauseTimer = timer;
    }
	
    /**
     * Set the multi timer.
     *
     * @param timer the multi timer
     *
     */
	public static void setMultiTimer(Timer timer)
	{
		multiTimer = timer;
	}
	
    /**
     * Set the record timer (Record mode).
     *
     * @param timer the record timer
     *
     */
	public static void setRecordTimer(Timer timer)
	{
		recordTimer = timer;
	}
	
	/**
     * Set the about timer (Record mode).
     *
     * @param timer the record timer
     *
     */
	public static void setAboutTimer(Timer timer)
	{
		aboutTimer = timer;
	}
	
	public static void stopAboutAndHelpTask()
	{
        if(aboutTimer.isRunning())
            aboutTimer.stop();
        if(helpTimer.isRunning())
            helpTimer.stop();
	}
}
