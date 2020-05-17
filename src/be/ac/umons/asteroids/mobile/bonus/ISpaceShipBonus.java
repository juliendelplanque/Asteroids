/**
 * Interface ISpaceShipBonus.
 * All the bonus that have an effect on the SpaceShip implements this interface.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-07-05
 */

package be.ac.umons.asteroids.mobile.bonus;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public interface ISpaceShipBonus
{
	/**
	 * Launch the effect of the bonus on the Spaceship.
	 *
	 * @param s the current SpaceShip
	 *
	 */
	public void effect(SpaceShip s);
}
