package filesprocessing.filters;

import filesprocessing.ErrorTypeOne;

import java.io.File;

/**
 * HiddenFilter class - checks if a file is hidden or not.
 */
public class HiddenFilter extends Filter {

    /** Constants representing this class. */
    private static final int HIDDEN_YES = 1;
    private static final int HIDDEN_NO = 2;
    private static final int HIDDEN_YES_NOT = 3;
    private static final int HIDDEN_NO_NOT = 4;

    /** Instance for this class. */
    private final int filterRecipe;

    /**
     * Constructor for this class.
     * @param filterRecipe an int that gives info. on which format of instructions we work
     * @param lineNumber the lineNumber of the commend in the file
     */
    public HiddenFilter(int filterRecipe, int lineNumber) {
        super(lineNumber);
        this.filterRecipe = filterRecipe;
    }

    /**
     * checks if a file fits the filter conditions
     * @param file given file from dir
     * @return true or false, according to if filter succeeded or not
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    @Override
    public boolean filter(File file) throws ErrorTypeOne {
        switch (filterRecipe) {
            case HIDDEN_YES:
                return file.isHidden();
            case HIDDEN_NO:
                return !file.isHidden();
            case HIDDEN_YES_NOT:
                return !file.isHidden();
            case HIDDEN_NO_NOT:
                return file.isHidden();
            default:
                throw new ErrorTypeOne();
        }
    }
}
