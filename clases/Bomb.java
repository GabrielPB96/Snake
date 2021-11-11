package clases;
import java.awt.Graphics;
import java.awt.Color;

//////////////
import javax.swing.*;

public class Bomb extends Food{
    public Bomb(){
        super();
    }

    public Bomb(int positionInRow, int positionInColumn){
        super(positionInRow, positionInColumn);
    }
    
    public void paint(Graphics g){
        int x = (getPositionInColumn()+1)*20;
        int y = (getPositionInRow()+1)*20;
        
        //mechero
        g.setColor(new Color(210, 163, 0));
        int xs[] = {x+9, x+9, x+14, x+16, x+11, x+11};
        int ys[] = {y-2, y-5, y-9, y-7, y-4, y-2};
        g.fillPolygon(xs, ys, 6);
        g.setColor(Color.BLACK);
        g.drawPolygon(xs, ys, 6);
        
        g.setColor(new Color(127, 127, 127));
        g.fillRect(x+7, y-3, 6, 5);
        g.setColor(Color.BLACK);
        g.drawRect(x+7, y-3, 6, 5);
        
        g.setColor(new Color(127, 127, 127));
        g.fillOval(x, y, 20, 20);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 20, 20);
    }
}
