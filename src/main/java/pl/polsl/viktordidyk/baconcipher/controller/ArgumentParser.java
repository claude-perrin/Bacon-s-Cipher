/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.controller;

import java.util.Map;
import static java.util.Map.entry;

/**
 *
 * @author viktor
 */
public class ArgumentParser {
    public Map<String, String> parseCmdArguments(String[] args) {
        String argumentLine = String.join(" ", args);
        if (argumentLine.matches("^(-d)\\s[a-zA-Z]+$")) {
            Map<String, String> decryptCommand = Map.ofEntries(
                entry("mode", "decrypt"),
                    entry("messageToDecrypt", args[args.length -1])
                );
            return decryptCommand;
        }
        else if (argumentLine.matches("^((-e -f)|(-ef)|(-fe)){1}\\s[a-zA-Z]+.txt$")) {
            Map<String, String> encryptCommand = Map.ofEntries(
                entry("mode", "encrypt"),
                    entry("fileName", args[args.length -1])
                );
            return encryptCommand;
        }
        
        Map<String, String> helpCommand = Map.ofEntries(entry("mode", "help"));
        return helpCommand;
    };
}
