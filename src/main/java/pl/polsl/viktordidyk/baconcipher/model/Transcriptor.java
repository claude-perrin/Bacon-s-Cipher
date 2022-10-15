/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;
import java.io.FileNotFoundException;
import java.io.IOException;
import pl.polsl.viktordidyk.baconcipher.model.helper.FileManager;
import java.util.Map;

/**
 *
 * @author admin
 */
public class Transcriptor {
    private BaconCipherStrategy transcriptionStrategy;
    private final MessageValidator messageValidator;
    private final String filePath;
    private final Map<Character, String> transcriptionRules;
    
       
    public Transcriptor(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.messageValidator = new MessageValidator();
        try {
            this.transcriptionRules = this.readTranscriptionRulesCsv(this.filePath);
        }
        catch (IOException exc) {
            throw new FileNotFoundException();
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
    
    public String encode(String message) throws InvalidUserInputException {
        String messageToEncrypt = message.replaceAll("\\s+","");
        messageValidator.validateMessage(messageToEncrypt);
        return transcriptionStrategy.encrypt(messageToEncrypt);
    }
    
    public String decode(String message) {
        return transcriptionStrategy.decrypt(message);
    }
}
