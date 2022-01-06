package clases;
import javax.swing.*;
import java.awt.*;

public class PaintSnake extends JFrame{
    public PaintSnake(){
        super("Dibujo Snake"); 
        setBounds(0, 0, 270, 250);
        setLocationRelativeTo(null);
        add(new Lamina());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        while(true){
            repaint();
        }
    }
    
    public static void main(String args[]){
        new PaintSnake();
    }
}

class Lamina extends JPanel{
    Snake snake = new Snake(2, 2);
    public Lamina(){
        //setBackground(Color.GREEN);
        snake.runLeft();
        snake.runBottom();
        snake.getHead().getTongue().initTimer();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //snake.toEat(new SnakePart());
        
        snake.paint(g);
    }
}
