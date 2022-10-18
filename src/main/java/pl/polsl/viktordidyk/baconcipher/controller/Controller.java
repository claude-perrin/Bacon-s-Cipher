/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;
import java.io.IOException;
import java.util.Map;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.InvalidUserInputException;


/**
 * Main class which is responsible for parsing user input, and termination of the program
 * @author Viktor Didyk
 * @version 1.0
 */
public class Controller {    
    public static void main(String[] args) throws IOException {     
        while (true){
            ModelView modelView = new ModelView();
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
    }
};

