<%-- 
    Document   : submitExamConfirmation
    Created on : Jul 20, 2012, 2:17:24 PM
    Author     : Devanand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Finish Examination</title>
    </head>
    <body>
        <%
        //int iQuestionNo=0;
        //    String SelectedOption = "";
            
        //    HttpSession UserSession = request.getSession(false);
        //Cookie[] ExamCookies = request.getCookies();
        //    for(int cookieCount = 0; cookieCount < ExamCookies.length; cookieCount++)
        //    {
         //       if(ExamCookies[cookieCount].getName().equals("QuestionNo"))
         //       {
          //          iQuestionNo = Integer.parseInt(ExamCookies[cookieCount].getValue());
          //      }
                
          //  }
            
            
                
            //SelectedOption = request.getParameter("OptionsRadioSelectedValue");
            //String OptionName= "Question"+iQuestionNo+"SelectedOption";
            //UserSession.setAttribute(OptionName, SelectedOption);
           //out.print("Selected Option is : "+SelectedOption);
            
        %>
        
        <form name="submitExam" id="submitExamNow" action="submitExamination" method="POST">
        <table border="0" cellspacing="0" cellpadding="10" style="position: absolute; top: 40%; left: 30%">
            <tr align="center" style="background-color: #e1e1f0; padding: 15px">
                <td><b style="font-size: 20px">Are you sure you wish to finish the exam?</b></td>
            </tr>
            <tr align="center" style="background-color: #e1e1f0; padding: 15px">
                <td>You still have time left. You can check your answers before submitting them.</td>
            </tr>
            <tr align="center" style="background-color: #a9a8f0; padding: 15px">
                <td align ="center">
                    <a href="javascript:void(0)" style="padding: 20px" 
                       onclick="javascript:document.getElementById('submitExamNow').submit()"><img src="images/exam-submit-confirm.png" style="border: 0"/></a>
                
                    <a href ="Examination.jsp" style="padding: 20px"><img src="images/back-to-exam.png" style="border: 0"/></a>
                </td>
            </tr>
        </table>
        </form>
    </body>
</html>
