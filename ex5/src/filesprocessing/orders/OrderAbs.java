package filesprocessing.orders;

import java.io.File;

/**
 * a class OrderAbs extends Order Sort files by absolute name , going from `a' to `z'
 */
public class OrderAbs extends Order {
    /**
     * boolean instance for checking reversed sorting
     */
    private final boolean reversed;

    /**
     * a constructor of OrderAbs
     * @param reversed boolean instance for checking reversed sorting
     */
    public OrderAbs(boolean reversed) {
        super(reversed);
        this.reversed = reversed;
    }

    /**
     * @return ture/false it reversed is needed
     */
    public boolean getReversed() {
        return reversed;
    }

    /**
     * compare between files path by their name
     * @param file1 given file fron dir
     * @param file2 given file fron dir
     * @return 1 - bigger ,-1 - smaller  or 0 - equal according to file1
     */
    @Override
    public int compare(File file1, File file2) {
        return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
    }
}
