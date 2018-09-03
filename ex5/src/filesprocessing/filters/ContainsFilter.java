package filesprocessing.filters;
import filesprocessing.ErrorTypeOne;

import java.io.File;

/**
 * a filter class the filters file if  given value is contained in the  file name
 */
public class ContainsFilter extends Filter {
    /**
     * constants
     */
    private static final int PREFIX_SPOT = 1;
    private static final int CONTAINS_CONDITION = 1;
    private static final int CONTAINS_CONDITION_NOT = 2;
    /**
     * instances
     */
    private final String[] commendOfFilter;
    private final int FilterRecipe;

    /**
     * a class ContainsFilter constructor
     * @param commendOfFilter a list of strings comprise of the commend from the commend file
     * @param filterRecipe an int that gives info. on which format of instructions we work
     * @param lineNumber the lineNumber of the commend in the file
     */
    public ContainsFilter(String[] commendOfFilter, int filterRecipe, int lineNumber) {
        super(lineNumber);
        this.commendOfFilter = commendOfFilter;
        this.FilterRecipe = filterRecipe;
    }


    /**
     * an implementation of the abstract method filter from filter class
     * @param file given file from dir
     * @return boolean true/false if  given value is contained in the  file name
     * @throws ErrorTypeOne a class ErrorTypeOne object
     */
    @Override
    public boolean filter(File file) throws ErrorTypeOne {
        String fileName = file.getName();
        String nameOfContainedFile = commendOfFilter[PREFIX_SPOT];
        switch (FilterRecipe) {
            case CONTAINS_CONDITION:
                return fileName.contains(nameOfContainedFile);
            case CONTAINS_CONDITION_NOT:
                return !fileName.contains(nameOfContainedFile);
            default:
                throw new ErrorTypeOne();
        }
    }
}
