/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;
import pl.polsl.viktordidyk.baconcipher.controller.ModelView;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.InvalidUserInputException;



/**
 *
 * @author SuperStudent.PL
 */
public class Controller {
    public static void main(String[] args) {
        boolean happyFlag = true;
        ModelView modelView = new ModelView();
        // do some manipulation with arguments, parse them
        // if user is retarded repeat
        while (happyFlag){
            try{
                modelView.run();
                happyFlag = false;
            }
            catch (InvalidUserInputException exc) {
                exc.getMessage();
            }
        }
    }
};
