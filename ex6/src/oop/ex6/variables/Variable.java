package oop.ex6.variables;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class representing a variable in an S-Java program (super-class to all type-specific variables)
 */
public abstract class Variable implements Cloneable {

    /** data members */
    protected String type;
    protected String name;
    protected boolean isFinal;
    protected String value;


    /**
     * a class Variable constructor
     * @param type one of five types: String, int, boolean, double, char
     * @param name variable name.
     * @param isFinal boolean for if the variable is final or not
     * @param value var value
     * @throws VariableException If value doesn't match the type
     */
    Variable(String type, String name, boolean isFinal, String value) throws VariableException{
        this.type = type;
        this.name = name;
        this.isFinal = isFinal;
        if (value != null){
            setNewValue(value);
        } else { // value is null
            this.value = null;
        }
    }

    /**
     * @return The variables' type
     */
    public String getType() {
        return type;
    }

    /**
     * @return The name of the variable
     */
    public String getName() {
        return name;
    }

    /**
     * @return True if the variable is final, false otherwise
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Changes the variable into a final/non-final
     * @param isFinal true if the new variable will be final false otherwise
     */
    public void setFinal(boolean isFinal){
        this.isFinal = isFinal;
    }

    /**
     * @return The value of this variable
     */
    public String getValue() {
        return value;
    }

    /**
     * @return The pattern that a value for this type of variable must match
     */
    abstract Pattern getValuePattern();

    /**
     * Sets a new value to the variable
     * @param newValue The value to assign
     * @throws VariableException Thrown if the value doesn't match the type/ no value exists
     */
    public void setNewValue(String newValue) throws VariableException {
        if (isFinal) {
            throw new VariableException(VariableException.VALUE_TO_FINAL_MESSAGE);
            // value to a final
        }else if(newValue == null){
          throw new VariableException(VariableException.INVALID_VALUE_MESSAGE);
        } else {
            Matcher matcher = this.getValuePattern().matcher(newValue);
            if (matcher.matches()) {
                value = newValue;
            } else {
                throw new VariableException(VariableException.INVALID_VALUE_MESSAGE);
            }
        }
    }


}
