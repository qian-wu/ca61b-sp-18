package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.Set;

public class Solver {
    private int moveTimes = 0;
    private Stack<WorldState> s;
    private MinPQ<WorldState> mp;
    private Set<WorldState> wordSet;

    public Solver(WorldState initial) {
        mp = new MinPQ<>(8, new WordComparator());
        s = new Stack<>();
        mp.insert(initial);
        Word curr = (Word) initial;

        while (!mp.isEmpty()) {
            curr = (Word) mp.delMin();
            System.out.println(curr);
            if (curr.isGoal()) {
                break;
            }
            for (WorldState word : curr.neighbors()) {
                Word w = (Word) word;
                if (curr.getPrev() == null || curr.getPrev() != null && !w.equals(curr.getPrev())) {
                    w.setPrev(curr);
                    w.setMoves(curr.getMoves() + 1);
                    mp.insert(word);
                }
            }

        }

        moveTimes = curr.getMoves();
        while (curr != null) {
            s.push(curr);
            curr = (Word) curr.getPrev();
        }
    }

    public int moves() {
        return moveTimes;
    }

    public Iterable<WorldState> solution() {
        return s;
    }
}