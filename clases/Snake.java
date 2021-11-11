package clases;


import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.Graphics2D;

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
    
    private void growingUp(PartSnake newPart){
        body.addLast(newPart);
        size++;
    }

    //pensar en otro nombre
    public void reduceBody(){
        if(isAlive()){
            body.pollLast();
            size--;
        }
    }

    public void toEat(Food food, PartSnake newPart){
        if(food != null) growingUp(newPart);
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
            g.setColor(new Color(163, 73, 164)); 
            int y = (p.getPositionInRow()+1)*20;
            int x = (p.getPositionInColumn()+1)*20;
            g.fillOval(x, y, 21, 21);
            if(p instanceof HeadSnake){ 
                paintTongue((Graphics2D)(g), x, y);
                paintHead(g, x, y);
            }else{
                g.setColor(Color.BLACK);
                g.drawOval(x, y, 21, 21);
                if(p != body.peekLast()){
                    g.setColor(new Color(192, 192, 192));
                    g.drawOval(x+2, y+2, 18, 18);
                }else{
                    g.setColor(new Color(210, 163, 0));
                    g.fillOval(x+6, y+6, 10, 10);
                }       
            }
        }
    }
    
    private void paintHead(Graphics g, int x, int y){
        Graphics2D g2 = (Graphics2D)g;
        if(head.getOrientation() == 1){ 
            g.setColor(new Color(163, 73, 164)); 
            g.fillOval(x-3, y+4, 15, 15);
            g.fillOval(x+8, y+4, 15, 15);
                
            g.setColor(Color.WHITE);
            g.fillOval(x, y+6, 10, 10);
            g.fillOval(x+11, y+6, 10, 10);
                
            g.setColor(Color.BLACK);
            g.fillOval(x+3,  y+5, 6, 6);
            g.fillOval(x+10, y+5, 6, 6);
        }else if(head.getOrientation() == 2){
            g.setColor(new Color(163, 73, 164)); 
            g.fillOval(x-3, y+2, 15, 15);
            g.fillOval(x+8, y+2, 15, 15);
                
            g.setColor(Color.WHITE);
            g.fillOval(x, y+4, 10, 10);
            g.fillOval(x+11, y+4, 10, 10);
                
            g.setColor(Color.BLACK);
            g.fillOval(x+3,  y+7, 6, 6);
            g.fillOval(x+10, y+7, 6, 6);
        }else if(head.getOrientation() == 3){
            g.setColor(new Color(163, 73, 164)); 
            g.fillOval(x+4, y-4, 15, 15);
            g.fillOval(x+4, y+10, 15, 15);
                
            g.setColor(Color.WHITE);
            g.fillOval(x+8, y, 10, 10);
            g.fillOval(x+8, y+10, 10, 10);
                
            g.setColor(Color.BLACK);
            g.fillOval(x+8,  y+3, 6, 6);
            g.fillOval(x+8,  y+10, 6, 6);
        }else if(head.getOrientation() == 4){
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