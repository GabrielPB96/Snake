package clases;


/**
 * Write a description of class Snake here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.geom.*;
import utils.Orientation;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeTongue extends Rectangle2D.Double{
    private Color color;
    private int orientation;
    private Timer timer;
    private TimerTask task;
    
    public SnakeTongue(int x, int y, int width, int height, Color color){
        super(x, y, width, height);
        this.color = color;
        orientation = Orientation.TOP;
    }
    
    public void setOrientation(int o) {
        orientation = o;
    }
    
    public void initTimer() {
        task = new TimerTask(){
            public void run() {
                crecer();
                try{
                    Thread.sleep(500);
                }catch(Exception e) {}
                menguar();
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 1000);
    }
    
    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        } 
    }
    
    public void crecer() {
        if(orientation == Orientation.TOP) {
            y -= 15;
            height += 15;
        }else if(orientation == Orientation.BOTTOM) {
            height += 15;
        }else if(orientation == Orientation.RIGHT) {
            width += 15;
        }else if(orientation == Orientation.LEFT) {
            x -= 15;
            width += 15;
        }
    }
    
    public void menguar() {
        if(orientation == Orientation.TOP) {
            y += 15;
            height -= 15;
        }else if(orientation == Orientation.BOTTOM) {
            height -= 15;
        }else if(orientation == Orientation.RIGHT) {
            width -= 15;
        }else if(orientation == Orientation.LEFT) {
            x += 15;
            width -= 15;
        }
    }
    
    private void computeWidthHeight(){
        if(orientation == Orientation.TOP || orientation == Orientation.BOTTOM){
            height = 1;
            width  = 4;
        }else{
            height = 4;
            width  = 1;
        }
    }
    
    public void computeFrame(SnakeHead ref) {
        int nY = (ref.getPositionInRow()+1)*20;
        int nX = (ref.getPositionInColumn()+1)*20;
        orientation = ref.getOrientation();
        //computeWidthHeight();
        if(orientation == Orientation.TOP){
            x = nX+8;
            y = nY;
        }else if(orientation == Orientation.BOTTOM){
            x = nX+8;
            y = nY+21;
        }else if(orientation == Orientation.LEFT){
            x = nX;
            y = nY+8;
        }else if(orientation == Orientation.RIGHT){
            x = nX+21;
            y = nY+8;
        }   
    }
    
    public void paint(Graphics2D g2){
        g2.setColor(color);
        setFrame(x, y, width, height);
        g2.fill(this);
    }
}
