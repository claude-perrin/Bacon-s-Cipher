/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import pl.polsl.viktordidyk.baconcipher.model.exceptions.EncryptionFailed;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.MessageCannotBeNonLatin;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.MessageCannotBeEmpty;

/**
 * Validates user input for encryption
 * 
 * @author Viktor Didyk
 * @version 1.0
 */
public class MessageValidator {
    /**
     * Checks that the String is not empty
     * @param message
     * @return 
     */
    private boolean nonEmptyString(String message) {
        return message.strip().length() != 0;
    }
    
    /**
     * Checks that the string contains latin alphabet only
     * @param message
     * @return 
     */
    private boolean latinAlphabetOnly(String message){
        return message.matches("[a-zA-Z\\s]+?");
    }
    
    /**
     * 
     * @param message
     * @throws EncryptionFailed 
     */
    public void validateMessage(String message) throws EncryptionFailed {
        if (this.nonEmptyString(message) == false) {
            throw new MessageCannotBeEmpty("Provided input is empty");
        }
        
        if (latinAlphabetOnly(message) == false) {
            throw new MessageCannotBeNonLatin("Please write the message in plain english");
        }
    }
}
