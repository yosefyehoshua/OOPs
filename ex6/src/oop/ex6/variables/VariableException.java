package oop.ex6.variables;
import oop.ex6.SjavaException;

/**
 * Exception class uniting all variable-related exceptions such as: non-existing type, invalid value etc.
 */
public class VariableException extends SjavaException {

    private static final long serialVersionUID = 1L;

    /** Variable Exception default messages */
    public static final String SAME_NAME_SAME_SCOPE_MESSAGE = "Two variables with the same name are defined" +
            "in the same scope";
    public static final String INVALID_TYPE_MESSAGE = "Invalid variable type";
    public static final String VAR_DOESNT_EXISTS_MESSAGE = "Referred Variable doesn't exists";
    public static final String VALUE_TO_FINAL_MESSAGE = "Cannot assign value to a 'final' variable";
    public static final String INVALID_VALUE_MESSAGE = "Invalid value to assign";

    /**
     * Constructs a new VariableException with a given message
     * @param Message the given message (usually error description)
     */
    public VariableException(String Message){
        super(Message);
    }
}
