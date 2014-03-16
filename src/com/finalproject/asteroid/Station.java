/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.asteroid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author kurtjunsheanespinosa
 */
public class Station {
    private double angle = Math.PI/2.0;
    private int hits = 0;
    private final double y;
    private final double x;
    
    public Station(double ix, double iy){
        x = ix;
        y = iy;
    }
    
    public void moveLeft(){
        angle = angle + 0.1;        
    }
    
    public void moveRight(){
        angle = angle - 0.1;
    }
    
    public void fire(LinkedList rockets){
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        
        Rocket r = new Rocket(x+15*cosAngle, y-15*sinAngle, 5*cosAngle, -5*sinAngle);
        rockets.addFirst(r);
    }
    
    public void checkHit(Asteroid rock){
        if(rock.nearTo(x, y))
            hits += rock.size;
    }
   
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        double lv = 20 * Math.sin(angle);//gun is 20
        double lh = 20 * Math.cos(angle);//pixels long
        g.drawLine((int)x, (int)y, (int)(x+lh), (int)(y-lv));
        g.drawString("Hits: "+hits, (int)(x+10), (int)(y-5));
    }
}
