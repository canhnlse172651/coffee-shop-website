/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package SERVLET;

import DAO.ADMIN.Account_DAO;
import DAO.Cart_DAO;
import DAO.OrderDetail_DAO;
import DAO.Order_DAO;
import DAO.Payment_DAO;
import MODEL.Cart_Model;
import MODEL.OrderDetail_Model;
import MODEL.Orders_Model;
import MODEL.Payment_Model;
import MODEL.User_Model;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LA DAT
 */
@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/CreateOrderServlet"})
public class CreateOrderServlet extends HttpServlet {

    private final String VIEW_CHECKOUT_PAGE = "/web/view/Checkout/checkout.jsp";
    private final String LOGIN_PAGE = "web/view/Login/login.html";
    private final String ERROR_PAGE = "web/error.jsp";
    private final String HOME_SERVLET = "/HomeServlet";

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "";
        try {
            Cart_DAO dao = new Cart_DAO();
            Order_DAO orderDAO = new Order_DAO();
            OrderDetail_DAO orderDetail_DAO = new OrderDetail_DAO();
            Payment_DAO payment_DAO = new Payment_DAO();
            Account_DAO accDAO = new Account_DAO();
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER") != null) {
                User_Model user = (User_Model) session.getAttribute("USER");
                int userId = user.getUserId();
                String address = request.getParameter("address");
                String fullName = request.getParameter("fullName");
                String phone = request.getParameter("phone");

                String paymentMethod = request.getParameter("paymentMethod");
                String shippingOption = request.getParameter("shippingOption");
                double totalPrice = Double.parseDouble(request.getParameter("totalPriceValue"));
                int ship = 0;
                if (shippingOption != null) {
                    if (shippingOption.equals("fast")) {
                        ship = 50000;
                    } else {
                        ship = 20000;
                    }
                }
                Payment_Model payment = new Payment_Model();
                if (paymentMethod != null) {
                    if (paymentMethod.equals("card")) {
                        String bankName = request.getParameter("bankName");
                        String owner = request.getParameter("owner");
                        String cardNumber = request.getParameter("cardNumber");
                        payment = new Payment_Model(-1, cardNumber, null, bankName, owner, false, "Credit card");
                    } else {
                        payment = new Payment_Model(-1, null, null, null, null, false, "Pay in cash");
                    }
                }
                Orders_Model order = new Orders_Model(-1, userId, 1, null, null, totalPrice, fullName, address, phone, ship);
                int orderId = orderDAO.insertOrder(order);
                if (orderId != -1) {
                    List<Cart_Model> listCart = dao.getCartsByUserId(userId);
                    for (Cart_Model item : listCart) {
                        totalPrice = item.getPrice() * item.getQuantity();
                        OrderDetail_Model orderDetail = new OrderDetail_Model(orderId, item.getProductId(), item.getQuantity(), 0, totalPrice);
                        orderDetail_DAO.insertOrderDetail(orderDetail, userId);
                    }
                    payment.setPaymentId(orderId);
                    payment_DAO.insertPayment(payment);
                    User_Model acc= accDAO.findOneById(userId);
                    acc.setNumberOrdered(acc.getNumberOrdered()+1);
                    accDAO.updateUser(acc);
                    url = HOME_SERVLET;
                } else {
                    url = VIEW_CHECKOUT_PAGE;
                }
            } else {
                url = LOGIN_PAGE;
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            url = ERROR_PAGE;
            request.setAttribute("ERROR_MESSAGE", "Database error: " + e.getMessage());
        } finally {
            response.sendRedirect(request.getContextPath() + url);
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
