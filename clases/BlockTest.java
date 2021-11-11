package clases;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BlockTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BlockTest{
    @Test
    public void test1(){
        Block b1 = new Block(3, 3, null);
        Block b2 = new Block(4, 3, null);
        assertEquals(false, b1.getPosition().equals(b2.getPosition()));
    }
    
    @Test
    public void test2(){
        Block b1 = new Block(3, 3, null);
        Block b2 = new Block(3, 3, null);
        assertEquals(false, !b1.getPosition().equals(b2.getPosition()));
    }
}
