package filesprocessing.orders;

import java.io.File;

/**
 * a OrderType class extends order Sort  files by file type, going from `a' to `z'
 */
public class OrderType extends Order {

    /**
     * a OrderType constructor
     * @param reversed given boolean instance while sort is reversed
     */
    public OrderType(boolean reversed) {
        super(reversed);
    }

    /**
     * compare between files path by their type
     * @param file1 given file fron dir
     * @param file2 given file fron dir
     * @return 1 - bigger ,-1 - smaller  or 0 - equal according to file1
     */
    @Override
    public int compare(File file1, File file2) {
        String file1AbsoluteName = file1.getAbsolutePath();
        String file2AbsoluteName = file2.getAbsolutePath();

        final int file1Dot = file1AbsoluteName.lastIndexOf('.');
        final int file2Dot = file2AbsoluteName.lastIndexOf('.');

        if (file1AbsoluteName.substring(file1Dot+1).compareTo(file2AbsoluteName.substring(file2Dot+1)) == 0){
            return file1.getName().compareTo(file2.getName());
        } else {
            return file1AbsoluteName.substring(file1Dot+1).compareTo(file2AbsoluteName.substring(file2Dot+1));
        }
    }
}
