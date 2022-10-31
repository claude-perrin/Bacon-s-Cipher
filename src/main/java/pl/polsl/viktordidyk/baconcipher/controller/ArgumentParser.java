/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;


import pl.polsl.viktordidyk.baconcipher.model.StrategyA;
import pl.polsl.viktordidyk.baconcipher.model.Transcriptor;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.EncryptionFailed;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.InvalidUserInputException;


/**
 *
 * @author viktor
 */
public class ArgumentParser {
    /**
     * Interface for a lambda that allows to define encrypt or decrypt and execute it
     */
    interface TranscriptionMode { 
        String execute(Transcriptor transcriptor, char strategy, String filePath) throws EncryptionFailed;
    }
    
    /**
     * If user provided "q" exit the program
     * @param args
     * @return boolean whether the loop should be broken
     */
    public boolean checkTerminationCommand(String[] args) {
        String argumentLine = String.join(" ", args);
        return "q".equals(argumentLine);
    }
    
    
/**
 * With a help of regex the matching user input is taken and parsed.
 * @param args command line parameters that are being parsed
 * @return Map that has a key "mode" and a value as a command to be executed
 * @throws pl.polsl.viktordidyk.baconcipher.model.exceptions.InvalidUserInputException
 */
    public TranscriptionMode parseCmdArguments(String[] args) throws InvalidUserInputException {
        String argumentLine = String.join(" ", args);
        if (argumentLine.matches("^(-s)\\s[ABab]\\s(-d)\\s[a-zA-Z]+$")) {
            TranscriptionMode decrypt = (transcriptor, strategy, message) -> {
                transcriptor.setStrategy(strategy);
                return transcriptor.decrypt(message);
            };
            return decrypt;
        }
        else if (argumentLine.matches("^(-s)\\s[ABab]\\s((-e -f)|(-ef)|(-fe)){1}\\s[a-zA-Z]+.txt$")) {
            TranscriptionMode encrypt = (transcriptor, strategy, filePath) -> {
                transcriptor.setStrategy(strategy);
                return transcriptor.encrypt(filePath);
            };
            return encrypt;
        } 
        throw new InvalidUserInputException();
    };
}
