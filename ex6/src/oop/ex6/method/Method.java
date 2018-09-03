package oop.ex6.method;

import oop.ex6.generalscope.GeneralScope;
import oop.ex6.SjavaException;
import oop.ex6.variables.FactoryVariable;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableException;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class representing method in an S-Java code. Containing the method's attributes (such as parameters,
 * variables etc.), a designated method interpreter and a few helper methods
 */
public class Method {

    /** The initial scope number of a method */
    private static final int LINE_NUMBER_DIFFERENCE = 1;

    /** Dummy value for parameters of type int/double/boolean */
    private static final String INT_DOUBLE_BOOLEAN_PARAMETER_DUMMY_VALUE = "1";

    /** Dummy value for parameters of type String */
    private static final String STRING_PARAMETER_DUMMY_VALUE = "\"1\"";

    /** Dummy value for parameters of type char */
    private static final String CHAR_PARAMETER_DUMMY_VALUE = "'1'";

    /** data members */

    /** The method's name*/
    private final String methodName;

    /** Arraylist of parameters types in method */
    ArrayList<Variable> parameters;

    /** Marks the line in which a return statement was last declared */
    private int returnStatementLine;

    /** The general scope in which the method is defined */
    GeneralScope generalScope;

    /** Flags whether the method's scope has ended or not */
    private boolean hasMethodEnded = false;

    /** The method's methodInterpreter */
    private MethodInterpreter methodInterpreter;

    /** A stack containing the method's variables, according to their scope */
    Stack<ArrayList<Variable>> variableStack;

    /**
     * Updates the location of the last return statement read in the method
     * @param returnStatementLine The line number of the last return statement
     */
    public void setReturnStatementLine(int returnStatementLine) {
        this.returnStatementLine = returnStatementLine;
    }

    /**
     * @return true if the method's code has been already read, false otherwise
     */
    public boolean hasMethodEnded() {
        return hasMethodEnded;
    }

    /**
     * Updates the 'hasMethodEnded' to true AKA notifying that the brackets closing the method were just
     * read by the reader
     */
    void notifyThatMethodEnded(){
        hasMethodEnded = true;
    }

    /**
     * class Method constructor
     *
     * @param methodName   The method Name.
     * @param generalScope The general scope in which the method lies
     */
    public Method(String methodName, GeneralScope generalScope) {
        this.methodName = methodName;
        this.generalScope = generalScope;
        this.methodInterpreter = new MethodInterpreter(this);
        this.parameters = new ArrayList<>();
        // Initialize the array of variables located in the method's outer scope
        variableStack = new Stack<>();
        variableStack.push(new ArrayList<>()); // New scope
    }

    /**
     * @return The method's name
     */
    public String getName() {
        return methodName;
    }

    /**
     * Adds a new variable to the current scope variables list
     *
     * @param newVar - The variable to add to the method
     * @throws VariableException If two variables with the same are declared in the same scope
     */
    public void addVariable(Variable newVar) throws VariableException {
        for (Variable existingVar : variableStack.peek()) {
            if (newVar.getName().equals(existingVar.getName())) {
                throw new VariableException(VariableException.SAME_NAME_SAME_SCOPE_MESSAGE);
            }
        }
        variableStack.peek().add(newVar);
    }

    /**
     * @param varName - The name of  the variable to receive
     * @return The variable (in the most inner scope) that matches the given name, null is not found
     */
    public Variable getVarByName(String varName) {
        Stack<ArrayList<Variable>> reversedStack = new Stack<>();
        Variable result = null;
        while (!variableStack.isEmpty()) {
            reversedStack.push(variableStack.pop());
        }
        for (ArrayList<Variable> VariablesList : reversedStack) {
            for (Variable variable : VariablesList) {
                if (varName.equals(variable.getName())) {
                    result = variable;
                }
            }
        }
        while (!reversedStack.isEmpty()) {
            variableStack.push(reversedStack.pop());
        }
        if (result == null) { // variable nor found - search the global variables
            return generalScope.getVarByName(varName);
        }
        return result;
    }

    /**
     * Adds a new parameter to the method and assigns it with a dummy value (so the interpreter would
     * analyze it as if it has one)
     *
     * @param parameter The parameter to add
     * @throws VariableException
     */
    public void addParameter(Variable parameter) throws VariableException {
        switch (parameter.getType()) {
            case FactoryVariable.BOOLEAN:
            case FactoryVariable.INT:
            case FactoryVariable.DOUBLE:
                parameter.setNewValue(INT_DOUBLE_BOOLEAN_PARAMETER_DUMMY_VALUE);
                break;
            case FactoryVariable.CHAR:
                parameter.setNewValue(CHAR_PARAMETER_DUMMY_VALUE);
                break;
            case FactoryVariable.STRING:
                parameter.setNewValue(STRING_PARAMETER_DUMMY_VALUE);
                break;
        }
        addVariable(parameter);
        parameters.add(parameter);
    }

    /**
     * Reads a line from the code of the program and analyze it according to the method's attributes and
     * S-java internal logic.
     *
     * @throws SjavaException If a logic error occurred in the method
     */
    public void readLine(String line) throws SjavaException {
        methodInterpreter.classifyLine(line);
        if (hasMethodEnded) {
            // Check if 'return' was the previous line
            if (returnStatementLine != generalScope.getCurrentLineNumber() - LINE_NUMBER_DIFFERENCE) {
                throw new MethodException(MethodException.NO_RETURN_STATEMENT_MESSAGE);
            }
        }
    }
}


