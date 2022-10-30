/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import pl.polsl.viktordidyk.baconcipher.model.helper.FileManager;
import java.util.Map;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.EncryptionFailed;

/**
 *
 * @author admin
 */
public class Transcriptor {
    private BaconCipherStrategy transcriptionStrategy;
    private final MessageValidator messageValidator;
    private final String filePath = "transcriptionRules.csv";
    private final Map<Character, String> transcriptionRules;
    private FileManager fileManager;
    

    public Transcriptor() throws FileNotFoundException {
        this.messageValidator = new MessageValidator();
        this.fileManager = new FileManager();
        try {
            this.transcriptionRules = this.readTranscriptionRulesCsv(this.filePath);
        }
        catch (IOException exc) {
            throw new FileNotFoundException();
        }
    }
    
    private Map<Character, String> readTranscriptionRulesCsv(String filePath) throws IOException {
        Map<Character, String> rules = fileManager.readCsv(filePath);
        return rules;
    }
    
        /**
     * Sets the strategy to be used for encoding and decoding.
     * @param requiredStratefy
     */
    public void setStrategy(char requiredStrategy) {
        if (Character.toLowerCase(requiredStrategy) == 'a') 
            this.transcriptionStrategy =  new StrategyA();
        else
            this.transcriptionStrategy =  new StrategyB();
        this.transcriptionStrategy.dictionary = transcriptionRules;
    }
    


    
    public String encrypt(String fileName) throws EncryptionFailed {
        try {
            String rawMessage = fileManager.readTxt(fileName);
            String messageToEncrypt = rawMessage.replaceAll("[\\s,.]+","");
            messageValidator.validateMessage(messageToEncrypt);
            return transcriptionStrategy.encrypt(messageToEncrypt);
        }
        catch (IOException exc) {
            throw new EncryptionFailed("""
                                              Provided FilePath for encryption is not found
                                              please check that the file exists""");
        }
    }
    
    public String decrypt(String message) {
        return transcriptionStrategy.decrypt(message);
    }
}
