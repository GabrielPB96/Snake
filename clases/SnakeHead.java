package clases;
import java.awt.*;
import utils.Orientation;

public class SnakeHead extends SnakePart{
    private SnakePart top, bottom, left, right;
    private SnakeTongue tongue;

    public SnakeHead(int positionInRow, int positionInColumn, int orientation){
        super(positionInRow, positionInColumn, orientation);
        tongue = new SnakeTongue(0, 0, 4, 8, Color.BLUE);
        tongue.computeFrame(this);
    }

    public void setTop(SnakePart newTop){
        top = newTop;
    }

    public void setBottom(SnakePart newBottom){
        bottom = newBottom;
    }
    public void setLeft(SnakePart newLeft){
        left = newLeft;
    }

    public void setRight(SnakePart newRight){
        right = newRight;
    }

    public SnakePart getTop(){
        return top;
    }

    public SnakePart getBottom(){
        return bottom;
    }

    public SnakePart getLeft(){
        return left;
    }

    public SnakePart getRight(){
        return right;
    }

    public SnakePart getConexion(){
        if(top != null) return top;
        if(bottom != null) return bottom;
        if(left != null) return left;
        if(right != null) return right;
        return null;
    }
    
    public SnakeTongue getTongue() {
        return tongue;
    }
    
    public boolean moveTop() {
        if(top == null){
            setPositionInRow(getPositionInRow()-1);
            setOrientation(Orientation.TOP);
            tongue.computeFrame(this);
            return true;
        }
        return false;
    }
    
    public boolean moveBottom() {
         if(bottom == null){
            setPositionInRow(getPositionInRow()+1);
            setOrientation(Orientation.BOTTOM);
            tongue.computeFrame(this);
            return true;  
        }
        return false;
    }
    
    public boolean moveRight() {
        if(right == null){
            setPositionInColumn(getPositionInColumn()+1);
            setOrientation(Orientation.RIGHT);
            tongue.computeFrame(this);
            return true;
        }
        return false;
    }
    
    public boolean moveLeft() {
        if(left == null){
            setPositionInColumn(getPositionInColumn()-1);
            setOrientation(Orientation.LEFT);
            tongue.computeFrame(this);
            return true;
        }
        return false;
    }
    
    public void updateConexion(SnakePart new_conexion, int orientation_new_conexion){
        top = bottom = right = left = null;
        if(orientation_new_conexion == Orientation.TOP) bottom = new_conexion;
        else if(orientation_new_conexion == Orientation.BOTTOM) top = new_conexion;
        else if(orientation_new_conexion == Orientation.RIGHT) left = new_conexion;
        else right = new_conexion;
    }
    
    public void paint(Graphics g){
        int y = (getPositionInRow()+1)*20;
        int x = (getPositionInColumn()+1)*20;
        
        tongue.paint((Graphics2D)g);
        
        g.setColor(new Color(163, 73, 164)); 
        g.fillOval(x, y, 21, 21);
        Graphics2D g2 = (Graphics2D)g;
        if(orientation == 1){ 
            g.setColor(new Color(163, 73, 164)); 
            g.fillOval(x-3, y+4, 15, 15);
            g.fillOval(x+8, y+4, 15, 15);
                
            g.setColor(Color.WHITE);
            g.fillOval(x, y+6, 10, 10);
            g.fillOval(x+11, y+6, 10, 10);
                
            g.setColor(Color.BLACK);
            g.fillOval(x+3,  y+5, 6, 6);
            g.fillOval(x+10, y+5, 6, 6);
        }else if(orientation == 2){
            g.setColor(new Color(163, 73, 164)); 
            g.fillOval(x-3, y+2, 15, 15);
            g.fillOval(x+8, y+2, 15, 15);
                
            g.setColor(Color.WHITE);
            g.fillOval(x, y+4, 10, 10);
            g.fillOval(x+11, y+4, 10, 10);
                
            g.setColor(Color.BLACK);
            g.fillOval(x+3,  y+7, 6, 6);
            g.fillOval(x+10, y+7, 6, 6);
        }else if(orientation == 3){
            g.setColor(new Color(163, 73, 164)); 
            g.fillOval(x+4, y-4, 15, 15);
            g.fillOval(x+4, y+10, 15, 15);
                
            g.setColor(Color.WHITE);
            g.fillOval(x+8, y, 10, 10);
            g.fillOval(x+8, y+10, 10, 10);
                
            g.setColor(Color.BLACK);
            g.fillOval(x+8,  y+3, 6, 6);
            g.fillOval(x+8,  y+10, 6, 6);
        }else if(orientation == 4){
            g.setColor(new Color(163, 73, 164)); 
            g.fillOval(x, y-4, 15, 15);
            g.fillOval(x, y+10, 15, 15);
                
            g.setColor(Color.WHITE);
            g.fillOval(x+3, y, 10, 10);
            g.fillOval(x+3, y+10, 10, 10);
                
            g.setColor(Color.BLACK);
            g.fillOval(x+8,  y+3,  6, 6);
            g.fillOval(x+8,  y+10, 6, 6);
        }
        g.setColor(Color.GREEN);
    }
}