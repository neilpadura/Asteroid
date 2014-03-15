/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.asteroid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author kurtjunsheanespinosa
 */
public class Rocket {
    public double x, y;
    private double dx, dy;
    
    public Rocket(double ix, double iy, double idx, double idy){
        x = ix;
        y = iy;
        dx = idx;
        dy = idy;
    }
    
    public void move(LinkedList asteroids){
        x += dx;
        y += dy;
        Iterator e = asteroids.iterator();
        while(e.hasNext()){
            Asteroid rock = (Asteroid)e.next();
            if(rock.nearTo(x, y)){
                rock.hit();
            }
        }
    }
    
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillOval((int)x, (int)y, 5, 5);
    }
}
