/**
 * Class Main.
 * Main class of the game.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-06-04
 */

package be.ac.umons.asteroids;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;

public class Main
{
    public static Timer menuTimer, helpTimer, aboutTimer, gameTimer, pauseTimer, multiTimer, recordTimer, tournamentTimer;
    public static final int MENUDELAY = 20;
    public static final int GAMEDELAY = 14;
	public static int delayMulti = MainTask.DELAY;
    
    public static void main(String[] args)
    {

        MainTask.init();
        
        // Menu Action Listener
        ActionListener menuTaskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MainTask.menuTask();
            }
        }; // end menu action listener
        
        // Help Action Listener
        ActionListener helpTaskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MainTask.helpTask();
            }
        }; // en help action listener
        
        // About Action Listener
        ActionListener aboutTaskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MainTask.aboutTask();
            }
        }; // en about action listener
        
        // Game Action Listener
        ActionListener gameTaskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                MainTask.gameTask();
                
            }
        }; // End game action listener
        
        //pause action listener
        ActionListener pauseTaskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                UserManager.checkAll(MainTask.ship, MainTask.listener, MainTask.en, MainTask.mainFrame, MainTask.panel, MainTask.menuBar);
            }
        };
		
		//MultiPlayer actionListener
		ActionListener multiTaskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                MainTask.multiPlayerTask();
			}
		};
		
		// Record Action Listener
        ActionListener recordTaskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                MainTask.recordTask();
            }
				
		};
		
		// Tournament Action Listener
        ActionListener tournamentTaskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                MainTask.tournamentTask();
            }
		};
        
        
        // Menu Timer
        menuTimer = new Timer(MENUDELAY, menuTaskPerformer);
        menuTimer.start();
        
        // Help Timer
        helpTimer = new Timer(MENUDELAY, helpTaskPerformer);
        
        // About Timer
        
        aboutTimer = new Timer(MENUDELAY, aboutTaskPerformer);
        
        //Game Timer
        gameTimer = new Timer(GAMEDELAY, gameTaskPerformer);
        
        // Pause Timer
        pauseTimer = new Timer(GAMEDELAY, pauseTaskPerformer);
		
		// Multi Timer
		multiTimer = new Timer(GAMEDELAY, multiTaskPerformer);

		// RecordMod Timer
		recordTimer = new Timer(GAMEDELAY, recordTaskPerformer);
		
		// TournamentMod Timer
		tournamentTimer = new Timer(GAMEDELAY, tournamentTaskPerformer);
        
        UserManager.setMenuTimer(menuTimer);
        UserManager.setHelpTimer(helpTimer);
        UserManager.setAboutTimer(aboutTimer);
        UserManager.setGameTimer(gameTimer);
        UserManager.setPauseTimer(pauseTimer);
        UserManager.setMultiTimer(multiTimer);
		UserManager.setRecordTimer(recordTimer);
    }
}
