package hw4.puzzle;

import java.util.Set;
import java.util.HashSet;

import edu.princeton.cs.introcs.In;

public class Word implements WorldState {
    private static Set<String> words;
    private static final String WORDFILE = "input/words10000.txt";
    private final String word;
    private final String goal;
    private WorldState prev;
    private int moves = 0;
    private boolean visited = false;

    public void setMoves(int n) {
        moves = n;
    }

    public int getMoves() {
        return moves;
    }

    public int allDist() {
        return estimatedDistanceToGoal() + moves;
    }

    public void setPrev(WorldState word) {
        prev = word;
    }

    public WorldState getPrev() {
        return prev;
    }

    /**
     * Reads the wordfile specified by the wordfile variable.
     */
    private void readWords() {
        words = new HashSet<String>();

        In in = new In(WORDFILE);
        while (!in.isEmpty()) {
            words.add(in.readString());
        }
    }

    /**
     * Creates a new Word.
     */
    public Word(String w, String g) {
        /* If words hasn't been read yet, read it. */
        if (words == null) {
            readWords();
        }

        if (!words.contains(w)) {
            throw new IllegalArgumentException("Invalid word: " + w);
        }

        if (!words.contains(g)) {
            throw new IllegalArgumentException("Invalid goal: " + g);
        }

        word = w;
        goal = g;
    }

    /**
     * Computes the edit distance between a and b. From
     * https://rosettacode.org/wiki/Levenshtein_distance.
     */
    public static int editDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                         a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }


    @Override
    public Iterable<WorldState> neighbors() {
        Set<WorldState> neighbs = new HashSet<>();
        for (String s : words) {
            if (editDistance(this.word, s) == 1) {
                neighbs.add(new Word(s, goal));
            }
        }
        return neighbs;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return editDistance(this.word, goal);
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Word word1 = (Word) o;

        if (word != null ? !word.equals(word1.word) : word1.word != null) {
            return false;
        }
        return goal != null ? goal.equals(word1.goal) : word1.goal == null;
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Word w = new Word("worse", "nurse");
        String s = "nurse";
        for (WorldState a : w.neighbors()) {
            System.out.println(a + " dis to " + s + " is " + Word.editDistance("nurse", a.toString()));
        }
    }

}
