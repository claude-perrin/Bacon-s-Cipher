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
 *
 * @author SuperStudent.PL
 */

public class Controller {    
    public static void main(String[] args) throws FileNotFoundException {
        View view = new View();
        try {
            Transcriptor transcriptor = new Transcriptor();
            while (true) {
                try {
                    TranscriptionMode userCommand = new ArgumentParser().parseCmdArguments(args);
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
