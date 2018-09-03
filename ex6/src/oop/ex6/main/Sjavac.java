package oop.ex6.main;

import oop.ex6.generalscope.GeneralScope;
import oop.ex6.SjavaException;

import java.io.File;
import java.io.IOException;


/**
 * A class containing the function 'main' which the S-java interpreter is open through.
 */
public class Sjavac {

    /** Return Values */
    private static final int VALID_CODE = 0;
    private static final int INVALID_CODE = 1;
    private static final int IO_FAILURE_CODE = 1;
    /** Argument constants*/
    private static final int NUMBER_OF_ARGS = 1;
    private static final int FIRST_POSITION = 0;
    private static final String FILE_ENDING = ".sjava";
    private static final String ARGS_ERROR_MESSAGE = "The program should get only one argument - a file" +
            "path to an sjava file (must end with '.sjava'";
    /**
     * Verifies the S-java code given. The method prints to the screen:
     * 0 - if code is legal, 1 - if the code is not legal, 2 - in case of an input/output failure
     * @param args The arguments to the program, suppose to contain a path to a .sjava file
     */
    public static void main(String[] args) {
        // Verify input:
        if(args.length != NUMBER_OF_ARGS){
            System.err.println(ARGS_ERROR_MESSAGE);
            return;
        }
        if(!args[FIRST_POSITION].endsWith(FILE_ENDING)){
            System.err.println(ARGS_ERROR_MESSAGE);
            return;
        }
        try {
            File sourceFile = new File(args[0]);
            GeneralScope code = new GeneralScope(sourceFile);
            try{
                code.verify();
                System.out.println(VALID_CODE);
            } catch (SjavaException e) {
                System.err.println(e.getMessage());
                System.out.println(INVALID_CODE);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println(IO_FAILURE_CODE);
        }

    }
}
