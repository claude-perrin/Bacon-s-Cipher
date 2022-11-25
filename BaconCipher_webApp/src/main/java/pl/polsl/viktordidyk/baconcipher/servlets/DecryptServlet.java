package pl.polsl.viktordidyk.baconcipher.servlets;

import java.io.*;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import pl.polsl.viktordidyk.baconcipher.model.BaconCipherStrategy;
import pl.polsl.viktordidyk.baconcipher.model.Transcriptor;

@WebServlet("/startDecryption")
public class DecryptServlet extends HttpServlet {
    private final Transcriptor transcriptor;
    
    public DecryptServlet() throws FileNotFoundException {
        this.transcriptor = new Transcriptor();
            }
   
    public String startDecryptionFlow(String messageToDecrypt) throws IOException {
        return this.transcriptor.decrypt(messageToDecrypt);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();        
        String strategy_character = request.getParameter("strategyForDecryption");
        String messageToDecrypt = request.getParameter("decryption");
        BaconCipherStrategy strategy = ServletHelper.getTranscriptionStrategy(strategy_character.charAt(0));
        this.transcriptor.setStrategy(strategy);
        out.print(this.startDecryptionFlow(messageToDecrypt));
    }
}
