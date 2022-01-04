package clases;


/**
 * Write a description of class PartSnakeFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import utils.Orientation;
public class PartSnakeFactory {
    public final int SIZE_BOARD = 30;
    public static PartSnakeFactory instance;
    
    static{
        instance = new PartSnakeFactory();
    }
    
    public static PartSnakeFactory getInstance() {
        return instance;
    }
    
    private PartSnakeFactory(){}
    
    public PartSnake computeNexPart(PartSnake lastPart, HeadSnake headSnake){
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
            return new PartSnake(positionRow, positionColumn, lastPart.getOrientation());
        }else{
            if(positionRow < 0 || positionRow >= SIZE_BOARD){
                if(lastPart.getPositionInColumn() + 1 != headSnake.getPositionInColumn()){
                    return new PartSnake(lastPart.getPositionInRow(), lastPart.getPositionInColumn()+1, Orientation.LEFT);
                }else{
                    return new PartSnake(lastPart.getPositionInRow(), lastPart.getPositionInColumn()-1, Orientation.RIGHT);
                }
            }else{
                if(lastPart.getPositionInRow() + 1 != headSnake.getPositionInRow()){
                    return new PartSnake(lastPart.getPositionInRow()+1, lastPart.getPositionInColumn(), Orientation.TOP);
                }else{
                    return new PartSnake(lastPart.getPositionInRow()-1, lastPart.getPositionInColumn(), Orientation.BOTTOM);
                }
            }
        }
    }
}
