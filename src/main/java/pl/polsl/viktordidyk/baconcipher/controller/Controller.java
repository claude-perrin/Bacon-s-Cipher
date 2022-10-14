/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;
import pl.polsl.viktordidyk.baconcipher.model.MessageCannotBeNonLatin;
import pl.polsl.viktordidyk.baconcipher.model.StrategyA;
import pl.polsl.viktordidyk.baconcipher.model.StrategyB;
import pl.polsl.viktordidyk.baconcipher.model.StrategyC;
import pl.polsl.viktordidyk.baconcipher.view.View;
import pl.polsl.viktordidyk.baconcipher.model.Transcription;




/**
 *
 * @author SuperStudent.PL
 */
public class Controller {
    public static void main(String[] args) {
        View view = new View();
        Transcription transcription = new Transcription();
        String messageToEncode = "qwert 123";
        StrategyA strategyA = new StrategyA();
        StrategyB strategyB = new StrategyB();
        StrategyC strategyC = new StrategyC();
        
        transcription.setTranscriptionStrategy(strategyB);
        try{
            String encodedMessage = transcription.encode(messageToEncode);
            view.print(encodedMessage);
        }
        catch (MessageCannotBeNonLatin exc) {
            view.showErrorMessage(exc.getMessage());
        }
    }
   
}
