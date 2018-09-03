import java.util.*;
/**
 * this class has a main method that measures the run-times
 * Measure the time required to perform the following:
 * 1. Adding all the words in data1.txt, one by one, to each of the data structures
 * 2. The same for data2.txt. Again – in milliseconds.
 * 3. For each data structure, perform contains(“hi”) when it’s initialized with data1.txt.
 * 4. For each data structure, perform contains(“-13170890158”) when it’s initialized with data1.txt.
 * 5. For each data structure, perform contains(“23”) when it’s initialized with data2.txt.
 * 6. For each data structure, perform contains(“hi”) when it’s initialized with data2.txt. “hi” does not
 * appear in data2.txt.
 *
 * Created by Yosef Yehoshua.
 */
public class SimpleSetPerformanceAnalyzer {

    private static final int SIZE = 5;
    private static final int BIG_NUMBER = 1000000;
    private static final int PRETTY_BIG_NUMBER = 70000;
    private static final int MID_NUMBER = 7000;
    private static final int DATA_1 = 0;
    private static final int DATA_2 = 1;
    private TreeSet<String> treeSet;
    private LinkedList<String> linkedList;
    private HashSet<String> hashSet;
    private SimpleSet[] dataStructuresArray;
    private String[][] DataArray = {Ex3Utils.file2array("data1.txt"), Ex3Utils.file2array("data2.txt")};
    private String[] dataStructuresArrayAsStrings = {"linkedList", "treeSet", "hashSet", "openHashSet",
            "closedHashSet"};
    private String[] checksArray = {"WARM_UP", "hi", "-13170890158", "23"};


    /** _________________________ Constructor _________________________ */

    /**
     * Constructor for initialing the data structures types & the array they in.
     */
    public SimpleSetPerformanceAnalyzer() {
        dataStructuresArray = new SimpleSet[SIZE];
        treeSet = new TreeSet<>();
        linkedList = new LinkedList<>();
        hashSet = new HashSet<>();
        SimpleHashSet openHashSet = new OpenHashSet();
        SimpleHashSet closedHashSet = new ClosedHashSet();
        dataStructuresArray[0] = new CollectionFacadeSet(linkedList);
        dataStructuresArray[1] = new CollectionFacadeSet(treeSet);
        dataStructuresArray[2] = new CollectionFacadeSet(hashSet);
        dataStructuresArray[3] = openHashSet;
        dataStructuresArray[4] = closedHashSet;
    }

    /** _________________________ Methods _________________________ */

    /**
     * calaculate the adding time of data1 & data2 for each data structure and printing the results
     */
    public void addingWordsTODataStructures(int dataNUmber) {
        // cosmetics...
        System.out.println();
        System.out.println("________________" + " Starting on data" + (dataNUmber + 1) + " ________________");
        System.out.println();
        System.out.println("*************************** Adding ****************************");
        System.out.println();

        for (int i = 0; i < dataStructuresArray.length; i++) {
            long timeBefore = System.nanoTime();
            for (int j = 0; j < DataArray[dataNUmber].length; j++) {
                dataStructuresArray[i].add(DataArray[dataNUmber][j]);
            }
            long timeEnds = System.nanoTime() - timeBefore;
            System.out.println("The running time of data" + (dataNUmber + 1) + " for '" +
                    dataStructuresArrayAsStrings[i] + "' is: "
                    + timeEnds / BIG_NUMBER + " milliseconds.");
            }
        }

    /**
     * calculating the running time of contains while cheking for a specific string in
     * dataStructuresArrayAsStrings and prints the results.
     */
    public void ContainsRunnigTime() {
        System.out.println();
        System.out.println("******************** Contains Running Time ********************");
        System.out.println();

        // iterating on 3 diffrent strings
        for (int i = 1; i < checksArray.length; i++) {
            // types of dast
            for (int j = 0; j < dataStructuresArrayAsStrings.length; j++) {
                // STARTING warm up
//                if (j == 0) {
//                    for (int y = 0; y < MID_NUMBER; y++) {
//                        dataStructuresArray[j].contains(checksArray[0]);
//                    }
//                } else {
//                    for (int k = 0; k < PRETTY_BIG_NUMBER; k++) {
//                        dataStructuresArray[j].contains(checksArray[0]);
//                    }
//                }
//                // warm up done!!!
                // actual check for contains
                long timeBefore = System.nanoTime();
                dataStructuresArray[j].contains(checksArray[i]);
                long timeEnds = System.nanoTime() - timeBefore;
                System.out.println("Contains running time for: '" + dataStructuresArrayAsStrings[j] +
                        "' with '" + checksArray[i] + "' is: " + timeEnds + " milliseconds.");
            }
        }
    }


    /**
     * calculating the cost of one contains action.
     */
    public void ContainsCost() {
        System.out.println();
        System.out.println("************************ Contains Cost ************************");
        System.out.println();
        // types of dast
        for (int j = 0; j < dataStructuresArrayAsStrings.length; j++) {
            // STARTING warm up
            if (j == 0) {
                for (int y = 0; y < MID_NUMBER; y++) {
                    dataStructuresArray[j].contains(checksArray[1]);
                }
            } else {
                for (int k = 0; k < PRETTY_BIG_NUMBER; k++) {
                    dataStructuresArray[j].contains(checksArray[1]);
                }
            }
            // warm up done!!!
            long timeBefore = System.nanoTime(); // start counting...
            // iterating on contains
            long timeEnds;
            if (j == 0) {
                for (int l = 0; l < MID_NUMBER; l++) {
                    dataStructuresArray[j].contains(checksArray[1]);
                }
                timeEnds = System.nanoTime() - timeBefore;
                timeEnds = timeEnds / MID_NUMBER;
            } else {
                for (int l = 0; l < PRETTY_BIG_NUMBER; l++) {
                    dataStructuresArray[j].contains(checksArray[1]);
                }
                timeEnds = System.nanoTime() - timeBefore;
                timeEnds = timeEnds / PRETTY_BIG_NUMBER;
            }
            System.out.println("The cost of contains for '" + dataStructuresArrayAsStrings[j] + "' is " +
                    timeEnds +
                    " milliseconds.");
        }
    }

    /**
     * main function that activate all the methods in the class..
     * @param args none
     */
    public static void main(String[] args) {
        SimpleSetPerformanceAnalyzer performance = new SimpleSetPerformanceAnalyzer();
        performance.addingWordsTODataStructures(DATA_1);
//        performance.ContainsCost();
        performance.ContainsRunnigTime();
//        SimpleSetPerformanceAnalyzer newPerformance = new SimpleSetPerformanceAnalyzer();
//        newPerformance.addingWordsTODataStructures(DATA_2);
//        newPerformance.ContainsCost();
//        newPerformance.ContainsRunnigTime();
    }
}