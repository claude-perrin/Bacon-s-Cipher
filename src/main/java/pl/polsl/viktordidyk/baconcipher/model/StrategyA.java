/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

/**
 * Capital letter is encoded as A
 * Non-capital letter is encoded as B
 * @author admin
 */
public class StrategyA extends BaconCipherStrategy {
    
    protected char useStrategyEncryptionRule(char character) {
        if (Character.isUpperCase(character)) {
            return 'A';
        }
        return 'B';
    };
    
    public String encode(String message) {
        return "Encode StrategyA";
    };

    public String decode(String encryptedMessage) {
        String decodedMessage = "";
        for(int i = 0; i < encryptedMessage.length(); i++) {
           char character = encryptedMessage.charAt(i);
           decodedMessage = decodedMessage + useStrategyEncryptionRule(character);
           
        }
      return "Decode StrategyA";

    };
}