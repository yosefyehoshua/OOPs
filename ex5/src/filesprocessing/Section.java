package filesprocessing;

import filesprocessing.filters.Filter;
import filesprocessing.filters.FilterFactory;
import filesprocessing.orders.Order;
import filesprocessing.orders.OrderFactory;
import java.io.*;
import java.util.ArrayList;

/**
 * class section initialize section: from FILTER to ORDER
 */
public class Section {

    /**
     * constants
     */
    private static final String STRING_FILTER = "FILTER";
    private static final String STRING_ORDER = "ORDER";

    /**
     * instances
     */
    private final Filter filter;
    private final Order order;
    private final ArrayList<String> warningArray;

    /**
     * a constructor of Section class
     * @param filter a class filter object
     * @param order a class oreder object
     * @param warningArray array of strings of error massage
     */
    public Section(Filter filter, Order order, ArrayList<String> warningArray) {
        this.filter = filter;
        this.order = order;
        this.warningArray = warningArray;
    }

    /**
     * getters
     */
    public Filter getFilter() {
        return filter;
    }
    public Order getOrder() { return order; }
    public ArrayList<String> getWarningArray() {
        return warningArray;
    }

    /**
     * prints WarningArray
     */
    public void printWarningArray(){warningArray.forEach(System.err::println);}

    /**
     * a local class Parsser that parsing a text file, line by line.
     */
    static class Parsser {

        private static int lineNumber = 0;

        /**
         * parssing function that reads line by line creates section objects and returns a list of sections
         * @param file the given commendfile
         * @return list of sections
         * @throws IOException an ioexception object in case of io errors
         * @throws ErrorTypeTwo an ErrorTypeTwo object when a the commendfile format is wrong
         */
        public static ArrayList<Section> parssingFile(String file) throws IOException, ErrorTypeTwo {
            ArrayList<Section> sectionList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int sectionCounter = 0;
                Filter currentFilter = null;
                Order currentOrder;
                ArrayList<String> warningList = new ArrayList<>();
                while ((line = reader.readLine()) != null || sectionCounter == 3) {
                    lineNumber++;
                    if (sectionCounter == 0 && !line.equals(STRING_FILTER)) {
                        throw new ErrorTypeTwo
                                ("FILTER sub-section is missing");
                    } // file doesn't start with FILTER
                    if (sectionCounter == 0) {
                        sectionCounter++;
                        continue;
                    }
                    if (sectionCounter == 1) {
                        try {
                            currentFilter = FilterFactory.getFilter(line, lineNumber);
                        } catch (ErrorTypeOne e) {
                            warningList.add("Warning in line " + lineNumber);
                            currentFilter = FilterFactory.defaultFilter(lineNumber);
                        }
                        sectionCounter++;
                        continue;
                    }
                    if (sectionCounter == 2 && !line.equals(STRING_ORDER)) {
                        throw new ErrorTypeTwo("ORDER " +
                                "sub-section is missing");
                    }
                    if (sectionCounter == 2) {
                        sectionCounter++;
                        continue;
                    }
                    if (sectionCounter == 3) { // full section FILTER to ORDER
                        if (line == null) {
                            sectionList.add(new Section(currentFilter, OrderFactory.defaultOrder(),
                                    warningList));
                            sectionCounter = 1;
                        } else if (line.equals(STRING_FILTER)) {
                            sectionList.add(new Section(currentFilter, OrderFactory.defaultOrder(),
                                    warningList));
                            sectionCounter = 1;
                            warningList = new ArrayList<>();
                        } else {
                            try {
                                currentOrder = OrderFactory.getOrder(line);
                            } catch (ErrorTypeOne e) {
                                warningList.add("Warning in line " + lineNumber);
                                currentOrder = OrderFactory.defaultOrder();
                            }
                            sectionList.add(new Section(currentFilter, currentOrder, warningList));
                            sectionCounter = 0;
                            warningList = new ArrayList<>();
                        }
                    }
                }
            } catch (ErrorTypeTwo e) {
                e.printErrorMessage();
            }
            lineNumber = 0;
            return sectionList;
        }
    }
}