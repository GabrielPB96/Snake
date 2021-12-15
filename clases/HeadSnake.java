package clases;
import java.awt.*;
import utils.Orientation;

public class HeadSnake extends PartSnake{
    private PartSnake top, bottom, left, right;

    public HeadSnake(int positionInRow, int positionInColumn, int orientation){
        super(positionInRow, positionInColumn, orientation);
    }

    public void setTop(PartSnake newTop){
        top = newTop;
    }

    public void setBottom(PartSnake newBottom){
        bottom = newBottom;
    }
    public void setLeft(PartSnake newLeft){
        left = newLeft;
    }

    public void setRight(PartSnake newRight){
        right = newRight;
    }

    public PartSnake getTop(){
        return top;
    }

    public PartSnake getBottom(){
        return bottom;
    }

    public PartSnake getLeft(){
        return left;
    }

    public PartSnake getRight(){
        return right;
    }

    public PartSnake getConexion(){
        if(top != null) return top;
        if(bottom != null) return bottom;
        if(left != null) return left;
        if(right != null) return right;
        return null;
    }
    
    public void updateConexion(PartSnake new_conexion, int orientation_new_conexion){
        top = bottom = right = left = null;
        if(orientation_new_conexion == Orientation.TOP) bottom = new_conexion;
        else if(orientation_new_conexion == Orientation.BOTTOM) top = new_conexion;
        else if(orientation_new_conexion == Orientation.RIGHT) left = new_conexion;
        else right = new_conexion;
    }
    
    public void paint(Graphics g, int x, int y){
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