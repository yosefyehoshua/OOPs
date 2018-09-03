package filesprocessing.filters;

import filesprocessing.ErrorTypeOne;

import java.io.File;

/**
 * a filter class the filters file if  given file is executable
 */
public class ExecutableFilter extends Filter {
    /**
     * constants
     */
    private static final int EXECUTABLE_YES = 1;
    private static final int EXECUTABLE_NO = 2;
    private static final int EXECUTABLE_YES_NOT = 3;
    private static final int EXECUTABLE_NO_NOT = 4;
    /**
     * instances
     */
    private final int filterRecipe;

    /**
     * a class ExecutableFilter constructor
     * @param filterRecipe an int that gives info. on which format of instructions we work
     * @param lineNumber the lineNumber of the commend in the file
     */
    public ExecutableFilter(int filterRecipe, int lineNumber) {
        super(lineNumber);
        this.filterRecipe = filterRecipe;
    }

    /**
     * an implementation of the abstract method filter from filter class
     * @param file given file from dir
     * @return true/false according to Executable instructions
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    @Override
    public boolean filter(File file) throws ErrorTypeOne {
        switch (filterRecipe) {
            case EXECUTABLE_YES:
                return file.canExecute();
            case EXECUTABLE_NO:
                return !file.canExecute();
            case EXECUTABLE_YES_NOT:
                return !file.canExecute();
            case EXECUTABLE_NO_NOT:
                return file.canExecute();
            default:
                throw new ErrorTypeOne();
        }

    }
}
