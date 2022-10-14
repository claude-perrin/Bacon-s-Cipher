/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

/**
 *
 * @author viktor
 */
public class InvalidUserInputException extends Exception {
    public InvalidUserInputException(String errorMessage) {
        super(errorMessage);
    }
}
