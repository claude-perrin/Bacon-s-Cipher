/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import pl.polsl.viktordidyk.baconcipher.model.BaconCipherStrategy;
import pl.polsl.viktordidyk.baconcipher.model.StrategyA;
import pl.polsl.viktordidyk.baconcipher.model.StrategyB;

/**
 * A helper class that defines useful for a controller functions
 * @author ViktorDidyk
 */
@WebServlet(name = "Helper")
public class ServletHelper {
    /**
     * Select proper strategy according to chosen label by user
     * @param strategy character for a corresponding startegy
     * @return BaconCipherStrategy which is one of the strategies defined
     */
    public static BaconCipherStrategy getTranscriptionStrategy(char strategy){
        if (strategy == 'A') {
            return new StrategyA();
        }
        else if (strategy == 'B') {
            return new StrategyB();
        }
        return null;
    }
    /**
     * get number of operations from a session
     * @param session
     * @return 
     */
    public static Integer getNumberOfOperations(HttpSession session){
        Integer numberOfOperations = (Integer) session.getAttribute("numberOfOperations");
        if (numberOfOperations == null) {
            numberOfOperations = 0;
            return numberOfOperations;
        }
        return numberOfOperations;
    }
}
