/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;
import java.io.FileNotFoundException;
import java.util.Map;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.InvalidUserInputException;
import pl.polsl.viktordidyk.baconcipher.view.View;


/** 
 * Main class which is responsible for parsing user input, and termination of the program
 * @author Viktor Didyk
 * @version 1.0
 */

// Do lambda function inside argument parser and also a function in argument parser to get input file/message
// so parseCmdArguments will return a command, getMessage will return end of String[] args
// and here it will fill it, thus modelview can be ommited 
public class Controller {    
    public static void main(String[] args) {
        View view = new View();
        while (true) {
            try {
                ModelView modelView = new ModelView(view);
                Map<String, String> userCommand = new ArgumentParser().parseCmdArguments(args);
                if ("terminate".equals(userCommand.get("mode"))) 
                    break;
                try {
                    modelView.run(userCommand);
                }
                catch (InvalidUserInputException exc) {
                    modelView.view.showErrorMessage(exc.getMessage());
                }
                args = modelView.view.getUserCommand();
            }
            catch (FileNotFoundException exc) {
                view.showErrorMessage("File with rules was deleted, please check it");
                break;
            }
        }
    }
};

