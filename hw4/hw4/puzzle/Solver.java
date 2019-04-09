package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.*;

public class Solver {
    private static Map<String, Integer> distMap = new HashMap<>();

    private class Node implements Comparable<Node>{
        private WorldState ws;
        private int times;
        private Node prev;

        Node(WorldState ws, int times, Node prev) {
            this.ws = ws;
            this.times = times;
            this.prev = prev;
        }

        boolean isGoal() {
            return ws.isGoal();
        }

        Iterable<Node> neighbors() {
            Set<Node> neighbors = new HashSet<>();
            for (WorldState w : ws.neighbors()) {
                neighbors.add(new Node(w, this.times + 1, this));
            }
            return neighbors;
        }

        public int getTimes() {
            return times;
        }

        public Node getPrev() {
            return prev;
        }

        public int getPiri() {
            int distToGoal = 0;
            if (distMap.containsKey(ws.toString())) {
                distToGoal = distMap.get(ws.toString());
//                System.out.println("hit " + ws.toString());
            } else {
                distToGoal = ws.estimatedDistanceToGoal();
                distMap.put(ws.toString(), distToGoal);
            }
            return times + distToGoal;
        }

        public WorldState getWorld() {
            return ws;
        }

        @Override
        public int compareTo(Node o) {
            return this.getPiri() - o.getPiri();
        }

        @Override
        public boolean equals(Object obj) {
            WorldState w2 = ((Node) obj).getWorld();
            return ws.equals(w2);
        }
    }

    private Stack<WorldState> s;
    private MinPQ<Node> mp;
    private int moveTimes;

    public Solver(WorldState initial) {
        mp = new MinPQ<>();
        s = new Stack<>();

        Node curr = new Node(initial, 0, null);
        mp.insert(curr);

        while (!mp.isEmpty()) {
            curr = mp.delMin();
//            System.out.println(curr.getWorld());
            if (curr.isGoal()) {
                break;
            }
            for (Node node : curr.neighbors()) {
                if (curr.getPrev() == null || curr.getPrev() != null && !node.equals(curr.getPrev())) {
                    mp.insert(node);
                }
            }

        }

        moveTimes = curr.getTimes();
        while (curr != null) {
            s.push(curr.getWorld());
            curr = curr.getPrev();
        }
    }

    public int moves() {
        return moveTimes;
    }

    public Iterable<WorldState> solution() {
        return s;
    }
}