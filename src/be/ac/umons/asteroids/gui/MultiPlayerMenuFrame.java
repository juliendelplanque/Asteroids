/**
 * Class MultiPlayerMenuFrame.
 * Create the multi player menu frame of the game and configure it.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-08-04
 */

package be.ac.umons.asteroids.gui;

import javax.swing.*;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.* ;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;

public class MultiPlayerMenuFrame extends JFrame
{
	private static final int WIDTH = 300;
	private static final int HEIGHT = 85;
	private int playerNumber = 2;
	private static Integer[] comboTabRecord = {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
	private static Integer[] comboTabTournament = {2,4,8,16};
	private static Integer[] comboTab;
	private static final String[] colorTab = {"Green ship", "Orange ship", "Blue ship"};
	private Box[] boxTab = new Box[16];
	private JTextField[] fieldTab = new JTextField[16];
	private String[] nameTab = new String[16];
	private String[] cTab  = {"Green ship","Green ship","Green ship","Green ship","Green ship","Green ship","Green ship","Green ship","Green ship","Green ship","Green ship","Green ship","Green ship","Green ship","Green ship","Green ship"};
	private Box mainBox;
	private Box verticalBox;
	private Box playersNumberBox;
	private boolean goButton = false;
	private boolean cancelB = false;
	private boolean mode; //false = record - true = tournament
	private ItemEvent temp;
    private int heightComponent;
    
    /**
     * Create the multiplayer setting frame.
     *
     * @param s the frame's string
     * @param _mode true for the combo mode, false for tournament mode
     *
     */
	public MultiPlayerMenuFrame(String s, boolean _mode)
	{
		super(s);
		mode = _mode;
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(this.getParent());
		playersNumberBox = Box.createHorizontalBox();
		if(mode == false)
			comboTab = comboTabRecord;
		else if(mode == true)
			comboTab = comboTabTournament;
			
		JLabel players = new JLabel("Players :");
		JComboBox numberOfPlayers = new JComboBox(comboTab);
		numberOfPlayers.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
                clearFrame();
				playerNumber = (Integer) e.getItem();
				setVisibleElements();
                setSize(new Dimension(WIDTH, HEIGHT+(playerNumber*heightComponent)));
			}
		});
		
		playersNumberBox.add(players);
        playersNumberBox.add(Box.createHorizontalStrut(5));
        playersNumberBox.add(numberOfPlayers);
		
		verticalBox = Box.createVerticalBox();
		
		for(int i = 1; i <= 16; i++)
		{
			final int index = i;
			Box box = Box.createHorizontalBox();
			JTextField textField = new JTextField("Player "+i);
			JComboBox colorOfPlayer = new JComboBox(colorTab);
			colorOfPlayer.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					cTab[index-1] = null;
					cTab[index-1] = (String) e.getItem();
				}
			});
			box.add(textField);
			box.add(colorOfPlayer);
			box.setVisible(false);
			boxTab[i-1] = box;
			fieldTab[i-1] = textField;
			verticalBox.add(box);
		}
		
		Box buttonBox = Box.createHorizontalBox();
		JButton go = new JButton("Let's go!");
		go.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				goButton = true;
				for(int i = 0; i < playerNumber; i++)
				{
					nameTab[i] = fieldTab[i].getText();
				}
			}
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				cancelB = true;
			}
		});
		
		buttonBox.add(cancel);
		buttonBox.add(Box.createGlue());
		buttonBox.add(go);
		
		mainBox = Box.createVerticalBox();
		mainBox.add(playersNumberBox);
		mainBox.add(verticalBox);
		
		add(mainBox,BorderLayout.NORTH);
		add(buttonBox,BorderLayout.SOUTH);
		setVisibleElements();
		setVisible(true);
        heightComponent = fieldTab[0].getSize().height;
        setSize(new Dimension(WIDTH, HEIGHT+(playerNumber*heightComponent)));
	}
	
    /**
     * Erase all the component on the frame.
     *
     */
	private void clearFrame()
	{
		for(int i = 0; i < playerNumber; i++)
		{
			boxTab[i].setVisible(false);
		}
	}
	
    /**
     * Make frame's component visible.
     *
     */
	private void setVisibleElements()
	{
		for(int i = 0; i < playerNumber; i++)
		{
			boxTab[i].setVisible(true);
			
		}
        
        setLocationRelativeTo(this.getParent());
	}
	
    /**
     * Return an array with all the players name.
     *
     * @return an array with the players name
     *
     */
	public String[] getNamesTab()
	{
		String[] tab = new String[playerNumber];
		for(int i = 0; i < playerNumber; i++)
		{
			tab[i] = nameTab[i];
		}
		return tab;
	}
	
    /**
     * Return an array that contains all the SpaceShip colors use.
     *
     * @return an array that contains all the SpaceShip colors choose on the current multiplayer game
     *
     */
	public String[] getColorsTab()
	{
		String[] tab = new String[playerNumber];
		for(int i = 0; i < playerNumber; i++)
		{
			tab[i] = cTab[i];
		}
		return tab;
	}
	
    /**
     * Return the state of the "go" button.
     *
     * @return true if the "go" button is paint, false otherwise 
     *
     */
	public boolean getGoButton()
	{
		return goButton;
	}

    /**
     * Set the "go" button state.
     *
     * @param true if the "go" button have to painted, false otherwise 
     *
     */	
	public void setGoButton(boolean b)
	{
		goButton = b;
	}
    
    /**
     * Return the state of the "go" button.
     *
     * @return true if the "go" button is paint, false otherwise
     *
     */
	public boolean getCancelButton()
	{
		return cancelB;
	}	
	
	/**
     * Set the mode of the game (record or tournament).
     *
     * @param b a boolean
     *
     */
	public void setMode(boolean b)
	{
		mode = b;
	}
	
	/**
     * Return the mode of the game (record or tournament).
     *
     * @return true if tournament mode return false if record mode
     *
     */
	public boolean getMode()
	{
		return mode;
	}
}
