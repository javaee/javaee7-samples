/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.sample.transaction.scope;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ContextNotActiveException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author arungup
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
    
    @Inject MyBean bean;
    
    @Inject MyBean bean2;
    
    @Inject UserTransaction ut;
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            try {
                out.println("<b>Scenario 1</b>: Bean1 injected twice, same id<br>");
                ut.begin();
                out.println("Bean1: " + bean.getId() + "<br>");
                out.println("Bean2: " + bean2.getId());
                ut.commit();
                
                out.println("<p><p>S<b>cenario 2</b>: Repeat of Scenario 1, different transaction, different ids<br>");
                ut.begin();
                out.println("Bean1: " + bean.getId() + "<br>");
                out.println("Bean2: " + bean2.getId());
                ut.commit();
                
                out.println("<p><p><b>Scenario 3</b>: Bean outside a transaction<br>");
                try {
                    bean.getId();
                } catch (ContextNotActiveException ex) {
                    out.println("Got expected ContextNotActiveException");
                }
            } catch (NotSupportedException 
                    | SystemException 
                    | RollbackException 
                    | HeuristicMixedException 
                    | HeuristicRollbackException 
                    | SecurityException 
                    | IllegalStateException ex) {
                Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
