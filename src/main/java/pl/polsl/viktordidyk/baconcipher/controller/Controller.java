/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;

import java.io.FileNotFoundException;
import pl.polsl.viktordidyk.baconcipher.controller.ArgumentParser.TranscriptionMode;
import pl.polsl.viktordidyk.baconcipher.model.StrategyA;
import pl.polsl.viktordidyk.baconcipher.model.Transcriptor;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.InvalidUserInputException;
import pl.polsl.viktordidyk.baconcipher.view.View;



/** 
 * Main class which is responsible for parsing user input, and termination of the program
 * @author Viktor Didyk
 * @version 1.0
 */

public class Controller {    
    public static void main(String[] args) throws FileNotFoundException {
        View view = new View();
        ArgumentParser argumentParser = new ArgumentParser();
        try {
            Transcriptor transcriptor = new Transcriptor();
            while (true) {
                if (argumentParser.checkTerminationCommand(args))
                    break;
                try {
                    TranscriptionMode userCommand = argumentParser.parseCmdArguments(args);
                    String filePath = args[args.length -1];
                    char strategy = args[1].charAt(0);
                    String message = userCommand.execute(transcriptor, strategy, filePath);
                    view.print(message);
                }
                catch (InvalidUserInputException exc) {
                    view.showErrorMessage(exc.getMessage());
                    view.printHelp();
                }
                args = view.getUserCommand();
                }
            }
        
        catch (FileNotFoundException exc) {
            view.showErrorMessage("File with rules is not found, please check that it is included in the directory");
        }
    }
};
