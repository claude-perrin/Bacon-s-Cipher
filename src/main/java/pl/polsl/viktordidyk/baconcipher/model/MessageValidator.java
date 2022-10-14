/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

/**
 *
 * @author admin
 */
public class MessageValidator {
    private boolean nonEmptyString(String message) {
        return message.strip().length() != 0;
    }
    
    private boolean latinAlphabetOnly(String message){
        return message.matches("[a-zA-Z\\s]+?");
    }
    
    public void validateMessage(String message) throws InvalidUserInputException {
        if (this.nonEmptyString(message) == false) {
            throw new MessageCannotBeEmpty("Provided input is empty");
        }
        
        if (latinAlphabetOnly(message) == false) {
            throw new MessageCannotBeNonLatin("Please write the message in plain english");
        }
    }
}
