package clases;


/**
 * Write a description of class SnakePartFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import utils.Orientation;
public class SnakePartFactory {
    public final int SIZE_BOARD = 30;
    public static SnakePartFactory instance;
    
    static{
        instance = new SnakePartFactory();
    }
    
    public static SnakePartFactory getInstance() {
        return instance;
    }
    
    private SnakePartFactory(){}
    
    public SnakePart computeNexPart(SnakePart lastPart, SnakeHead headSnake){
        int positionRow, positionColumn;
        positionRow    = lastPart.getPositionInRow();
        positionColumn = lastPart.getPositionInColumn();

        if(lastPart.getOrientation() == Orientation.TOP){
            positionRow += 1; 
        }else if(lastPart.getOrientation() == Orientation.BOTTOM){
            positionRow -= 1;
        }else if(lastPart.getOrientation() == Orientation.LEFT){
            positionColumn += 1;
        }else if(lastPart.getOrientation() == Orientation.RIGHT){
            positionColumn -= 1;
        }

        if((positionRow >= 0 && positionRow < SIZE_BOARD) && (positionColumn >= 0 && positionColumn < SIZE_BOARD)){
            return new SnakePart(positionRow, positionColumn, lastPart.getOrientation());
        }else{
            if(positionRow < 0 || positionRow >= SIZE_BOARD){
                if(lastPart.getPositionInColumn() + 1 != headSnake.getPositionInColumn()){
                    return new SnakePart(lastPart.getPositionInRow(), lastPart.getPositionInColumn()+1, Orientation.LEFT);
                }else{
                    return new SnakePart(lastPart.getPositionInRow(), lastPart.getPositionInColumn()-1, Orientation.RIGHT);
                }
            }else{
                if(lastPart.getPositionInRow() + 1 != headSnake.getPositionInRow()){
                    return new SnakePart(lastPart.getPositionInRow()+1, lastPart.getPositionInColumn(), Orientation.TOP);
                }else{
                    return new SnakePart(lastPart.getPositionInRow()-1, lastPart.getPositionInColumn(), Orientation.BOTTOM);
                }
            }
        }
    }
}
