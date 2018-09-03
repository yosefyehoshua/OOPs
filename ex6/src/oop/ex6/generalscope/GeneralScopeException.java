package oop.ex6.generalscope;

import oop.ex6.SjavaException;

/**
 * Exception class uniting all general scope exceptions such as: invalid lines, unexpected calls etc.
 */
public class GeneralScopeException extends SjavaException {

    private static final long serialVersionUID = 1L;

    /** Variable Exception default messages */
    public static final String SAME_NAME_METHODS_MESSAGE = "Cannot define two methods with the same name";
    public static final String CLOSING_BRACKETS_MESSAGE = "Closing Brackets out of place";
    public static final String METHOD_CALL_MESSAGE = "Cannot call a method from general scope";
    public static final String RETURN_OUT_OF_PLACE_MESSAGE = "Return Statement outside Method";
    public static final String IF_WHILE_OUT_OF_PLACE_MESSAGE = "If/While Statement outside Method";

    /**
     * Constructs a new MethodException with a given message
     * @param Message the given message (usually error description)
     */
    public GeneralScopeException(String Message){
        super(Message);
    }
}
