package visual;
import javax.swing.*;
import java.awt.*;


/**
 * Write a description of class Instruccion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Instruccion extends JDialog{
    private JLabel up, down, right, left, pause, resume, title;
    private JLabelURL urlGit;
    private final Toolkit screen = Toolkit.getDefaultToolkit();
    private Icon[] imagenes = {new ImageIcon(screen.getImage("./img/up.png").getScaledInstance(20, 20, Image.SCALE_SMOOTH)),
                                new ImageIcon(screen.getImage("./img/down.png").getScaledInstance(20, 20, Image.SCALE_SMOOTH)),
                                new ImageIcon(screen.getImage("./img/right.png").getScaledInstance(20, 20, Image.SCALE_SMOOTH)),
                                new ImageIcon(screen.getImage("./img/left.png").getScaledInstance(20, 20, Image.SCALE_SMOOTH)),
                                new ImageIcon(screen.getImage("./img/pause.png").getScaledInstance(20, 20, Image.SCALE_SMOOTH)),
                                new ImageIcon(screen.getImage("./img/resume.png").getScaledInstance(20, 20, Image.SCALE_SMOOTH))};
    
    private Container panel;
    public Instruccion(FrameSnake f) {
        super(f, "Help", true);
        setBounds(0, 0, 350, 220);
        setLocationRelativeTo(f);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        panel = getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        
        init();
        setVisible(true);
    }
    
    private void init() {
        title = new JLabel("Controles");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        up = new JLabel(" Para ir arriba.                ", imagenes[0], SwingConstants.RIGHT);
        down = new JLabel(" Para ir abajo.               ", imagenes[1], SwingConstants.RIGHT);
        right = new JLabel(" Para ir a la derecha. ", imagenes[2], SwingConstants.RIGHT);
        left = new JLabel(" Para ir a la izquierda. ", imagenes[3], SwingConstants.RIGHT);
        pause = new JLabel(" Para pausar el juego. ", imagenes[4], SwingConstants.RIGHT);
        resume = new JLabel(" Para reanuar el juego. ", imagenes[5], SwingConstants.RIGHT);
        
        urlGit = new JLabelURL("Respositorio: ", "https://github.com/GabrielPB96/Snake.git");
        
        up.setAlignmentX(Component.CENTER_ALIGNMENT);
        down.setAlignmentX(Component.CENTER_ALIGNMENT);
        right.setAlignmentX(Component.CENTER_ALIGNMENT);
        left.setAlignmentX(Component.CENTER_ALIGNMENT);
        pause.setAlignmentX(Component.CENTER_ALIGNMENT);
        resume.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        urlGit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(title);
        panel.add(up);
        panel.add(down);
        panel.add(right);
        panel.add(left);
        panel.add(pause);
        panel.add(resume);
        panel.add(urlGit);
    }
    
    public static void main() {
        new Instruccion(null);
    }
}
