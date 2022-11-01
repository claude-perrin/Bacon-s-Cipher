/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.EncryptionFailed;


/**
 * Testing model Validators 
 * 
 * @author Viktor Didyk
 */
public class MessageValidatorTest {
    
    public MessageValidatorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testValidateMessage()  {
        System.out.println("validateMessage");
        MessageValidator instance = new MessageValidator();
        try {
            instance.validateMessage("");
            fail("Message should be caught as it is empty.");
        }
        catch (EncryptionFailed exc) {}
        try {
            instance.validateMessage("połish");
            fail("Message should be caught as it contains non-latin symbols.");
        }
        catch (EncryptionFailed exc) {}
        try {
            instance.validateMessage("русский");
            fail("Message should be caught as it contains non-latin symbols.");
        }
        catch (EncryptionFailed exc) {}
        try {
            instance.validateMessage("plain english");
        }
        catch (EncryptionFailed exc) {
            fail("Message is legal and shouldn't be caught.");

        }
    }
    
}