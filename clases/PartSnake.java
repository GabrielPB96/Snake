package clases;

public class PartSnake extends Element implements Orientation{
    public int orientation;

    public PartSnake(){
        super();
        orientation = RIGHT;
    }

    public PartSnake(int positionInRow, int positionInColumn, int orientation){
        super(positionInRow, positionInColumn);
        try{
            if(orientation != TOP && orientation != BOTTOM && orientation != LEFT && orientation != RIGHT)
                throw new Exception("Orientation Undefined");
            else
                this.orientation = orientation;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int getOrientation(){
        return orientation;
    }

    public void setOrientation(int newOrientation){
        orientation = newOrientation;
    }
}