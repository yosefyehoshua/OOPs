package oop.ex6.generalscope;

import oop.ex6.SecondaryInterpreter;
import oop.ex6.SjavaException;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableException;

/**
 * The secondary Interpreter that analyzes only lines that are within the general scope, (including
 * method declaration and excluding brackets closing a method's scope). The Interpreter analyzes the
 * line according to S-java's logic and throws exceptions in case of violation
 */
public class GeneralScopeInterpreter extends SecondaryInterpreter {

    /** The general scope which the interpreter is going over */
    GeneralScope generalScope;

    /**
     * Constructs a new GeneralScopeInterpreter
     * @param generalScope - The general scope on which the interpreter works on
     */
    GeneralScopeInterpreter(GeneralScope generalScope) {
        this.generalScope = generalScope;
    }


    /**
     * Handles a line that fits the closing brackets pattern - throws exception
     * @param line the line of the declaration
     * @throws GeneralScopeException If closed brackets not inside a method
     */
    @Override
    public void closedBracketsHandler(String line) throws GeneralScopeException{
        throw new GeneralScopeException(GeneralScopeException.CLOSING_BRACKETS_MESSAGE);
    }

    /**
     * Handles a new method call - Throws exception
     * @param methodName - The name of the called method
     * @param Parameters - The parameters used for the call
     * @throws SjavaException - method calls must be located within a method
     */
    @Override
    public void methodCallHandler(String methodName, String Parameters) throws SjavaException{
        throw new GeneralScopeException(GeneralScopeException.METHOD_CALL_MESSAGE);
    }

    /**
     * Handles a variable assignment - does nothing (global variables assignment is handled in the
     * initial interpreter
     * @param name The name of the assigned variable
     * @param value The value which is assigned (may be a name of an existing variable)
     * @throws VariableException if invalidValue/ non existing variable
     */
    @Override
    public void variableAssignmentHandler(String name, String value) throws VariableException {
        return;// Handled by the InitialInterpreter
    }

    /**
     * Registers a new variable - does nothing. Registering global variables is handled in the initial
     * interpreter
     * @param newVar The variable to register
     * @throws VariableException If a variable of that name exists in the same scope
     */
    @Override
    public void registerVariable(Variable newVar) throws VariableException {
        return; // Handled by the InitialInterpreter
    }

    /**
     * Handles a line that fits the return statement pattern - throws exception
     * @param line The line containing the return statement
     * @throws SjavaException If return statement is located in the general scope
     */
    @Override
    public void returnStatementHandler(String line) throws GeneralScopeException{
        throw new GeneralScopeException(GeneralScopeException.RETURN_OUT_OF_PLACE_MESSAGE);
    }

    /**
     * Handles a line that fits Variable Declaration - does nothing. global variables declaration is
     * handled in the initial interpreter
     * @param line the line of the declaration
     * @throws VariableException If wrong type/ invalid value
     */
    @Override
    public void newVariableDeclarationHandler(String line, String type, boolean isFinal) throws VariableException {
        return;// Handled by the InitialInterpreter
    }

    /**
     * Handles a line that fits the method declaration pattern - marks the current method to start
     * sending lines to the appropriate method interpreter
     * @param parametersDeclaration the declaration of the parameters
     * @param methodName The name of the new method
     * @throws SjavaException If parameters declaration is invalid or if a logic error occurred in the
     * method's code
     */
    @Override
    public void newMethodHandler(String parametersDeclaration, String methodName) throws SjavaException {
        generalScope.setCurrentMethod(generalScope.getMethodByName(methodName));
    }

    /**
     * Handles a line that fits the if statement or while loop patterns - throws exception
     * @param line the line of the declaration
     * @throws SjavaException If line is in Illegal Position (outside of method)
     */
    @Override
    public void ifWhileHandler(String line) throws GeneralScopeException{
        throw new GeneralScopeException(GeneralScopeException.IF_WHILE_OUT_OF_PLACE_MESSAGE);
    }
}
