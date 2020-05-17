/**
 * Class Geom.
 * Vectors and points operations.
 * @author Delplanque Julien - Giammello Alessandro
 * @version 1.0, 2013-02-20
 */

package be.ac.umons.asteroids.manager;

import java.awt.geom.Point2D;

import be.ac.umons.asteroids.mobile.*;
import be.ac.umons.asteroids.mobile.weapon.*;
import be.ac.umons.asteroids.mobile.bonus.*;
import be.ac.umons.asteroids.mobile.asteroid.*;
import be.ac.umons.asteroids.manager.*;
import be.ac.umons.asteroids.gui.*;
import be.ac.umons.asteroids.explosion.*;
import be.ac.umons.asteroids.exception.*;
import be.ac.umons.asteroids.*;

public class Geom
{
    /**
     * Don't let anyone instantiate this class.
     */
    private Geom() {}
    
    /**
     * Build a new vector from p1 to p2.
     *
     * @param p1 a Point2D.Double point
     * @param p2 another Point2D.Double point
     *
     * @return new vector from p1 to p2
     *
     */
    public static Point2D.Double vector(Point2D.Double p1, Point2D.Double p2)
    {
        Point2D.Double vector = new Point2D.Double(p2.x - p1.x, p2.y-p1.y);
        return vector;
    }
    
    /**
     * Build a new vector (result the sum of v1 and v2).
     *
     * @param v1 a Point2D.Double vector 
     * @param v2 another Point2D.Double vector
     *
     * @return new vector (sum of v1 and v2)
     *
     */    
    public static Point2D.Double add(Point2D.Double v1, Point2D.Double v2)
    {
        Point2D.Double vector = new Point2D.Double(v1.x+v2.x, v1.y+v2.y);
        return vector;
    }
    
    /**
     *
     * Calcul the Euclidean distance between two points p1 and p2.
     *
     * @param p1 a Point2D.Double point
     * @param p2 another Point2D.Double point
     *
     * @return the Euclidean distance
     *
     */
    public static double distance(Point2D.Double p1, Point2D.Double p2)
    {
        Point2D.Double vector = vector(p1, p2);
        double distance = Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2));
        return distance;
    }
    
    /**
     * Calcul the norm of the vector.
     *
     * @param v a Point2D.Double vector
     *
     * @return the norm of v 
     *
     */    
    public static double magnitude(Point2D.Double v)
    {
        double magnitude = Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.y, 2));
        return magnitude;
    }
    
    /**
     * Set the norm of the vector v (Default norm = 1).
     *
     * @param v a vector v
     *
     * @return a new normalized vector
     *
     */
    public static Point2D.Double normalize(Point2D.Double v)
    {
        double ratio = magnitude(v);
        return new Point2D.Double(v.x/ratio,v.y/ratio);
    }
    
    /**
     * Set the norm of the vector v (Default norm = 1).
     *
     * @param v a vector v
     * @param n a double argument
     *
     * @return a new normalized vector
     *
     */
    public static Point2D.Double normalize(Point2D.Double v, double n)
    {
         if (n==0)
            return new Point2D.Double();
        else
        {
            double ratio = magnitude(v)/n;
            return new Point2D.Double(v.x/ratio,v.y/ratio);
        }
    }
    
    
    /**
     * Calcul the angle between two vector v1 and v2 (degrees).
     *
     * @param v1  a Point2D.Double vector
     * @param v2  another Point2D.Double vector
     *
     * @return the angle
     *
     */
    public static double angle(Point2D.Double v1, Point2D.Double v2)
    {
        Point2D.Double normalizedV1 = normalize(v1);
        Point2D.Double normalizedV2 = normalize(v2);
        double angleV1 = Math.toDegrees(Math.acos(normalizedV1.x));
        double angleV2 = Math.toDegrees(Math.acos(normalizedV2.x));
        double angle = Math.max(angleV1, angleV2) - Math.min(angleV1, angleV2);
        
        return angle;
        
    }
    
    /**
     * Create a new vector that the result of the rotation of a vector by an angle
     * in the clockwise.
     *
     * @param v a Point2D.Double vector
     * @param angle a double argument (degrees)
     *
     * @return a new vector 
     *
     */
    public static Point2D.Double rotate(Point2D.Double v, double angle)
    {
        double radAngle = Math.toRadians(angle);
        double x = v.x*Math.cos(radAngle) - v.y*Math.sin(radAngle); // Angle rotation matrice (clockwise)
        double y = v.x*Math.sin(radAngle) + v.y*Math.cos(radAngle);
        Point2D.Double vector = new Point2D.Double(x, y);
        
        return vector;
    }
    
    /**
     * Return a new vector that is the opposite of the vector in the parameter.
     *
     * @param vec a new vector
     *
     */
    public static Point2D.Double opposite(Point2D.Double vec)
    {
        return new Point2D.Double(-vec.x, -vec.y);
    }   
}
