/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.viktordidyk.baconcipher.dao.HistoryDAO;
import pl.polsl.viktordidyk.baconcipher.entities.HistoryModel;

/**
 * Servlet that loads history from database and displays it in a form of a table
 * @author Viktor Didyk
 */
@WebServlet(name = "History", urlPatterns = {"/History"})
public class HistoryServlet extends HttpServlet {
    private HistoryDAO historyDao;
    
    public HistoryServlet(){
        try {
            this.historyDao = HistoryDAO.getInstance();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            out.print("""
                             <style>
                             table {
                               border-collapse: collapse;
                               width: 100%;
                             }
                             td, th {
                               border: 1px solid #dddddd;
                               text-align: left;
                               padding: 8px;
                             }
                             </style>
                             <table>
                               <tr>
                                 <th>Number</th>
                                 <th>Operation</th>
                                <th>Strategy</th>
                                 <th>Input</th>
                                 <th>Result</th>
                               </tr>
                       """);
            List<HistoryModel> histories;
            try {
                histories = historyDao.getHistories();
                for (HistoryModel i : histories) {
                    out.println("<tr>");
                    out.println("<td>" + i.getId()+ "</td>");
                    out.println("<td>" + i.getTranscriptionMode() + "</td>");
                    out.println("<td>" + i.getStrategy() + "</td>");
                    out.println("<td>" + i.getOriginalMessage() + "</td>");
                    out.println("<td>" + i.getTranscriptedMessage() + "</td>");
                    out.println("</tr>");

                }
            } catch (SQLException ex) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());            

            }
            out.print("""     
                          </table>
                            <button onclick="location.href='/BaconCipher_webApp/'">Go Back</button>
                            </body>
                    </html> 
                      """);

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
