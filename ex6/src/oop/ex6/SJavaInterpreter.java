package oop.ex6;

import oop.ex6.variables.FactoryVariable;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableException;
import oop.ex6.generalscope.GeneralScopeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An interface implemented by all Interpreters of S-Java in the program. Containing important patterns,
 * classifying and handling methods (some of which are already implemented)
 */
public interface SJavaInterpreter {
    /** Saved word in S-java*/
    String RETURN_SAVED_WORD = "return";
    String VOID_SAVED_WORD = "void";
    String FINAL_SAVED_WORD = "final";
    String WHILE_SAVED_WORD = "while";
    String IF_SAVED_WORD = "if";
    String CLOSED_BRACKETS_SAVED_WORD = "}";


    /** Basic syntax patterns */
    /**  Pattern that matches the first non-space excluding the semicolon sign*/
    String VALID_VARIABLE_NAME = "([a-zA-Z]\\w*|_\\w+)";
    String VALID_METHOD_NAME = "[a-zA-Z]\\w*";
    String VALID_VARIABLE_TYPE_NAME = "[a-zA-Z]\\w*";
    String BRACKETS_CONTAINING_SOMETHING = "\\(.+\\)";
    String BRACKETS_END_OF_LINE = "\\{\\s*$";
    String SEMICOLON_END_OF_LINE = ";\\s*$";
    String ASSIGN_SOME_VALUE = "=\\s*(\".*\"|'.'|-?[\\w.]+)";
    String LEGAL_VALUE = "(\".*\"|'.'|-?[\\w.]+)";
    String PARAMETER = "\\s*(?<final>"+FINAL_SAVED_WORD+")?"+
            "\\s*(?<type>"+VALID_VARIABLE_TYPE_NAME+")\\s+(?<name>"+VALID_VARIABLE_NAME+")\\s*";
    String NO_GROUP_PARAMETER = "\\s*(?:"+FINAL_SAVED_WORD+")?"+
            "\\s*(?:"+VALID_VARIABLE_TYPE_NAME+")\\s+(?:"+VALID_VARIABLE_NAME+")\\s*";

    /** Line patterns */
    Pattern ifStatementPattern = Pattern.compile("^\\s*"+IF_SAVED_WORD+"\\s*(?<condition>" +
            BRACKETS_CONTAINING_SOMETHING +")\\s*"+BRACKETS_END_OF_LINE);
    Pattern whileLoopPattern = Pattern.compile("^\\s*"+WHILE_SAVED_WORD+"\\s*(?<condition>" +
            BRACKETS_CONTAINING_SOMETHING +")\\s*"+BRACKETS_END_OF_LINE);
    Pattern methodDeclarationPattern = Pattern.compile
            ("^\\s*"+VOID_SAVED_WORD+"\\s+(?<methodName>"+VALID_METHOD_NAME+")\\s*\\((?<param>" +
                    NO_GROUP_PARAMETER+ "(,"+NO_GROUP_PARAMETER +")*)?\\)\\s*"+BRACKETS_END_OF_LINE);
    Pattern closingBracketsPattern = Pattern.compile("\\s*"+CLOSED_BRACKETS_SAVED_WORD+
            "\\s*$");
    Pattern commentPattern = Pattern.compile("^/{2}.*$");
    Pattern emptyLinePattern = Pattern.compile("^\\s*$");
    Pattern finalDeclarationPattern = Pattern.compile
            ("^\\s*"+FINAL_SAVED_WORD+"\\s+(?<type>" +VALID_VARIABLE_TYPE_NAME+")\\s+"+
                    "(?:\\s*"+VALID_VARIABLE_NAME +"\\s*"+ASSIGN_SOME_VALUE+"\\s*,\\s*)*\\s*"+
                    VALID_VARIABLE_NAME +"\\s*"+ASSIGN_SOME_VALUE+"\\s*"+SEMICOLON_END_OF_LINE);
    Pattern returnPattern = Pattern.compile
            ("^\\s*"+RETURN_SAVED_WORD+"\\s*"+SEMICOLON_END_OF_LINE);
    Pattern variableDeclarationPattern = Pattern.compile
            ("^\\s*(?<type>" + VALID_VARIABLE_TYPE_NAME + ")\\s+"+
                    "(?:\\s*"+VALID_VARIABLE_NAME +"\\s*(?:\\s*"+ASSIGN_SOME_VALUE+"\\s*)?\\s*,\\s*)*\\s*"+
                    VALID_VARIABLE_NAME +"\\s*(?:\\s*"+ASSIGN_SOME_VALUE+"\\s*)?\\s*"+SEMICOLON_END_OF_LINE);
    Pattern singleParameterPattern = Pattern.compile(PARAMETER);
    Pattern variableAssignmentPattern = Pattern.compile("\\s*(?<name>"+VALID_VARIABLE_NAME+")\\s*" +
            "=\\s*(?<value>\".*\"|'.?'|-?[\\w.]+)\\s*"+SEMICOLON_END_OF_LINE);
    Pattern methodCallPattern = Pattern.compile("\\s*(?<methodName>" + VALID_METHOD_NAME +
            ")\\s*\\((?<param>\\s*"+LEGAL_VALUE+"\\s*(\\s*,\\s*"+LEGAL_VALUE+"\\s*)*)*\\s*\\)" +
            "\\s*"+SEMICOLON_END_OF_LINE);

