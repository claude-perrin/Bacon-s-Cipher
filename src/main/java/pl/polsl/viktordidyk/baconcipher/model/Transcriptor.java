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
 * Class that manages whole business logic.
 * 
 * @author Viktor Didyk
 * @version 1.0
 */
public class Transcriptor {
    private BaconCipherStrategy transcriptionStrategy;
    private final MessageValidator messageValidator;
    private final String filePath = "transcriptionRules.csv";
    private final Map<Character, String> transcriptionRules;
    private FileManager fileManager;
    
    /**
     * Constructor that aggregates needed classes
     * @throws FileNotFoundException 
     */
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
    
    /**
     * Using FileManager try to read needed csvFile
     * @param filePath
     * @return content of csv file
     * @throws IOException 
     */
    private Map<Character, String> readTranscriptionRulesCsv(String filePath) throws IOException {
        Map<Character, String> rules = fileManager.readCsv(filePath);
        return rules;
    }
    
    /**
     * Sets the strategy to be used for encoding and decoding.
     * @param requiredStrategy the char which represent the strategy to be used
     */
    public void setStrategy(char requiredStrategy) {
        if (Character.toLowerCase(requiredStrategy) == 'a') 
            this.transcriptionStrategy =  new StrategyA();
        else
            this.transcriptionStrategy =  new StrategyB();
        this.transcriptionStrategy.dictionary = transcriptionRules;
    }
    
    public BaconCipherStrategy getTranscriptionStrategy() {
        return this.transcriptionStrategy;
    }
    
    /**
     * Starts encryption
     * @param fileName
     * @return Encrypted message
     * @throws EncryptionFailed 
     */
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
    
    /**
     * Starts decryption
     * @param message
     * @return decrypted message
     */
    public String decrypt(String message) {
        return transcriptionStrategy.decrypt(message);
    }
}
