package clases;
import java.awt.Graphics;
import java.awt.Color;

//////////////////////
import javax.swing.*;

public class Mouse extends Food{
    public Mouse(){
        super();
        tiempoVida = 5;
        activarTimer();
    }

    public Mouse(int positionInRow, int positionInColumn){
        super(positionInRow, positionInColumn);
        tiempoVida = 5;
        activarTimer();
    }    
    
    /*public void paint(Graphics g){
        int x = (getPositionInColumn()+1)*20;
        int y = (getPositionInRow()+1)*20;
        
        //body
        g.setColor(new Color(84, 82, 86));
        g.fillOval(x-5, y-25, 30, 30);
        
        //head
        g.setColor(Color.GRAY);
        g.fillOval(x, y, 20, 25);
        g.fillOval(x-5, y-5, 15, 15);
        g.fillOval(x+10, y-5, 15, 15);
        g.setColor(Color.PINK);
        g.fillArc(x-3, y-3, 10, 10, 50, 200);
        g.fillArc(x+12, y-3, 10, 10, 150, -220);
        
        //eyes, nose
        g.setColor(Color.BLACK);
        g.fillOval(x+4, y+10, 5, 5);
        g.fillOval(x+11, y+10, 5, 5);
        g.fillOval(x+8, y+20, 5, 5);
    }*/
    
    public void paint(Graphics g){
        int x = (getPositionInColumn()+1)*20;
        int y = (getPositionInRow()+1)*20;
        
        //ears
        g.setColor(Color.GRAY);
        g.fillOval(x-5, y-5, 15, 15);
        g.fillOval(x+10, y-5, 15, 15);
        g.setColor(Color.BLACK);
        g.drawOval(x-5, y-5, 15, 15);
        g.drawOval(x+10, y-5, 15, 15);
        
        //head
        g.setColor(Color.GRAY);
        g.fillOval(x, y, 20, 20);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 20, 20);
        
        //ears
        g.setColor(Color.PINK);
        g.fillArc(x-3, y-3, 10, 10, 50, 200);
        g.fillArc(x+12, y-3, 10, 10, 150, -220);
        
        //eyes
        g.setColor(Color.BLACK);
        g.fillOval(x+4, y+7, 5, 5);
        g.fillOval(x+11, y+7, 5, 5);
        //nose
        g.fillOval(x+8, y+16, 5, 5);
    }
    
    public void interactuar(Snake snake) {
        SnakePart newPart1 = SnakePartFactory.getInstance().computeNexPart(snake.getBody().peekLast(), snake.getHead());
        snake.toEat(newPart1);
        SnakePart newPart2 = SnakePartFactory.getInstance().computeNexPart(snake.getBody().peekLast(), snake.getHead());
        snake.toEat(newPart2);
    }
}