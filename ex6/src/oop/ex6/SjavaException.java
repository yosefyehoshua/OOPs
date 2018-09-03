package oop.ex6;

/**
 * Super-class uniting all exceptions in the program except IOExceptions
 */
public class SjavaException extends Throwable{

    public static final String ILLEGAL_LINE_MESSAGE = "Illegal Line";

    /**
     * Constructs a new SjavaException with a given message
     * @param Message the given message (usually error description)
     */
    public SjavaException(String Message){
        super(Message);
    }

}
