/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.model;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;


/**
 *
 * @author admin
 */
abstract class BaconCipherStrategy {
    private HashMap<String, String> dictionary;
    
    public abstract String encode(String message);
    
    public abstract String decode(String message);

//    public void readCsvFile() {
//        Scanner scanner = new Scanner(new File("F:\\CSVDemo.csv"));  
//        scanner.useDelimiter(",");  
//        while (scanner.hasNext()) {  
//            System.out.print(scanner.next());  
//        }   
//        scanner.close(); 
//    }  

};
    

