/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

/**
 *
 * @author admin
 */
public class Transcription {
    private BaconCipherStrategy transcriptionStrategy;
    private final MessageValidator messageValidator = new MessageValidator();
    
    public void setTranscriptionStrategy(BaconCipherStrategy transcriptionStrategy) {
        this.transcriptionStrategy = transcriptionStrategy;
    }
    
    public String encode(String message) throws MessageCannotBeNonLatin {
        messageValidator.validateMessage(message);
        return transcriptionStrategy.encode(message);
    }
    
    public String decode(String message) {
        return transcriptionStrategy.decode(message);
    }
}
