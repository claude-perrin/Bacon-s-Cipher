/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author admin
 */
abstract class BaconCipherStrategy {
    protected Map<Character, String> dictionary;
    
    protected final int splitSize = 5;
    
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
    
    protected List<String> splitMessageBySubstrings(String text) {
        List<String> results = new ArrayList<>();
        int length = text.length();

        for (int i = 0; i < length; i += splitSize) {
            results.add(text.substring(i, Math.min(length, i + splitSize)));
        }
        return results;
    }
    
    
    protected <K, V> K getKey(Map<K, V> map, V value)
    {
        for (Map.Entry<K, V> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    protected String getEncryptedBinarySequence(String secretMessage){
        String binarySequence = "";
        for(int i = 0; i < secretMessage.length(); i++) {
           char character = Character. toLowerCase(secretMessage.charAt(i));
           binarySequence += getCharacterBinarySequence(character);
        } 
        return binarySequence;
    };

            
    private String getCharacterBinarySequence(char key) {
        return this.dictionary.get(key);
    };
     
    public String encrypt(String secretMessage) {
           String binarySequence = this.getEncryptedBinarySequence(secretMessage);
           return generateEncryptedMessage(binarySequence);
       };    
    
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

};
    

