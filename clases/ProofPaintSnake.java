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
    }
    
    public static void main(String args[]){
        new ProofPaintSnake();
    }
}

class LaminaSnake extends JPanel{
    public LaminaSnake(){
        setBackground(Color.GREEN);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Snake snake = new Snake(2, 2);
        snake.paint(g);
    }
}
