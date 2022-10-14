/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

/**
 * Letters from "A" until "M" are encoded as A
 * Letters from "N" until "Z" are encoded as B
 * @author admin
 */
public class StrategyB extends BaconCipherStrategy {
    
    protected char useStrategyEncryptionRule(char character){
        if ('A' <= character && character <= 'M') {
            return 'A';
        }
        return 'B';
    };
    
    public String encode(String message) {
        return "Encode StrategyB";
    };

    public String decode(String message) {
        return "Decode StrategyB";
    };
}