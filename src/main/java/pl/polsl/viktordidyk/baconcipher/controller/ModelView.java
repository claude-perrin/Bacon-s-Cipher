/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;

import java.io.IOException;
import java.util.Map;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.InvalidUserInputException;
import pl.polsl.viktordidyk.baconcipher.model.StrategyA;
import pl.polsl.viktordidyk.baconcipher.model.StrategyB;
import pl.polsl.viktordidyk.baconcipher.model.Transcriptor;
import pl.polsl.viktordidyk.baconcipher.view.View;

/**
 *
 * @author viktor
 */
public class ModelView {
    protected View view;
    private StrategyA strategyA;
    private StrategyB strategyB;
    protected boolean terminateFlag = false;
    
    public void run(Map<String, String> userCommand) throws InvalidUserInputException {
        // -----------------
        // user should choose strategy !
        // switch statements depending on mode !!
        // user should be able to terminate the program with q() !!!!!!!
        // --------------
        view = new View();
        strategyA = new StrategyA();
        strategyB = new StrategyB();
        Transcriptor transcriptor = new Transcriptor();
        transcriptor.setTranscriptionStrategy(strategyB);

        switch (userCommand.get("mode")){
            case "help" -> view.printHelp();
            case "encrypt" -> {
                String encryptedMessage = transcriptor.encrypt(userCommand.get("fileName"));
                view.print(encryptedMessage);
            }
            case "decrypt" -> {
                String decodedMessage = transcriptor.decrypt(userCommand.get("messageToDecrypt"));
                view.print(decodedMessage);
            }
        }
        
         
    };
}
