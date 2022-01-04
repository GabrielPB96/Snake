package clases;


import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.Graphics2D;
import utils.Orientation;
import utils.Element;

public class Snake{
    private final int LIMIT_POSITION_X = 29, LIMIT_POSITION_Y = 29;

    private int size;
    private LinkedList<PartSnake> body;
    private HeadSnake head;
    private Rectangle2D tongue;
    private int widthTongue, heightTongue;
    private int xTongue, yTongue; 

    public Snake(){
        init();
    }
    
    public Snake(int row_head, int column_head){
        init2(row_head, column_head);
    }
    
    private void init2(int row_head, int column_head){
        size = 2;
        body        = new LinkedList<PartSnake>();
        PartSnake p = new PartSnake(row_head+1, column_head, Orientation.TOP);
        head        = new HeadSnake(row_head, column_head, Orientation.TOP);
        head.setBottom(p);
        body.add(head);
        body.addLast(p);
        initTongue();
    }

    private void initTongue(){
        widthTongue  = 4;
        heightTongue = 8;
        xTongue = 0;
        yTongue = 0;
        tongue  = new Rectangle2D.Double(xTongue , yTongue, widthTongue, heightTongue);
    }
    
    private void init(){
        size = 2;
        body        = new LinkedList<PartSnake>();
        PartSnake p = new PartSnake(15, 14, Orientation.TOP);
        head        = new HeadSnake(14, 14, Orientation.TOP);
        head.setBottom(p);
        body.add(head);
        body.addLast(p);
        initTongue();
    }

    public boolean isAlive(){
        return size >= 2;
    }

    public int size(){
        return size-1;
    }
    
    public LinkedList<PartSnake> getBody(){
        return body;
    }

    public HeadSnake getHead(){
        return head;
    }

    //pensar en otro nombre
    public void reduceBody(){
        if(isAlive()){
            body.pollLast();
            size--;
        }
    }

    public void toEat(PartSnake newPart){
        body.addLast(newPart);
        size++;
    }
    
    public void kill(){
        size = 0;
    }

    public void restart(){
        init();
    }

    private void moveBody(){
        for(PartSnake part: body){
            if(part != head){
                if(part.getOrientation() == Orientation.TOP)
                    part.setPositionInRow(part.getPositionInRow()-1);
                else if(part.getOrientation() == Orientation.BOTTOM)
                    part.setPositionInRow(part.getPositionInRow()+1);
                else if(part.getOrientation() == Orientation.LEFT)
                    part.setPositionInColumn(part.getPositionInColumn()-1);
                else if(part.getOrientation() == Orientation.RIGHT)
                    part.setPositionInColumn(part.getPositionInColumn()+1);
                updateOrientationPartSnake(part);
            }
        }
    }

    private void updateOrientationPartSnake(PartSnake part){
        PartSnake partAnt  = body.get(body.indexOf(part)-1);
        if(partAnt.getPositionInRow() == part.getPositionInRow()){
            if(part.getPositionInColumn() > partAnt.getPositionInColumn())
                part.setOrientation(Orientation.LEFT);
            else part.setOrientation(Orientation.RIGHT);
        }else if(partAnt.getPositionInColumn() == part.getPositionInColumn()){
            if(part.getPositionInRow() > partAnt.getPositionInRow())
                part.setOrientation(Orientation.TOP);
            else part.setOrientation(Orientation.BOTTOM);
        }
    }

    private void updateConexionHead(){
        head.updateConexion(body.get(1), body.get(1).getOrientation());
    }

    public void runLeft(){
        if(head.getLeft() == null){
            head.setPositionInColumn(head.getPositionInColumn()-1);
            head.setOrientation(Orientation.LEFT);
            moveBody();
        }
        if(head.getPositionInColumn() < 0){
            kill();
            return;
        }
        updateConexionHead();
    }

    public void runRight(){
        if(head.getRight() == null){
            head.setPositionInColumn(head.getPositionInColumn()+1);
            head.setOrientation(Orientation.RIGHT);
            moveBody();
        }
        if(head.getPositionInColumn() > LIMIT_POSITION_X){
            kill();
            return;
        }
        updateConexionHead();
    }

    public void runTop(){
        if(head.getTop() == null){
            head.setPositionInRow(head.getPositionInRow()-1);
            head.setOrientation(Orientation.TOP);
            moveBody();
        }
        if(head.getPositionInRow() < 0){
            kill();
            return;
        }
        updateConexionHead();
    }

    public void runBottom(){
        if(head.getBottom() == null){
            head.setPositionInRow(head.getPositionInRow()+1);
            head.setOrientation(Orientation.BOTTOM);
            moveBody();
        }
        if(head.getPositionInRow() > LIMIT_POSITION_Y){
            kill();
            return;
        }
        updateConexionHead();
    }
    
    private void computeWidthHeightTongue(int o){
        if(o == Orientation.TOP || o == Orientation.BOTTOM){
            heightTongue = 8;
            widthTongue  = 4;
        }else{
            heightTongue = 4;
            widthTongue  = 8;
        }
    }
    
    public void paintTongue(Graphics2D g2, int x, int y){
        g2.setColor(Color.RED);
        computeWidthHeightTongue(head.getOrientation());
        if(head.getOrientation() == Orientation.TOP){
            xTongue = x+8;
            yTongue = y-7;
            tongue.setFrame(xTongue, yTongue, widthTongue, heightTongue);
            g2.fill(tongue);
        }else if(head.getOrientation() == Orientation.BOTTOM){
            xTongue = x+8;
            yTongue = y+20;
            tongue.setFrame(xTongue, yTongue, widthTongue, heightTongue);
            g2.fill(tongue);
        }else if(head.getOrientation() == Orientation.LEFT){
            xTongue = x-7;
            yTongue = y+8;
            tongue.setFrame(xTongue, yTongue, widthTongue, heightTongue);
            g2.fill(tongue);
        }else if(head.getOrientation() == Orientation.RIGHT){
            xTongue = x+20;
            yTongue = y+7;
            tongue.setFrame(xTongue, yTongue, widthTongue, heightTongue);
            g2.fill(tongue);
        }
    }
    
    public boolean headShock(){
        for(PartSnake part: body){
            if(part != head)
                if(part.equalsPosition(head)){
                    kill();
                    return true;
                }
        }
        return false;
    }
        
    public void paint(Graphics g){
        for(Element p: body){
            int y = (p.getPositionInRow()+1)*20;
            int x = (p.getPositionInColumn()+1)*20;
            if(p instanceof HeadSnake){ 
                paintTongue((Graphics2D)(g), x, y);
                p.paint(g, x, y);
            }else{
                PartSnake p2 = (PartSnake)p;
                if(p2 != body.peekLast()){
                    p2.paint(g, x, y, false);
                }else{
                    p2.paint(g, x, y, true);
                }   
            }
        }
    }
}