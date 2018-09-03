package oop.ex6.variables;

import java.util.regex.Pattern;

/**
 * A class representing a variable of type int in S-Java
 */
public class IntVariable extends Variable {

    /** The pattern all int values must match */
    static final Pattern intPattern = Pattern.compile("-?\\d+");

    /**
     * a class IntVariable constructor
     * @param type type: int.
     * @param name variable name.
     * @param isFinal boolean for if the variable is final or not
     * @param value var value
     * @throws VariableException If value doesn't match the type
     */
    IntVariable(String type, String name, boolean isFinal, String value) throws VariableException {
        super(type, name, isFinal, value);
    }

    /**
     * @return The pattern that an int values fit
     */
    public Pattern getValuePattern() {
        return intPattern;
    }

}
