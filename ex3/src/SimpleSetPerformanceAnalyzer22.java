import java.util.*;

public class SimpleSetPerformanceAnalyzer22 {
    private static SimpleSet[] InitializeSets() {
        SimpleSet[] hashSetArray = { new ClosedHashSet(), new OpenHashSet(),
                new CollectionFacadeSet(new TreeSet<String>()),
                new CollectionFacadeSet(new LinkedList<String>()),
                new CollectionFacadeSet(new HashSet<String>()) };
        return hashSetArray;
    }

    /**
     * @param args
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[][] data_file = { Ex3Utils.file2array("data1.txt"),
                Ex3Utils.file2array("data2.txt") };
        String[] Sets = { "ClosedHashSet", "OpenHashSet", "TreeSet",
                "LinkedList", "HashSet" };
        String[] Tests = { "loadmetothememory", "hi", "-13170890158", "23" };
        SimpleSet[] hashSetArray;
        // LinkedList<String>, TreeSet<String>, or
        long start, difference;
        for (int k = 0; k < data_file.length; k++) {
            hashSetArray = InitializeSets();
            for (int i = 0; i < 2; i++) {
                start = System.nanoTime();
                for (String item : data_file[k]) {
                    hashSetArray[i].add(item);
                }
                difference = System.nanoTime() - start;
                System.out.println(Sets[i] + " data" + (k+1) + ": " + difference
                        / 1000000);
                for (int j = 0; j < Tests.length; j++) {
                    hashSetArray[i].contains(Tests[j]);//Because the first time sucks
                    hashSetArray[i].contains(Tests[j]);//Because the first time sucks
                    hashSetArray[i].contains(Tests[j]);//Because the first time sucks
                    start = System.nanoTime();
                    hashSetArray[i].contains(Tests[j]);
                    difference = System.nanoTime() - start;
                    System.out.println(Sets[i] + " \"" + Tests[j] + "\": "
                            + difference);
                }
                System.out.println("********");
            }
            start = System.currentTimeMillis();
        }
    }

}