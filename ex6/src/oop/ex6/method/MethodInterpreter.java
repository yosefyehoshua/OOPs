package oop.ex6.method;

import oop.ex6.generalscope.GeneralScopeException;
import oop.ex6.SecondaryInterpreter;
import oop.ex6.SjavaException;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The secondary Interpreter that analyzes only lines that are within a method, (excluding
 * method declaration and including brackets closing a method's scope). The Interpreter analyzes the
 * line according to S-java's logic and throws exceptions in case of violation
 */
public class MethodInterpreter extends SecondaryInterpreter {

    /** Basic method patterns */
    private static final String CONDITION_CONSTANTS = "\\s*(-\\d+|\\d+|-\\d+[.]\\d+|\\d+" +
            "[.]\\d+|true|false)\\s*";
    private static final String SINGLE_VARIABLE_IN_CONDITION = "\\s*(?<name>[^\\|\\||^\\&\\&]+)\\s*";
    private final String CONDITION_STATEMENT = "(?<condition>(?:\\s*(?:\\w+)\\s*(?:\\|\\||\\&\\&))*\\s*(?:\\w+)\\s*)";
    private static final String SPACE = " ";
    private static final String COMMA = ",";

    private static final int FIRST_CELL = 0;
    private static final int VAR_CELL = 0;
    /** Dummy value for parameters of type int/double/boolean */
    private static final String INT_DOUBLE_BOOLEAN_PARAMETER_DUMMY_VALUE = "1";


    /** Pattern that matches the condition and pra if/while statement  */
    Pattern conditionPattern = Pattern.compile(CONDITION_STATEMENT);

    /** The method which the interpreter is going over */
    Method method;

    /**
     * Constructs a new MethodInterpreter
     * @param method - The method on which the interpreter works on
     */
    MethodInterpreter(Method method) {
        this.method = method;
    }

    /**
     * Handles a line that fits Variable Declaration - creating a new variable and registering it
     * @param line the line of the declaration
     * @throws VariableException If wrong type/ invalid value
     */
    public void newVariableDeclarationHandler(String line, String type, boolean isFinal) throws
            VariableException {
        variableDeclaration(line, type, isFinal);
    }

    /**
     * Handles a line that fits the if statement or while loop patterns - Opening a new scope and
     * validating the condition
     * @param condition the condition inside the if/while statement
     * @throws SjavaException If line is in Illegal Position
     */
    public void ifWhileHandler(String condition) throws SjavaException {
        if (isConditionValid(condition)) {
            method.variableStack.push(new ArrayList<>());
        } else {
            throw new ConditionException(); // "Condition invalid"
        }
    }

    /**
     * This method gets the boolean condition of an if statement or a while loop and checks if it's a
     * valid condition
     * @param condition a string with some boolean condition (can be invalid)
     * @return true if the boolean condition is a valid one, false otherwise
     * @throws ConditionException If condition is Invalid
     */
    public boolean isConditionValid(String condition) throws SjavaException {
        // pattern of a condition in if/while (i.e: true || 6 && false, 5)
        Matcher matcher = conditionPattern.matcher(condition);
        if (matcher.matches()) {
            boolean conditionIsCool = false;
            // slice the condition string to bits of var names
            Pattern variableTillAndOrPattern = Pattern.compile(SINGLE_VARIABLE_IN_CONDITION);
            Matcher variableMatcher = variableTillAndOrPattern.matcher(condition);
            // pattern of constants/true/false that may appear as condition (i.e: 0.0, -5, 4.20)
            Pattern conditionConstantPattern = Pattern.compile(CONDITION_CONSTANTS);
            while (variableMatcher.find()) { // If this is a valid variable name
                String rawVariable = variableMatcher.group(VAR_NAME_GROUP);
                Matcher conditionConstantMatcher = conditionConstantPattern.matcher(rawVariable);
                String[] rawVariableArray = rawVariable.split(SPACE);
                if (conditionConstantMatcher.matches()) {
                    conditionIsCool = true;
                } else {
                    Variable testVar = method.getVarByName(rawVariableArray[VAR_CELL]);
                    String originalValue = testVar.getValue();
                    // Check if var can get a boolean\int\double value. if not - an exception is thrown
                    try{
                        testVar.setNewValue(INT_DOUBLE_BOOLEAN_PARAMETER_DUMMY_VALUE);
                        testVar.setNewValue(originalValue); // If value was null - exception thrown
                    } catch (VariableException e){ // Assigning a boolean value is invalid
                        throw new ConditionException();
                    }
                    conditionIsCool = true;
                }
            }
            return conditionIsCool;
        } else { // Pattern doesn't match
            throw new ConditionException();
        }
    }

    /**
     * Handles a variable assignment - checks the assigned variable and the value
     * @param name The name of the assigned variable
     * @param value The value which is assigned (may be a name of an existing variable)
     * @throws VariableException if invalidValue/ non existing variable
     */
    public void variableAssignmentHandler(String name, String value) throws VariableException {
        Variable assignedVariable = method.getVarByName(name);
        Variable valueVariable = method.getVarByName(value);
        if (assignedVariable != null){
            //Check if the assigned variable is global
            if (assignedVariable.equals(method.generalScope.getVarByName(assignedVariable.getName()))){
                // Create a new 'delegate' of the global in the used scope
                assignedVariable = variablesFactory.getVariable(assignedVariable.getType(),
                        assignedVariable.getName(),assignedVariable.isFinal(), null);
                method.addVariable(assignedVariable);
            }
            if (valueVariable != null) {
                assignedVariable.setNewValue(valueVariable.getValue());
            } else {
                assignedVariable.setNewValue(value);
            }
        } else { // Assigned variable doesn't exists
            throw new VariableException(VariableException.VAR_DOESNT_EXISTS_MESSAGE);
        }
    }

    /**
     * Registers a new variable
     * @param newVar The variable to register
     * @throws VariableException If a variable of that name exists in the same scope
     */
    @Override
    public void registerVariable(Variable newVar) throws VariableException {
        method.addVariable(newVar);
    }

    /**
     * Handles a line that fits the return statement pattern - updates the line in which the statement
     * is made.
     * @param line The line containing the return statement
     * @throws SjavaException If return statement is located in the general scope
     */
    public void returnStatementHandler(String line) {
        method.setReturnStatementLine(method.generalScope.getCurrentLineNumber());
    }

    /**
     * Handles a line that fits the method declaration pattern - throws exception
     * @param parametersDeclaration the declaration of the parameters
     * @param methodName The name of the new method
     * @throws SjavaException If a new method is declared in another method
     */
    public void newMethodHandler(String parametersDeclaration, String methodName) throws SjavaException {
        throw new MethodException(MethodException.METHOD_DECLARATION_INSIDE_METHOD_MESSSAGE);
    }

    /**
     * Handles a line that fits the closing brackets pattern - closes current scope and notifying if
     * the method's scope has ended
     * @param line the line of the declaration
     * @throws GeneralScopeException If closed brackets not inside a method
     */
    public void closedBracketsHandler(String line) throws GeneralScopeException {
        method.variableStack.pop(); // Throw away variables from inner Scope
        if (method.variableStack.isEmpty()) { // Current scope is general scope
            method.notifyThatMethodEnded();
        }
    }

    /**
     * Handles a new method call - checks validity of method name and parameters
     * @param methodName - The name of the called method
     * @param parameters - The parameters used for the call
     * @throws SjavaException If method doesn't exists or parameters doesn't match method requirements
     */
    public void methodCallHandler(String methodName, String parameters) throws MethodException,
            VariableException {
        Method calledMethod = method.generalScope.getMethodByName(methodName);
        String[] parametersArray = new String[FIRST_CELL];
        if(parameters != null){
            parameters = parameters.replaceAll("\\s","");
            parametersArray = parameters.split(COMMA);
        }
        if (calledMethod == null){ // Method doesn't exists
            throw new MethodException(MethodException.CALLED_METHOD_DOES_NOT_EXISTS_MESSAGE);
        }
        if (parametersArray.length == calledMethod.parameters.size()){
            for (int i = FIRST_CELL; i < parametersArray.length; i++) {
                String parameter = parametersArray[i];
                Variable existingVariable = method.getVarByName(parameter);
                if (existingVariable != null) {
                    try{
                        calledMethod.parameters.get(i).setNewValue(existingVariable.getValue());
                    } catch (VariableException e) {
                        throw new MethodException(MethodException.INCORRECT_PARAMETERS_MESSAGE);
                    }
                } else {
                    try{
                        calledMethod.parameters.get(i).setNewValue(parameter);
                    } catch (VariableException e) {
                        throw new MethodException(MethodException.INCORRECT_PARAMETERS_MESSAGE);
                    }
                }
            }
        } else { // Too few/many parameters
            throw new MethodException(MethodException.INCORRECT_PARAMETERS_MESSAGE);
        }
    }

}
