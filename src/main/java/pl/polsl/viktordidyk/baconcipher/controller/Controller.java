/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;
import java.io.IOException;
import java.util.Map;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.InvalidUserInputException;


/**
 *
 * @author SuperStudent.PL
 */
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
            }
            args = modelView.view.getUserCommand();
        }
    }
};
