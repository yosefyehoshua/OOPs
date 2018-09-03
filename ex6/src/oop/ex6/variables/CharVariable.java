package oop.ex6.variables;

import java.util.regex.Pattern;

/**
 * A class representing a variable of type char in S-Java
 */
public class CharVariable extends Variable {

    /** The pattern all char values must match */
    static final Pattern charPattern = Pattern.compile("'.'");

    /**
     * a class CharVariable constructor
     * @param type type: char.
     * @param name variable name.
     * @param isFinal boolean for if the variable is final or not
     * @param value var value
     * @throws VariableException If value doesn't match the type
     */
    CharVariable(String type, String name, boolean isFinal, String value) throws VariableException {
        super(type, name, isFinal, value);
    }

    /**
     * @return The pattern that a char values fit
     */
    public Pattern getValuePattern() {
        return charPattern;
    }

}



