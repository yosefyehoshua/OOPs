/**
 * This class represents a library patron that has a name and assigns values to different literary aspects of
 * books.
 */
public class Patron {

    /** The patron First Name. */
    String firstName;

    /** The patron Last Name. */
    String lastName;

    /** The Patron Tendency value to a comic book. */
    int tendencyToComic;

    /** The Patron Tendency value to a dramatic book. */
    int tendencyToDramatic;

    /** The Patron Tendency value to a educational book. */
    int tendencyToEducational;

    /** The Threshold of patron Enjoyment from the book. */
    int EnjoymentThresholdOfPatron;

    /** The number of books the partron borrowed */
    int numberOfBorrowedBooks = 0;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new book with the given characteristic.
     * @param patronFirstName The patron First Name.
     * @param patronLastName The patron Last Name.
     * @param comicTendency The Patron Tendency to a comic book.
     * @param dramaticTendency The Patron Tendency to a dramatic book.
     * @param educationalTendency The Patron Tendency to a educational book.
     * @param patronEnjoymentThreshold The Threshold of patron Enjoyment from the book.
     */
    Patron(String patronFirstName, String patronLastName, int comicTendency, int dramaticTendency,
           int educationalTendency, int patronEnjoymentThreshold) {
        firstName = patronFirstName;
        lastName = patronLastName;
        tendencyToComic = comicTendency;
        tendencyToDramatic = dramaticTendency;
        tendencyToEducational = educationalTendency;
        EnjoymentThresholdOfPatron = patronEnjoymentThreshold;
        numberOfBorrowedBooks = 0;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Returns a string representation of the patron, which is a sequence of its first and last name,
     * separated by a single white space. For example, if the patron's first name is "Ricky" and his last
     * name is "Bobby", this method will return the String "Ricky Bobby".
     * @return the String representation of this patron.
     */

    String stringRepresentation() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the literary value this patron assigns to the given book.
     * @param book - The book to asses.
     * @return the literary value this patron assigns to the given book.
     */
    int getBookScore(Book book) {
        return tendencyToComic * book.comicValue + tendencyToDramatic * book.dramaticValue +
                tendencyToEducational * book.educationalValue;
    }

    /**
     * Returns true of this patron will enjoy the given book, false otherwise.
     * @param book - The book to asses.
     * @return true of this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book) {
        return (getBookScore(book) >= EnjoymentThresholdOfPatron);
    }

    /**
     * update (add) the number Of Borrowed Books of the patron
     */
    void addBorrowedBook() {
        numberOfBorrowedBooks++;
    }

    /**
     * update (remove) the number Of Borrowed Books of the patron
     */
    void removeBorrowedBook() {
        numberOfBorrowedBooks--;
    }
    }



