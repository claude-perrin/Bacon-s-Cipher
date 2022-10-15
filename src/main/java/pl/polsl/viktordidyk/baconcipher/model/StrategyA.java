/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import java.util.List;
import java.util.Random;

/**
 * Capital letter is encrypted as a
 * Non-capital letter is encrypted as b
 * 
 * Example:
 *     Encrypted Message |    decryption   |    Secret Message
 *     GkwRt ceUya porrE -> abbab bbabb bbbba -> P I G
 * @author admin
 */
public class StrategyA extends BaconCipherStrategy {
    
    protected char useStrategyTranscriptionAlgorithm(char character) {
        if (Character.isUpperCase(character)) {
            return 'a';
        }
        return 'b';
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
    }
    
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
   
    protected String transformBinarySequenceToHumanReadForm(String binarySequence){
        String decryptedMessage = "";
        List<String> binariesSubstring = this.splitMessageBySubstrings(binarySequence);
        for (String substring: binariesSubstring) {
            if (substring.length() == this.splitSize) {
                decryptedMessage = decryptedMessage + this.getKey(dictionary, substring);
            }
        }
        return decryptedMessage;
    };

    public String encrypt(String secretMessage) {
           String binarySequence = this.getEncodedBinarySequence(secretMessage);
           return generateEncryptedMessage(binarySequence);
       };
    
    public String decrypt(String encryptedMessage) {
        String decryptedBinarySequence = "";
        for(int i = 0; i < encryptedMessage.length(); i++) {
           char character = encryptedMessage.charAt(i);
           decryptedBinarySequence += useStrategyTranscriptionAlgorithm(character);
        }
        //Transform to human-read form aaaabbaaababaa -> PIG
        String decryptedMessage = transformBinarySequenceToHumanReadForm(decryptedBinarySequence);
        return decryptedMessage;

    };
}