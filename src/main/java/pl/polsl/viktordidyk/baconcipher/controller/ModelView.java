/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;

import pl.polsl.viktordidyk.baconcipher.model.exceptions.FileWithRulesIsNotFound;
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
    private View view;
    private Transcriptor transcriptor;
    
    private void createTranscriptor(String transcriptionRulesFileName) throws FileWithRulesIsNotFound {
        transcriptor = new Transcriptor(transcriptionRulesFileName);
    }
    
    
    private String startEncrypting(String messageToEncrypt) throws InvalidUserInputException {
        return transcriptor.encrypt(messageToEncrypt);
    }
    
    public void run() throws InvalidUserInputException{
        // -----------------
        // separate by functions all logic, possibly tries should be encapsulated
        // --------------
        view = new View();
        String messageToEncode = "hallo iam domi";
        StrategyA strategyA = new StrategyA();
        StrategyB strategyB = new StrategyB();
        //String csvFileName = "transcriptionRules.csv";
        //csvFileName = view.getTranscriptionRulesFileName();
        // ------------
        // all arguments should be parse, possibly file should be verified
        // ------------
        transcriptor = new Transcriptor(transcriptionRulesFileName);

        transcriptor.setTranscriptionStrategy(strategyB);
        view.print(encodedMessage);
        String decodedMessage = transcriptor.decrypt(encodedMessage);
        view.print(decodedMessage);    
    };
}
