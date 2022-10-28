/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author viktor
 */
public class TranscriptorTest {
    Transcriptor transcriptor;

    @BeforeEach
    public void setUp() throws Exception {
        this.transcriptor = new Transcriptor();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"GkqwdFgJRKOMiqZ"})
    public void testDecryptStrategyA(String message) {
        System.out.println("testDecryptStrategyA");
        String expResult = "pig";
        StrategyA strategyA = new StrategyA();
        this.transcriptor.setTranscriptionStrategy(strategyA);
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
        this.transcriptor.setTranscriptionStrategy(strategyB);
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
    public void testEncryptStrategyA() throws Exception {
        String binaryEncryption = "abbbbabaaaaabba";
        String fileName = "testEncrypt.txt";
        StrategyA strategyA = new StrategyA();
        this.transcriptor.setTranscriptionStrategy(strategyA);
        String encryptionResult = transcriptor.encrypt(fileName);
        for (int i=0; i<binaryEncryption.length(); i++) {
            if (binaryEncryption.charAt(i) == 'a' && Character.isLowerCase(encryptionResult.charAt(i))) {
                fail("The character at the position <" +i+"> should be uppercase");
            }
            if (binaryEncryption.charAt(i) == 'b' && Character.isUpperCase(encryptionResult.charAt(i))) {
                fail("The character at the position <" +i+"> should be lowercase");
            }
        }

    }
    
    @Test
    public void testEncrypStrategyB() throws Exception {
        String binaryEncryption = "abbbbabaaaaabba";
        String fileName = "testEncrypt.txt";
        StrategyB strategyB = new StrategyB();
        this.transcriptor.setTranscriptionStrategy(strategyB);
        String encryptionResult = transcriptor.encrypt(fileName);
        for (int i=0; i<binaryEncryption.length(); i++) {
            if (binaryEncryption.charAt(i) == transcriptor.getTranscriptionStrategy().useStrategyTranscriptionAlgorithm(encryptionResult.charAt(i))) {
               continue;
            }
            fail("The character at the position <" +i+"> is encrypted incorrectly");
        }

    }
}
