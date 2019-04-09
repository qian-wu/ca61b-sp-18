package hw4.puzzle;

import java.util.Comparator;

public class WordComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Word w1 = (Word) o1;
        Word w2 = (Word) o2;

        return w1.allDist() - w2.allDist();
    }
}
