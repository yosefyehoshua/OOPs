import java.io.IOException;
import java.io.Reader;
import java.util.List;


class F  {
    public static void main(String[] args) {
        List list;
        try {
            foo();
            System.out.println("0");
        } catch (IOException e) {
            System.out.println("1");
        } finally {
            System.out.println("3");
        }
        System.out.println("4");
    }
    public static void foo() throws MyException {
        throw new MyException();
    }

    private static class MyException extends IOException {
    }
}