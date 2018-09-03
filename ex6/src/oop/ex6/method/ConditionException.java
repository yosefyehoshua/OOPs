package oop.ex6.method;

import oop.ex6.SjavaException;

/**
 * Exception class designated to notify bad condition format in if statements or while loops
 */
public class ConditionException extends SjavaException {

    /** The default message for condition exception*/
    private static final String DEFAULT_MESSAGE = "Invalid condition for If statement/ While loop";

    /**
     * Constructs a new SjavaException with the default message
     */
    public ConditionException(){
        super(DEFAULT_MESSAGE);
    }
}
