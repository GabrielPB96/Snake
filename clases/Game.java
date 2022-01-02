package clases;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import javax.swing.JOptionPane;
import utils.*;
import java.util.Iterator;

public class Game{
    private Snake snake;
    private ArrayList<Food> foods;
    private ArrayList<Wall> walls;
    private final ArrayList<Position[]> op_walls;
    private boolean en_juego;
    private Timer timer_generate_foods;
    private TimerTask task;
    private boolean pause;
    private Random random;
    private int level;
    private Element board[][];
    
    private FoodFactory factory;
    
    {
        op_walls = new ArrayList<Position[]>(3);
        //Tres opciones, [[[]]]
        Position op1[] = {new Position(3, 3), new Position(3, 26), new Position(26, 3), new Position(26, 26)};
        Position op2[] = {new Position(6, 6), new Position(6, 23), new Position(23, 6), new Position(23, 23)};
        Position op3[] = {new Position(9, 9), new Position(9, 20), new Position(20, 9), new Position(20, 20)};
        op_walls.add(op1);
        op_walls.add(op2);
        op_walls.add(op3);
    }

    public Game(){
        snake      = new Snake();
        factory    = new RandomFood();
        foods      = new ArrayList<Food>();
        walls      = new ArrayList<Wall>();
        en_juego   = false;
        pause      = true;
        random     = new Random();
        level      = 1;
        board      = new Element[30][30];
        createWalls();
        updateBoard();
    }
    
    public Game(Snake snake){
        this.snake = snake;
        factory    = new RandomFood();
        foods      = new ArrayList<Food>();
        walls      = new ArrayList<Wall>();
        en_juego   = false;
        pause      = true;
        random     = new Random();
        level      = 1;
        board      = new Element[30][30];
        createWalls();
        updateBoard();
    }
    
    public void initTimer(){
        task = new TimerTask(){
            public void run(){
                if(!pause) generateFood();
            }
        };
        timer_generate_foods = new Timer();
        timer_generate_foods.schedule(task, 5000, 4000);
    }
    
    public void finishTimer(){
        timer_generate_foods.cancel();
    }

    public ArrayList<Food> getFoods(){
        return foods;
    }
    
    public ArrayList<Wall> getWalls(){
        return walls;
    }
    
    public boolean isPause(){
        return pause;
    }
    
    public void pause(){
        pause = true;
        for(Food f : foods) {
            f.pauseContador();
        }
    }
    
    public void resume(){
        pause = false;
        for(Food f : foods) {
            f.resumeContador();
        }
    }
    
    public void begin(){
        en_juego = true;
        pause    = false;
        updateBoard();
    }
    
    public boolean isInGame(){
        return en_juego;
    }
    
    public Snake getSnake(){
        return snake;
    }
    
    public int getLevel(){
        return level;
    }
    
    public void nextLevel(){
        if(level < 3) level++;
        else level = 1;
        createWalls();
    }
    
    public boolean win(){
        if(level == 1){
            return snake.size() >= 30;
        }else if(level == 2){ 
            return snake.size() >= 40;
        }else return snake.size() >= 50;
    }
    
    public void end(){
        en_juego = pause = false;
    }
    
    public void restartSnake(){
        snake.restart();
        foods.clear();
    }
    
    private void createWalls(){
        walls.clear();
        Position positions_walls[] = null;
        Wall newWall = null;
        int pos = level;
        while(pos-1 >= 0){
            positions_walls = op_walls.get(pos-1);
            for(int i=0; i<positions_walls.length; i++){
                if(i == 1 || i == 2){
                    if(i == 1){
                        newWall = new ShapeEle(positions_walls[i], new Color(160, 68, 46));
                        newWall.rotateLeft(2);
                        walls.add(newWall);
                    }else{
                        newWall = new ShapeEle(positions_walls[i], new Color(160, 68, 46));
                        walls.add(newWall);
                    }
                }else{
                    if(i == 0){
                        newWall = new ShapeJ(positions_walls[i], new Color(160, 68, 46));
                        newWall.rotateLeft(2);
                        walls.add(newWall);
                    }else{
                        newWall = new ShapeJ(positions_walls[i], new Color(160, 68, 46));
                        walls.add(newWall);
                    }
                }
            }
            pos--;
        }
    }
    
    private PartSnake computeNexPart(PartSnake lastPart, HeadSnake headSnake){
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

        if((positionRow >= 0 && positionRow < board.length) && (positionColumn >= 0 && positionColumn < board.length)){
            return new PartSnake(positionRow, positionColumn, lastPart.getOrientation());
        }else{
            if(positionRow < 0 || positionRow >= board.length){
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
    
    public synchronized void interactuar(){
        PartSnake head = snake.getHead();

        for(Food food: foods){
            if(food.equalsPosition(head)){
                food.kill();
                foods.remove(food);
                if(food instanceof Venom){
                    snake.reduceBody();
                    if(!snake.isAlive())
                        en_juego = false;
                    break;
                }else if(food instanceof Bomb){
                    snake.kill();
                    en_juego = false;
                    break;
                }else{
                    PartSnake newPart1  = computeNexPart(snake.getBody().peekLast(), snake.getHead());
                    snake.toEat(food, newPart1);                    

                    if(food instanceof Mouse){
                        PartSnake newPart2 = computeNexPart(snake.getBody().peekLast(), snake.getHead());
                        snake.toEat(food, newPart2);
                    }
                    break;
                }
            }
        }
        if(headShockWalls()) snake.kill();
        if(!snake.isAlive()) en_juego = false;
    }

    public synchronized void generateFood(){
        int positionRow, positionColumn;
        boolean volver;
        do{
            positionRow    = (int)(random.nextInt(board.length));
            positionColumn = (int)(random.nextInt(board.length));
            volver = true;
            if (board[positionRow][positionColumn] != null) volver  = board[positionRow][positionColumn].isEmpty();
        }while(!volver);

        Food food = factory.createFood();
        food.setPositionInRow(positionRow);
        food.setPositionInColumn(positionColumn);
        foods.add(food);
    }

    public synchronized void updateBoard(){
        board = new Element[30][30];
        for(PartSnake part: snake.getBody())
            board[part.getPositionInRow()][part.getPositionInColumn()] = part;
        var deads = new ArrayList<Food>();
        for(Element element : foods) {
            Food f = (Food)element;
            if (f.vivo()) {
                board[f.getPositionInRow()][f.getPositionInColumn()] = f; 
            }else{
                deads.add(f);
            }
        }
        for(Food d : deads) {
            foods.remove(d);
        }
        for(Wall wall: walls)
            for(Block block: wall.getBlocks())
                board[block.getPositionInRow()][block.getPositionInColumn()] = block;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == null) board[i][j] = new Element();
            }
        }
    }
    
    public synchronized void paint(Graphics g) {
        snake.paint(g);
        for(Element p: foods){
            p.paint(g);
        }
        
        for(Wall w: walls){
            w.paint(g);
        }
    }

    public boolean headShockWalls(){
        for(Wall wall: walls){
            for(Block block: wall.getBlocks()){
                if(block.getPosition().equals(snake.getHead().getPosition())){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean verifyConditions(){
        return isInGame() && snake.isAlive() && !snake.headShock() && !win();
    }
}