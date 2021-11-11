package clases;
import java.awt.Graphics;
import java.awt.Color;

//////////////
import javax.swing.*;

public class Chicken extends Food{
    public Chicken(){
        super();
    }

    public Chicken(int positionInRow, int positionInColumn){
        super(positionInRow, positionInColumn);
    }
    
    /*public void paint(Graphics g){
        int x = (getPositionInColumn()+1)*20;
        int y = (getPositionInRow()+1)*20;
        
        //cresta
        g.setColor(Color.RED);
        g.fillOval(x+9,  y-27, 5, 5);
        g.fillOval(x+13, y-28, 5, 5);
        g.fillOval(x+17, y-27, 5, 5);
        
        //Body
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 30, 25);
        g.setColor(Color.ORANGE);
        
        //head
        g.setColor(Color.WHITE);
        g.fillRect(x+10, y-15, 11, 17);
        g.fillOval(x+9, y-25, 12, 15);
        
        //eyes
        g.setColor(Color.BLACK);
        g.fillOval(x+10, y-17, 5, 5);
        g.fillOval(x+15, y-17, 5, 5);
        
        //pico
        g.setColor(Color.ORANGE);
        int xs[] = {x+10, x+15, x+20};
        int ys[] = {y-10, y-5, y-10};
        g.fillPolygon(xs, ys, 3);
        
        //plumas
        g.setColor(new Color(240, 135, 40));
        int xs1[] = {x+6, x+10, x+14};
        int ys1[] = {y+3, y+12, y+3};
        g.fillPolygon(xs1, ys1, 3);
        int xs2[] = {x+17, x+21, x+25};
        int ys2[] = {y+3, y+12, y+3};
        g.fillPolygon(xs2, ys2, 3);
        int xs3[] = {x+11, x+15, x+21};
        int ys3[] = {y+1, y+10, y+1};
        g.fillPolygon(xs3, ys3, 3);
        g.setColor(Color.WHITE);
        g.fillRect(x+11, y+1, 10, 2);
    }*/
    
    public void paint(Graphics g){
        int x = (getPositionInColumn()+1)*20;
        int y = (getPositionInRow()+1)*20;
        
        //cresta
        g.setColor(Color.RED);
        g.fillOval(x+5, y-4, 7, 7);
        g.fillOval(x+10, y-6, 10, 10);
        g.setColor(Color.BLACK);
        g.drawOval(x+5, y-4, 7, 7);
        g.drawOval(x+10, y-6, 10, 10);
        
        //body
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 25, 20);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 25, 20);
        
        //eyes
        g.setColor(Color.BLACK);
        g.fillOval(x+7, y+3, 5, 5);
        g.fillOval(x+14, y+3, 5, 5);
    
        //pico
        g.setColor(Color.ORANGE);
        int xs[] = {x+8, x+13, x+18};
        int ys[] = {y+10, y+15, y+10};
        g.fillPolygon(xs, ys, 3);
    }
}