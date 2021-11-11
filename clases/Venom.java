package clases;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;

public class Venom extends Food{
    public Venom(){
        super();
    }

    public Venom(int positionInRow, int positionInColumn){
        super(positionInRow, positionInColumn);
    }
    
    public void paint(Graphics g){
        int x = (getPositionInColumn()+1)*20;
        int y = (getPositionInRow()+1)*20;
        
        g.setColor(Color.RED);
        g.fillOval(x, y, 20, 20);
        
        int xs1[] = {x+5, x+10, x+15};
        int ys1[] = {y+1, y-10, y+1};
        g.fillPolygon(xs1, ys1, 3);
        
        int xs2[] = {x+1, x-10, x+1};
        int ys2[] = {y+5, y+10, y+15};
        g.fillPolygon(xs2, ys2, 3);
        
        int xs3[] = {x+5, x+10, x+15};
        int ys3[] = {y+19, y+30, y+19};
        g.fillPolygon(xs3, ys3, 3);
        
        int xs4[] = {x+19, x+30, x+19};
        int ys4[] = {y+5, y+10, y+15};
        g.fillPolygon(xs4, ys4, 3);
        
        int xs5[] = {x, x-5, x+6};
        int ys5[] = {y+5, y-3, y};
        g.fillPolygon(xs5, ys5, 3);
        
        int xs6[] = {x+15, x+25, x+20};
        int ys6[] = {y, y-3, y+5};
        g.fillPolygon(xs6, ys6, 3);
        
        int xs7[] = {x, x-5, x+5};
        int ys7[] = {y+15, y+23, y+20};
        g.fillPolygon(xs7, ys7, 3);
        
        int xs8[] = {x+20, x+25, x+15};
        int ys8[] = {y+15, y+23, y+20};
        g.fillPolygon(xs8, ys8, 3);
        
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 20, 20);
        
        g.fillOval(x+5, y+5, 5, 5);
        g.fillOval(x+10, y+5, 5, 5);
        g.drawArc(x+5, y+7, 10, 10, 180, 180);
    }
}