package com.finalproject.asteroid;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AsteroidGame extends JFrame {
    private int FrameWidth = 500;
    private int FrameHeight = 400;
    private LinkedList asteroids = new LinkedList();
    private LinkedList rockets1 = new LinkedList();
    private LinkedList rockets2 = new LinkedList();
    private Station station1 = new Station(FrameWidth/2, FrameHeight/2);
    //private Station station2 = new Station(FrameWidth/3, FrameHeight/3);
    
    public static void main(String[] args) {
        AsteroidGame world = new AsteroidGame();
        world.setVisible(true);
        world.run();   
    }
    
    public AsteroidGame(){
        setTitle("Asteroid Game");
        setSize(FrameWidth, FrameHeight);
        addKeyListener(new KeyReader());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
    }
    
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);//added
        station1.paint(g);
        //station2.paint(g);
        Iterator e = asteroids.iterator();
        while(e.hasNext()){
            Asteroid rock = (Asteroid)e.next();
            //validate();//added
            rock.paint(g);            
        }   
        e = rockets1.iterator();
        //Iterator r = rockets2.iterator();
        while(e.hasNext()){
            Rocket rock1 = (Rocket)e.next();
            //Rocket rock2 = (Rocket)r.next();
            //validate();//added
            rock1.paint(g);
            //rock2.paint(g);
        }      
        //this.repaint();//added
        //this.validate();//added     
    }
            
    private class KeyReader extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            char key = e.getKeyChar();
            switch(key){
                case 'j': station1.turnLeft();break;
                case 'k': station1.turnRight();break;
                case 'l': station1.fire(rockets1);break;
                case 'w': station1.moveUp();break;
                case 's': station1.moveDown();break;
                case 'a': station1.moveLeft();break;
                case 'd': station1.moveRight();break;
                case 'q': System.exit(0);                      
            }
        }
    }
    
    private void movePieces(){
        //create a random new asteroid
        if(Math.random() < 0.3){
            Asteroid newRock = new Asteroid(FrameWidth * Math.random(), 20, 10 * Math.random()-5, 3 + 3 * Math.random());
            asteroids.addFirst(newRock);
        }//then move everything
        Iterator e = asteroids.iterator();
        while(e.hasNext()){
            Asteroid rock = (Asteroid)e.next();
            rock.move();
            station1.checkHit(rock);
        }      
        e = rockets1.iterator();
        Iterator r = rockets2.iterator();
        while(e.hasNext() || r.hasNext()){
            Rocket rock1 = (Rocket)e.next();
            rock1.move(asteroids, station1);
//          Rocket rock2 = (Rocket)r.next();
//          rock2.move(asteroids);
        }
    }
    
    public void run(){
        while(station1.stillAlive()==true) {//now move pieces
            movePieces();       
            repaint();         
            try{Thread.sleep(100);}
            catch(Exception e){}  
        }
        JOptionPane.showMessageDialog(null, "Game Over!");
        System.exit(0); 
    }
}