/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;
<<<<<<< Updated upstream
import java.io.IOException;
import java.util.Map;
=======
import java.io.FileNotFoundException;
import pl.polsl.viktordidyk.baconcipher.controller.ArgumentParser.TranscriptionMode;
import pl.polsl.viktordidyk.baconcipher.model.StrategyA;
import pl.polsl.viktordidyk.baconcipher.model.Transcriptor;
>>>>>>> Stashed changes
import pl.polsl.viktordidyk.baconcipher.model.exceptions.InvalidUserInputException;


/**
 *
 * @author SuperStudent.PL
 */
<<<<<<< Updated upstream
public class Controller {    
    public static void main(String[] args) throws IOException {        
        ModelView modelView = new ModelView();
        while (modelView.terminateFlag == false){
            Map<String, String> userCommand = new ArgumentParser().parseCmdArguments(args);
            try {
                modelView.run(userCommand);
            }
            catch (InvalidUserInputException exc) {
                modelView.view.showErrorMessage(exc.getMessage());
=======

public class Controller {    
    public static void main(String[] args) {
        View view = new View();
        try {
            Transcriptor transcriptor = new Transcriptor();
            StrategyA strategyA = new StrategyA();
            transcriptor.setTranscriptionStrategy(strategyA);
            while (true) {
                if (args.length != 0) {
                    String filePath = args[args.length -1];
                    try {
                        TranscriptionMode userCommand = new ArgumentParser().parseCmdArguments(args);
                        String message = userCommand.execute(transcriptor, filePath);
                        view.print(message);
                    }
                    catch (InvalidUserInputException exc) {
                        view.showErrorMessage(exc.getMessage());
                        view.printHelp();
                    }
                }
                args = view.getUserCommand();
>>>>>>> Stashed changes
            }
            args = modelView.view.getUserCommand();
        }
        catch (FileNotFoundException exc) {
            view.showErrorMessage("File with rules is not found, please check that it is included in the directory");
        }
    }
};
