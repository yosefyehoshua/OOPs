import java.math.*;
/**
 * tests a hash set - change line 15 from closed to open and vise versa.
 * run this with 2 parameters in 'edit configurations':
 * 1. a file name with data
 * 2. a number indication constructor type (1-default, 2-with load factors, 3-with data).
 *
 * Example: "data1.txt 3""
 *
 * NOTICE!! The tester is defaulted for a closed hash set.
 * To test open hash set - simple replace all "Closed" with "Open".
 */
public class Tester {


    public static void main(String args[]) {

        /**#These values correspond to the time it takes (in ns) to check if "hi" is contained in
        the data structures initialized with data1 */
        int OpenHashSet_Contains_hi1 = Math.abs(26771 - 19890);
        int ClosedHashSet_Contains_hi1 = Math.abs(2142 - 23874);
        int TreeSet_Contains_hi1 = Math.abs(18070 -5625);
        int LinkedList_Contains_hi1 = Math.abs(940766 - 1451990);
        int HashSet_Contains_hi1 = Math.abs(387 - 7279);

        /** #These values correspond to the time it takes (in ns) to check if "-13170890158" is contained in
        #the data structures initialized with data1 */
        int OpenHashSet_Contains_negative = Math.abs(1034483 -1446910);
        int ClosedHashSet_Contains_negative = Math.abs(4663519    -    4591035);
        int TreeSet_Contains_negative = Math.abs(14804       -         42482);
        int LinkedList_Contains_negative = Math.abs(859654      -      1993153);
        int HashSet_Contains_negative = Math.abs(1120       -          25115);

        /** #These values correspond to the time it takes (in ns) to check if "23" is contained in
        #the data structures initialized with data2 */
        int OpenHashSet_Contains_23 = Math.abs(828       -           -  45240);
        int ClosedHashSet_Contains_23 = Math.abs(265                -   31543);
        int TreeSet_Contains_23 = Math.abs(13452                   -    84392);
        int LinkedList_Contains_23 = Math.abs(779557                  - 46850);
        int HashSet_Contains_23 = Math.abs(226                       -  35720);

        /** #These values correspond to the time it takes (in ns) to check if "hi" is contained in
        #the data structures initialized with data2 */
        int OpenHashSet_Contains_hi2 = Math.abs(880               -      38100);
        int ClosedHashSet_Contains_hi2 = Math.abs(1090             -     2150);
        int TreeSet_Contains_hi2 = Math.abs(430                     -    14695);
        int LinkedList_Contains_hi2 = Math.abs(962934                -   1548760);
        int HashSet_Contains_hi2 = Math.abs(722           -             1772);

        System.out.println("HashSet_Contains_hi2 = " + HashSet_Contains_hi2);
        System.out.println("LinkedList_Contains_hi2 = " + LinkedList_Contains_hi2);
        System.out.println("TreeSet_Contains_hi2 = " + TreeSet_Contains_hi2);
        System.out.println("ClosedHashSet_Contains_hi2 = " + ClosedHashSet_Contains_hi2);
        System.out.println("OpenHashSet_Contains_hi2 = " + OpenHashSet_Contains_hi2);
        System.out.println("HashSet_Contains_23 = " + HashSet_Contains_23);
        System.out.println("LinkedList_Contains_23 = " + LinkedList_Contains_23);
        System.out.println("TreeSet_Contains_23 = " + TreeSet_Contains_23);
        System.out.println("ClosedHashSet_Contains_23 = " + ClosedHashSet_Contains_23);
        System.out.println("OpenHashSet_Contains_23 = " + OpenHashSet_Contains_23);
        System.out.println("HashSet_Contains_negative = " + HashSet_Contains_negative);
        System.out.println("LinkedList_Contains_negative = " + LinkedList_Contains_negative);
        System.out.println("TreeSet_Contains_negative = " + TreeSet_Contains_negative);
        System.out.println("ClosedHashSet_Contains_negative = " + ClosedHashSet_Contains_negative);
        System.out.println("OpenHashSet_Contains_negative = " + OpenHashSet_Contains_negative);
        System.out.println("HashSet_Contains_hi1 = " + HashSet_Contains_hi1);
        System.out.println("LinkedList_Contains_hi1 = " + LinkedList_Contains_hi1);
        System.out.println("TreeSet_Contains_hi1 = " + TreeSet_Contains_hi1);
        System.out.println("ClosedHashSet_Contains_hi1 = " + ClosedHashSet_Contains_hi1);
        System.out.println("OpenHashSet_Contains_hi1 = " + OpenHashSet_Contains_hi1);




















//        if (args.length != 2){
//            System.out.println("Error in configurations!\n Enter file name and a number 1/2/3.");
//        }
//
//        String[] words = Ex3Utils.file2array(args[0]);
////        String [] words = {"a", "b", "c", "d", "e", "f","g", "h", "i", "j", "k", "l"};
//        System.out.println("data added");
//        SimpleHashSet hashSet;
//        long beginTime = System.nanoTime();
//        switch(args[1]){
//            case "1":
//                hashSet = new ClosedHashSet();
//                System.out.println("opened hash set");
//                break;
//            case "2":
//                hashSet = new ClosedHashSet(0.60f, 0.30f);
//                break;
//            case "3":
//                hashSet = new ClosedHashSet(words);
//                break;
//            default:
//                hashSet = new ClosedHashSet();
//        }
//
//
//        System.out.println("hashset size: " + hashSet.size());
//
//        if (!args[1].equals("3")) {
//            for (String word : words) {
//                hashSet.add(word);
//            }
//        }
//
//        System.out.println("hashset size: " + hashSet.size());
//        System.out.println("adding a duplicate (should fail): "+ hashSet.add(words[3]));
//
//        for (int i = 0; i < words.length; i ++) {
//
//            if (!hashSet.contains(words[i])) {
//                System.out.println(words[i]);
//                System.out.println("should be but not there... ");
//            }
//        }
//
//
//        int coundDeleted = 0;
//            for (int j = 0; j < words.length; j += 10) {
//
//                hashSet.delete(words[j]);
//                coundDeleted++;
//                if (hashSet.contains(words[j])) {
//                    System.out.println("oh oh: "+ words[j]);
//                }
//
//            }
//        System.out.println("deleted "+ coundDeleted);
//
//        System.out.println("hashset size: " + hashSet.size());
//
//        long difference = System.nanoTime() - beginTime;
//        System.out.println("Time: " + difference / 1000000 + " milliseconds");





    }
}

