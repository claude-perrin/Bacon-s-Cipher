/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import java.util.Random;

/**
 * Letters from "A" until "M" are encoded as a
 * Letters from "N" until "Z" are encoded as b
 * 
 * Example:
 * Encrypted Message |    decryption   |    Secret Message
 * GkwRt ceUya porrE <-> aabbb aabba bbbba <-> D O G
 * @author admin
 */
public class StrategyB extends BaconCipherStrategy {
    protected String generateEncryptedMessage(String binarySequence){
        String encryptedMessage = "";
        for(int i = 0; i < binarySequence.length(); i++) {
            if (binarySequence.charAt(i) == 'a') {
                char generatedChar = generateRandomCharacter('a');
                encryptedMessage += generatedChar;
            }
            else {
                char generatedChar = generateRandomCharacter('n');
                encryptedMessage += generatedChar;
            }
        }
        return encryptedMessage;
    };
    
    private char generateRandomCharacter(char leftLimit) {
            Random random = new Random();
            boolean shouldUseUppercase = random.nextBoolean();
            char randomCharacter = (char)(random.nextInt(13)+leftLimit);
            return shouldUseUppercase ? Character.toUpperCase(randomCharacter): randomCharacter;
    }; 
    
    protected char useStrategyTranscriptionAlgorithm(char character) {
        char lowerCaseCharacter = Character.toLowerCase(character);
        if ('a' <= lowerCaseCharacter && lowerCaseCharacter <= 'm') {
            return 'a';
        }
        return 'b';
    };   
}