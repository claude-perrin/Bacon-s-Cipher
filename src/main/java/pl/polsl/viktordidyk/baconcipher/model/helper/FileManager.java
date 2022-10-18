/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for reading files
 * @author Viktor Didyk
 * @version 1.0
 */
public class FileManager {
    /**
     * Reads file in csv format
     * @param filePath
     * @return csv as a map, first column as a key second as a value
     * @throws IOException 
     */
    
    public Map<Character, String> readCsv(String filePath) throws IOException {
        HashMap<Character, String> map = new HashMap<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(",", 2);
                if (keyValuePair.length == 2) {
                    char key = keyValuePair[0].charAt(0);
                    String value = keyValuePair[1];
                    map.put(key, value);
                } else {
                    System.out.println("No Key:Value found in line, ignoring: " + line);
                }
            }
        }
        return map;
    }
    
    /**
     *  Reads file in txt format
     * @param fileName
     * @return Content of txt file as a String
     * @throws IOException 
     */
    public String readTxt(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        String content = Files.readString(filePath);
        return content;
    }
}
