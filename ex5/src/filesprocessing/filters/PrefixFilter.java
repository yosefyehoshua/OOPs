package filesprocessing.filters;

import filesprocessing.ErrorTypeOne;

import java.io.File;

/**
 * PrefixFilter class - checks if a file's prefix matches a certain given prefix.
 */
public class PrefixFilter extends Filter {

    /** Constants for this class. */
    private static final int PREFIX_SPOT = 1;
    private static final int PREFIX_CONDITION = 1;
    private static final int PREFIX_CONDITION_NOT = 2;

    /**Instances for this class. */
    private final String[] commendOfFilter;
    private final int hiddenFilterRecipe;

    /**
     * Constructor for this class.
     * @param commendOfFilter array of strings commend
     * @param filterRecipe an int that gives info. on which format of instructions we work
     * @param lineNumber the lineNumber of the commend in the file
     */
    public PrefixFilter(String[] commendOfFilter, int filterRecipe, int lineNumber) {
        super(lineNumber);
        this.commendOfFilter = commendOfFilter;
        this.hiddenFilterRecipe = filterRecipe;
    }

    /**
     * checks if a file matches the filter condition.
     * @param file given file from dir.
     * @return true or false, whether the file matched the filter condition or not.
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile.
     */
    @Override
    public boolean filter(File file) throws ErrorTypeOne {
        String fileName = file.getName();
        String prefix = commendOfFilter[PREFIX_SPOT];
        switch (hiddenFilterRecipe) {
            case PREFIX_CONDITION:
                return fileName.startsWith(prefix);
            case PREFIX_CONDITION_NOT:
                return !fileName.startsWith(prefix);
            default:
                throw new ErrorTypeOne();
        }
    }
}
