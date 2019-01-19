import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    private StudentArrayDeque<Integer> stuDque = new StudentArrayDeque<>();
    private ArrayDequeSolution<Integer> solDeque = new ArrayDequeSolution<>();

    @Test
    public void testOnDeque() {
        int number, oper;

        Integer actual = 0;
        Integer expect = 0;

        while (actual.equals(expect)) {
            number = StdRandom.uniform(1, 1000);
            oper = StdRandom.uniform(0, 4);

            switch (oper) {
                case 0 :
                    stuDque.addFirst(number);
                    solDeque.addFirst(number);
                    break;
                case 1 :
                    stuDque.addLast(number);
                    solDeque.addLast(number);
                    break;
                case 2 :
                    if (stuDque.size() == 0 || solDeque.size() == 0) {
                        break;
                    }
                    expect = stuDque.removeFirst();
                    actual = solDeque.removeFirst();
                    break;
                case 3 :
                    if (stuDque.size() == 0 || solDeque.size() == 0) {
                        break;
                    }
                    expect = stuDque.removeLast();
                    actual = solDeque.removeLast();
                    break;
            }
        }

        assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                        + " not equal to " + expect + "!",
                expect, actual);
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(AssertEqualsStringDemo.class);
    }
}
