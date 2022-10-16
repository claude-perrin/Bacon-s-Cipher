/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import pl.polsl.viktordidyk.baconcipher.model.exceptions.FileWithRulesIsNotFound;
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
    private final String filePath;
    private final Map<Character, String> transcriptionRules;
    
       
    public Transcriptor(String filePath) throws FileWithRulesIsNotFound {
        this.filePath = filePath;
        this.messageValidator = new MessageValidator();
        try {
            this.transcriptionRules = this.readTranscriptionRulesCsv(this.filePath);
        }
        catch (IOException exc) {
            throw new FileWithRulesIsNotFound("""
                                              Provided FilePath doesn't exist or was deleted
                                               please check that therule file exists""");
        }
    }
    
    private Map<Character, String> readTranscriptionRulesCsv(String filePath) throws IOException {
        FileManager fileManager = new FileManager();
        Map<Character, String> rules = fileManager.readCsv(filePath);
        return rules;
    }
    
    /**
     * Sets the strategy to be used for encoding and decoding.
     * @param transcriptionStrategy
     */
    public void setTranscriptionStrategy(BaconCipherStrategy transcriptionStrategy) {
        this.transcriptionStrategy = transcriptionStrategy;
        this.transcriptionStrategy.dictionary = transcriptionRules;
    }
    
    public String encrypt(String message) throws EncryptionFailed {
        String messageToEncrypt = message.replaceAll("\\s+","");
        messageValidator.validateMessage(messageToEncrypt);
        return transcriptionStrategy.encrypt(messageToEncrypt);
    }
    
    public String decrypt(String message) {
        return transcriptionStrategy.decrypt(message);
    }
}
