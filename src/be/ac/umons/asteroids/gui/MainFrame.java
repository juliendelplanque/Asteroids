/**
 * Class MainFrame.
 * Create the main frame of the game and configure it.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-08-04
 */

package be.ac.umons.asteroids.gui;

import javax.swing.*;
import java.awt.BorderLayout;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;


public class MainFrame extends JFrame
{
	/**
	 * Build a MainFrame object and configure it.
	 *
	 * @param width the width of the main frame
     * @param height the height of the main frame
     * @param panel the main panel 
     * @param menu the menu bar
     *
	 */
	public MainFrame(int width, int height, PlayerListener listener, DrawablePanel panel, MenuBar menu)
	{
		super("Asteroid");
		setSize(width, height+menu.getHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
        addKeyListener(listener);
        setJMenuBar(menu);
		add(panel);
		setVisible(true);
		setLocationRelativeTo(this.getParent());
	}
}
