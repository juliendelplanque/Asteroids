/**
 * Interface IDrawable.
 * Give the methods to override if a class implements this interface.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-07-04
 */

package be.ac.umons.asteroids.gui;
 
import java.awt.Graphics;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;

public interface IDrawable
{
    
    /**
     * Returns if the component can be deleted or not.
     *
     * @return true if the component can be deleted, otherwise false
     *
     */
    public boolean isDeletable();
    
    /**
     * Set if the component can be deletable or not.
     *
     * @param true if it can be deletable, otherwise false
     *
     */
    public void setDeletable(boolean newValue);
    
    /**
     * Draw the component.
     *
     * @param g Graphics to draw with.
     *
     */
	public void draw(Graphics g);
}
