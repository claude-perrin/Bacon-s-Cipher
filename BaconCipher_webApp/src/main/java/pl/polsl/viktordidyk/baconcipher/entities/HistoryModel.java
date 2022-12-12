/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.entities;

/**
 *
 * @author Viktor Didyk
 */
public class HistoryModel {
    
    private int id;
    private String strategy;
    private String transcriptionMode;
    private String originalMessage;
    private String transcriptedMessage;
    
    public HistoryModel(String strategy, String transcriptionMode, String originalMessage, String transcriptedMessage) {
        this.transcriptionMode = transcriptionMode;
        this.strategy = strategy;
        this.originalMessage = originalMessage;
        this.transcriptedMessage = transcriptedMessage;     
    }

   
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id=id;
    }
    
    public String getStrategy() {
        return strategy;
    }
    
    public String getTranscriptionMode() {
         return transcriptionMode;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public String getTranscriptedMessage() {
        return transcriptedMessage;
    }    
    
}
