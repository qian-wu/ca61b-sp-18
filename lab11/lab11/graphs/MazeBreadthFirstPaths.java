package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

import java.util.LinkedList;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    public Queue<Integer> fringe = new Queue();

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);

        for(int i = 0; i < m.N() * m.N(); i++) {
            distTo[i] = 0;
            edgeTo[i] = 0;
        }

        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int s) {
        if (s == t) {
            return;
        }

        fringe.enqueue(s);
        while (!fringe.isEmpty()) {
            int v = fringe.dequeue();
            marked[v] = true;
            announce();

            for (int w : maze.adj(v)) {
                if (w == t) {
                    targetFound = true;
                }

                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    announce();

                    if (targetFound) {
                        return;
                    }

                    fringe.enqueue(w);
                }
            }
        }
    }


    @Override
    public void solve() {
//        for (int i : edgeTo) {
//            System.out.println("i : " + i);
//        }
        bfs(s);
        for (int i = 0; i < edgeTo.length; i++) {
            System.out.println("edgeTo : " + i + " " + edgeTo[i]);
        }

        for (int i = 0; i < distTo.length; i++) {
            System.out.println("distTo : " + i + " " + distTo[i]);
        }

    }
}

