/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import java.util.Random;

/**
 * Capital letter is encrypted as a
 * Non-capital letter is encrypted as b
 * 
 * Example:
 *     Encrypted Message |    decryption   |    Secret Message
 *     GkwRt ceUya porrE <-> abbab bbabb bbbba <-> P I G
 * @author admin
 */
public class StrategyA extends BaconCipherStrategy {
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
    protected char useStrategyTranscriptionAlgorithm(char character) {
        if (Character.isUpperCase(character)) {
            return 'a';
        }
        return 'b';
    };
}