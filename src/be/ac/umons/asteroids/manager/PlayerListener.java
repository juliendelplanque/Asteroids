/**
 * Class PlayerListener.
 * Listener for the player.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-07-04
 */
 
package be.ac.umons.asteroids.manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public class PlayerListener implements KeyListener
{
	private boolean keyZ, keyS, keyQ, keyD, keyB, keyP, keyH, keyM, keySpace, keyX, key1, key2, key3;
	
    /**
     * Create a keyListener with all keys to false state 
     */
	public PlayerListener()
	{
		keyZ = false;//accelerate
		keyS = false;//decelerate
		keyD = false;//turn right
		keyQ = false;//turn left
		keyB = false;//fire with secondary weapon
		keyP = false;//pause
		keyH = false;//help
		keyM = false;//menu
		keySpace = false;//fire
        keyX = false;//quit game
		key1 = true;//select secondary weapon 1
		key2 = false;//select secondary weapon 2
		key3 = false;//select secondary weapon 3
	}
	
    /**
     * Return if the "Z" key is pressed.
     *
     * @return true if Z is pressed, false otherwise
     *
     */
    public boolean isKeyZPressed()
    {   
        return keyZ;
    }

    /**
     * Return if the "S" key is pressed.
     *
     * @return true if S is pressed, false otherwise
     *
     */
    public boolean isKeySPressed()
    {
        return keyS;
    }
    
    /**
     * Return if the "D" key is pressed.
     *
     * @return true if D is pressed, false otherwise
     *
     */
    public boolean isKeyDPressed()
    {
        return keyD;
    }

    /**
     * Return if the "Q" key is pressed.
     *
     * @return true if Q is pressed, false otherwise
     *
     */
    public boolean isKeyQPressed()
    {
        return keyQ;
    }
	
    /**
     * Return if the "B" key is typed.
     *
     * @return true if B is typed, false otherwise
     *
     */
	public boolean isKeyBTyped()
	{
		return keyB;
	}
	
    /**
     * Set the state of the key B.
     *
     * @param b the new state of the key
     *
     */
	public void setKeyB(boolean b)
	{
		keyB = b;
	}
	
    /**
     * Return if the "P" key is typed.
     *
     * @return true if P is typed, false otherwise
     *
     */
	public boolean isKeyPTyped()
	{
		return keyP;
	}

    /**
     * Set the state of the key P.
     *
     * @param b the new state of the key
     *
     */	
	public void setKeyP(boolean b)
	{
		keyP = b;
	}
	
    /**
     * Return if the "H" key is pressed.
     *
     * @return true if H is pressed, false otherwise
     *
     */	
	public boolean isKeyHPressed()
	{
		return keyH;
	}

    /**
     * Return if the "M" key is typed.
     *
     * @return true if M is typed, false otherwise
     *
     */	
	public boolean isKeyMTyped()
	{
		return keyM;
	}

    /**
     * Set the state of the key M.
     *
     * @param b the new state of the key
     *
     */		
	public void setKeyM(boolean b)
	{
		keyM = b;
	}

    /**
     * Return if the "space" key is pressed.
     *
     * @return true if "space" is pressed, false otherwise
     *
     */	
    public boolean isKeySpacePressed()
    {
        return keySpace;
    }

    /**
     * Return if the "X" key is pressed.
     *
     * @return true if X is pressed, false otherwise
     *
     */	    
    public boolean isKeyXPressed()
    {
        return keyX;
    }

    /**
     * Set the state of the key X.
     *
     * @param b the new state of the key
     *
     */	    
    public void setKeyX(boolean b)
	{
		keyX = b;
	}

    /**
     * Return if the "1" key is typed.
     *
     * @return true if 1 is typed, false otherwise
     *
     */		
	public boolean isKey1Typed()
	{
		return key1;
	}
    
    /**
     * Return if the "2" key is typed.
     *
     * @return true if 2 is typed, false otherwise
     *
     */		
	public boolean isKey2Typed()
	{
		return key2;
	}
    
    /**
     * Return if the "3" key is typed.
     *
     * @return true if 3 is typed, false otherwise
     *
     */	
	public boolean isKey3Typed()
	{
		return key3;
	}
	
	@Override
    public void keyPressed(KeyEvent e)
    {
        char key = e.getKeyChar();
        if (key == 'z' || key == 'Z') { keyZ = true; }
        if (key == 's' || key == 'S') { keyS = true; }
        if (key == 'q' || key == 'Q') { keyQ = true; }
        if (key == 'd' || key == 'D') { keyD = true; }
		if (key == 'h' || key == 'H') { keyH = true; }
        if (key == ' ') { keySpace = true; }
        if (key == 'x' || key == 'X') { keyX = true; }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        char key = e.getKeyChar();
        if (key == 'z' || key == 'Z') { keyZ = false; }
        if (key == 's' || key == 'S') { keyS = false; }
        if (key == 'q' || key == 'Q') { keyQ = false; }
        if (key == 'd' || key == 'D') { keyD = false; }
		if (key == 'm' || key == 'M') { keyM = false; }
        if (key == ' ') { keySpace= false; }
        if (key == 'x' || key == 'X') { keyX = false; }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
		char key = e.getKeyChar();
		if (key == 'p') { keyP = true; };
		if (key == 'm' || key == 'M') { keyM = true; }
		if (key == 'b' || key == 'B') { keyB = true; }
		if (key == '1')
		{
			key1 = true;
			key2 = false;
			key3 = false;
		}
		if (key == '2')
		{
			key2 = true;
			key1 = false;
			key3 = false;
		}
		if (key == '3')
		{
			key3 = true;
			key1 = false;
			key2 = false;
		}
    }
    
    /**
     * Reset all key state to false.
     *
     */
    public void reset()
    {
    	keyZ = false;
        keyX = false;
		keyS = false;
		keyD = false;
		keyQ = false;
		keyB = false;
		keyP = false;
		keyH = false;
		keyM = false;
		keySpace = false;
        keyX = false;
		key1 = true;
		key2 = false;
		key3 = false;
    }
	
}
