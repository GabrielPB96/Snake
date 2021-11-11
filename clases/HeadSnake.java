package clases;


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
}