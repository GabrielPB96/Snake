package utils;

import java.awt.Color;

public class ShapeJ extends Wall{
    public ShapeJ(int row_center, int column_center, Color color){
        super(4, row_center, column_center, color);
        createWall(row_center, column_center);
    }
    
    public ShapeJ(Position center, Color color){
        super(4, center, color);
        createWall(center.getRow(), center.getColumn());
    }
    
    protected void createWall(int row_center, int column_center){
        blocks.add(new Block(row_center-2, column_center, color));
        blocks.add(new Block(row_center-1, column_center, color));
        blocks.add(new Block(row_center, column_center, color));
        blocks.add(new Block(row_center, column_center-1, color));
    }
}
