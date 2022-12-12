/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.EncryptionFailed;
import pl.polsl.viktordidyk.baconcipher.model.helper.FileManager;



/**
 * Testing model transcriptor 
 * 
 * @author Viktor Didyk
 */
public class TranscriptorTest {
    Transcriptor transcriptor;

    @BeforeEach
    public void setUp() {
        try {
            this.transcriptor = new Transcriptor();
        } catch (FileNotFoundException ex) {
            fail("File Doesn't exist");

        }
    }
    
    @ParameterizedTest
    @CsvSource({"GkqwdFgJRKOMiqZ,pig"})
    public void testDecryptStrategyA(String message, String expResult) {
        System.out.println("testDecryptStrategyA");
        StrategyA strategyA = new StrategyA();
        this.transcriptor.setStrategy(strategyA);
        String result = transcriptor.decrypt(message);
        assertEquals(expResult, result);
    }
    //csv test
    @ParameterizedTest
    @ValueSource(strings = {"GkqwdFgJRKOMiqZ"})
    public void testDecryptStrategyB(String message) {
        System.out.println("testDecryptStrategyB");
        String expResult = "gct";
        StrategyB strategyB = new StrategyB();
        this.transcriptor.setStrategy(strategyB);
        String result = transcriptor.decrypt(message);
        assertEquals(expResult, result);
    }


    /**
     * Test of encrypt method, of class Transcriptor.
     * 
     * pig   <-> aabab aabaa aabbb <-> CDfDfDDfDDDDfff 
     * 
     */
    @Test
    public void testEncryptStrategyA() {
        String binaryEncryption = "abbbbabaaaaabba";
        String fileName = "files/testEncrypt.txt";
        StrategyA strategyA = new StrategyA();
        this.transcriptor.setStrategy(strategyA);
        FileManager filemanager = new FileManager();
        try {
            try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
                String messageToEncrypt = filemanager.readTxtFromInputStream(inputStream);
                String encryptionResult = transcriptor.encrypt(messageToEncrypt);
                for (int i=0; i<binaryEncryption.length(); i++) {
                    if (binaryEncryption.charAt(i) == 'a' && Character.isLowerCase(encryptionResult.charAt(i))) {
                        fail("The character at the position <" +i+"> should be uppercase");
                    }
                    if (binaryEncryption.charAt(i) == 'b' && Character.isUpperCase(encryptionResult.charAt(i))) {
                        fail("The character at the position <" +i+"> should be lowercase");
                    }
                }
            }
        } 
        catch (EncryptionFailed | IOException ex) {
            fail("File is not found");
        }

    }
    
    @Test
    public void testEncrypStrategyB() {
        String binaryEncryption = "abbbbabaaaaabba";
        String fileName = "files/testEncrypt.txt";
        StrategyB strategyB = new StrategyB();
        this.transcriptor.setStrategy(strategyB);
        FileManager filemanager = new FileManager();
        try {
            try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
                String messageToEncrypt = filemanager.readTxtFromInputStream(inputStream);
                String encryptionResult = transcriptor.encrypt(messageToEncrypt);
                for (int i=0; i<binaryEncryption.length(); i++) {
                    if (binaryEncryption.charAt(i) == transcriptor.getTranscriptionStrategy().useStrategyTranscriptionAlgorithm(encryptionResult.charAt(i))) {
                        continue;
                    }
                    fail("The character at the position <" +i+"> is encrypted incorrectly");
                }
            }
        }
        catch (EncryptionFailed | IOException ex) {
            fail("File is not found");
        }
    }
}