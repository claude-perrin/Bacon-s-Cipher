/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

/**
 * Letters from "A" until "M" are encoded as a
 * Letters from "N" until "Z" are encoded as b
 * @author admin
 */
public class StrategyB extends BaconCipherStrategy {
    
    protected char useStrategyTranscriptionAlgorithm(char character) {
        if ('A' <= character && character <= 'M') {
            return 'a';
        }
        return 'b';
    };
    
    protected String getCharacterBinarySequence(char key) {
        return "A";
    };

    
    public String encrypt(String message) {
        return "Encode StrategyB";
    };

    public String decrypt(String message) {
        return "Decode StrategyB";
    };
}