    /** Names of the different groups in the line pattern*/
    String TYPE_GROUP = "type";
    String PARAMETER_GROUP = "param";
    String VAR_NAME_GROUP = "name";
    String METHOD_NAME_GROUP = "methodName";
    String VALUE_GROUP = "value";
    String CONDITION_GROUP = "condition";
    /** The difference between two lines/cells */
    int INDEX_DIFFERENCE = 1;

    /** The variables factory */
    FactoryVariable variablesFactory = FactoryVariable.instance();

    /** Methods */

    /**
     * Classifies the line according to the different line patterns and makes sure its syntax is valid.
     * @param line The line to classify
     * @throws SjavaException Thrown in case of invalid method declaration / invalid variable
     * assignment/ invalid variable declaration
     */
    default void classifyLine(String line) throws SjavaException {
        Matcher lineMatcher = ifStatementPattern.matcher(line);
        if (lineMatcher.matches()){ // the line is a valid if statement
            String conditionNoBrackets = lineMatcher.group(CONDITION_GROUP).substring(INDEX_DIFFERENCE,lineMatcher.group
                    (CONDITION_GROUP).length()-INDEX_DIFFERENCE);
            ifWhileHandler(conditionNoBrackets);
            return; // end classification
        }
        lineMatcher.usePattern(whileLoopPattern);
        if(lineMatcher.matches()){ // line is a valid while loop statement
            String conditionNoBrackets = lineMatcher.group(CONDITION_GROUP).substring(INDEX_DIFFERENCE,lineMatcher.group
                    (CONDITION_GROUP).length()-INDEX_DIFFERENCE);
            ifWhileHandler(conditionNoBrackets);
            return; // end classification
        }
        lineMatcher.usePattern(returnPattern);
        if(lineMatcher.matches()){ // line is a valid return statement
            returnStatementHandler(line);
            return; // end classification
        }
        lineMatcher.usePattern(commentPattern);
        if(lineMatcher.matches()){ // line is a valid comment
            return; // Do nothing and end classification
        }
        lineMatcher.usePattern(emptyLinePattern);
        if(lineMatcher.matches()){ // line is a valid empty line
            return; // Do nothing and end classification
        }
        lineMatcher.usePattern(closingBracketsPattern);
        if(lineMatcher.matches()){ // line is a valid closing curled brackets ('}')
            closedBracketsHandler(line);
            return; // end classification
        }
        lineMatcher.usePattern(variableDeclarationPattern);
        if(lineMatcher.matches()){ // line is a declaration of a new variable(s)
            if(!FactoryVariable.isTypeName(lineMatcher.group(TYPE_GROUP))) {
                throw new VariableException(VariableException.INVALID_TYPE_MESSAGE);
            }
            newVariableDeclarationHandler(line, lineMatcher.group(TYPE_GROUP), false);
            return; // end classification
        }
        lineMatcher.usePattern(finalDeclarationPattern);
        if(lineMatcher.matches()){ // line is a declaration of new final variable(s)
            if(!FactoryVariable.isTypeName(lineMatcher.group(TYPE_GROUP))) {
                throw new VariableException(VariableException.INVALID_TYPE_MESSAGE);
            }
            newVariableDeclarationHandler(line, lineMatcher.group(TYPE_GROUP), true);
            return; // end classification
        }
        lineMatcher.usePattern(methodDeclarationPattern);
        if(lineMatcher.matches()){ // line is a declaration of a new method
            newMethodHandler(lineMatcher.group(PARAMETER_GROUP), lineMatcher.group(METHOD_NAME_GROUP));
            return; //End classification
        }
        lineMatcher.usePattern(variableAssignmentPattern);
        if(lineMatcher.matches()){ // line is a value assignment for a variable
            variableAssignmentHandler(lineMatcher.group(VAR_NAME_GROUP), lineMatcher.group(VALUE_GROUP));
            return; //End classification
        }
        lineMatcher.usePattern(methodCallPattern);
        if(lineMatcher.matches()){ // line is a value assignment for a variable
            methodCallHandler(lineMatcher.group(METHOD_NAME_GROUP), lineMatcher.group(PARAMETER_GROUP));
            return; //End classification
        }
        else{ // line doesn't fit a line pattern
            throw new SjavaException(SjavaException.ILLEGAL_LINE_MESSAGE);
        }
    }

