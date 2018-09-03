package filesprocessing;

/**
 * an ErrorTypeTwo class that extends Exception for commendfile format errors
 */
public class ErrorTypeTwo extends Exception {

    /**
     * String errorMassage instance initialization
     */
    private final String errorMassage;

    /**
     * an ErrorTypeTwo gets the info. of the error that happened
     * @param errorMassage String of the error info.
     */
    public ErrorTypeTwo(String errorMassage) {
        this.errorMassage = errorMassage;
    }

    /**
     * prints an error massage using errorMassage instance
     */
    public void printErrorMessage(){
        System.err.println("ERROR: " + errorMassage);
    }

}
