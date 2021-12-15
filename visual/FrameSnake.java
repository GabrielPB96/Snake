package visual;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.*;
import javax.swing.*;

public class FrameSnake extends JFrame{
    private JButton start, pause, resume, help;
    private LaminaSnake lamina_snake;
    
    public FrameSnake(){
        super("Snake");
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("./img/snakeC.png").getImage());
        setBounds(0, 0, 660, 710);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }
    
    private void init(){
        lamina_snake = new LaminaSnake(this);
        add(lamina_snake, BorderLayout.CENTER);
        createButtons();
    }
    
    private void createButtons(){
        start = new JButton("Start");
        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!lamina_snake.enJuego())
                    lamina_snake.start();
            }
        });
        
        pause = new JButton("Pause");
        pause.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(lamina_snake.enJuego())
                    lamina_snake.pause();
            }
        });
        
        resume = new JButton("Resume");
        resume.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(lamina_snake.isPause())
                    lamina_snake.resume();
            }
        });
        
        help = new JButton("Help");
        help.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Instruccion(FrameSnake.this);
            }
        });
        
        help.setCursor(new Cursor(12));
        start.setCursor(new Cursor(12));
        pause.setCursor(new Cursor(12));
        resume.setCursor(new Cursor(12));
        
        JPanel lamina_buttons = new JPanel();
        lamina_buttons.setBackground(new Color(128, 128, 128));
        lamina_buttons.setLayout(new FlowLayout());
        lamina_buttons.add(start);
        lamina_buttons.add(pause);
        lamina_buttons.add(resume);
        lamina_buttons.add(help);
        add(lamina_buttons, BorderLayout.NORTH);
    }
}
