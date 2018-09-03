package filesprocessing.filters;

import filesprocessing.ErrorTypeOne;

import java.io.File;

/**
 * SmallerThanFilter class - checks if the file's size is smaller than a certain value
 */
public class SmallerThanFilter extends Filter {

    /** Constants for this class. */
    private static final double DOUBLE_KB = 1024.0;
    private static final int SMALLER_NUMBER = 1;
    private static final int SMALLER_NUMBER_NOT = 2;
    private static final int NOT_SMALLER_THEN_SPOT = 1;

    /** Instances for this class. */
    private final String[] commendOfFilter;
    private final int filterRecipe;
    private final int lineNumber;

    /** @return the line number of the command in the text. */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     *  Constructor for this class.
     * @param commendOfFilter array of strings commend
     * @param filterRecipe an int that gives info. on which format of instructions we work
     * @param lineNumber the lineNumber of the commend in the file
     */
    public SmallerThanFilter(String[] commendOfFilter, int filterRecipe, int lineNumber) {
        super(lineNumber);
        this.commendOfFilter = commendOfFilter;
        this.filterRecipe = filterRecipe;
        this.lineNumber = lineNumber;
    }

    /**
     * checks if a file fits the filter conditions
     * @param file given file from dir
     * @return true or false, according to filter name validity
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    @Override
    public boolean filter(File file) throws ErrorTypeOne {
        switch (filterRecipe){
            case SMALLER_NUMBER:
                if (file.length() / DOUBLE_KB < Double.parseDouble(commendOfFilter[NOT_SMALLER_THEN_SPOT])) {
                    return true;
                } else if (file.length()/DOUBLE_KB >
                        Double.parseDouble(commendOfFilter[NOT_SMALLER_THEN_SPOT])) {
                    return false;
                } else {
                    return false;
                }
            case SMALLER_NUMBER_NOT:
                if (file.length() / DOUBLE_KB > Double.parseDouble(commendOfFilter[NOT_SMALLER_THEN_SPOT])) {
                    return true;
                } else if (file.length()/DOUBLE_KB <
                        Double.parseDouble(commendOfFilter[NOT_SMALLER_THEN_SPOT])){
                    return false;
                } else {
                    return false;
                }
            default:
                throw new ErrorTypeOne();
        }
    }

}
