package clases;
import javax.swing.*;
import java.awt.*;

public class ProofPaintSnake extends JFrame{
    public ProofPaintSnake(){
        super("Dibujo Snake"); 
        setBounds(0, 0, 250, 250);
        setLocationRelativeTo(null);
        add(new LaminaSnake());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        while(true){
            repaint();
        }
    }
    
    public static void main(String args[]){
        new ProofPaintSnake();
    }
}

class LaminaSnake extends JPanel{
    Snake snake = new Snake(2, 2);
    public LaminaSnake(){
        //setBackground(Color.GREEN);
        snake.runLeft();
        snake.runBottom();
        //snake.getHead().getTongue().initTimer();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //snake.toEat(new SnakePart());
        
        snake.paint(g);
    }
}
