/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastruct.caterpillar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author kurtjunsheanespinosa
 */
public class CaterpillarGame extends JFrame{
    private Caterpillar playerOne = new Caterpillar(Color.BLUE, new Point(20,10));
    private Caterpillar playerTwo = new Caterpillar(Color.RED, new Point(20,30));
    final static int BoardWidth = 60;
    final static int BoardHeight = 40;
    final static int SegmentSize = 10;
    
    public static void main(String[] args) {
        CaterpillarGame world = new CaterpillarGame();
        world.setVisible(true);
        world.run();
    }
    
    public CaterpillarGame(){
        setSize((BoardWidth+2)*SegmentSize, BoardHeight*SegmentSize+30);
        setTitle("Caterpillar Game");
        addKeyListener(new KeyReader());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
    }
    
    public void run(){
        while(true){
            movePieces();
            repaint();
            try{
                Thread.sleep(100);                
            }catch(Exception e){}
        }
    }
    
    public void paint(Graphics g){
        super.paintComponents(g);
        
        playerOne.paint(g);
        playerTwo.paint(g);
        
        
    }
    
    public void movePieces(){
        
        playerOne.move(this);
        playerTwo.move(this);
        
        
    }
    private class KeyReader extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            char c = e.getKeyChar();
            switch(c){
                case 'a': playerOne.setDirection('W');break;
                case 'd': playerOne.setDirection('E');break;
                case 'w': playerOne.setDirection('N');break;
                case 's': playerOne.setDirection('S');break;
                    
                case 'j': playerTwo.setDirection('W');break;
                case 'k': playerTwo.setDirection('S');break;
                case 'l': playerTwo.setDirection('E');break;
                case 'i': playerTwo.setDirection('N');break;                    
                    
            }
        }
    }
    
    public boolean canMove(Point np){
        //test playing board
        if((np.x <= 0) || (np.y <= 0)) return false;
        if((np.x >= BoardWidth) || (np.y >= BoardHeight)) return false;
        //test caterpillars
        if(playerOne.inPosition(np)) return false;
        if(playerTwo.inPosition(np)) return false;
        //ok, safe square
        return true;
    }
}
