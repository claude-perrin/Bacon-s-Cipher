package pl.polsl.viktordidyk.baconcipher.servlets;

import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
    Transcriptor transcriptor;

   
    public String startEncryptionFlow(InputStream filecontent) throws IOException, EncryptionFailed {
        FileManager fileManager = new FileManager();
        this.transcriptor = new Transcriptor();
        String messageToEncrypt = fileManager.readTxtFromInputStream(filecontent);
        return this.transcriptor.encrypt(messageToEncrypt);
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();

        Part filePart = request.getPart("encryptionFile");
        Enumeration<String> e = request.getParameterNames();
        out.println(e);
           while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            out.println(name + " = " + request.getParameter(name));
        }
//        strategyForEncryption
//        InputStream filecontent = filePart.getInputStream();
//        try {
//            String encryptedMessage = this.startEncryptionFlow(filecontent);
//            out.println("Encrypted message is: \n" + encryptedMessage);
//
//        }
//        catch (EncryptionFailed exc) {
//            out.println("Encryption failed due to \n" + exc.getMessage());
//
//        }
        
    }
}