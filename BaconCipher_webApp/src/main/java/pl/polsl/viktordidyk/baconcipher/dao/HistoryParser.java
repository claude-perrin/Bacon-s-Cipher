/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.dao;

import pl.polsl.viktordidyk.baconcipher.entities.HistoryModel;

/**
 *
 * @author Viktor Didyk
 */
public class HistoryParser {
    public static String createSaveQuerySqlStatement(HistoryModel history) {
    String query = String.format("INSERT INTO History(Strategy, TranscriptionMode, OriginalMessage, TranscriptedMessage) VALUES ('%s','%s','%s','%s');",
            history.getStrategy(), history.getTranscriptionMode(),history.getOriginalMessage(), history.getTranscriptedMessage());
        return query;
    }
    
    public static String createHistoryTableSqlStatement() {
        String query = "CREATE TABLE if not exists History (Id int NOT NULL AUTO_INCREMENT,Strategy varchar(255), TranscriptionMode varchar(255), OriginalMessage varchar(1024), TranscriptedMessage varchar(1024), PRIMARY KEY (Id))";
        return query;
    }
    
    public static String getHistoriesSqlStatement() {
        String query = "SELECT * FROM History";
        return query;
    }
 }

