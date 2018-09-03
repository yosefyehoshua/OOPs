package filesprocessing.orders;

import filesprocessing.ErrorTypeOne;

import java.io.File;
import java.util.Comparator;

/**
 * An Order abstract class
 */
public abstract class Order implements Comparator<File> {

    /**
     * constants
     */
    private static final int NUMBER_OF_CELLS = 2;
    private static final int SECOUND_CELL = 1;
    private static final String REVERSE = "REVERSE";
    private static final int SINGLE_CELL = 1;
    private final boolean reversed;

    /**
     * a constractor of Order
     * @param reversed boolean instance for checking reversed sorting
     */
    public Order(boolean reversed) {
        this.reversed = reversed;
    }

    /**
     * @return ture/false it reversed is needed
     */
    public boolean getReversed() {return reversed;}

    /**
     * checks if commendfile string array has a valid REVERSE order
     * @param commendOfOrder commendfile line string array
     * @return true/false
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    public static boolean isReverse(String[] commendOfOrder) throws ErrorTypeOne {
        if (commendOfOrder.length == SINGLE_CELL) { return false; }
        if (commendOfOrder.length == NUMBER_OF_CELLS && commendOfOrder[SECOUND_CELL].equals(REVERSE)) {
            return true;
        } else {
            throw new ErrorTypeOne();
        }
    }

}
