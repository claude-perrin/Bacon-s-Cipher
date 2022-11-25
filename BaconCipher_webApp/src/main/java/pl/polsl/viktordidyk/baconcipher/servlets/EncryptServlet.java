package pl.polsl.viktordidyk.baconcipher.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import pl.polsl.viktordidyk.baconcipher.model.BaconCipherStrategy;

import pl.polsl.viktordidyk.baconcipher.model.Transcriptor;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.EncryptionFailed;
import pl.polsl.viktordidyk.baconcipher.model.helper.FileManager;
/**
 * Main class of the servlet displaying current time, date and image. Site
 * generated by servlet is refreshed every 10 seconds.
 *
 * @author Gall Anonim
 * @version 1.0
 */
@WebServlet("/startEncryption")
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class EncryptServlet extends HttpServlet {
    private final Transcriptor transcriptor;

    public EncryptServlet() throws FileNotFoundException {
        this.transcriptor = new Transcriptor();
            }
   
    public String startEncryptionFlow(InputStream filecontent) throws IOException, EncryptionFailed {
        FileManager fileManager = new FileManager();
        String messageToEncrypt = fileManager.readTxtFromInputStream(filecontent);
        return this.transcriptor.encrypt(messageToEncrypt);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();

        Part filePart = request.getPart("encryptionFile");
        String strategy_character = request.getParameter("strategyForEncryption");
        BaconCipherStrategy strategy = ServletHelper.getTranscriptionStrategy(strategy_character.charAt(0));
        this.transcriptor.setStrategy(strategy);
        InputStream filecontent = filePart.getInputStream();
        try {
            String encryptedMessage = this.startEncryptionFlow(filecontent);
            out.println("Encrypted message is: \n" + encryptedMessage);
        }
        catch (EncryptionFailed exc) {
            out.println("Encryption failed due to \n" + exc.getMessage());

        }
        
    }
}
