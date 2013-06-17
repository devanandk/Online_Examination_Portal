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
import java.sql.ResultSet;
import java.sql.Statement;
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
public class submitExamination extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String dbUrl = "jdbc:odbc:Online_Exam_Portal";
//String dbClass = "com.mysql.jdbc.Driver";
String query = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession UserSession = request.getSession(false);
        int currentQuestionNo = 0;
        int attemptedCount = 0;
        int correctAnsCount = 0;
        String currentAnswer = "";
        String UserSelection = "";
        int TotalScore = 0;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               
        Connection con = DriverManager.getConnection (dbUrl,"","");
        Statement stmt = con.createStatement();
        
        query="select QuestionNo, Answer from Exam_Question_Bank where ExamId="
                +UserSession.getAttribute("ExamID")+" order by QuestionNo";
        ResultSet rs = stmt.executeQuery(query);
        
        //-- Checking user answers and generating test score
        while(rs.next())
        {
            currentQuestionNo = rs.getInt("QuestionNo");
            currentAnswer = rs.getString("Answer");
            String OptionName= "Question"+currentQuestionNo+"SelectedOption";
        if(UserSession.getAttribute(OptionName)!=null)
                   {
                       attemptedCount++;
            UserSelection = UserSession.getAttribute(OptionName).toString();
                   }
        else
        {
            UserSelection = null;
        }
        //out.println("<br><br>Question No : "+currentQuestionNo);
        //out.println("<br>UserSelection : "+UserSelection);
        //out.println("<br>Correct Answer : "+currentAnswer);
        if(UserSelection == null)
        {
          //  out.println("<br>Answer not selected<br><br>");
            
        }
        else if(currentAnswer.contains(UserSelection))
        {
            //out.println("<br>Correct<br><br>");
            correctAnsCount++;
            TotalScore = TotalScore + 4;
           
        }
            //out.println("Score So Far : "+TotalScore);
        }
        
        //--- Removing user answers from current session
        //for(int iQNo=0; iQNo< 25; iQNo++)
       // {
        //    String OptionName= "Question"+iQNo+"SelectedOption";
       //     UserSession.setAttribute(OptionName, null);
       // }
        
        //--- Removing exam tracking cookies
        Cookie[] ExamCookies = request.getCookies();
            for(int cookieCount = 0; cookieCount < ExamCookies.length; cookieCount++)
            {
                if(ExamCookies[cookieCount].getName().equals("QuestionNo"))
                {
                    ExamCookies[cookieCount].setMaxAge(0);
                }
                if(ExamCookies[cookieCount].getName().equals("MoveDirection"))
                {
                    ExamCookies[cookieCount].setMaxAge(0);
                }
            }
            
            //--- Removing question tracking session attributes
            UserSession.setAttribute("CurrentQuestion", null);
            UserSession.setAttribute("OptionA", null);
            UserSession.setAttribute("OptionB", null);
            UserSession.setAttribute("OptionC", null);
            UserSession.setAttribute("OptionD", null);
            
            con.close();
//---------------------------------------------------------------------------------------------
//Writing Exam Results to Database
//---------------------------------------------------------------------------------------------
            
            Connection con2 = DriverManager.getConnection (dbUrl,"","");
        PreparedStatement insertUserInfo;
        
        String ExamInfoInsertQuery="insert into Exam_Results (UEID, ExamId, UserId, ExamName, MarksScored, TimeStamp) "
                + "values(?,?,?,?,?,?);";
        
        insertUserInfo = con2.prepareStatement(ExamInfoInsertQuery);
        
        insertUserInfo.setString(1, UserSession.getAttribute("UEID").toString());
        insertUserInfo.setInt(2, Integer.parseInt(UserSession.getAttribute("ExamID").toString()));
        insertUserInfo.setInt(3, Integer.parseInt(UserSession.getAttribute("UserId").toString()));
        insertUserInfo.setString(4, UserSession.getAttribute("ExamName").toString());
        insertUserInfo.setInt(5, TotalScore);
        insertUserInfo.setString(6, UserSession.getAttribute("ExamDate").toString());
        
        UserSession.setAttribute("ExamScore", TotalScore);
        UserSession.setAttribute("AttemptedCount", attemptedCount);
        UserSession.setAttribute("CorrectAnsCount", correctAnsCount);
        
        int output = insertUserInfo.executeUpdate();
        con2.close();
            
        response.sendRedirect("ExaminationResult.jsp");
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
