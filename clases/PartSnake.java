package clases;
import java.awt.*;
import utils.Element;
import utils.Orientation;

public class PartSnake extends Element implements Orientation{
    public int orientation;

    public PartSnake(){
        super();
        orientation = RIGHT;
    }

    public PartSnake(int positionInRow, int positionInColumn, int orientation){
        super(positionInRow, positionInColumn);
        try{
            if(orientation != TOP && orientation != BOTTOM && orientation != LEFT && orientation != RIGHT)
                throw new Exception("Orientation Undefined");
            else
                this.orientation = orientation;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int getOrientation(){
        return orientation;
    }

    public void setOrientation(int newOrientation){
        orientation = newOrientation;
    }
    
    public void paint(Graphics g, int x, int y, boolean last){
        g.setColor(new Color(163, 73, 164)); 
        g.fillOval(x, y, 21, 21);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 21, 21);
        g.setColor(new Color(192, 192, 192));
        g.drawOval(x+2, y+2, 17, 17);  
        if(last) {
            g.setColor(new Color(210, 163, 0));
            g.fillOval(x+6, y+6, 10, 10);
        }
    }
}