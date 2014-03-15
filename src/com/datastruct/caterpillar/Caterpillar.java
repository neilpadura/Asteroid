/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastruct.caterpillar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author kurtjunsheanespinosa
 */
public class Caterpillar {
    private Color color;
    private Point position;
    private char direction = 'W';
    private Queue body = new LinkedList();
    private Queue commands = new LinkedList();
    
    public Caterpillar(Color c, Point sp){
        color = c;
        for(int i = 0; i < 10; i++){
            position = new Point(sp.x+i, sp.y);
            body.add(position);
        }
    }
    
    public void setDirection(char d){
        commands.add(new Character(d));
    }
    
    public void move(CaterpillarGame game){
        //first see if we should change direction
        if(commands.size() > 0){
            Character c = (Character)commands.remove();
            direction = c.charValue();
        }
        
        //then find new position
        Point np = newPosition();
        //erase one segment, add another
        if(game.canMove(np)){
            body.remove();
            body.add(np);
            position = np;
        }
    }
    
    private Point newPosition(){
        int x = position.x;
        int y = position.y;
        if(direction == 'E') x++;
        else if(direction == 'W') x--;
        else if(direction == 'N') y--;
        else if(direction == 'S') y++;
        return new Point(x,y);
    }
    
    public boolean inPosition(Point np){
        Iterator e = body.iterator();
        while(e.hasNext()){
            Point location = (Point) e.next();
            if(np.equals(location)) return true;
        }
        return false;
    }
    
    public void paint(Graphics g){        
        g.setColor(color);
        Iterator e = body.iterator();
        while(e.hasNext()){
            Point p = (Point)e.next();
            g.fillOval(5 + CaterpillarGame.SegmentSize * p.x,10 + CaterpillarGame.SegmentSize * p.y, CaterpillarGame.SegmentSize, CaterpillarGame.SegmentSize);
            
        }
    }
}
