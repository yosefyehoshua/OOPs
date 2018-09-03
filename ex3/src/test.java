/**
 * Created by Yosef.Yehoshua on 04/04/2016.
 */
public class test {

    public static void main(String args[]){
        String[] list = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "15",
                "16","17"};
        SimpleHashSet hashSet = new OpenHashSet();
        for (String word : list) {
            hashSet.add(word);
            System.out.println("contain word = " + hashSet.contains(word));
        }

        System.out.println(hashSet.contains("10"));

    }
}
