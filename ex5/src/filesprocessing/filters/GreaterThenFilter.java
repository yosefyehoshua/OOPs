package filesprocessing.filters;

import filesprocessing.ErrorTypeOne;

import java.io.File;

/**
 * GreaterThanFilter class - filters according to which filter has greater size.
 */
public class GreaterThenFilter extends Filter {

    /** Constants for this class. */
    private static final int NOT_GREATER_THEN_SPOT = 1; // NOT statement at the end of the array
    private static final double DOUBLE_KB = 1024.0;
    private static final int GREATER_NUMBER_NOT = 2;
    private static final int GREATER_NUMBER = 1;

    /** Instances in this class. */
    private final String[] commendOfFilter;
    private final int filterRecipe;
    private final int lineNumber;

    /**
     * Constructor for GreaterThanFilter
     * @param commendOfFilter array of strings commend
     * @param filterRecipe an int that gives info. on which format of instructions we work
     * @param lineNumber the lineNumber of the commend in the file
     */
    public GreaterThenFilter(String[] commendOfFilter, int filterRecipe, int lineNumber) {
        super(lineNumber);
        this.filterRecipe = filterRecipe;
        this.commendOfFilter = commendOfFilter;
        this.lineNumber = lineNumber;
    }

    /**
     * checks if a file fits the filter conditions
     * @param file given file from dir
     * @return true or false, according to if filter succeeded or not
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    @Override
    public boolean filter(File file) throws ErrorTypeOne {

        switch (filterRecipe){
            case GREATER_NUMBER:
                if (file.length() / DOUBLE_KB > Double.parseDouble(commendOfFilter[NOT_GREATER_THEN_SPOT])) {
                    return true;
                } else if (file.length() / DOUBLE_KB <
                        Double.parseDouble(commendOfFilter[NOT_GREATER_THEN_SPOT])) {
                    return false;
                } else {
                    return false;
                }
            case GREATER_NUMBER_NOT:
                if (file.length()/DOUBLE_KB < Double.parseDouble(commendOfFilter[NOT_GREATER_THEN_SPOT])) {
                    return true;
                } else if (file.length()/DOUBLE_KB >
                        Double.parseDouble(commendOfFilter[NOT_GREATER_THEN_SPOT])){
                    return false;
                } else {
                    return false;
                }
            default:
                throw new ErrorTypeOne();
        }
    }
}
