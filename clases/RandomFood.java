package clases;


/**
 * Write a description of class RandomFood here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomFood implements FoodFactory {
    public Food createFood() {
        int typeFood = random.nextInt(13);
        Food food = null;
        
        if(typeFood < 6)
            food = new Chicken();
        else if(typeFood > 5 && typeFood < 10)
            food = new Mouse();
        else if(typeFood > 10 && typeFood < 12)
            food = new Venom();
        else
            food = new Bomb();

        return food;
    }
}
