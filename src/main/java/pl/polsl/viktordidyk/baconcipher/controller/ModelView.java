/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.InvalidUserInputException;
import pl.polsl.viktordidyk.baconcipher.model.StrategyA;
import pl.polsl.viktordidyk.baconcipher.model.StrategyB;
import pl.polsl.viktordidyk.baconcipher.model.Transcriptor;
import pl.polsl.viktordidyk.baconcipher.view.View;

/**
 * Responsible for managing Model logic and view
 * @author Viktor Didyk
 * @version 1.0
 */
public class ModelView {
    protected final View view;
    private final StrategyA strategyA;
    private final StrategyB strategyB;
    private final Transcriptor transcriptor;
    
    public ModelView() throws FileNotFoundException {
        view = new View();
        strategyA = new StrategyA();
        strategyB = new StrategyB();
        transcriptor = new Transcriptor();      
    }
    
    
    
    /**
     * Run the main flow of the program, e.g. encoding, decoding
     * @param userCommand user input as a map.
     * @throws InvalidUserInputException 
     */
    public void run(Map<String, String> userCommand) throws InvalidUserInputException {
        // -----------------
        // user should choose strategy !
        // --------------
        this.transcriptor.setTranscriptionStrategy(strategyB);
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
