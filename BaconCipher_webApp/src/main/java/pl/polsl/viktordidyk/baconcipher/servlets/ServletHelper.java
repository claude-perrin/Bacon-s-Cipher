/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.servlets;

import pl.polsl.viktordidyk.baconcipher.model.BaconCipherStrategy;
import pl.polsl.viktordidyk.baconcipher.model.StrategyA;
import pl.polsl.viktordidyk.baconcipher.model.StrategyB;

/**
 *
 * @author viktor
 */
public class ServletHelper {
    public static BaconCipherStrategy getTranscriptionStrategy(char strategy){
        if (strategy == 'A') {
            return new StrategyA();
        }
        else if (strategy == 'B') {
            return new StrategyB();
        }
        return null;
    }
}
