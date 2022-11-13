/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import pl.polsl.viktordidyk.baconcipher.model.StrategyA;
import pl.polsl.viktordidyk.baconcipher.model.StrategyB;

import pl.polsl.viktordidyk.baconcipher.model.Transcriptor;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.EncryptionFailed;
import pl.polsl.viktordidyk.baconcipher.view.*;
import pl.polsl.viktordidyk.baconcipher.model.helper.FileManager;



/** 
 * Main class which is responsible for parsing user input, and termination of the program
 * @author Viktor Didyk
 * @version 1.0
 */

public class Controller {    
    FrameWindow view;
    Transcriptor transcriptor;
    
    public Controller() {
    }
    
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.view = new FrameWindow(controller);
        try {
            controller.transcriptor = new Transcriptor();
        } catch (FileNotFoundException ex) {
            controller.view.showPopUpMessage("File with rules is not found");
        }
    };
    
    public void setChosenStrategy(String strategy) {
        switch (strategy) {
            case "A" -> this.transcriptor.setStrategy(new StrategyA());
            case "B" -> this.transcriptor.setStrategy(new StrategyB());   
        }
    }
    
    public void startEncryption(String filePath) {
        setChosenStrategy(this.view.getChosenStrategy());
        FileManager fileManager = new FileManager();
        try {        
            String info = fileManager.readTxt(filePath);
            String encryptedMessage = this.transcriptor.encrypt(info);
            this.view.setEncryptionMessageTextField(encryptedMessage);
        } 
        
        catch (EncryptionFailed ex) {
            this.view.showPopUpMessage("Something went wrong");
        } 
        catch (NullPointerException ex) {
            this.view.showPopUpMessage("You didn't choose the strategy");
        }
        catch (IOException ex) {
            this.view.showPopUpMessage("File doesn't exist");
        } 
        this.view.appendHistoryAction(String.format("Encryption (%s) from file %s", this.transcriptor.getTranscriptionStrategy(),filePath));
    }
    public void startDecryption(String messageToDecrypt) {
        setChosenStrategy(this.view.getChosenStrategy());
        String decryptedMessage = this.transcriptor.decrypt(messageToDecrypt);
        this.view.showPopUpMessage(decryptedMessage);
        this.view.appendHistoryAction(String.format("Decryption (%s) of %s", this.transcriptor.getTranscriptionStrategy() ,messageToDecrypt));
    }
    
}
