package clases;
import javax.swing.*;
import java.awt.*;

public class ProofPaintFood extends JFrame{
    public ProofPaintFood(){
        super("Foods");
        setBounds(0, 0, 250, 250);
        setLocationRelativeTo(null);
        add(new LaminaFood());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    } 
    
    public static void main(String args[]){
        new ProofPaintFood();
    }
}

class LaminaFood extends JPanel{
    private Food mouse, chicken, venom, bomb;
    public LaminaFood(){
        setBackground(Color.PINK);
        mouse   = new Mouse(3, 3);
        chicken = new Chicken(3, 5);
        venom   = new Venom(5, 3);
        bomb    = new Bomb(5, 5);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        mouse.paint(g);
        chicken.paint(g);
        venom.paint(g);
        bomb.paint(g);
    }
}
