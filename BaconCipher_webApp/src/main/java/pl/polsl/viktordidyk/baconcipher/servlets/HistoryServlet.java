/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet that loads history and displays it in a form of a table
 * @author Viktor Didyk
 */
@WebServlet(name = "History", urlPatterns = {"/History"})
public class HistoryServlet extends HttpServlet {

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
                                 <th>Input</th>
                                 <th>Result</th>
                               </tr>
                       """);
            if (session != null) {
                Integer numberOfOperations = (Integer) session.getAttribute("numberOfOperations");
                for (int i = 1; i <= numberOfOperations; i++) {
                    String[] operationData = ((String) session.getAttribute(i + "operationData")).split("\\^");
                    out.println("<tr>");
                    out.println("<td>" + operationData[0] + "</td>");
                    out.println("<td>" + operationData[1] + "</td>");
                    out.println("<td>" + operationData[2] + "</td>");
                    out.println("<td>" + operationData[3] + "</td>");
                    out.println("</tr>");
                }
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
