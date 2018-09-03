package oop.ex6.variables;

/**
 * A factory (singleton) for creating variables in S-java
 */
public class FactoryVariable {

    /** The different variable types in s-java */
    public static final String STRING = "String";
    public static final String INT = "int";
    public static final String DOUBLE = "double";
    public static final String BOOLEAN = "boolean";
    public static final String CHAR = "char";
    private static final String[] VAR_TYPES_NAMES = {STRING, INT, DOUBLE, CHAR, BOOLEAN};
    /** The single instance of this factory*/
    private static FactoryVariable factory = new FactoryVariable();

    /**
     * @param typeName - The name to verify
     * @return True if this is a name of a known variable type, false otherwise
     */
    public static boolean isTypeName(String typeName){
        for(String type: VAR_TYPES_NAMES){
            if(type.equals(typeName)){
                return true;
            }
        }
        return false;
    }

    /**
     *  A private constructor
     *  */
    private FactoryVariable(){}

    /**
     * @return The only instance of FactoryVariable
     */
    public static FactoryVariable instance(){
        return factory;
    }

    /**
     * generates a variable according to the type ,of the variable that is given. and throw a
     * VariableException, if the type doesn't exists.
     * @param type one of five types: String, int, boolean, double, char
     * @param name variable name.
     * @param isFinal boolean for if the variable is final or not
     * @param value var value
     * @return a type Variable object
     * @throws VariableException
     */
    public Variable getVariable(String type, String name, boolean isFinal, String value) throws VariableException {
        switch (type) {
            case STRING:
                return new StringVariable(type, name, isFinal, value);
            case INT:
                return new IntVariable(type, name, isFinal, value);
            case DOUBLE:
                return new DoubleVariable(type, name, isFinal, value);
            case BOOLEAN:
                return new BooleanVariable(type, name, isFinal, value);
            case CHAR:
                return new CharVariable(type, name, isFinal, value);
            default:
                throw new VariableException(VariableException.INVALID_TYPE_MESSAGE);
        }
    }
}
