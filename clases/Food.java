package clases;
import utils.Element;

public abstract class Food extends Element{
    public Food(){
        super();
    }

    public Food(int positionInRow, int positionInColumn){
        super(positionInRow, positionInColumn);
    }
}