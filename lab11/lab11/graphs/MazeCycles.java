package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s, t;
    private boolean targetFound = false;
    private int[] edgeTrace;
    private Stack edgeStack = new Stack();
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        t = maze.xyTo1D(20, 20);

        edgeTrace = new int[maze.V()];
        distTo[s] = 0;
        edgeTrace[s] = s;
    }

    @Override
    public void solve() {
        dfs(s, s);
    }

    // Helper methods go here
    private void dfs(int parent, int curr) {
        marked[curr] = true;
        announce();


        for (int next : maze.adj(curr)) {
            edgeStack.push(next);

            if (!marked[next]) {
                edgeTrace[next] = curr;
                announce();
                distTo[next] = distTo[curr] + 1;
                dfs(curr, next);
                if (targetFound) {
                    return;
                }
            } else if (marked[next] && next != parent) {
                edgeTrace[next] = curr;
                targetFound = true;
                int tmp, count = 1;
                edgeStack.pop();

                while ((int )edgeStack.pop() != next) {
                    count ++;
                }

                for (int i = 0; i < count; i++) {
                    edgeTo[curr] = edgeTrace[curr];
                    announce();
                    curr = edgeTrace[curr];
                }
                break;
            }
        }

        if (targetFound) return;
    }
}

