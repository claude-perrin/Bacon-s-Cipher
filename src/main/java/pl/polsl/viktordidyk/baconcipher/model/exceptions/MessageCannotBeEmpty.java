/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model.exceptions;

/**
 * Child of EncryptionFailed class 
 * @author Viktor Didyk
 * @version 1.0
 */
public class MessageCannotBeEmpty extends EncryptionFailed {
    public MessageCannotBeEmpty(String errorMessage) {
        super(errorMessage);
    }
}
