package com.finalproject.asteroid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Station {
    private double angle = Math.PI/2.0;
    public static int score = 0;
    public int hits = 1000;
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
            hits -= rock.size;
    }
    
    public boolean stillAlive() {
        return hits != 0;
    }
    
    public void addScore(int i) {
        score += i;
    }
   
    public void paint(Graphics g){
        g.setColor(Color.black);
        double lv = 20 * Math.sin(angle);
        double lh = 20 * Math.cos(angle);
        g.drawLine((int)x, (int)y, (int)(x+lh), (int)(y-lv));
        g.drawString("HP: " + hits +"/1000", 50, 50);
        g.drawString("Score: " + score, 400, 50);
    }
}