/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model.exceptions;

/**
 *
 * @author admin
 */
public class MessageCannotBeNonLatin extends EncryptionFailed {
    public MessageCannotBeNonLatin(String errorMessage) {
        super(errorMessage);
    }
}
