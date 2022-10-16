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

    public void printHelp() {
        String help = """
                      Usage:
                      BaconCipher [-d | -e] [-f <fileName>]
                      
                      Options:
                      -d decrypt message
                      -e encrypt message
                      -f provide file name, defualt is "transcriptionRules.csv"
                      -h displays help
                      
                      It's possible to run program without any flags
                      """;
        print(help);
    }
    
}
