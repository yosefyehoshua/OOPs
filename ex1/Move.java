/**
 * The Move class represents a move in the Nim game by a player. A move consists of the row on which it is
 * applied, the left bound (inclusive) of the sequence of sticks to mark, and the right bound (inclusive) of
 * the same sequence.
 */
public class Move
        extends java.lang.Object {
    /** inTheRow - The row on which the move is performed. */
    int inTheRow;

    /** fromTheLeft - The left bound of the sequence to mark. */
    int fromTheLeft;

    /** toTheRight - The right bound of the sequence to mark. */
    int toTheRight;

    /**
     * Constructs a Move object with the given parameters.
     * @param inRow The row on which the move is performed.
     * @param inLeft The left bound of the sequence to mark.
     * @param inRight The right bound of the sequence to mark.
     */
    public Move(int inRow, int inLeft, int inRight) {
        inTheRow = inRow;
        fromTheLeft = inLeft;
        toTheRight = inRight;
    }

    /**
     * @return The right bound of the stick sequence to mark.
     */
    public int getLeftBound() {
        return fromTheLeft;
    }

    /**
     * @return The row on which the move is performed.
     */
    public int getRightBound() {
        return toTheRight;
    }

    /**
     * @return The row on which the move is performed.
     */
    public int getRow() {
        return inTheRow;
    }

    /**
     * @return a string representation of the move. For example, if the row is 2, the left bound of the
     * sequence is 3 and the right bound is 5, this function will return the string "2:3-5"
     * (without any spaces).
     */
    public String toString() {
        return inTheRow + ":" + fromTheLeft + "-" + toTheRight;
    }

}


