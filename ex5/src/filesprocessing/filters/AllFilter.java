package filesprocessing.filters;

import java.io.File;

/**
 * a filter class that let all the files pass
 */
public class AllFilter extends Filter {

    /**
     * a constractor that get a line number of the commend
     * @param lineNumber the lineNumbe rof the commend in the file
     */
    AllFilter(int lineNumber) {
        super(lineNumber);
    }

    /**
     * an implementation of the abstract method filter from filter class
     * @param file
     * @return always true
     */
    @Override
    public boolean filter(File file) {
        return true;
    }
}
