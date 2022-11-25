/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Strategy class that implements strategy designing pattern
 * Defines common functionality for all strategies
 * @author Viktor Didyk
 * @version 1.0
 */
abstract class BaconCipherStrategy {
    protected Map<Character, String> dictionary;
    
    protected final int splitSize = 5;
    
    /**
     * Using rules dictionary transforms the decoded sequence to words
     * @param binarySequence
     * @return originalMessage
     */
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
    
        
    /**
     * The message is split into list by 5 characters
     * @param text
     * @return List of split words
     */
    protected List<String> splitMessageBySubstrings(String text) {
        List<String> results = new ArrayList<>();
        int length = text.length();

        for (int i = 0; i < length; i += splitSize) {
            results.add(text.substring(i, Math.min(length, i + splitSize)));
        }
        return results;
    }
    
    /**
     * From dictionary, depending on a value get the key
     * @param map the dictionary
     * @param value based on which value get corresponding key
     * @return the key
     */
    protected Character getKey(Map<Character, String> map, String value)
    {
        for (Map.Entry<Character, String> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return '#';
    }
 
    /**
     * Get binary sequence of the secretMessage
     * @param secretMessage
     * @return encrypted binary sequence of the secret message
     */     
    protected String getEncryptedBinarySequence(String secretMessage){
        String binarySequence = "";
        for(int i = 0; i < secretMessage.length(); i++) {
           char character = Character. toLowerCase(secretMessage.charAt(i));
           binarySequence += getCharacterBinarySequence(character);
        } 
        return binarySequence;
    };
    
    /**
    * From the rules get encryption for a character
    * @param key
    * @return encryption binarySequence
    */          
    private String getCharacterBinarySequence(char key) {
        return this.dictionary.get(key);
    };
    
    /**
     * Encrypts the message
     * @param secretMessage
     * @return Encrypted message
     */
    public String encrypt(String secretMessage) {
           String binarySequence = this.getEncryptedBinarySequence(secretMessage);
           return generateEncryptedMessage(binarySequence);
       };    

    /**
     * Decrypts the message
     * @param encryptedMessage
     * @return Original message
     */
    public String decrypt(String encryptedMessage) {
        String decryptedBinarySequence = "";
        for(int i = 0; i < encryptedMessage.length(); i++) {
           char character = encryptedMessage.charAt(i);
           decryptedBinarySequence += useStrategyTranscriptionAlgorithm(character);
        }
        //Transform to human-read form aaaabbaaababaa -> PIG
        String decryptedMessage = this.transformBinarySequenceToHumanReadForm(decryptedBinarySequence);
        return decryptedMessage;
    };    
    protected abstract char useStrategyTranscriptionAlgorithm(char character);
     
        
    protected abstract String generateEncryptedMessage(String binarySequence);
    
    public abstract String toString();


};
    

