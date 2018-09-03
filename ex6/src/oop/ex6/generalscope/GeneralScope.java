package oop.ex6.generalscope;

import oop.ex6.SjavaException;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableException;
import oop.ex6.method.Method;

import java.io.*;
import java.util.ArrayList;

/**
 * A class representing the general (most outer) scope of the given code
 */
public class GeneralScope {

    /** First line of the file */
    private static final int FIRST_LINE = 0;
    /** The general scope's defined global variables */
    ArrayList<Variable> globalVariables;
    /** The general scope defined methods */
    ArrayList<Method> methods;
    /** The current line in the code */
    int currentLine;
    /** The buffered reader */
    BufferedReader secondVerificationReader;
    /** The file in which the code is found */
    File code;
    /** The current method who's code is read */
    Method currentMethod = null;

    /**
     * Constructs a new general scope instance
     *
     * @param code The code of the Java-S program of which this the general scope
     */
    public GeneralScope(File code) {
        this.code = code;
        this.globalVariables = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.currentLine = FIRST_LINE;
    }

    /**
     * Register a given variable as a global variable of this scope
     *
     * @param newVar - The var to add
     * @throws VariableException If there already exists a global variable with the same name
     */
    public void addGlobalVariable(Variable newVar) throws VariableException {
        if (varNameExists(newVar.getName())) {
            throw new VariableException(VariableException.SAME_NAME_SAME_SCOPE_MESSAGE);
        }
        globalVariables.add(newVar);
    }

    /**
     * @param varName A variable Name
     * @return true if the given name identical to one of the variables in the general scope
     */
    public boolean varNameExists(String varName) {
        for (Variable var : globalVariables) {
            if (varName.equals(var.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param varName The variable's name to receive
     * @return The variable with the given name, null if does'nt exists
     */
    public Variable getVarByName(String varName) {
        for (Variable var : globalVariables) {
            if (varName.equals(var.getName())) {
                return var;
            }
        }
        return null;
    }

    /**
     * @param methodName The method's name to receive
     * @return The method with the given name, null if doesn't exists
     */
    public Method getMethodByName(String methodName) {
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return method;
            }
        }
        return null;
    }

    /**
     * Register a given method as a method of this scope
     *
     * @param newMethod - The method to add
     * @throws GeneralScopeException If a method with the same name is already defined
     */
    public void addMethod(Method newMethod) throws GeneralScopeException {
        Method sameNameMethod = getMethodByName(newMethod.getName());
        if (sameNameMethod != null) {
            throw new GeneralScopeException(GeneralScopeException.SAME_NAME_METHODS_MESSAGE);
        } else {
            methods.add(newMethod);
        }
    }

    /**
     * @return The current line number
     */
    public int getCurrentLineNumber() {
        return currentLine;
    }

    /**
     * Performs an initial verification on the code - checks for basic syntax errors and constructs global
     * variables and
     *
     * @throws SjavaException
     * @throws IOException
     */
    private void initialVerification() throws SjavaException, IOException {
        try (Reader reader = new FileReader(code);
             BufferedReader firstVerificationReader = new BufferedReader(reader)) {
            String line = firstVerificationReader.readLine();
            InitialInterpreter initialInterpreter = new InitialInterpreter(this);
            while (line != null) {
                currentLine++;
                try {
                    initialInterpreter.classifyLine(line);
                } catch (SjavaException e) {
                    throw new SjavaException("Error in line " + currentLine + ": " + e.getMessage());
                }
                line = firstVerificationReader.readLine();
            }
        }
    }

    /**
     * Verifies the code given in construction according to S-java's syntax and logic structure
     *
     * @throws IOException    If there was an I/O problem
     * @throws SjavaException If there was a syntax/logic problem in the code
     */
    public void verify() throws IOException, SjavaException {
        initialVerification();
        try (Reader reader = new FileReader(code);
             BufferedReader bfReader = new BufferedReader(reader)) {
            this.secondVerificationReader = bfReader;
            currentLine = FIRST_LINE; // Start counting from the top
            String line = this.secondVerificationReader.readLine();
            GeneralScopeInterpreter logicParser = new GeneralScopeInterpreter(this);
            while (line != null) {
                currentLine++;
                try {
                    if (currentMethod != null) {
                        if (!currentMethod.hasMethodEnded()) {
                            currentMethod.readLine(line);
                        } else {
                            logicParser.classifyLine(line);
                        }
                    } else {
                        logicParser.classifyLine(line);
                    }

                } catch (SjavaException e) {
                    throw new SjavaException("Error in line " + currentLine + ": " + e.getMessage());
                }
                line = this.secondVerificationReader.readLine();
            }
        }
    }

    /**
     * Sets the method the reader is currently going over
     * @param currentMethod
     */
    public void setCurrentMethod(Method currentMethod) {
        this.currentMethod = currentMethod;
    }
}