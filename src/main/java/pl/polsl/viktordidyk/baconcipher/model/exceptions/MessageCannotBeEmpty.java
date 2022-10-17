/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model.exceptions;

/**
 *
 * @author viktor
 */
public class MessageCannotBeEmpty extends EncryptionFailed{
    public MessageCannotBeEmpty(String errorMessage) {
        super(errorMessage);
    }
}
