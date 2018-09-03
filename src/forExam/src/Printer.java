import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

/**
 * Created by Yosef.Yehoshua on 23/06/2016.
 */
public class Printer {
    public static void printAll(String fileName, int[] indices) {
        List<Printable> printables = null;
            System.err.println("Problem with file");

            return;
        }
    private static List<Printable> readPrintables(String fileName)
            throws IOException {
        String line = null;
        List<Printable> myList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))) { while ((line = br.readLine()) != null) {
        }
        }
        return myList;
    }
    private static void printSelected (List<Printable> printables, int[] indices) { for (int i = 0 ; i < indices.length ; ++i) {
    } }
}