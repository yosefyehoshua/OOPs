package filesprocessing.filters;

import filesprocessing.ErrorTypeOne;

import java.io.File;

/**
 * SuffixFilter class - checks if a file has a certain suffix.
 */
public class SuffixFilter extends Filter {

    /** Constants for this class. */
    private static final int SUFFIX_SPOT = 1;
    private static final int SUFFIX_CONDITION = 1;
    private static final int SUFFIX_CONDITION_NOT = 2;

    /** Instances for this class. */
    private final int commendOfFilter;
    private final String[] commendList;

    /**
     * Constructor for this class.
     * @param commendList array of strings commend
     * @param filterRecipe an int that gives info. on which format of instructions we work
     * @param lineNumber the lineNumber of the commend in the file
     */
    public SuffixFilter(String[] commendList, int filterRecipe, int lineNumber) {
        super(lineNumber);
        this.commendList = commendList;
        this.commendOfFilter = filterRecipe;
    }

    /**
     * checks if the file complies with the filter condition
     * @param file given file from dir.
     * @return true or false, whether the file matched the condition or not
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    @Override
    public boolean filter(File file) throws ErrorTypeOne {
        String fileName = file.getName();
        String suffix = commendList[SUFFIX_SPOT];
        switch (commendOfFilter) {
            case SUFFIX_CONDITION:
                return fileName.endsWith(suffix);
            case SUFFIX_CONDITION_NOT:
                return !fileName.endsWith(suffix);
            default:
                throw new ErrorTypeOne();
        }
    }
}
