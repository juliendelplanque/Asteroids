/**
 * Class DrawablePanel.
 * Create the main panel.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-06-04
 */

package be.ac.umons.asteroids.gui;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.awt.Font;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;

public class DrawablePanel extends JPanel
{
    private ArrayList<IDrawable> drawablesList = new ArrayList<IDrawable>(0);
	private Image image;
	private Image pauseImage, menuImage, gameOverImage, keysImage, aboutImage;
	private boolean pause, menu, gameOver, keys, about;
    
    /**
     * Create a Panel with a specified width and height.
     *
     * @param width the width of the panel
     * @param height the height of the panel
     *
     */
    public DrawablePanel()
    {
        setFont(new Font("arial", Font.BOLD, 12));
		image = GetImage.getBackgroundImage();
		pauseImage = GetImage.getPauseImage();
		menuImage = GetImage.getMenuImage();
		gameOverImage = GetImage.getGameOverImage();
		keysImage = GetImage.getKeysImage();
		aboutImage = GetImage.getAboutImage();
		pause = false;
		menu = false;
		gameOver = false;
		keys = false;
		about = false;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        
        // High quality rendering
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);    
        g2.setRenderingHints(rh);
        
        super.paintComponent(g2);
		g2.drawImage(image,0,0,null);
		
		for(int i = 0; i < drawablesList.size(); i++)
		{
            if(drawablesList.get(i).isDeletable())
                removeDrawable(drawablesList.get(i));
            
            if(i < drawablesList.size())
                drawablesList.get(i).draw(g2);
		}
		if(pause == true)
			g.drawImage(pauseImage,0,-40,null); 
			
		if(menu == true)
			g.drawImage(menuImage,0,-40,null);
			
        if(gameOver == true)
			g.drawImage(gameOverImage,0,-40,null);

		if(keys == true)
			g.drawImage(keysImage,0,0,null);
		
		if(about == true)
			g.drawImage(aboutImage,0,0,null);
    }
    
    /**
     * Add an IDrawable element in the list of the elements to draw in the panel.
     *
     * @param d the IDrawable to add in the list
     *
     */
    public void addDrawable(IDrawable d)
	{
		drawablesList.add(d);
    }
	
    /**
     * Add all element contains by the list of drawable in another list.
     *
     * @param d the list to add all elements
     *
     */
	public void addAllDrawable(ArrayList<Asteroid> d)
	{
		drawablesList.addAll(d);
	}

    /**
     * Remove all the elements contains by the list of drawable elements.
     *
     * @param d the list that contains the elements to remove
     *
     */
    public void removeDrawable(IDrawable d)
	{
        drawablesList.remove(d);
    }
    
    /**
     * Clear the list of drawable elements
     *
     */
    public void clearDrawables()
	{
        drawablesList.clear();
    }
	
    /**
     * Replace all the elements in the list of drawable element.
     *
     * @param a the list that contains the news elements
     *
     */
	public void remplaceDrawables(ArrayList<IDrawable> a)
	{
		drawablesList = a;
	}
	
    /**
     * Set the pause state.
     *
     * @param b true if the game is in pause, otherwise false
     *
     */
	public void setPause(boolean b)
	{
		pause = b;
	}
	
    /**
     * Set the menu state.
     *
     * @param b true if the menu is active, false otherwise
     *
     */
	public void setMenu(boolean b)
	{
		menu = b;
	}
	
    /**
     * Set the game over state.
     *
     * @param b true if the game is over
     *
     */
	public void setGameOver(boolean b)
	{
		gameOver = b;
	}
	
    /**
     * Set the keys help.
     *
     * @param b true if the keys help is active, false otherwise
     *
     */
	public void setKeys(boolean b)
	{
		keys = b;
	}
	
    /**
     * Get the game over state.
     *
     * @return the game over state
     *
     */
	public boolean getGameOver()
	{
		return gameOver ;
	}
	
	/**
     * Set the about state.
     *
     * @param b true if the keys help is active, false otherwise
     *
     */
	public void setAbout(boolean b)
	{
		about = b;
	}
	
	/**
     * Clean the panel.
     *
     */
	public void clean()
	{
		pause = false;
		menu = false;
		gameOver = false;
		keys = false;
		about = false;
	}
}
