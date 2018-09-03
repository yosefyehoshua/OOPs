
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.LinkedList; import java.util.List;
public class TestClass {

    static void printList(List<Object> list) {
        for (Object e : list)
            System.out.println(e);
    }

    public static void main(String[] args) {
        List<String> list = new LinkedList<String>();
        list.add("fun");
    }
}