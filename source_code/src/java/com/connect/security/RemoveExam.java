/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Devanand
 */
public class RemoveExam extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String dbUrl = "jdbc:odbc:Online_Exam_Portal";
String dbClass = "sun.jdbc.odbc.JdbcOdbcDriver";
String query = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String examIdToRemove = null;
        HttpSession UserSession = request.getSession(false);
        UserSession.setAttribute("RemoveExamException", null);
        try {
            examIdToRemove = request.getParameter("ExamIdToRemoveRadio");
            
            if("".equals(examIdToRemove)||examIdToRemove == null)
            {
                UserSession.setAttribute("RemoveExamException", "Please select an exam to delete");
                response.sendRedirect("AdminRemoveExam.jsp");
            }
            
            Class.forName(dbClass);
            Connection con = DriverManager.getConnection (dbUrl,"","");
                PreparedStatement insertNewQuestion;
        
                query=""
                        + "delete from Exam_List where ExamId = ?;"
                        + "delete from Exam_Question_Bank where ExamId = ?;";
        
                insertNewQuestion = con.prepareStatement(query);
        
                insertNewQuestion.setString(1, examIdToRemove);
                insertNewQuestion.setString(2, examIdToRemove);
                
                 int output = insertNewQuestion.executeUpdate();
            
            response.sendRedirect("AdminRemoveExam.jsp");
        }
        catch(Exception e)
        {
            e.printStackTrace(out);
        }
        finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
