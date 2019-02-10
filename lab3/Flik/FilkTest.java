import org.junit.Test;
import static org.junit.Assert.*;

public class FilkTest {
    @Test
    public void testIsSameNumber() {
        int i = 127;
        for (int j = 127; i < 500; ++i, ++j) {
//            System.out.println("i is : " + i);
            assertTrue(Flik.isSameNumber(i, j));
        }
    }
}
