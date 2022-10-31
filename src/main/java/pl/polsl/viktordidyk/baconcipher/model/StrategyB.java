/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import java.util.Random;

/**
 * Transcript the message in the following way:
 * 
 * Letters from "A" until "M" are encoded as a
 * Letters from "N" until "Z" are encoded as b
 * 
 * @author Viktor Didyk
 * @version 1.0
 */
public class StrategyB extends BaconCipherStrategy {
    /**
     * Generates random letters to noise the encrypted message
     * @param binarySequence
     * @return 
     */
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
    
    /**
     * Get random character that is used to noise the encrypted message
     * @param startingChar
     * @return character
     */
    private char generateRandomCharacter(char leftLimit) {
            Random random = new Random();
            boolean shouldUseUppercase = random.nextBoolean();
            char randomCharacter = (char)(random.nextInt(13)+leftLimit);
            return shouldUseUppercase ? Character.toUpperCase(randomCharacter): randomCharacter;
    }; 
    
    /**
     * The strategy Transcription rule
     * @param character
     * @return 
     */
    protected char useStrategyTranscriptionAlgorithm(char character) {
        char lowerCaseCharacter = Character.toLowerCase(character);
        if ('a' <= lowerCaseCharacter && lowerCaseCharacter <= 'm') {
            return 'a';
        }
        return 'b';
    };   
}