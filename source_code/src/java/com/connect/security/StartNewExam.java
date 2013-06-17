/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Devanand
 */
public class StartNewExam extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String dbUrl = "jdbc:odbc:Online_Exam_Portal";
String dbClass = "com.mysql.jdbc.Driver";
String query = "";
    private static final int EXAMDURATION = 3601;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String UEID = "";
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StartNewExam</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StartNewExam at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
            HttpSession Usersession = request.getSession(false);
            if(Usersession.getAttribute("Username") == null)
       {
   response.sendRedirect("index.html");
      

}
else
       {
            Date date = new Date();
        long milliseconds = date.getTime();
        long StartTime = milliseconds/1000;
                
        Usersession.setAttribute("ExamID", request.getParameter("ExamCode"));
        Usersession.setAttribute("ExamName", request.getParameter("ExamNamePassOn"));
        
        UEID = UEID.concat(request.getParameter("ExamCode")+Usersession.getAttribute("UserId")+StartTime);
        Usersession.setAttribute("UEID", UEID);
        Usersession.setAttribute("StartTime",StartTime);
        Usersession.setAttribute("Alotted", EXAMDURATION);
        Cookie ExamCookie;
        ExamCookie = new Cookie("QuestionNo","1");
        response.addCookie(ExamCookie);
        for(int i=1;i<=25;i++)
        {
        String OptionName= "Question"+i+"SelectedOption";
        Usersession.setAttribute(OptionName, null);
        }
        try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               
        Connection con = DriverManager.getConnection (dbUrl,"","");
        Statement stmt = con.createStatement();
        
        query="select Question, OptionA, OptionB, OptionC, OptionD from Exam_Question_Bank where ExamId="
                +Usersession.getAttribute("ExamID")+" and QuestionNo = 1";
        ResultSet rs = stmt.executeQuery(query); 
        
        if(rs.next())
        {
            Usersession.setAttribute("CurrentQuestion", rs.getString("Question"));
            Usersession.setAttribute("OptionA", rs.getString("OptionA"));
            Usersession.setAttribute("OptionB", rs.getString("OptionB"));
            Usersession.setAttribute("OptionC", rs.getString("OptionC"));
            Usersession.setAttribute("OptionD", rs.getString("OptionD"));
        }
        }
        catch(Exception e)
        {
            e.printStackTrace(out);
        }
        response.sendRedirect("ExamInstructions.jsp");
        } 
        }finally {            
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
