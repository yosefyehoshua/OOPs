package filesprocessing.filters;

import filesprocessing.ErrorTypeOne;

import java.io.File;

/**
 * a filter class the filters file by a given range between two numbers
 */
public class BetweenFilter extends Filter {
    /**
     * constants
     */
    private static final int START_RANGE_SPOT = 1;
    private static final int END_RANGE_SPOT = 2;
    private static final double DOUBLE_KB = 1024.0;
    private static final int BETWEEN_NUM1_NUM2 = 1;
    private static final int BETWEEN_NUM1_NUM2_NOT = 2;

    /**
     * instances
     */
    private final String[] commendOfFilter;
    private final int filterRecipe;

    /**
     * a class betweenFilter constructor
     * @param commendOfFilter a list of strings comprise of the commend from the commend file
     * @param filterRecipe an int that gives info. on which format of instructions we work
     * BETWEEN#num1#num2 or BETWEEN#num1#num2#NOT.
     * @param lineNumber the lineNumbe of the commend in the file
     */
    public BetweenFilter(String[] commendOfFilter, int filterRecipe, int lineNumber) {
        super(lineNumber);
        this.commendOfFilter = commendOfFilter;
        this.filterRecipe = filterRecipe;
    }

    /**
     * an implementation of the abstract method filter from filter class
     * @param file given file fro dir
     * @return boolean true/false if the file is or not between the range.
     * @throws ErrorTypeOne a class ErrorTypeOne object
     */
    @Override
    public boolean filter(File file) throws ErrorTypeOne {
        switch (filterRecipe) {
            case BETWEEN_NUM1_NUM2:
                if (file.length()/DOUBLE_KB >= Double.parseDouble(commendOfFilter[START_RANGE_SPOT]) &&
                        file.length()/DOUBLE_KB <= Double.parseDouble(commendOfFilter[END_RANGE_SPOT])) {
                    return true;
                } else if (file.length()/DOUBLE_KB <= Double.parseDouble(commendOfFilter[START_RANGE_SPOT]) &&
                        file.length()/DOUBLE_KB >= Double.parseDouble(commendOfFilter[END_RANGE_SPOT])){
                    return false;
                } else {
                    return false;
                }
            case BETWEEN_NUM1_NUM2_NOT:
                if (file.length()/DOUBLE_KB <= Double.parseDouble(commendOfFilter[START_RANGE_SPOT]) &&
                        file.length()/DOUBLE_KB >= Double.parseDouble(commendOfFilter[END_RANGE_SPOT])) {
                    return true;
                } else if (file.length()/DOUBLE_KB >= Double.parseDouble(commendOfFilter[START_RANGE_SPOT]) &&
                        file.length()/DOUBLE_KB <= Double.parseDouble(commendOfFilter[END_RANGE_SPOT])){
                    return false;
                } else {
                    return false;
                }
            default:
                throw new ErrorTypeOne();
        }
    }
}
