package filesprocessing.filters;;
import filesprocessing.ErrorTypeOne;

import java.io.File;

/**
 * WritableFilter - checks if a file is writable or not.
 */
public class WritableFilter extends Filter {

    /** Constants for this class. */
    private static final int WRITABLE_YES = 1;
    private static final int WRITABLE_NO = 2;
    private static final int WRITABLE_YES_NOT = 3;
    private static final int WRITABLE_NO_NOT = 4;

    /**instance for this class. */
    private final int commendOfFilter;

    /**
     * Constructor for this class.
     * @param filterRecipe an int that gives info. on which format of instructions we work
     * @param lineNumber the lineNumber of the commend in the file
     */
    public WritableFilter(int filterRecipe, int lineNumber) {
        super(lineNumber);
        this.commendOfFilter = filterRecipe;
    }

    /**
     * Checks if a file complies with the filter's condition.
     * @param file given file from dir.
     * @return true or false, whether the file matches the condition
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    @Override
    public boolean filter(File file) throws ErrorTypeOne {
        switch (commendOfFilter) {
            case WRITABLE_YES:
                return file.canWrite();
            case WRITABLE_NO:
                return !file.canWrite();
            case WRITABLE_YES_NOT:
                return !file.canWrite();
            case WRITABLE_NO_NOT:
                return file.canWrite();
            default:
                throw new ErrorTypeOne();
        }
    }
}
