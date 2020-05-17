/**
 * Class GetImage.
 * Methods for getting specifics images.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-12-04
 */

package be.ac.umons.asteroids.manager;

import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.IOException;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;


public class GetImage
{
	private static Image[] imageTab = new Image[39];
	private static ImageIcon icon;
    private static final String imagePath = "/be/ac/umons/asteroids/resources/img";

    /**
     * Don't let anyone instantiate this class.
     *
     */
	private GetImage(){}
	
	/**
	 * Get the image that have the name given in parameter.
	 *
	 * @param url a URL
	 *
	 * @return the image
	 *
	 */
    public static Image getImage(URL url)
	{
        BufferedImage image = null;
        
        try
        {
            image = ImageIO.read(url);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
		return image;
	}
    
    /**
     * Load all images.
     *
     */
	public static void loadImages()
	{
		URL url = GetImage.class.getResource(imagePath+"/spaceships/spc-1.png");
		imageTab[0] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/spaceships/spc1.png");
		imageTab[1] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/spaceships/spc-2.png");
		imageTab[2] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/spaceships/spc2.png");
		imageTab[3] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/spaceships/spc-3.png");
		imageTab[4] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/spaceships/spc3.png");
		imageTab[5] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/asteroid/ast.png");
		imageTab[6] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/bonus/bon-1.png");
		imageTab[7] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/bonus/bon-2.png");
		imageTab[8] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/bonus/bon-3.png");
		imageTab[9] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/weapons/clay.png");
		imageTab[10] = getImage(url);
        for(int x = 1; x < 17; x++)
        {
            url = GetImage.class.getResource(imagePath+"/explosion/exp-"+x+".png");
            imageTab[x+10] = getImage(url);
        }
		imageTab[26] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/other/background.png");
		imageTab[27] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/other/pause.png");
		imageTab[28] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/other/menu.png");
		imageTab[29] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/other/game_over.png");
		imageTab[30] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/asteroid/ast.png");
		icon = new ImageIcon(url);
		url = GetImage.class.getResource(imagePath+"/ammo/ammo-1.png");
		imageTab[31] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/ammo/ammo-2.png");
		imageTab[32] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/ammo/ammo-3.png");
		imageTab[33] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/other/keys.png");
		imageTab[34] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/bonus/bon-4.png");
		imageTab[35] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/bonus/bon-5.png");
		imageTab[36] = getImage(url);
		url = GetImage.class.getResource(imagePath+"/bonus/bon-6.png");
		imageTab[37] = getImage(url);
        url = GetImage.class.getResource(imagePath+"/other/about.png");
		imageTab[38] = getImage(url);
	}
	
    /**
     * Return the Blue Ship images.
     * 
     * @return an Array that contains Image of the Blue Ship
     *
     */
	public static Image[] getBlueShipImage()
	{
		Image[] tab = new Image[2];
        
		tab[0] = imageTab[0];
		tab[1] = imageTab[1];
        
		return tab;
	}
	
    /**
     * Return the green Ship images.
     * 
     * @return an Array that contains Image of the green Ship
     *
     */
	public static Image[] getGreenShipImage()
	{
		Image[] tab = new Image[2];
        
		tab[0] = imageTab[4];
		tab[1] = imageTab[5];
        
		return tab;
	}
	
    /**
     * Return the orange Ship images.
     * 
     * @return an Array that contains Image of the orange Ship
     *
     */
	public static Image[] getOrangeShipImage()
	{
		Image[] tab = new Image[2];
        
		tab[0] = imageTab[2];
		tab[1] = imageTab[3];
        
		return tab;
	}
	
	/**
	 * Get the image of an Asteroid.
	 *
	 * @return the image
	 *
	 */	
	public static Image getAsteroidImage()
	{
		return imageTab[6];
	}
	
	/**
	 * Get the image of a DivideBonus.
	 *
	 * @return the image
	 *
	 */	
	public static Image getDivideBonusImage()
	{
		return imageTab[7];
	}
	
	/**
	 * Get the image of a LifeBonus.
	 *
	 * @return the image
	 *
	 */	
	public static Image getLifeBonusImage()
	{
		return imageTab[8];
	}
	
	/**
	 * Get the image of a InvulnerabilityBonus.
	 *
	 * @return the image
	 *
	 */
	public static Image getInvulnerabilityBonusImage()
	{
		return imageTab[9];
	}
	
	/**
	 * Get the image of a Claymore.
	 *
	 * @return the image
	 *
	 */	
	public static Image getClaymoreImage()
	{
		return imageTab[10];
	}
	
	/**
	 * Get the images for an explosion an return a tab with this images.
	 *
	 * @return tab the tab of images
	 *
	 */	
	public static Image[] getExplosionImages()
	{
		Image[] tab = new Image[16];
		int x = 0;
        for(int i = 11; i < 27; i++)
        {
            tab[x] = imageTab[i];
			x++;
        }
		return tab;
	}
	
	/**
	 * Get the image of the background.
	 *
	 * @return the background image
	 *
	 */	
	public static Image getBackgroundImage()
	{
		return imageTab[27];
	}
	
    /**
	 * Get the image of the pause.
	 *
	 * @return the pause image
	 *
	 */	
	public static Image getPauseImage()
	{
		return imageTab[28];
	}
	
    /**
	 * Get the image of the menu.
	 *
	 * @return the menu image
	 *
	 */
	public static Image getMenuImage()
	{
		return imageTab[29];
	}
	
    /**
	 * Get the Game Over image.
	 *
	 * @return the Game Over image
	 *
	 */
	public static Image getGameOverImage()
	{
		return imageTab[30];
	}
	
    /**
	 * Get the image of the popup frame.
	 *
	 * @return the popum image
	 *
	 */
    public static ImageIcon getIconMessage()
    {
		return icon;
    }
    
    /**
	 * Get the image of a Pulse ammo.
	 *
	 * @return the image
	 *
	 */	
    public static Image getPulseAmmoImage()
    {
    	return imageTab[33];
    }
    
    /**
	 * Get the image of a Homing ammo.
	 *
	 * @return the image
	 *
	 */	
    public static Image getHomingAmmoImage()
    {
    	return imageTab[31];
    }
    
    /**
	 * Get the image of a Claymore ammo.
	 *
	 * @return the image
	 *
	 */	
    public static Image getClaymoreAmmoImage()
    {
    	return imageTab[32];
    }
    
    /**
	 * Get the keys image.
	 *
	 * @return the image
	 *
	 */	
	 public static Image getKeysImage()
	 {
	 	return imageTab[34];
	 }
	 
	 /**
	 * Get Get the image of a MultiFireBonus.
	 *
	 * @return the image
	 *
	 */	
	 public static Image getMultiFireBonusImage()
	 {
	 	return imageTab[36];
	 }
	 
	 /**
	 * Get Get the image of a FireRateBonus.
	 *
	 * @return the image
	 *
	 */	
	 public static Image getFireRateBonusImage()
	 {
	 	return imageTab[37];
	 }
	 
	 /**
	 * Get Get the image of a MissileLifeBonus.
	 *
	 * @return the image
	 *
	 */	
	 public static Image getMissileLifeBonusImage()
	 {
	 	return imageTab[35];
	 }
    
    /**
	 * Get Get the image of help.
	 *
	 * @return the image
	 *
	 */	
	 public static Image getAboutImage()
	 {
	 	return imageTab[38];
	 }
}