    /**
     * Handles a line that fits Variable Declaration
     * @param line the line of the declaration
     * @throws VariableException If wrong type/ invalid value
     */
    void newVariableDeclarationHandler(String line, String type, boolean isFinal) throws VariableException;

    /**
     * Handles a line that fits the method declaration pattern
     * @param parametersDeclaration the declaration of the parameters
     * @param methodName The name of the new method
     * @throws SjavaException If parameters declaration is invalid or if a logic error occurred in the
     * method's code
     */
    void newMethodHandler(String parametersDeclaration, String methodName) throws SjavaException;

    /**
     * Handles a line that fits the if statement or while loop patterns
     * @param line the line of the declaration
     * @throws SjavaException If line is in Illegal Position
     */
    void ifWhileHandler(String line) throws SjavaException;

    /**
     * Handles a line that fits the return statement pattern
     * @param line The line containing the return statement
     * @throws SjavaException If return statement is located in the general scope
     */
    void returnStatementHandler(String line) throws SjavaException;

    /**
     * Handles a line that fits the closing brackets pattern
     * @param line the line of the declaration
     * @throws GeneralScopeException If closed brackets not inside a method
     */
    void closedBracketsHandler(String line) throws GeneralScopeException;

    /**
     * Handles a new method call
     * @param methodName - The name of the called method
     * @param Parameters - The parameters used for the call
     * @throws SjavaException If method doesn't exists or parameters doesn't match method requirements
     */
    void methodCallHandler(String methodName, String Parameters) throws SjavaException;

    /**
     * Handles a variable assignment
     * @param name The name of the assigned variable
     * @param value The value which is assigned (may be a name of an existing variable)
     * @throws VariableException if invalidValue/ non existing variable
     */
    void variableAssignmentHandler(String name, String value) throws VariableException;

    /**
     * Creates new variable(s) from a syntax-valid variable declaration (whether their final or not) and
     * using a helper method, registers it in the appropriate scope
     * @param declaration - The raw declaration
     * @param varType - The type of the variable(s) declared
     * @param isFinal - flags whether the variables declared are final
     * @throws VariableException - Thrown if type doesn't exists/value doesn't match the type
     */
    default void variableDeclaration(String declaration, String varType, boolean isFinal)
            throws VariableException {
        Pattern declarationPattern = Pattern.compile("^\\s*(?:"+FINAL_SAVED_WORD+")?\\s+"+varType+"\\s*");
        Matcher finalTypeMatcher = declarationPattern.matcher(declaration);
        if(finalTypeMatcher.find()){
            declaration = finalTypeMatcher.replaceFirst("");//remove 'final' and type
        }
        Pattern semicolonPattern = Pattern.compile(SEMICOLON_END_OF_LINE);
        Matcher semicolonMatcher = semicolonPattern.matcher(declaration);
        if(semicolonMatcher.find()){
            declaration = semicolonMatcher.replaceFirst("");//remove semicolon
        }
        String[] rawVariables = declaration.split(","); // divide into single variable declaration
        Pattern singleVariablePattern = Pattern.compile("^\\s*"+varType+"\\s*(?<name>"+VALID_VARIABLE_NAME+")" +
                "\\s*(?:=\\s*(?<value>\".*\"|'.'|-?[\\w.]+))*");

        for(String singleVar: rawVariables){
            Matcher singleVarMatcher = singleVariablePattern.matcher(singleVar);
            if (singleVarMatcher.find()){
                String varName = singleVarMatcher.group(VAR_NAME_GROUP);
                String varValue = singleVarMatcher.group(VALUE_GROUP);
                //Create Empty variable
                Variable newVar = variablesFactory.getVariable(varType, varName, false, null);
                registerVariable(newVar); //register Variable
                if (varValue != null){
                    variableAssignmentHandler(newVar.getName(), varValue); // Assign the value to the variable
                }
                newVar.setFinal(isFinal); // Set the final attribute
            }
        }
    }

    /**
     * Registers a new variable
     * @param newVar The variable to register
     * @throws VariableException If a variable of that name exists in the same scope
     */
    void registerVariable(Variable newVar) throws VariableException;
}
