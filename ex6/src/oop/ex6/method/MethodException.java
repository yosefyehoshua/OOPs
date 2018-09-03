package oop.ex6.method;

import oop.ex6.SjavaException;

/**
 * Exception class uniting all variable-related exceptions such as: invalid parameters, invalid method call
 * etc.
 */
public class MethodException extends SjavaException {

    private static final long serialVersionUID = 1L;

    /** Variable Exception default messages */
    public static final String NO_RETURN_STATEMENT_MESSAGE = "No return Statement at the end of the method";
    public static final String METHOD_DECLARATION_INSIDE_METHOD_MESSSAGE = "New method cannot be declared " +
            "inside a method";
    public static final String INCORRECT_PARAMETERS_MESSAGE = "Parameters are sent doesn't match the " +
            "called method";
    public static final String CALLED_METHOD_DOES_NOT_EXISTS_MESSAGE = "Called method doesn't exists";

    /**
     * Constructs a new MethodException with a given message
     * @param Message the given message (usually error description)
     */
    public MethodException(String Message){
        super(Message);
    }
}
