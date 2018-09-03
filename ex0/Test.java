/**
 * Created by Keren Meron on 2/26/2016.
 */
public class Test {

    //books, patrons, and a library for test running
    static Book book1 = new Book("Huji Guidebook", "Dean", 1921, 9, 6, 2);
    static Book book2 = new Book("The little prince", "Antoine", 1943, 5, 3, 7);
    static Book book3 = new Book("Physics for Dummies", "Newton", 1702, 9, 9, 1);
    static Patron patron1 = new Patron("Cat", "In Boots", 2, 5, 6, 20);
    static Patron patron2 = new Patron("Donald", "Duck", 3, 6, 2, 18);
    static Patron patron3 = new Patron("John", "Smith", 5,5,5,10);
    static Library herman = new Library(2, 2, 2);


    static void print (String input){
        System.out.println(input);
    }

    public static void main (String[] args){
        print ("Hello. These basic tests will only work if your IDs are based on index in array, starting " +
                "count from 0. and if for all 'negative numbers' required, you gave -1.");

        print ("starting tests on Book class.");
        print("string representation: " + book1.stringRepresentation());
        print ("literary value: " + book1.getLiteraryValue() + " (should be 17).");
        print ("setting the borrower id to 13.");
        book1.setBorrowerId(13);
        print ("current borrower id is: " + book1.getCurrentBorrowerId() + " (should be 13).");
        print ("returning book.");
        book1.returnBook();
        print ("current borrower id is: " + book1.getCurrentBorrowerId() + " (should be -1).");
        print ("Completed tests on Book Class!");
        print ("");

        print ("Starting tests on Patron Class.");
        print ("string representation: " + patron1.stringRepresentation());
        print ("book score for huji guide book is: " + patron1.getBookScore(book1) + " (should be 60).");
        print ("book score for the little prince is: " +  patron1.getBookScore(book2) + " (should be 67");
        print ("Will cat in boots enjoy huji guide book? " + patron1.willEnjoyBook(book1) + " (should be " +
                "true)");
        print ("Will cat in boots enjoy the little prince? " + patron1.willEnjoyBook(book2) + " (should be " +
                "true");
        print ("Completed tests on Patron Class!");
        print ("");

        print ("Starting tests on Library Class.");
        print ("adding Huji Guidebook and The Little Prince to library.");
        int id1 = herman.addBookToLibrary(book1);
        int id2 = herman.addBookToLibrary(book2);
        print ("their ids are: " + id1 + id2 + " (should be 0,1).");
        print ("trying to add Physics for dummies to library.");
        int id3 = herman.addBookToLibrary(book3);
        print ("max books allowed is two, so book should not have been added. if: -1 == " + id3);
        print ("is huji guidebook in library? " + herman.isBookIdValid(id1));
        print ("is the little prince in library? " + herman.isBookIdValid(id2));
        print ("is physics for dummies in library? " + herman.isBookIdValid(id3));
        print ("huji guidebook's id is: " + herman.getBookId(book1));
        print ("the little prince's id is: " + herman.getBookId(book2));
        print ("physics for dummies's id is: " + herman.getBookId(book3));
        print ("huji guidebook and the little prince should both be available: " +herman.isBookAvailable
                (id1) +", "+ herman.isBookAvailable(id2) + ". And if i check a random id it should not: " +
                herman.isBookAvailable(2));
        print ("Registering Cat in boots and Donald Duck to the library.");
        int patronid1 = herman.registerPatronToLibrary(patron1);
        int patronid2 = herman.registerPatronToLibrary(patron2);
        print ("their ids are: " + patronid1 + patronid2 + ".  (should be 0,1).");
        print ("Trying to register John Smith to the library (while max patrons allowed is 2, so should not" +
                " be able to succeed.");
        int patronid3 = herman.registerPatronToLibrary(patron3);
        print ("So should return an id of -1. You received: " + patronid3);
        print ("Checking again if cat in boots and donald duck registered successfully to the library, and " +
                "that john smith was not. 0,1,-1 == " + herman.getPatronId(patron1) + herman.getPatronId
                (patron2) + herman.getPatronId(patron3));
        print ("Checking again via a different function. true, true, false == " + herman.isPatronIdValid
                (patronid1) + herman.isPatronIdValid(patronid2) + herman.isPatronIdValid(patronid3));
        print ("");

        print ("Cat in boots will borrow the huji guidebook.");
        print ("true == "+ herman.borrowBook(id1, patronid1));
        print ("John smith will try to borrow the little prince, but will fail.");
        print ("false == " + herman.borrowBook(id2, patronid3));
        print ("Cat in boots will borrow the little prince as well.");
        print ("true == " + herman.borrowBook(id2, patronid1));
        print ("Donald duck will try to borrow the huji guidebook, and will fail.");
        print ("false == " + herman.borrowBook(id1, patronid2));
        print ("Cat in boots will return the huji guidebook.");
        herman.returnBook(id1);
        print ("Donald duck will try again to borrow the huji guidebook, and will succeed.");
        print ("true == " + herman.borrowBook(id1, patronid2));
        print ("");

        print ("the current borrower ids for books are:");
        print ("huji guidebook: "+ book1.getCurrentBorrowerId() + " (should be donaldduck's 1");
        print ("the little prince: "+ book2.getCurrentBorrowerId() + " (should be catinboot's 0");
        print ("physics for dummies: "+ book3.getCurrentBorrowerId() + " (should be -1");


        print ("Returning the huji guidebook.");
        herman.returnBook(id1);
        print ("the suggested book for cat in boots should be the little prince == " + herman
                .suggestBookToPatron(patronid1).stringRepresentation());
        print ("Completed Library class tests!");
        print ("");
        print ("These are minimal basic tests only.");
        print ("Good Luck :)");

    }

}
