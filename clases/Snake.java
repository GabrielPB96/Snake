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
    private LinkedList<SnakePart> body;
    private SnakeHead head;

    public Snake(){
        init();
    }
    
    public Snake(int row_head, int column_head){
        init2(row_head, column_head);
    }
    
    private void init2(int row_head, int column_head){
        size = 2;
        body        = new LinkedList<SnakePart>();
        SnakePart p = new SnakePart(row_head+1, column_head, Orientation.TOP);
        head        = new SnakeHead(row_head, column_head, Orientation.TOP);
        head.setBottom(p);
        body.add(head);
        body.addLast(p);
    }

    private void init(){
        size = 2;
        body        = new LinkedList<SnakePart>();
        SnakePart p = new SnakePart(15, 14, Orientation.TOP);
        head        = new SnakeHead(14, 14, Orientation.TOP);
        head.setBottom(p);
        body.add(head);
        body.addLast(p);
    }

    public boolean isAlive(){
        return size >= 2;
    }

    public int size(){
        return size-1;
    }
    
    public LinkedList<SnakePart> getBody(){
        return body;
    }

    public SnakeHead getHead(){
        return head;
    }

    //pensar en otro nombre
    public void reduceBody(){
        if(isAlive()){
            body.pollLast();
            size--;
        }
    }

    public void toEat(SnakePart newPart){
        body.addLast(newPart);
        size++;
    }
    
    public void kill(){
        size = 0;
        head.getTongue().cancelTimer();
    }

    public void restart(){
        init();
    }

    private void moveBody(){
        for(SnakePart part: body){
            if(part != head){
                part.move();
                updateOrientationSnakePart(part);
            }
        }
    }

    private void updateOrientationSnakePart(SnakePart part){
        SnakePart partAnt  = body.get(body.indexOf(part)-1);
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
        if(head.moveLeft()) moveBody();
        if(head.getPositionInColumn() < 0){
            kill();
            return;
        }
        updateConexionHead();
    }

    public void runRight(){
        if(head.moveRight()) moveBody();
        if(head.getPositionInColumn() > LIMIT_POSITION_X){
            kill();
            return;
        }
        updateConexionHead();
    }

    public void runTop(){
        if(head.moveTop()) moveBody();
        if(head.getPositionInRow() < 0){
            kill();
            return;
        }
        updateConexionHead();
    }

    public void runBottom(){
        if(head.moveBottom()) moveBody();
        if(head.getPositionInRow() > LIMIT_POSITION_Y){
            kill();
            return;
        }
        updateConexionHead();
    }

    public boolean headShock(){
        for(SnakePart part: body){
            if(part != head) {
                if(part.equalsPosition(head)){
                    kill();
                    return true;
                }
            }
        }
        return false;
    }
        
    public void paint(Graphics g){
        for(Element p: body){
            if(p == head){ 
                p.paint(g);
            }else{
                SnakePart p2 = (SnakePart)p;
                if(p2 != body.peekLast()){
                    p2.paint(g, false);
                }else{
                    p2.paint(g, true);
                }   
            }
        }
    }
}