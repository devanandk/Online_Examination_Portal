<%-- 
    Document   : ExaminationResult
    Created on : Jul 20, 2012, 3:36:48 PM
    Author     : Devanand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            function printCertificate()
            {
                alert("Please note that certificate my not be properly rendered on Chrome Browser. Sorry for the inconvenience.");
                window.open('/Online_Examination_Portal/CreateDownloadableTestReport', 'Certificate', '');
            }
        </script>
        <%
        HttpSession UserSession = request.getSession(false);
        if(UserSession.getAttribute("Username") == null)
       {
   response.sendRedirect("index.html");
      

}
else
    
       {
        %>
        <title><% out.print(UserSession.getAttribute("ExamName")+" : "+UserSession.getAttribute("UEID")); %> - Online Examination Portal</title>
    </head>
    <body>
    <center>
        
        
        <table border ="0" cellspacing ="0" cellpadding="10" width="600" style="padding-top: 50px">
            <tr>
                <td colspan="3" align="center"  style="background-color: #a9a8f0; padding: 8px; font-size: 15px; font-weight: bold">
                    ONLINE EXAMINATION PORTAL</td>
            </tr>
            <tr>
                <td colspan="3" align="center"  style="background-color: #a9a8f0; padding: 8px; font-size: 24px; font-weight: bold">
                    <% out.println(UserSession.getAttribute("ExamName")); %></td>
            </tr>
            <tr style="background-color: #e1e1f0">
                <td>
                    UEID
                </td>
                <td>
                    :
                </td>
                <td>
                    <% out.println(UserSession.getAttribute("UEID")); %>
                </td>
            </tr>
            <tr style="background-color: #e1e1f0">
                <td>
                    Examination ID
                </td>
                <td>
                    :
                </td>
                <td>
                    <% out.println(UserSession.getAttribute("ExamID")); %>
                </td>
            </tr>
            <tr style="background-color: #e1e1f0">
                <td>
                    User ID
                </td>
                <td>
                    :
                </td>
                <td>
                    <% out.println(UserSession.getAttribute("UserId")); %>
                </td>
            </tr>
            <tr style="background-color: #e1e1f0">
                <td>
                    Date
                </td>
                <td>
                    :
                </td>
                <td>
                    <% out.println(UserSession.getAttribute("ExamDate")); %>
                </td>
            </tr>
            <tr style="background-color: #a9a8f0">
                <td colspan="3" align ="center" style="font-size: 20px; font-weight: bold; padding: 15px">
                    EXAMINATION SUMMARY   
                </td>
            </tr>
            <tr style="background-color: #e1e1f0">
                <td>
                    Total Questions
                </td>
                <td>
                    :
                </td>
                <td>
                    25
                </td>
            </tr>
            <tr style="background-color: #e1e1f0">
                <td>
                    No. Attempted
                </td>
                <td>
                    :
                </td>
                <td>
                    <% out.println(UserSession.getAttribute("AttemptedCount")); %>
                </td>
            </tr>
            <tr style="background-color: #e1e1f0">
                <td>
                    Correct Answers
                </td>
                <td>
                    :
                </td>
                <td>
                    <% out.println(UserSession.getAttribute("CorrectAnsCount")); %>
                </td>
            </tr>
            <tr style="background-color: #e1e1f0">
                <td>
                    Score obtained
                </td>
                <td>
                    :
                </td>
                <td>
                    <% out.println(UserSession.getAttribute("ExamScore")); %> / 100
                </td>
            </tr>
            <tr style="background-color: #a9a8f0">
                <td colspan="3" align="center">
                    <a href="javascript:void(0)" style="padding: 15px" onclick="javascript:printCertificate()"><img src="images/print-certificate-button.png" style="border: 0"/></a>
            
                <a href="resultAnalysis.jsp" style="padding: 15px"><img src="images/answer-review.png" style="border: 0"/></a>
            
                <a href="home.jsp" style="padding: 15px"><img src="images/return-home.png" style="border: 0"/></a>
            </td>
            </tr>
        </table>
        <br/>
        <br/>
        
        <form name="printScoreCard" id="PrintScoreCard" action="CreateDownloadableTestReport" action="POST">
            
            
        </form>
    </center>
                    <%
                                       }
                    %>
    </body>
</html>
