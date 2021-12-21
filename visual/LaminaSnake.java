package visual;
import javax.swing.*;
import clases.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TimerTask;
import utils.*;

public class LaminaSnake extends JPanel{
    private Snake snake;
    private Game game;
    private int orientation;
    private JFrame marco;
    private Thread hilo_game;
    
    private boolean init;
    private int recuento;
    private int delay;
    
    public LaminaSnake(JFrame marco){
        setLayout(null);
        delay = 90;
        recuento = 3;
        init = false;
        setBackground(new Color(128, 128, 128));
        addKeyListener(new Manager());
        setFocusable(true);
        this.marco = marco;
        snake = new Snake();
        game = new Game(snake);
        initThread();
        orientation = snake.getHead().getOrientation();
    }
    
    private void initThread(){
        hilo_game = new Thread(new Runnable(){
            public void run(){
                while(game.verifyConditions()){
                    game.updateBoard();
                    if(!game.isPause()){
                        try{
                            if(orientation == Orientation.TOP){
                                snake.runTop();
                            }else if(orientation == Orientation.BOTTOM){
                                snake.runBottom();
                            }else if(orientation == Orientation.LEFT){
                                snake.runLeft();
                            }else if(orientation == Orientation.RIGHT){
                                snake.runRight();
                            }
                            repaint();
                            game.interactuar();
                            Thread.sleep(delay);
                        }catch(Exception e){}
                    }
                }
                
                if(game.win()){
                    game.end();
                    game.nextLevel();
                    delay -= 10;
                    if(game.getLevel() < 3) JOptionPane.showMessageDialog(marco, "PASSED!!!");
                    else{
                        delay = 90;
                        JOptionPane.showMessageDialog(marco, "MISION COMPLETE!!!");
                    }
                }
                if(snake.headShock()) game.end();
                game.finishTimer();
                game.restartSnake();
                orientation = snake.getHead().getOrientation();
                repaint();
            }
        });
    }
    
    public boolean isPause(){
        return game.isPause();
    }
    
    public void pause(){
        game.pause();
    }
    
    public void resume(){
        game.resume();
        setFocusable(true);
        requestFocusInWindow();
    }
    
    public void actInit() {
        init = true;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Size: "+(snake.getBody().size()-1), 240, 15);
        g.drawString("Level: "+game.getLevel(), 330, 15);
        g.setColor(new Color(144, 238, 144));
        g.fillRoundRect(18, 18, 604, 604, 7,7 );
        
        g.setColor(new Color(34, 177, 76));
        for(int i=2; i<=30; i++){
            g.drawLine(20, i*20, 620, i*20);
            g.drawLine(i*20, 20, i*20, 620);
        }
        
        //bordes
        g.setColor(Color.BLACK);
        g.drawLine(20, 20, 620, 20);
        g.drawLine(20, 20, 20, 620);
        g.drawLine(20, 620, 620, 620);
        g.drawLine(620, 20, 620, 620);
        g.drawRoundRect(18, 18, 604, 604, 7,7 );
        
        /*snake.paint(g);
        
        for(Element p: game.getFoods()){
            p.paint(g);
        }
        
        for(Wall w: game.getWalls()){
            w.paint(g);
        }*/
        game.paint(g);
        
        if (init) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 200));
            g.drawString(""+recuento, 250, 370);
        }
    }
    
    public void start(){
        game.begin();
        game.initTimer();
        initThread();
        setFocusable(true);
        requestFocusInWindow();
        recuento();
    }
    
    private void recuento() {
        Thread h = new Thread(new Runnable(){
            public void run(){
                try{
                    while (recuento > 0) {
                        repaint();
                        Thread.sleep(700);
                        recuento--;
                    }
                    init = false;
                    recuento = 3;
                    hilo_game.start();
                }catch(Exception e) {}
            }
        });
        h.start();
    }
    
    private class Manager implements KeyListener{
        public void keyPressed(KeyEvent e){
            if(game.isInGame()){
                if(e.getKeyCode() == KeyEvent.VK_LEFT && snake.getHead().getLeft() == null){
                    orientation = Orientation.LEFT;
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getHead().getRight() == null){
                    orientation = Orientation.RIGHT;
                }else if(e.getKeyCode() == KeyEvent.VK_UP && snake.getHead().getTop() == null){
                    orientation = Orientation.TOP;
                }else if(e.getKeyCode() == KeyEvent.VK_DOWN && snake.getHead().getBottom() == null){
                    orientation = Orientation.BOTTOM;
                }else if(e.getKeyCode() == KeyEvent.VK_A) {
                    pause();
                }else if(e.getKeyCode() == KeyEvent.VK_D) {
                    resume();
                }
            }
        }
        public void keyReleased(KeyEvent e){}
        public void keyTyped(KeyEvent e){}
    }
    
    public boolean enJuego(){
        return game.isInGame();
    }
}
