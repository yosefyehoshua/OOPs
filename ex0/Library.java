/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library to
 * be able to check out books, if a copy of the requested book is available.
 */
public class Library {

    /**
     * The maximum Capacity the library can store.
     */
    int maximumBookCapacity;

    /**
     * The maximum Capacity that one patron can borrow.
     */
    int maximumBorrowedBooks;

    /**
     * The maximum number of patrons in library.
     */
    int maximumPatronCapacity;

    /**
     * The list of books in library.
     */
    Book[] booksArray;

    /**
     * the list of patrons in library.
     */
    Patron[] patronsArray;

    /**
     * number of books in library.
     */
    int numberOfBooks = 0;

    /**
     * number of borrowed books in library.
     */
    int numberOfBorrowedBooks = 0;

    /**
     * number of registered patrons in library.
     */
    int registeredPatronsInLibrary = 0;

    /**
     * magic number symbolize a non valid value.
     */
    final static int NONVALID = -1;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new book with the given characteristic.
     *
     * @param maxBookCapacity   The max Capacity of books the library can store.
     * @param maxBorrowedBooks  The max Capacity a patron can borrow.
     * @param maxPatronCapacity The max number of patrons in library.
     */

    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
        maximumBookCapacity = maxBookCapacity;
        maximumBorrowedBooks = maxBorrowedBooks;
        maximumPatronCapacity = maxPatronCapacity;
        booksArray = new Book[maximumBookCapacity];
        patronsArray = new Patron[maximumPatronCapacity];
        numberOfBooks = 0;
        numberOfBorrowedBooks = 0;
        registeredPatronsInLibrary = 0;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     *
     * @param book - The book to add to this library.
     * @return a non-negative id number for the book if there was a spot and the book was successfully added,
     * or if the book was already in the library; a negative number otherwise.
     */

    int addBookToLibrary(Book book) {
        if (book.getCurrentBorrowerId() != -1) {
            book.returnBook();
        }
        for (int i = 0; i < maximumBookCapacity; i++) {
            if (booksArray[i] == null) {
                booksArray[i] = book;
                numberOfBooks++;
                return i;
            } else if (booksArray[i] == book) {
                return i;
            }
        }
        return NONVALID;
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id, if this
     * book is available, the given patron isn't already borrowing the maximal number of books allowed,
     * and if the patron will enjoy this book.
     * @param bookId   - The id number of the book to borrow.
     * @param patronId - The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */

    boolean borrowBook(int bookId, int patronId) {
        if (isBookIdValid(bookId) && isPatronIdValid(patronId)) {
            if (isBookAvailable(bookId) && patronsArray[patronId].numberOfBorrowedBooks <
                    maximumPatronCapacity
                    && patronsArray[patronId].willEnjoyBook(booksArray[bookId])) {
                patronsArray[patronId].addBorrowedBook();
                booksArray[bookId].setBorrowerId(patronId);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     *
     * @param book - The book for which to find the id number.
     * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */

    int getBookId(Book book) {
        for (int i = 0; i < numberOfBooks; i++) {
            if (booksArray[i] == book) {
                return i;
            }
        }
        return NONVALID;
    }

    /**
     * Returns the non-negative id number of the given patron if he is registered to this library,
     * -1 otherwise.
     * @param patron - The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     */
    int getPatronId(Patron patron) {
        for (int i = 0; i < maximumPatronCapacity; i++) {
            if (patronsArray[i] == patron) {
                return i;
            }
        }
        return NONVALID;
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     * Parameters:
     *
     * @param bookId - The id number of the book to check.
     * @return true if the book with the given id is available, false otherwise.
     */

    boolean isBookAvailable(int bookId) {
        return (isBookIdValid(bookId) && booksArray[bookId].getCurrentBorrowerId() == -1);
    }

    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     *
     * @param bookId - The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        return (bookId >= 0 && bookId < maximumBookCapacity && booksArray[bookId] != null);
    }

    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     *
     * @param patronId - The id to check.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId) {
        return (patronId >= 0 && patronId < maximumPatronCapacity && patronsArray[patronId] != null);
    }

    /**
     * Registers the given Patron to this library, if there is a spot available.
     *
     * @param patron - The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot and the patron was successfully
     * registered, a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron) {
        int patronId = getPatronId(patron);
        if (patronId != NONVALID) {
                return patronId;
        } if (registeredPatronsInLibrary < maximumPatronCapacity) {
            patronsArray[registeredPatronsInLibrary] = patron;
            registeredPatronsInLibrary++;
            return registeredPatronsInLibrary -1;
             }

        return NONVALID;
    }

     /**
     * Return the given book.
     *
     * @param bookId - The id number of the book to return.
     */
    void returnBook(int bookId) {
        if (isBookIdValid(bookId)) {
            // updates the number of books the patron borrowed
            patronsArray[booksArray[bookId].getCurrentBorrowerId()].removeBorrowedBook();
            booksArray[bookId].setBorrowerId(NONVALID);
        }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most, out of all available books he
     * will enjoy, if any such exist.
     * @param patronId - The id number of the patron to suggest the book to.
     * @return The available book the patron with the given will enjoy the most. Null if no book is available.
     */
    Book suggestBookToPatron(int patronId) {
        int bestBook;
        if (isPatronIdValid(patronId) && isBookAvailable(0)) {
            bestBook = 0;
            for (int i = 1; i < numberOfBooks; i++) {
                if (isBookAvailable(i) && patronsArray[patronId].willEnjoyBook(booksArray[i]) &&
                        patronsArray[patronId].getBookScore(booksArray[i]) >=
                                patronsArray[patronId].getBookScore(booksArray[bestBook])) {
                    bestBook = i;
                }

            }
            return booksArray[bestBook];
        }
        return null;
    }
}





