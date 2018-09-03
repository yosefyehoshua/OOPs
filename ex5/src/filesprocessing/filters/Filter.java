package filesprocessing.filters;
import filesprocessing.ErrorTypeOne;

import java.io.File;
/**
 * A Filter abstract class
 */
public abstract class Filter {
    /**
     * constants
     */
    protected static final String NOT = "NOT";
    protected static final int NOT_SUFFIX_ON_SPOT = 1;
    protected static final int NO_NOT_SUFFIX = 0;
    /**
     * instances
     */
    private final int lineNumber;
    /**
     * @return lineNumber of the commend in commendfile
     */
    public int getLineNumber() {
        return lineNumber;
    }

    Filter(int lineNumber){
        this.lineNumber = lineNumber;
    }

    /**
     * a semi-check if the string commendline is valid, it checks a more general valid format length - at the
     * beginning and NOT at the end
     * @param commendOfFilter an array of strings ,each cell in array has info. about type, and action of
     *                       this filter
     * @param minLength min length of the array of strings
     * @param maxLength max length of the array of strings
     * @return int 0/1 it there is or not NOT commend at the end
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    protected static int isFilterCommendsValid(String[] commendOfFilter, int minLength, int maxLength)
            throws ErrorTypeOne {
        if ( (commendOfFilter.length == minLength) || commendOfFilter.length == maxLength) {
            if (commendOfFilter.length == maxLength) {
                    if (commendOfFilter[commendOfFilter.length - 1].equals(NOT)) {
                        return NOT_SUFFIX_ON_SPOT;
                    } else {
                        throw new ErrorTypeOne();
                    }
            } else {
                    return NO_NOT_SUFFIX;
                }
        } else {
            throw new ErrorTypeOne();
        }
    }
    public abstract boolean filter(File file) throws ErrorTypeOne;
}
