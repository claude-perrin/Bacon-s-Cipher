/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.view;

import java.util.Scanner;

/**
 *
 * @author SuperStudent.PL
 */
public class View {
    
    public void print(String message) {
        System.out.println(message);
    }
    
    public void showErrorMessage(String errorMessage) {
        System.err.println(errorMessage);
    }
    
    public String getTranscriptionRulesFileName() {
        Scanner sc= new Scanner(System.in); 
        System.out.print("Enter Transcription Rules File Name: ");  
        return sc.nextLine(); 
    }
    
    public String[] getUserCommand() {
        Scanner sc= new Scanner(System.in);
        this.print("Enter next command: ");  
        String userCommand= sc.nextLine();
        return userCommand.split(" ");
    }

    public void printHelp() {
        String help = """
                      Usage:
                      -s <strategy> [-d <message> | -e -f <fileName>]                      
                      Options:
                      -d decrypt message, requires message to be provided afterwards, e.g. -d AFeFSAeSFAfdswr
                      -e encrypt message, filename to be encrypted
                      -f provide file name to be decrypted, format must be ".txt"
                      q quit
                      
                      
                      It's possible to run program without any flags
                      """;
        print(help);
    }
    
}
