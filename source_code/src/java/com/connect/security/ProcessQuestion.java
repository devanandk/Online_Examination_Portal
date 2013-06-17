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
public class ProcessQuestion extends HttpServlet {

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
    private String readTextarea(String textAreaName, HttpServletRequest request)
    {
        String textAreaContent = "";
        textAreaContent = request.getParameter(textAreaName);
        return textAreaContent;
    }
    
    private String convertTextareaContent(String content)
    {
        StringBuilder text = new StringBuilder(content);
  
        int loc = (new String(text)).indexOf('\n');
        while(loc > 0){
            text.replace(loc, loc+1, "<BR>");
            loc = (new String(text)).indexOf('\n');
       }
        return text.toString();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String txtQuestion = "";
        String txtOptionA = "";
        String txtOptionB = "";
        String txtOptionC = "";
        String txtOptionD = "";
        String Answer = "";
        int QuestionNo = 0;
        HttpSession UserSession = request.getSession(false);
        
        UserSession.setAttribute("QuestionEntryException", null);
        try {
            txtQuestion = readTextarea("addNewQuestion",request);
            txtOptionA = readTextarea("addOptionA",request);
            txtOptionB = readTextarea("addOptionB",request);
            txtOptionC = readTextarea("addOptionC",request);
            txtOptionD = readTextarea("addOptionD",request);
            Answer = request.getParameter("CorrectAnswerRadio");
            //out.print(txtOptionD); 
            UserSession.setAttribute("QuestionEnteredValue", txtQuestion);
                UserSession.setAttribute("OptionAEnteredValue", txtOptionA);
                UserSession.setAttribute("OptionBEnteredValue", txtOptionB);
                UserSession.setAttribute("OptionCEnteredValue", txtOptionC);
                UserSession.setAttribute("OptionDEnteredValue", txtOptionD);
            
            if(txtQuestion==null|| "".equals(txtQuestion))
            {
                UserSession.setAttribute("QuestionEntryException", "Please enter a question");
                UserSession.setAttribute("QuestionEnteredValue", null);
                response.sendRedirect("AddQuestions.jsp");
            }
            else if(txtOptionA==null || "".equals(txtOptionA))
            {
                UserSession.setAttribute("QuestionEntryException", "Please enter Option A");
                response.sendRedirect("AddQuestions.jsp");
            }
            else if(txtOptionB==null || "".equals(txtOptionB))
            {
                UserSession.setAttribute("QuestionEntryException", "Please enter Option B");
                response.sendRedirect("AddQuestions.jsp");
            }
            else if(txtOptionC==null || "".equals(txtOptionC))
            {
                UserSession.setAttribute("QuestionEntryException", "Please enter Option C");
                response.sendRedirect("AddQuestions.jsp");
            }
            else if(txtOptionD==null || "".equals(txtOptionD))
            {
                UserSession.setAttribute("QuestionEntryException", "Please enter Option D");
                response.sendRedirect("AddQuestions.jsp");
            }
            else if(Answer==null)
            {
                UserSession.setAttribute("QuestionEntryException", "Please select the correct answser");
                response.sendRedirect("AddQuestions.jsp");
            }
            else
            {
                UserSession.setAttribute("QuestionEnteredValue", null);
                UserSession.setAttribute("OptionAEnteredValue", null);
                UserSession.setAttribute("OptionBEnteredValue", null);
                UserSession.setAttribute("OptionCEnteredValue", null);
                UserSession.setAttribute("OptionDEnteredValue", null);
                UserSession.setAttribute("QuestionEntryException", null);
                
                txtQuestion = convertTextareaContent(txtQuestion);
                txtOptionA = convertTextareaContent(txtOptionA);
                txtOptionB = convertTextareaContent(txtOptionB);
                txtOptionC = convertTextareaContent(txtOptionC);
                txtOptionD = convertTextareaContent(txtOptionD);
                
                QuestionNo = Integer.parseInt(UserSession.getAttribute("RegisterQNo").toString());
                out.print(QuestionNo);
                
                Connection con = DriverManager.getConnection (dbUrl,"","");
                PreparedStatement insertNewQuestion;
        
                String ExamInfoInsertQuery=""
                + "insert into Exam_Question_Bank "
                + "(ExamId, QuestionNo, Question, OptionA, OptionB, OptionC, OptionD, Answer) "
                + "values(?,?,?,?,?,?,?,?);";
        
                insertNewQuestion = con.prepareStatement(ExamInfoInsertQuery);
        
                insertNewQuestion.setInt(1, Integer.parseInt(UserSession.getAttribute("NewExamId").toString()));
                insertNewQuestion.setInt(2, QuestionNo);
                insertNewQuestion.setString(3, txtQuestion);
                insertNewQuestion.setString(4, txtOptionA);
                insertNewQuestion.setString(5, txtOptionB);
                insertNewQuestion.setString(6, txtOptionC);
                insertNewQuestion.setString(7, txtOptionD);
                insertNewQuestion.setString(8, Answer);
                
                int output = insertNewQuestion.executeUpdate();
                
                QuestionNo++;
                if(QuestionNo<=25)
                {
                UserSession.setAttribute("RegisterQNo", QuestionNo);
                response.sendRedirect("AddQuestions.jsp");
                }
                else
                {
                    response.sendRedirect("AdminConsoleHome.jsp");
                }
            }
        }
            catch(Exception e)
            {
                
            }
                
            
            
            //response.sendRedirect("AddQuestions.jsp");
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
