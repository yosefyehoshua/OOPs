package oop.ex6.variables;

import java.util.regex.Pattern;

/**
 * A class representing a variable of type String in S-Java
 */
public class StringVariable extends Variable {

    /** The pattern all String values must match */
    static final Pattern stringPattern = Pattern.compile("\".*\"");

    /**
     * a class StringVariable constructor
     * @param type type: String.
     * @param name variable name.
     * @param isFinal boolean for if the variable is final or not
     * @param value var value
     * @throws VariableException If value doesn't match the type
     */
    StringVariable(String type, String name, boolean isFinal, String value) throws VariableException {
        super(type, name, isFinal, value);
    }

    /**
     * @return The pattern that a String values fit
     */
    public Pattern getValuePattern() {
        return stringPattern;
    }

}
