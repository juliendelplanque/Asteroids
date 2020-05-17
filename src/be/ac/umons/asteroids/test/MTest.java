package be.ac.umons.asteroids.test;

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

public class MTest
{
    public static final double delta = 0.01;
    public static void main(String[] args)
    {
        missileTest1();
        missileTest2();
        missileTest3();
        missileTest4();
        missileTest5();
        missileTest6();
        missileTest7();
        missileTest8();
    }

    public static void missileTest1() // Test with speed ship 0 - Simple missile
    {
        int x = 0;
        int count = 0;
        SpaceShip ship = new SpaceShip(new Point2D.Double(0, 0), new Point2D.Double(0, -1), 0, 100);
        for(int i = 0; i < 50; i++)
        {
            Missile[] tab = ship.fire(Main.GAMEDELAY);
            if(tab != null)
            {
                for(int j = 0; j < tab.length; j++)
                {
                    Missile mis = tab[j];
                    while(x < 100)
                    {
                        mis.refreshPosition();
                        x++;
                        if(mis.getPosition().x - (ship.getVecOr().x * 2) <= delta && mis.getPosition().y - (ship.getVecOr().y * 2) <= delta)
                        {
                            count++;
                            if(count == 100)
                                System.out.println("1 : Simple Missile : OK ("+count+"/100)");
                        }
                        
                    }
                }
                count = 0;
            }
        }
    }
    
    public static void missileTest2() // Test with speed ship 0 - Multi Missile (multi + simple)
    {
        int x = 0;
        int count = 0;
        SpaceShip ship = new SpaceShip(new Point2D.Double(0, 0), new Point2D.Double(0, -1), 0, 100);
        ship.setMultiFire();
        for(int i = 0; i < 50; i++)
        {
            Missile[] tab = ship.fire(Main.GAMEDELAY);
            if(tab != null)
            {
                for(int j = 0; j < tab.length; j++)
                {
                    Missile mis = tab[j];
                    while(x < 100)
                    {
                        mis.refreshPosition();
                        x++;
                        if(mis.getPosition().x - (ship.getVecOr().x * 2) <= delta && mis.getPosition().y - (ship.getVecOr().y * 2) <= delta)
                        {
                            count++;
                            if(count == 100)
                                System.out.println("2 : Mutli missile : OK ("+count+"/100)");
                        }

                    }
                }
                count = 0;

            }
        }
    }
    
    public static void missileTest3() // Test with speed ship 0 - Multi Missile + fireRate
    {
        int x = 0;
        int count = 0;
        SpaceShip ship = new SpaceShip(new Point2D.Double(0, 0), new Point2D.Double(0, -1), 0, 100);
        ship.setMultiFire();
        ship.increaseFireRate();
        for(int i = 0; i < 50; i++)
        {
            Missile[] tab = ship.fire(Main.GAMEDELAY);
            if(tab != null)
            {
                for(int j = 0; j < tab.length; j++)
                {
                    Missile mis = tab[j];
                    while(x < 100)
                    {
                        mis.refreshPosition();
                        x++;
                        if(mis.getPosition().x - (ship.getVecOr().x * 2) <= delta && mis.getPosition().y - (ship.getVecOr().y * 2) <= delta)
                        {
                            count++;
                            if(count == 100)
                                System.out.println("3 : Mutli missile + Fire Rate : OK ("+count+"/100)");
                        }

                    }
                }
                count = 0;

            }
        }
    }
    
    public static void missileTest4() // Test with speed ship 0 - Multi Missile + fireRate + life++
    {
        int x = 0;
        int count = 0;
        SpaceShip ship = new SpaceShip(new Point2D.Double(0, 0), new Point2D.Double(0, -1), 0, 100);
        ship.setMultiFire();
        ship.increaseFireRate();
        ship.increaseMissileLife();
        for(int i = 0; i < 50; i++)
        {
            Missile[] tab = ship.fire(Main.GAMEDELAY);
            if(tab != null)
            {
                for(int j = 0; j < tab.length; j++)
                {
                    Missile mis = tab[j];
                    while(x < 100)
                    {
                        mis.refreshPosition();
                        x++;
                        if(mis.getPosition().x - (ship.getVecOr().x * 2) <= delta && mis.getPosition().y - (ship.getVecOr().y * 2) <= delta)
                        {
                            count++;
                            if(count == 100)
                                System.out.println("4 : Mutli missile + Fire Rate + life++ : OK ("+count+"/100)");
                        }

                    }
                }
                count = 0;

            }
        }
    }
    
