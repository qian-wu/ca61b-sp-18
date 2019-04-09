package hw4.puzzle;

public interface WorldState {
    /** Provides an estimate of the number of moves to reach the goal.
     * Must be less than or equal to the correct distance. */
    int estimatedDistanceToGoal();

    /** Provides an iterable of all the neighbors of this WorldState. */
    Iterable<WorldState> neighbors();

//    /** Set prev node **/
//    void setPrev(WorldState word);
//
//    /** Get prev node **/
//    WorldState getPrev();
//
//    /** Get all moves to this node **/
//    int getMoves();
//
//    /** Set all moves to this node **/
//    void setMoves(int n);

    default boolean isGoal() {
        return estimatedDistanceToGoal() == 0;
    }
}
