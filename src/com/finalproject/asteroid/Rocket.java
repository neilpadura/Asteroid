package com.finalproject.asteroid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

public class Rocket {
    public double x, y;
    private double dx, dy;
    public static int score = 0;
    
    public Rocket(double ix, double iy, double idx, double idy){
        x = ix;
        y = iy;
        dx = idx*4;
        dy = idy*4;
    }
    
    public void move(LinkedList asteroids, Station s){
        x += dx;
        y += dy;
        Iterator e = asteroids.iterator();
        while(e.hasNext()){
            Asteroid rock = (Asteroid)e.next();
            if(rock.nearTo(x, y)){
                rock.hit();
                s.addScore(1);
                score = 1;
            }
        }
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval((int)x, (int)y, 5, 5);
    }
}