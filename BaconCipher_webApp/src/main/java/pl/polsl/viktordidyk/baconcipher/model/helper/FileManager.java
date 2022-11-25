/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Helper class that handles files and all actions related to opening, reading files
 * @author viktor
 */
public class FileManager {
    /**
     * method that reads information from a csv file
     * @param filePath
     * @return a map that is used as dictionary for transcriptor
     * @throws IOException 
     */
    public Map<Character, String> readCsv(String filePath) throws IOException {
        HashMap<Character, String> map = (HashMap<Character, String>) Files.readAllLines(Paths.get(filePath))
            .stream()
            .map(line->line.split(","))
            .filter(line->line.length>1)
            .collect(Collectors.toMap(key-> key[0].charAt(0), value -> value[1]));
        return map;
    }
    
    /**
     * method that reads txt file with message that is going to be encrypted
     * @param fileName
     * @return a secret message to be encrypted
     * @throws IOException 
     */
    public String readTxt(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        String content = Files.readString(filePath);
        return content;
    }
    
    public String readTxtFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = inputStream.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
    public Map<Character, String> readCsvFromInputStream(String filePath) throws IOException {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath)) {
            Map<Character, String> loadedFromTextFileHashMap=new HashMap<>();
            Properties prop = new Properties();
            prop.load(inputStream);
            prop.forEach((key, value) -> loadedFromTextFileHashMap.put(key.toString().charAt(0), value.toString()));
            return loadedFromTextFileHashMap;
        }
    }
}
