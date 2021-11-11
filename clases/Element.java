package clases;
import java.awt.Graphics;


public class Element{
    protected Position position;

    public Element(){
        position = new Position(-1, -1);
    }

    public Element(int positionInRow, int positionInColumn){
        position = new Position(positionInRow, positionInColumn);
    }

    public Position getPosition(){
        return position;
    }
    
    public void setPosition(Position new_position){
        position = new_position;
    }
    
    public boolean isEmpty(){
        return position.getRow() == -1;
    }

    public int getPositionInRow(){
        return position.getRow();
    }

    public int getPositionInColumn(){
        return position.getColumn();
    }

    public void setPositionInRow(int newPosition){
        position.setRow(newPosition);
    }

    public void setPositionInColumn(int newPosition){
        position.setColumn(newPosition);
    }

    public boolean equalsPosition(Element e){
        return position.equals(e.getPosition());
    }

    public String toString(){
        return "("+position.getRow()+","+position.getColumn()+")";
    }
    
    public void paint(Graphics g){}
}