/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package SERVLET.ADMIN.CATEGORY;

import DAO.ADMIN.Category_DAO;
import MODEL.Cate_Model;
import MODEL.User_Model;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AdminUpdateCategoryServlet", urlPatterns = {"/AdminUpdateCategoryServlet"})
public class AdminUpdateCategoryServlet extends HttpServlet {

    private static String ADMIN_CATEGORY_MANAGE_SERVLET = "/AdminCategoryServlet";
    private final String LOGIN_PAGE = "/web/view/Login/login.html";
    String url = LOGIN_PAGE;
    Category_DAO cateDAO = new Category_DAO();

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
        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER") != null) {
                User_Model userAdmin = (User_Model) session.getAttribute("USER");
                if (userAdmin.isRole() == true) {
                    String name = request.getParameter("name");
                    String image = request.getParameter("image");
                    int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                    url = "/MainServlet?btn=adminGetUpdateCategory&categoryId=" + categoryId;
                    boolean status = Integer.parseInt(request.getParameter("status")) == 1;
                    if (name != null && image != null) {
                        Cate_Model category = new Cate_Model();
                        category.setCategoryId(categoryId);
                        category.setName(name);
                        category.setImage(image);
                        category.setStatus(status);
                        if (cateDAO.updateCategory(category)) {
                            url = ADMIN_CATEGORY_MANAGE_SERVLET;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("SERVLET.ADMIN.ACCOUNT.AddAccountServlet.processRequest()" + e);
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
