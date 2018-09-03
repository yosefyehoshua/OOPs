package filesprocessing.orders;

import filesprocessing.ErrorTypeTwo;

import java.io.File;

/**
 * a class OrderSize extends Order Sort files by file size, going from smallest to largest
 */
public class OrderSize extends Order {

    /**
     * a OrderSize constructor
     * @param reversed given boolean instance while sort is reversed
     */
    public OrderSize(boolean reversed) {
        super(reversed);
    }

    /**
     * compare between files path by their size
     * @param file1 given file fron dir
     * @param file2 given file fron dir
     * @return 1 - bigger ,-1 - smaller  or 0 - equal according to file1
     */
    @Override
    public int compare(File file1, File file2) {

        double fileSize1 = file1.length();
        double fileSize2 = file2.length();

        fileSize1 = fileSize1 / 1024.0;
        fileSize2 = fileSize2 / 1024.0;

        if (fileSize1 > fileSize2){
            return 1;
        } else if (fileSize1 < fileSize2){
            return -1;
        } else {
            return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
        }
    }
}
