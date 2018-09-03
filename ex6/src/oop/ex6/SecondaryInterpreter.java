package oop.ex6;

import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableException;
import oop.ex6.generalscope.GeneralScopeException;

/**
 * A verifier for S-Java code, after classifying the line, provides tools to check its logical structure
 * according to the laws of the programming language
 */
public abstract class SecondaryInterpreter implements SJavaInterpreter {


    /**
     * Handles a line that fits Variable Declaration
     * @param line the line of the declaration
     * @throws VariableException If wrong type/ invalid value
     */
    public abstract void newVariableDeclarationHandler(String line, String type, boolean isFinal) throws
            VariableException;

    /**
     * Handles a line that fits the method declaration pattern
     * @param parametersDeclaration the declaration of the parameters
     * @param methodName The name of the new method
     * @throws SjavaException If parameters declaration is invalid
     */
    public abstract void newMethodHandler(String parametersDeclaration, String methodName) throws
            SjavaException;

    /**
     * Handles a line that fits the if statement or while loop patterns
     * @param line the line of the declaration
     * @throws SjavaException If line is in Illegal Position
     */
    public abstract void ifWhileHandler(String line) throws SjavaException;

    /**
     * Handles a line that fits the return statement pattern
     * @param line The line containing the return statement
     * @throws SjavaException If return statement is located in the general scope
     */
    public abstract void returnStatementHandler(String line) throws SjavaException;

    /**
     * Handles a line that fits the closing brackets pattern
     * @param line the line of the declaration
     * @throws GeneralScopeException If closed brackets not inside a method
     */
    public abstract void closedBracketsHandler(String line) throws GeneralScopeException;

    /**
     * Handles a variable assignment
     * @param name The name of the assigned variable
     * @param value The value which is assigned (may be a name of an existing variable)
     * @throws VariableException if invalidValue/ non existing variable
     */
    public abstract void variableAssignmentHandler(String name, String value) throws VariableException;

    /**
     * Registers a new variable
     * @param newVar The variable to register
     * @throws VariableException If a variable of that name exists in the same scope
     */
    public abstract void registerVariable(Variable newVar) throws VariableException;


}
