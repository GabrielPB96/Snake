package utils;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PositionTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PositionTest{
    @Test
    public void test1(){
        Position p1 = new Position(3, 3);
        assertEquals(false, p1.equals(new Position(3, 4)));
    }
    @Test
    public void test2(){
        Position p1 = new Position(3, 3);
        assertEquals(false, !p1.equals(new Position(3, 3)));
    }
}
