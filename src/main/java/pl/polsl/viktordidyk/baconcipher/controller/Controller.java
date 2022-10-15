/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;
import java.io.FileNotFoundException;
import pl.polsl.viktordidyk.baconcipher.model.InvalidUserInputException;
import pl.polsl.viktordidyk.baconcipher.model.StrategyA;
import pl.polsl.viktordidyk.baconcipher.model.StrategyB;
import pl.polsl.viktordidyk.baconcipher.view.View;
import pl.polsl.viktordidyk.baconcipher.model.Transcriptor;




/**
 *
 * @author SuperStudent.PL
 */
public class Controller {
    public static void main(String[] args) {
        View view = new View();
        String messageToEncode = "lol nelol";
        StrategyA strategyA = new StrategyA();
//        StrategyB strategyB = new StrategyB();
        Transcriptor transcriptor;
        String csvFileName = "transcriptionRules.csv";
//        csvFileName = view.getTranscriptionRulesFileName();
        try {
            transcriptor = new Transcriptor(csvFileName);
            transcriptor.setTranscriptionStrategy(strategyA);
            try {
                String encodedMessage = transcriptor.encode(messageToEncode);
                view.print(encodedMessage);
                String decodedMessage = transcriptor.decode(encodedMessage);
                view.print(decodedMessage);

            }
            catch (InvalidUserInputException exc) {
                view.showErrorMessage(exc.getMessage());
            }  
        }
        catch (FileNotFoundException exc) {
            view.showErrorMessage("Please check that your rule file exists ");
        } 
    };
   
};
