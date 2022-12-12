/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.viktordidyk.baconcipher.entities.HistoryModel;



public final class HistoryDAO {
    private static HistoryDAO instance;
    private final Connection connection;
    private Statement statement;
    private String query;
    private final DBConnectionManager connectionManager;
   
    private HistoryDAO() throws ClassNotFoundException, SQLException {
        this.connectionManager = new DBConnectionManager();
        this.connection = connectionManager.getConnection();
    }
    
    public static HistoryDAO getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) 
            instance = new HistoryDAO();
        return instance;
    }
    
    
    public void setUpDbSchema() throws SQLException  {
        this.createTableHistory();
    }

    private void createTableHistory() throws SQLException{
        this.query = HistoryParser.createHistoryTableSqlStatement();
        this.statement = this.connection.createStatement();
        this.statement.executeUpdate(this.query);
        this.statement.close();
    }

    public void insertHistoryDataIntoTable(HistoryModel history) throws SQLException  {
        this.query = HistoryParser.createSaveQuerySqlStatement(history);
        this.statement = this.connection.createStatement();
        this.statement.executeUpdate(this.query);
        this.statement.close();
    }
    
    
    public List<HistoryModel> getHistories() throws SQLException{
        this.query = HistoryParser.getHistoriesSqlStatement();
        this.statement = this.connection.createStatement();
        ResultSet rs = this.statement.executeQuery(query);
        List<HistoryModel> histories = new ArrayList<>();
        while (rs.next()) {
          int id = rs.getInt("id");
          String strategy = rs.getString("Strategy");
          String transcriptionMode = rs.getString("TranscriptionMode");
          String originalMessage = rs.getString("OriginalMessage");
          String transcriptedMessage = rs.getString("TranscriptedMessage");
          HistoryModel history = new HistoryModel(strategy, transcriptionMode, originalMessage, transcriptedMessage);
          history.setId(id);
          histories.add(history);
        }
        this.statement.close();   
        return histories;
    }
    
}
