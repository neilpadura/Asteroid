package com.finalproject.asteroid;

import java.awt.Color;
import java.awt.Graphics;

public class Asteroid {
    public double x, y;
    private double dx, dy;
    public int size = 20;
    
    public Asteroid(double ix, double iy, double idx, double idy){
        x = ix;
        y = iy;
        dx = idx;
        dy = idy;
    }
    
    public void move(){
        x += dx;
        y +=dy;
    }
    
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.drawOval((int)x, (int)y, size, size);
    }
    
    public void hit(){
        size = size - 4;
    }
    
    public boolean nearTo(double tx, double ty){
        double distance = Math.sqrt((x-tx)*(x-tx)+(y-ty)*(y-ty));
        return distance < size; 
    }
}