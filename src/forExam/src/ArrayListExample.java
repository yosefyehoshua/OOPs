/**
 * Created by Yosef.Yehoshua on 29/06/2016.
 */
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> groupList = new ArrayList<>();
        groupList.add(list);
        list.add(4);
        list.add(new Integer(0));
        int number = list.get(0)/list.get(1);

        list = new ArrayList<Integer>();
        int result = groupList.get(0).get(0);
    }
}

