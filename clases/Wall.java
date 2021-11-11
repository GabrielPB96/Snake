package clases;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public abstract class Wall{
    protected ArrayList<Block> blocks;
    protected Color color;
    protected int length;
    protected Position center_position;
    
    public Wall(int length, int row_center, int column_center, Color color){
        this.length     = length;
        this.color      = color;
        blocks          = new ArrayList();
        center_position = new Position(row_center, column_center);
    }
    
    public Wall(int length, Position center, Color color){
        this.length     = length;
        this.color      = color;
        blocks          = new ArrayList();
        center_position = center;
    }
    
    protected abstract void createWall(int row_center, int column_center);
    
    public void rotateLeft(){
        for(Block block: blocks){
            Position position_rotate = computeRotatePosition(block.getPosition(), false);
            int new_row    = position_rotate.getRow() + center_position.getRow();
            int new_column = position_rotate.getColumn() + center_position.getColumn();
            block.setPosition(new Position(new_row, new_column));
        }
    }
    
    public void rotateRight(){
        for(Block block: blocks){
            Position position_rotate = computeRotatePosition(block.getPosition(), true);
            int new_row    = position_rotate.getRow() + center_position.getRow();
            int new_column = position_rotate.getColumn() + center_position.getColumn();
            block.setPosition(new Position(new_row, new_column));
        }
    }
    
    private Position computeRotatePosition(Position p, boolean horario){
        int row_origen    = p.getRow() - center_position.getRow();
        int column_origen = p.getColumn() - center_position.getColumn();
        if(horario) return new Position(column_origen, -row_origen);
        else return new Position(-column_origen, row_origen);
    }
    
    public void runTop(){
        runTopBottom(-1);
    }
    
    public void runBottom(){
        runTopBottom(1);
    }
    
    public void runLeft(){
        runLeftRight(-1);
    }
    
    public void runRight(){
        runLeftRight(1);
    }
    
    private void runTopBottom(int step){
        setCenter(new Position(center_position.getRow()+step, center_position.getColumn()));
        for(Block block: blocks){
            block.setPositionInRow(block.getPositionInRow()+step);
        }  
    }
    
    private void runLeftRight(int step){
        setCenter(new Position(center_position.getRow(), center_position.getColumn()+step));
        for(Block block: blocks){
            block.setPositionInColumn(block.getPositionInColumn()+step);
        }
    }
    
    public void rotateLeft(int cant_rotate){
        for(int i=1; i<=cant_rotate; i++) rotateLeft();
    }
    
    public void rotateRifht(int cant_rotate){
        for(int i=1; i<=cant_rotate; i++) rotateRight();
    }
    
    public void paint(Graphics g){
        for(Block block: blocks) block.paint(g);
    }
    
    public Position center(){
        return center_position;
    }
    
    public void setCenter(Position new_position){
        center_position = new_position;
    }
    
    public ArrayList<Block> getBlocks(){
        return blocks;
    }
}
