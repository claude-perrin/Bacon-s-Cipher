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
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import pl.polsl.viktordidyk.baconcipher.dao.HistoryDAO;
import pl.polsl.viktordidyk.baconcipher.entities.HistoryModel;
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
    private String mode = "encryption";
    private HistoryDAO historyDao;
    private FileManager fileManager;

    
    /**
     * loads transcriptor
     * @throws FileNotFoundException 
     */
    public EncryptionServlet() throws FileNotFoundException {
        this.fileManager = new FileManager();
        this.transcriptor = new Transcriptor();
        try {
            this.historyDao = HistoryDAO.getInstance();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    } 

   
    /**
     * Pass encryption arguments to model
     * @param messageToEncrypt
     * @return
     * @throws EncryptionFailed
     * @throws FileNotFoundException 
     */
    public String startEncryptionFlow(String messageToEncrypt) throws EncryptionFailed, FileNotFoundException {
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
            Integer visitNumber = 0;
            Integer visitNumberBeforeNoon = 0;
            Integer visitNumberAfterNoon = 0;

            HttpSession session = request.getSession();


            Part filePart = request.getPart("encryptionFile");
            String strategyCharacter = request.getParameter("strategyForEncryption");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("errorCounter")) {
                        errorCounter = Integer.valueOf(cookie.getValue());
                    }
                    else if (cookie.getName().equals("visitNumber")) {
                        visitNumber = Integer.valueOf(cookie.getValue());
                    }
                    else if (cookie.getName().equals("visitNumberAfterNoon")) {
                        visitNumberAfterNoon = Integer.valueOf(cookie.getValue());
                    }
                    else if (cookie.getName().equals("visitNumberBeforeNoon")) {
                        visitNumberBeforeNoon = Integer.valueOf(cookie.getValue());
                    }
                }
            }

            visitNumber++;
            Cookie cookie = new Cookie("visitNumber", visitNumber.toString());
            response.addCookie(cookie);
            if (ServletHelper.getCurrentHour() > 12) {
                visitNumberAfterNoon++;
                cookie = new Cookie("visitNumberAfterNoon", visitNumberAfterNoon.toString());
                response.addCookie(cookie);

            }
            else {
                visitNumberBeforeNoon++;
                cookie = new Cookie("visitNumberBeforeNoon", visitNumberBeforeNoon.toString());
                response.addCookie(cookie);
            }

            BaconCipherStrategy strategy = ServletHelper.getTranscriptionStrategy(strategyCharacter.charAt(0));
            this.transcriptor.setStrategy(strategy);
            InputStream filecontent = filePart.getInputStream();
            String messageToEncrypt = fileManager.readTxtFromInputStream(filecontent);
            try {
                String encryptedMessage = this.startEncryptionFlow(messageToEncrypt);
                out.println("Encrypted message is: \n" + encryptedMessage);                
                HistoryModel history = new HistoryModel(mode,strategyCharacter,ServletHelper.shortTheMessage(messageToEncrypt), ServletHelper.shortTheMessage(encryptedMessage));
                try {
                    historyDao.insertHistoryDataIntoTable(history);
                } catch (SQLException ex) {
                    errorCounter++;
                    cookie = new Cookie("errorCounter", errorCounter.toString());
                    response.addCookie(cookie);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());            
                }
            }
            catch (EncryptionFailed | FileNotFoundException exc) {
                errorCounter++;
                cookie = new Cookie("errorCounter", errorCounter.toString());
                response.addCookie(cookie);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, exc.getMessage());

            }
            out.println("<h3>Total errors: " + errorCounter + "</h3>");
            out.println("<h3>Total visits: " + visitNumber + "</h3>");
            out.println("<h3>Total visits before noon: " + visitNumberBeforeNoon + "</h3>");
            out.println("<h3>Total visits after noon: " + visitNumberAfterNoon + "</h3>");

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
        try ( PrintWriter out = response.getWriter()) {
        out.print("Get method is not allowed");
        }
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
