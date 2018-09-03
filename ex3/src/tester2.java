/**
 * Created by Yosef.Yehoshua on 04/04/2016.
 */
public class tester2 {
        public static void main(String args[]){
            String[] words = new String[3] ;
            words[0] = "maya";
            words[1] = "sela";
            words[2] = "oop";
            OpenHashSet myOpenHashSet = new OpenHashSet(words);
            System.out.println(myOpenHashSet.add("dan"));
            System.out.println(myOpenHashSet.add("maya"));
            System.out.println(myOpenHashSet.contains("sela"));
//        WrapperLinkedList[] list = new WrapperLinkedList[2];
//        list[0] = new WrapperLinkedList();
//        list[1] = new WrapperLinkedList();
//        list[0].linkedList.add(words[0]);
//        System.out.println(list[0]);
//        System.out.print(list[0].linkedList.contains(words[0]));
        }
    }



