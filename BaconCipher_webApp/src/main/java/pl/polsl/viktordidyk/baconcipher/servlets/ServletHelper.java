/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.servlets;

import java.util.Calendar;
import java.util.TimeZone;
import javax.servlet.annotation.WebServlet;
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
     * Get current hour in integer represenation
     * @return hour, e.g. 17, 15
     */
    public static int getCurrentHour() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTimeZone(tz);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    /**
     * Used to shortcut long message
     * @param message
     * @return 
     */
    public static String shortTheMessage(String message) {
        if (message.length() > 50) {
            message = message.substring(0, 50);
            message += "...";
        }
        return message;
    }    
}
