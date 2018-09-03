import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.tools.javac.util.*;

import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.List;
import java.io.Serializable;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Created by Yosef.Yehoshua on 23/06/2016.
 */
public class main {
    public static Integer b = 3;

    public static void main(String[] args) throws IOException {
        Object a = new B();
        ObjectOutputStream os = new ObjectOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        });
        B b = (B)a;

        System.out.println(b.foo());
        System.out.println(b.c);
        List list = new LinkedList<>();
        list.add("hello");
        String s = (String)list.get(0);

    }
}