    public static void missileTest5() // Test with speed ship 0 - fireRate + life++
    {
        int x = 0;
        int count = 0;
        SpaceShip ship = new SpaceShip(new Point2D.Double(0, 0), new Point2D.Double(0, -1), 0, 100);
        ship.increaseFireRate();
        ship.increaseMissileLife();
        for(int i = 0; i < 50; i++)
        {
            Missile[] tab = ship.fire(Main.GAMEDELAY);
            if(tab != null)
            {
                for(int j = 0; j < tab.length; j++)
                {
                    Missile mis = tab[j];
                    while(x < 100)
                    {
                        mis.refreshPosition();
                        x++;
                        if(mis.getPosition().x - (ship.getVecOr().x * 2) <= delta && mis.getPosition().y - (ship.getVecOr().y * 2) <= delta)
                        {
                            count++;
                            if(count == 100)
                                System.out.println("5 : Fire Rate + life++ : OK ("+count+"/100)");
                        }

                    }
                }
                count = 0;

            }
        }
    }
    
    public static void missileTest6() // Test with speed ship 0 - Multi Missile + life++
    {
        int x = 0;
        int count = 0;
        SpaceShip ship = new SpaceShip(new Point2D.Double(0, 0), new Point2D.Double(0, -1), 0, 100);
        ship.setMultiFire();
        ship.increaseMissileLife();
        for(int i = 0; i < 50; i++)
        {
            Missile[] tab = ship.fire(Main.GAMEDELAY);
            if(tab != null)
            {
                for(int j = 0; j < tab.length; j++)
                {
                    Missile mis = tab[j];
                    while(x < 100)
                    {
                        mis.refreshPosition();
                        x++;
                        if(mis.getPosition().x - (ship.getVecOr().x * 2) <= delta && mis.getPosition().y - (ship.getVecOr().y * 2) <= delta)
                        {
                            count++;
                            if(count == 100)
                                System.out.println("6 : Mutli missile + life++ : OK ("+count+"/100)");
                        }

                    }
                }
                count = 0;

            }
        }
    }
    
    public static void missileTest7() // Test with speed ship 0 - fireRate
    {
        int x = 0;
        int count = 0;
        SpaceShip ship = new SpaceShip(new Point2D.Double(0, 0), new Point2D.Double(0, -1), 0, 100);
        ship.increaseFireRate();
        for(int i = 0; i < 50; i++)
        {
            Missile[] tab = ship.fire(Main.GAMEDELAY);
            if(tab != null)
            {
                for(int j = 0; j < tab.length; j++)
                {
                    Missile mis = tab[j];
                    while(x < 100)
                    {
                        mis.refreshPosition();
                        x++;
                        if(mis.getPosition().x - (ship.getVecOr().x * 2) <= delta && mis.getPosition().y - (ship.getVecOr().y * 2) <= delta)
                        {
                            count++;
                            if(count == 100)
                                System.out.println("7 : Fire Rate: OK ("+count+"/100)");
                        }

                    }
                }
                count = 0;

            }
        }
    }
    
    public static void missileTest8() // Test with speed ship 0 - life++
    {
        int x = 0;
        int count = 0;
        SpaceShip ship = new SpaceShip(new Point2D.Double(0, 0), new Point2D.Double(0, -1), 0, 100);
        ship.setMultiFire();
        ship.increaseFireRate();
        ship.increaseMissileLife();
        for(int i = 0; i < 50; i++)
        {
            Missile[] tab = ship.fire(Main.GAMEDELAY);
            if(tab != null)
            {
                for(int j = 0; j < tab.length; j++)
                {
                    Missile mis = tab[j];
                    while(x < 100)
                    {
                        mis.refreshPosition();
                        x++;
                        if(mis.getPosition().x - (ship.getVecOr().x * 2) <= delta && mis.getPosition().y - (ship.getVecOr().y * 2) <= delta)
                        {
                            count++;
                            if(count == 100)
                                System.out.println("8 : life++ : OK ("+count+"/100)");
                        }

                    }
                }
                count = 0;

            }
        }
    }

}