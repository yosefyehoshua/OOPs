package oop.ex6.variables;

import java.util.regex.Pattern;

/**
 * A class representing a variable of type boolean in S-Java
 */
public class BooleanVariable extends Variable {

    /** The pattern all boolean values must match */
    static final Pattern booleanPatten = Pattern.compile("true|false|"+ IntVariable
            .intPattern+"|"+ DoubleVariable.doublePattern);

    /**
     * a class BooleanVariable constructor
     * @param type type: boolean.
     * @param name variable name.
     * @param isFinal boolean for if the variable is final or not
     * @param value var value
     * @throws VariableException If value doesn't match the type
     */
    BooleanVariable(String type, String name, boolean isFinal, String value) throws VariableException{
        super(type, name, isFinal, value);
    }

    /**
     * @return The pattern that boolean values fit
     */
    public Pattern getValuePattern() {
        return booleanPatten;
    }
}
