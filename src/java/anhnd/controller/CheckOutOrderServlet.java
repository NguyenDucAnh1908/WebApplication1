/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.controller;

import anhnd.Cart.CartObj;
import anhnd.order.OrdersDAO;
import anhnd.order.OrdersDTO;
import anhnd.orderdetails.OrderDetailsDTO;
import anhnd.product.ProductDTO;
import anhnd.ultil.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DucAnh
 */
@WebServlet(name = "CheckOutOrderServlet", urlPatterns = {"/CheckOutOrderServlet"})
public class CheckOutOrderServlet extends HttpServlet {
private final String CONFIRM_CHECK_OUT_PAGE = "confirmCheckOut.jsp";
private final String CHECK_OUT_SUCCESS_PAGE = "checkOutSuccess.jsp";
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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("txtName");
        String address = request.getParameter("txtAdress");
        String total = request.getParameter("txtTotal");
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.CheckOutProductFeatures.CONFIRM_CHECK_OUT_PAGE);
        
        try {
            //1. Cust goes to cart place
            HttpSession session = request.getSession(false);
            //2. Cust takes his/her cart
            if (session != null) {
                CartObj cart = (CartObj)session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets all items
                    Map<ProductDTO, Integer> items 
                            = (Map<ProductDTO, Integer>)session.getAttribute("CHECK_OUT_ITEMS");
                    if (items != null) {
                        int orderID = 
                                cart.checkOutItemsOfCart(name, address, total, items);
                        if (orderID > 0) {
                            OrdersDAO ordersDAO = new OrdersDAO();
                            OrdersDTO ordersDTO = ordersDAO.getOrders(orderID);
                            
                            List<OrderDetailsDTO> orderDetailsDTO = 
                                    cart.addItemsToOrderDetailsDTO(items, orderID);
                            
                            for (ProductDTO dto : items.keySet()) {
                                    cart.removeItemsForCheckOut(dto);
                            }
                            
                            session.setAttribute("ORDER", ordersDTO);
                            session.setAttribute("LIST_ORDER_DETAILS", orderDetailsDTO);
                            session.setAttribute("CART", cart);
                            session.removeAttribute("CHECK_OUT_ITEMS");
                            url = siteMaps.getProperty(MyApplicationConstants.CheckOutProductFeatures.CHECK_OUT_SUCCESS_PAGE);
                            }
                        }
                    }
                }
        } catch (SQLException ex) {
            log("CheckOutOrderServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutOrderServlet_Naming: " + ex.getMessage());
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            //7. refresh viewing cart ==> call view cart function
//            String urlRewriting = "DispatchServlet?btAction=View Your Cart";
            //không xài được RequestDispatcher vì bt action sẽ bị trùng
//            response.sendRedirect(urlRewriting);
            response.sendRedirect(url);
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
    try {
        processRequest(request, response);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CheckOutOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    try {
        processRequest(request, response);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CheckOutOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
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
