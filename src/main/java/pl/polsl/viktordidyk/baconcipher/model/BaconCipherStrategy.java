/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map;
import java.util.Map;
/**
 *
 * @author admin
 */
abstract class BaconCipherStrategy {
    protected Map<Character, String> dictionary;
    
    protected final int splitSize = 5;
    
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
    
    protected List<String> splitMessageBySubstrings(String text) {
        List<String> results = new ArrayList<>();
        int length = text.length();

        for (int i = 0; i < length; i += splitSize) {
            results.add(text.substring(i, Math.min(length, i + splitSize)));
        }
        return results;
    }
            
    private String getCharacterBinarySequence(char key) {
        return this.dictionary.get(key);
    };   
    
    protected String getEncodedBinarySequence(String secretMessage){
        String binarySequence = "";
        for(int i = 0; i < secretMessage.length(); i++) {
           char character = secretMessage.charAt(i);
           binarySequence += getCharacterBinarySequence(character);
        } 
        return binarySequence;
    };

    
    public abstract String encrypt(String message);
    
    public abstract String decrypt(String message);
    
    protected abstract char useStrategyTranscriptionAlgorithm(char character);
     
    protected abstract String transformBinarySequenceToHumanReadForm(String binarySequence);
    
    protected abstract String generateEncryptedMessage(String binarySequence);

};
    

