package visual;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import clases.*;
import utils.*;

/**
 * Write a description of class Instruccion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Instruccion extends JDialog{
    private JLabel up, down, right, left, pause, resume, title;
    private JLabelURL urlGit;
    private ArrayList<Food> foods;
    private Wall muro;
    
    private Container panel;
    public Instruccion(FrameSnake f) {
        super(f, "Help", true);
        foods = new ArrayList<Food>();
        setLayout(new BorderLayout());
        setBounds(0, 0, 350, 250);
        setLocationRelativeTo(f);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        init();
        setVisible(true);
    }
    
    private void init() {
        urlGit = new JLabelURL("Respositorio: ", "https://github.com/GabrielPB96/Snake.git");
        
        foods.add(new Chicken(9, 2));
        foods.add(new Mouse(11, 2));
        foods.add(new Venom(13, 2));
        foods.add(new Bomb(15, 2));
        muro = new ShapeEle(new Position(18, 3), new Color(160, 68, 46));
        muro.rotateLeft();
        
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(new Panel());
        
        add(scroll, BorderLayout.CENTER);
        add(urlGit, BorderLayout.SOUTH);
    }
    
    public static void main() {
        new Instruccion(null);
    }
    
    private class Panel extends JPanel {
        public Panel() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(300, 420));
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("CONTROLES", 108, 20);
            //up
            int x1[] = {90, 95, 100};
            int y1[] = {40, 30, 40};
            g.fillPolygon(x1, y1, 3);
            g.fillRect(93, 40, 5, 10);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g.drawString("Para ir arriba.", 120, 50);
            
            //down
            int x2[] = {90, 95, 100};
            int y2[] = {70, 80, 70};
            g.fillPolygon(x2, y2, 3);
            g.fillRect(93, 60, 5, 10);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g.drawString("Para ir abajo.", 120, 75);
            
            //right
            int x3[] = {95, 105, 95};
            int y3[] = {87, 92, 97};
            g.fillPolygon(x3, y3, 3);
            g.fillRect(85, 90, 10, 5);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g.drawString("Para ir a la derecha.", 120, 100);
            
            //left
            int x4[] = {94, 84, 94};
            int y4[] = {112, 117, 122};
            g.fillPolygon(x4, y4, 3);
            g.fillRect(94, 115, 10, 5);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g.drawString("Para ir a la izquierda.", 120, 125);
            
            //pause
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("A", 90, 150);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g.drawString("Para pausar el juego.", 120, 150);
            
            //resume
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("D", 90, 177);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g.drawString("Para reanuar el juego.", 120, 175);
            
            g.drawString("Aumenta el tamaño en uno.", 110, 217);
            g.drawString("Aumenta el tamaño en dos.", 110, 257);
            g.drawString("Reduce el tamaño en uno", 110, 298);
            g.drawString("Game Over.", 110, 335);
            g.drawString("Game Over.", 110, 385);
            for (Food f : foods) {
                f.paint(g);
            }
            
            muro.paint(g);
        }
    }
}
