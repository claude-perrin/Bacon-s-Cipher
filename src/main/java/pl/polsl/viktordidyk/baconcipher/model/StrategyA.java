/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import java.util.Random;

/**
 * Transcript the message in the following way:
 * Capital letter is encrypted as a
 * Non-capital letter is encrypted as b
 * 
 * 
 * pig  -> aaab aaab bbba  -> FFFg SSSf gggA
 * 
 * 
 * @author Viktor Didyk
 * @version 1.0
 */
public class StrategyA extends BaconCipherStrategy {
    /**
     * Generates random letters to noise the encrypted message
     * @param binarySequence
     * @return 
     */
    protected String generateEncryptedMessage(String binarySequence){
        String encryptedMessage = "";
        String generatedString = generateRandomStringSequence(binarySequence.length());
        for(int i = 0; i < binarySequence.length(); i++) {
            if (binarySequence.charAt(i) == 'a') {
                encryptedMessage += Character.toUpperCase(generatedString.charAt(i));
            }
            else {
                encryptedMessage += generatedString.charAt(i);
            }
        }
        return encryptedMessage;
    };    
    /**
     * Get random character that is used to noise the encrypted message
     * @param leftLimit
     * @return character
     */
    private String generateRandomStringSequence(int stringLength) {
        int leftLimit = 'a'; 
        int rightLimit = 'z'; 
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .limit(stringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();

        return generatedString;
    };
    /**
     * The strategy Transcription rule
     * @param character
     * @return 
     */
    protected char useStrategyTranscriptionAlgorithm(char character) {
        if (Character.isUpperCase(character)) {
            return 'a';
        }
        return 'b';
    };
}