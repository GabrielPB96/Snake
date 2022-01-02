package clases;
import java.util.Random;


/**
 * Write a description of interface FoodFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface FoodFactory {
    public final Random random = new Random();
    
    public abstract Food createFood();
}
