package filesprocessing.filters;

import filesprocessing.ErrorTypeOne;

import java.io.File;

/**
 * Class FilterFile - see if a given name is equals to the name of a file
 */
public class FilterFile extends Filter {

    /** Constants representing this class. */
    private static final int GIVEN_NAME_SPOT = 1;
    private static final int CONTAINS_CONDITION = 1;
    private static final int CONTAINS_CONDITION_NOT = 2;

    /** Instanes for this class. */
    private final String[] commendOfFilter;
    private final int filterRecipe;

    /**
     * a FilterFile constructor
     * @param commendOfFilter array of strings commend
     * @param filterRecipe an int that gives info. on which format of instructions we work
     * @param lineNumber the lineNumber of the commend in the file
     */
    public FilterFile(String[] commendOfFilter, int filterRecipe, int lineNumber) {
        super(lineNumber);
        this.filterRecipe = filterRecipe;
        this.commendOfFilter = commendOfFilter;
    }

    /**
     * checks if a file matches the filter condition.
     * @param file given file from dir
     * @return true or false, according to filter name validity
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    @Override
    public boolean filter(File file) throws ErrorTypeOne {
        String fileName = file.getName();
        String givenName = commendOfFilter[GIVEN_NAME_SPOT];
        switch (filterRecipe) {
            case CONTAINS_CONDITION:
                return fileName.equals(givenName);
            case CONTAINS_CONDITION_NOT:
                return !fileName.equals(givenName);
            default:
                throw new ErrorTypeOne();
        }
    }
}

