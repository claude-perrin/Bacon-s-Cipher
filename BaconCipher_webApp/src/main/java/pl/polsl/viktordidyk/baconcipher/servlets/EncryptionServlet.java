/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import pl.polsl.viktordidyk.baconcipher.model.BaconCipherStrategy;
import pl.polsl.viktordidyk.baconcipher.model.Transcriptor;
import pl.polsl.viktordidyk.baconcipher.model.exceptions.EncryptionFailed;
import pl.polsl.viktordidyk.baconcipher.model.helper.FileManager;

/**
 * Servlet that is responsible for Encryption
 * @author ViktorDidyk
 */

@WebServlet(name = "EncryptionServlet", urlPatterns = {"/startEncryption"})
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class EncryptionServlet extends HttpServlet {

    private final Transcriptor transcriptor;
    
    /**
     * loads transcriptor
     * @throws FileNotFoundException 
     */
    public EncryptionServlet() throws FileNotFoundException {
            this.transcriptor = new Transcriptor();
                }
   
    /**
     * Pass encryption arguments to model
     * @param filecontent
     * @return
     * @throws EncryptionFailed
     * @throws FileNotFoundException 
     */
    public String startEncryptionFlow(InputStream filecontent) throws EncryptionFailed, FileNotFoundException {
        FileManager fileManager = new FileManager();
        String messageToEncrypt = fileManager.readTxtFromInputStream(filecontent);
        String encryptedMessage = this.transcriptor.encrypt(messageToEncrypt);
        return encryptedMessage;
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.With cookies errorCounter is saved.
     * Using session attributes, history is passed     * @param request servlet request
     * @param request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            Integer errorCounter = 0;
            HttpSession session = request.getSession();
            Integer operationCounter = ServletHelper.getNumberOfOperations(session);

            Part filePart = request.getPart("encryptionFile");
            String strategy_character = request.getParameter("strategyForEncryption");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("errorCounter")) {
                        errorCounter = Integer.valueOf(cookie.getValue());
                        break;
                    }
                }
            }

            BaconCipherStrategy strategy = ServletHelper.getTranscriptionStrategy(strategy_character.charAt(0));
            this.transcriptor.setStrategy(strategy);
            InputStream filecontent = filePart.getInputStream();
            try {
                String encryptedMessage = this.startEncryptionFlow(filecontent);
                out.println("Encrypted message is: \n" + encryptedMessage);
                operationCounter++;
                session.setAttribute("numberOfOperations", operationCounter);
                
                String operationResult;
                if (encryptedMessage.length() > 50) {
                    operationResult = encryptedMessage.substring(0, 50);
                    operationResult += "...";
                }
                else {
                    operationResult = encryptedMessage;
                }
                String inputFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String operationData = operationCounter.toString() + "^" + "Encryption" + "^" + inputFileName+ "^" + operationResult;
                session.setAttribute(operationCounter.toString()+"operationData", operationData);
            }
            catch (EncryptionFailed | FileNotFoundException exc) {
                errorCounter++;
                Cookie cookie = new Cookie("errorCounter", errorCounter.toString());
                response.addCookie(cookie);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, exc.getMessage());

            }
            out.println("<h3>Total errors: " + errorCounter + "</h3>");
            getServletContext().getRequestDispatcher("/History").include(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
