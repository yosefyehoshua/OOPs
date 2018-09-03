package filesprocessing;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * a DirectoryProcessor class, this class only contains a main function that runs all the program and
 * sorting the files according to filter and order objects, and printing them by order.
 */
public class DirectoryProcessor {

    /**
     * constants
     */
    private static final int CORRECT_SIZE_OF_ARA = 2;

    public static void main(String[] args) throws ErrorTypeTwo, IOException, ErrorTypeOne {
        try {
            if (args.length == CORRECT_SIZE_OF_ARA) {
                String commendfile = args[1];
                File sourceDir = new File(args[0]);
                File[] listFiles = sourceDir.listFiles();
                ArrayList<Section> sectionsList = Section.Parsser.parssingFile(commendfile);
                for (Section aSectionsList : sectionsList) { // iterating on sections
                    ArrayList<File> filterOrderedList = new ArrayList<>();
                    for (File listFile : listFiles != null ? listFiles : new File[0]) { // iterating on files
                        if (!listFile.exists()) {
                            throw new ErrorTypeTwo("one of the files doesn't " +
                                    "exist.");
                        }
                        if (aSectionsList.getFilter().filter(listFile)) {
                            if (!listFile.isDirectory()) {
                                filterOrderedList.add(listFile);
                            }
                        }
                    }
                    if (aSectionsList.getWarningArray().size() > 0) { // prints Type One errors
                        aSectionsList.printWarningArray();
                    }
                    if (aSectionsList.getOrder().getReversed()) { // checking for REVERSED order
                        filterOrderedList.sort(aSectionsList.getOrder().reversed());
                    } else {
                        filterOrderedList.sort(aSectionsList.getOrder());
                    }
                    for (File aFilterOrderedList : filterOrderedList) { // prints sorted files
                        System.out.println(aFilterOrderedList.getName());
                    }
                }
            } else {
                throw new ErrorTypeTwo("more than two arguments.");
            }
        } catch (ErrorTypeTwo e) {
            e.printErrorMessage();
        }
    }
}



