/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.controller;

import anhnd.ultil.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DucAnh
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    //private final String LOGIN_PAGE = "login.html";loginPage
    private final String LOGIN_PAGE = "loginPage";
    private final String LOGIN_CONTROLLER = "loginController";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastNameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String TRIGGER_APP_CONTROLLER = "TriggerControllerServlet";
    private final String UPDATE_ACCOUNT = "UpdateServlet";
    private final String ADD_ITEM_TO_CART = "AddItemToCartServlet";
    
    private final String VIEW_YOUR_CART = "viewCart.jsp";
    private final String REMOVE_ITEM_FORM_CART = "RemoveItemServlet";
    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateNewAccountServlet";
    private final String SEARCH_PRODUCT = "SearchProductServlet";
    private final String LIST_PRODUCT = "ListProductServlet";
    private final String SHOPPING_PRODUCT = "shopping.jsp";
    private final String CHECK_OUT_TROLLER="CheckOutOrderServlet";
    private final String CONFIRM_CHECK_OUT = "ConfirmCheckOutServlet";
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
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(LOGIN_PAGE);
        // which button did user clicked?
        String button = request.getParameter("btAction");
        try {

            if (button == null) {
                 //tranfer to Login page
            } else if (button.equals("Login")) {
                //url = LOGIN_CONTROLLER;
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if (button.equals("Search")) {
                url = siteMaps.getProperty(MyApplicationConstants.searchLastnameFeature.SEARCH_LASTNAME_CONTROLLER);
            } else if (button.equals("Delete")) {
                url = siteMaps.getProperty(MyApplicationConstants.deleteAccountFeature.DELETE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Update")) {
                url = siteMaps.getProperty(MyApplicationConstants.updateAccountFeature.UPDATE_ACCOUNT_CONTROLLER);
            } else if(button.equals("shopping")){
                url=SHOPPING_PRODUCT;
                
            } else if (button.equals("Add")) {
                url = siteMaps.getProperty(MyApplicationConstants.AddProductToCartFeature.LIST_PRODUCT_CONTROLLER);

            } else if (button.equals("View Your Cart")) {
                url = siteMaps.getProperty(MyApplicationConstants.ViewCartFeature.VIEW_CART_PAGE);
            } else if(button.equals("Remove Selected Items")){
                url = siteMaps.getProperty(MyApplicationConstants.RemoveProductFeature.REMOVE_BOOK_FROM_CART);
            } else if(button.equals("Check Out Selected Books")){
                url = siteMaps.getProperty(MyApplicationConstants.ConfirmProductCheckOutFeature.CHECK_OUT_ORDER_CONTROLLER);
            } else if(button.equals("Check Out")){
                url= siteMaps.getProperty(MyApplicationConstants.ConfirmProductCheckOutFeature.CONFIRM_CHECK_OUT_CONTROLLER);
            } else if(button.equals("Create New Account")){
                url = CREATE_NEW_ACCOUNT_CONTROLLER;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
