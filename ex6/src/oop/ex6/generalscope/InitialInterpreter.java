package oop.ex6.generalscope;

import oop.ex6.SJavaInterpreter;
import oop.ex6.SjavaException;
import oop.ex6.variables.FactoryVariable;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableException;
import oop.ex6.method.Method;
import oop.ex6.method.MethodException;

import java.util.regex.Matcher;

/**
 * An initial verifier for S-Java, after classifying the line, provides tools to check its syntactical
 * structure according to the laws of the programming language. The interpreter also constructs method and
 * global variable, preparing the ground for the second, more logic oriented, verifier.
 */
public class InitialInterpreter implements SJavaInterpreter {

    /** The number represent the general scope*/
    private static final int GENERAL_SCOPE_NUMBER = 0;

    /** Data Members*/
    /** Counting the depth of the scope (where 0 is the general scope) */
    private int currentScope = GENERAL_SCOPE_NUMBER;
    /** flags whether the line being read is inside the general scope*/
    private boolean inGeneralScope = true;
    /** The general scope on which the parser is working */
    private GeneralScope generalScope;
    /** The variables factory of the program */
    private FactoryVariable variablesFactory;

    /**
     * The default constructor od InitialInterpreter
     * @param generalScope - The general scope on which the interpreter is working
     */
    InitialInterpreter(GeneralScope generalScope){
        this.generalScope = generalScope;
        variablesFactory = FactoryVariable.instance();
    }

    /**
     * Handles a line that fits the method declaration pattern. Creates a new method and registering it in
     * the general scope
     * @param parametersDeclaration the declaration of the parameters
     * @param methodName The name of the new method
     * @throws SjavaException If parameters declaration is invalid
     */
    public void newMethodHandler(String parametersDeclaration, String methodName) throws SjavaException {
        currentScope++;
        inGeneralScope = false;
        Method newMethod = new Method(methodName, generalScope); // Create a new method
        if(parametersDeclaration == null){ // Method has no parameters
            generalScope.addMethod(newMethod); // Register the new method in the general scope
            return;
        }
        String[] rawParameters = parametersDeclaration.split(","); // get single declarations
        for(String parameter: rawParameters){
            boolean isFinal = false;
            Matcher singleParameterMatcher = singleParameterPattern.matcher(parameter);
            if(singleParameterMatcher.matches()){
                if(singleParameterMatcher.group(FINAL_SAVED_WORD) != null){ // the word 'final' exists
                    isFinal = true;
                }
                String paramName = singleParameterMatcher.group(VAR_NAME_GROUP);
                String paramType = singleParameterMatcher.group(TYPE_GROUP);
                Variable newVar = variablesFactory.getVariable(paramType,paramName,isFinal, null);
                newMethod.addParameter(newVar);
            } else{
                throw new MethodException(MethodException.INCORRECT_PARAMETERS_MESSAGE);
            }
        }
        generalScope.addMethod(newMethod); // Register the new method in the general scope
    }

    /**
     * Handles a variable assignment - if the assignment is inside the general scope, making sure that the
     * assignment is legal (existing variables and legal values) and updates the global variable's value
     * @param name The name of the assigned variable
     * @param value The value which is assigned (may be a name of an existing variable)
     * @throws VariableException if invalidValue/ non existing variable
     */
    public void variableAssignmentHandler(String name, String value) throws VariableException {
        if(inGeneralScope){
            Variable valueVariable = generalScope.getVarByName(value);
            Variable assignedVariable = generalScope.getVarByName(name);
            if (valueVariable != null) { // The value assigned is an existing global variable
                if (assignedVariable != null) { // Variable doesn't exists
                    assignedVariable.setNewValue(valueVariable.getValue());
                } else {
                    throw new VariableException(VariableException.VAR_DOESNT_EXISTS_MESSAGE);
                }
            } else { // The value assigned is not an existing global variable
                if (assignedVariable != null) { // Variable doesn't exists
                    assignedVariable.setNewValue(value);
                } else {
                    throw new VariableException(VariableException.VAR_DOESNT_EXISTS_MESSAGE);
                }
            }
        }

    }

    /**
     * Handles a line that fits the if statement or while loop patterns and updating the current scope
     * @param line the line of the declaration
     * @throws SjavaException If line is in Illegal Position
     */
    public void ifWhileHandler(String line) throws SjavaException{
        currentScope++; // Inner Scope entered
        inGeneralScope = false;
    }

    /**
     * Handles a line that fits the return statement pattern - does nothing
     * @param line The line containing the return statement
     * @throws SjavaException If return statement is located in the general scope
     */
    public void returnStatementHandler(String line)throws SjavaException {
        return; // Do nothing - Syntax is fine.
    }

    /**
     * Handles a line that fits the closing brackets pattern - updates the scope and makes sure that
     * closing brackets are inside a method's scope
     * @param line the line of the declaration
     * @throws GeneralScopeException If closed brackets not inside a method
     */
    public void closedBracketsHandler(String line) throws GeneralScopeException{
        currentScope--; // Inner scope closed
        if(currentScope == GENERAL_SCOPE_NUMBER){
            inGeneralScope = true;
        } else if( currentScope < GENERAL_SCOPE_NUMBER){
            throw new GeneralScopeException(GeneralScopeException.CLOSING_BRACKETS_MESSAGE);
        }
    }

    /**
     * Handles a line that fits a variable declaration, using a helper function that registers the
     * variable only if the declaration is done inside the general scope
     * @param line the line of the declaration
     * @throws VariableException If wrong type/ invalid value
     */
    public void newVariableDeclarationHandler(String line, String type, boolean isFinal) throws
            VariableException{
        if(inGeneralScope) { //This is a declaration of a global variable(s)
            variableDeclaration(line, type, isFinal);
        }
    }

    /**
     * Registers global variables
     * @param newVar The variable to register
     * @throws VariableException If a variable of that name exists in the same scope
     */
    public void registerVariable(Variable newVar) throws VariableException {
        generalScope.addGlobalVariable(newVar);
    }

    /**
     * Handles a new method call - does nothing
     * @param methodName - The name of the called method
     * @param Parameters - The parameters used for the call
     * @throws SjavaException If method doesn't exists or parameters doesn't match method requirements
     */
    public void methodCallHandler(String methodName, String Parameters) throws SjavaException{
        return; // Do nothing - Syntax is fine.
    }
}
