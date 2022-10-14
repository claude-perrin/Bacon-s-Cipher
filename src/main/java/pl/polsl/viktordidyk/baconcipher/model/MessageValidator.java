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
    // multi words 
    private boolean latinAlphabetOnly(String message){
        return message.matches("\\w+");
    }
    
    public void validateMessage(String message) throws MessageCannotBeNonLatin {
        if (latinAlphabetOnly(message) == false) {
            throw new MessageCannotBeNonLatin("Please write the message in plain english");
        }
    }
}
