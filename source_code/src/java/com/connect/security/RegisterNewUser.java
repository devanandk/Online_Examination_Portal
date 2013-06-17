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

/**
 *
 * @author Devanand
 */
public class RegisterNewUser extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    String dbUrl = "jdbc:odbc:Online_Exam_Portal";
String dbClass = "sun.jdbc.odbc.JdbcOdbcDriver";
String userInfoInsertQuery = "";
String userLoginCredentialsInsertQuery = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
             out.println("<html>");
            out.println("<head>");
            out.println("<title>Please wait while we analyze your request...</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Please wait... </h2>");
           // out.println("<br><h3>Oops... it seems our registration service is currently being updated...<br>"
             //       + "We're sorry for the inconvenience.... "
               //     + "You can check back later and we'll have it all up and running again.... Thank You..!!!</h3>");
            
            
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               
        Connection con = DriverManager.getConnection (dbUrl,"","");
        PreparedStatement insertUserInfo;
        
        userInfoInsertQuery="insert into User_Information (Name, Age, Address, Phone, Email) "
                + "values(?,?,?,?,?);"
                + ""
                + "insert into Login_Credentials (Username, Password)"
                + "values (?,?);";
        
        insertUserInfo = con.prepareStatement(userInfoInsertQuery);
        
        String Name= request.getParameter("name");
        String Age = request.getParameter("age");
        String Address = request.getParameter("address");
        String Phone = request.getParameter("phoneno");
        String Email = request.getParameter("email");
        String UserName = request.getParameter("username");
        String Password = request.getParameter("password2");
        
        insertUserInfo.setString(1, Name);
        insertUserInfo.setInt(2, Integer.parseInt(Age));
        insertUserInfo.setString(3, Address);
        insertUserInfo.setString(4, Phone);
        insertUserInfo.setString(5, Email);
        insertUserInfo.setString(6, UserName);
        insertUserInfo.setString(7, Password);
        
        int output = insertUserInfo.executeUpdate();
                
                
        //--------------------------------------------------------------------------------
        // The following segment of out.println() code is only for debugging purposes...
        //--------------------------------------------------------------------------------
        out.println("<br><br><h3>New User Registration Details</h3>" 
                + "-----------------------------------------------------------------<br>");
        out.println("<br>Name     &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: "+Name);
        out.println("<br>Age      &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: "+Age);
        out.println("<br>Address  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: "+Address);
        out.println("<br>Phone    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: "+Phone);
        out.println("<br>Email    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: "+Email);
        out.println("<br>Username &nbsp&nbsp&nbsp&nbsp&nbsp: "+UserName);
        out.println("<br>Password &nbsp&nbsp&nbsp&nbsp&nbsp: "+Password);
        
         out.println("<br><br> Rows Updated are :  "+output);
        //--------------------------------------------------------------------------------        
        out.println("<br><br><a href='index.html'>Click here to login...</a></body>");
        out.println("</html>");
        } 
        catch(Exception er)
        {
            out.println("<br><br> It seems we've got bugs in our systems.... Thank you for bearing with us....<br>"
                    + "We'll get this fixed for you shortly.....<br> In the meantime, here's some techical jargon<br>"
                    + "for the geeks among you....<br><br>"
                    + er.getMessage());
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
