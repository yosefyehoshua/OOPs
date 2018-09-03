package oop.ex6.variables;

import java.lang.String;
import java.util.regex.Pattern;

/**
 * A class representing a variable of type double in S-Java
 */
public class DoubleVariable extends Variable {

    /** The pattern all double values must match */
    static final Pattern doublePattern = Pattern.compile("-?\\d+\\.?\\d*");

    /**
     * a class DoubleVariable constructor
     * @param type type: Double.
     * @param name variable name.
     * @param isFinal boolean for if the variable is final or not
     * @param value var value
     * @throws VariableException If value doesn't match the type
     */
    DoubleVariable(String type, String name, boolean isFinal, String value) throws VariableException {
        super(type, name, isFinal, value);
    }

    /**
     * @return The pattern that a double values fit
     */
    public Pattern getValuePattern() {
        return doublePattern;
    }

}
