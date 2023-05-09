/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.controller;

import anhnd.registration.RegistrationDAO;
import anhnd.registration.RegistrationDTO;
import anhnd.ultil.DBHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.Registration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Buu
 */
public class LoginSerlet extends HttpServlet {

    private final String INVALID_PACE = "invalidPage";
    private final String SEARCH_PAGE = "searchPage";

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
        PrintWriter out = response.getWriter();
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //String url = INVALID_PACE;
        String url = siteMaps.getProperty(INVALID_PACE);
//        String username = request.getParameter("txtUsername");
//        String password = request.getParameter("txtPassword");
 //       String button = request.getParameter("btAction");
        try {
            //

            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            // 3. Call model
            //3.1 new obj
            RegistrationDAO dao = new RegistrationDAO();
            //3.2 call methed from that obj 
            RegistrationDTO result = dao.checkLogin(username, password);
            //4. send view
            if (result != null) {
                //url = SEARCH_PAGE;
                url = siteMaps.getProperty(SEARCH_PAGE);
                HttpSession session = request.getSession();
                session.setAttribute("USER", result);
                //store cookie
//                Cookie cookies = new Cookie(username, url);
//                cookies.setMaxAge(60 * 3);
//                response.addCookie(cookies);
            }
            // controller da 40 55 xem du lieu qua view va gan nguoc lai cho bien url
            //end user click login
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
           ex.printStackTrace();
        } finally {
            //5. set value to res obj
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
