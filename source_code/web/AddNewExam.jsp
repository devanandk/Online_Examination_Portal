<%-- 
    Document   : AddNewExam
    Created on : Jul 15, 2012, 2:27:54 AM
    Author     : Devanand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Exam - Online Examination Portal</title>
        <link href="menuStyle.css" rel="stylesheet" type="text/css" />
        
        <script type="text/javascript" src="scripts/addExamValidation.js"></script>
    </head>
    <body>
                     
<%   
HttpSession Usersession = request.getSession(false);
if(Usersession.getAttribute("Username") == null)
       {
   response.sendRedirect("index.html");
      

}
else if(Usersession.getAttribute("Privilage") == null)
       {
    response.sendRedirect("index.html");
}
else if(!(Usersession.getAttribute("Privilage").toString().contains("adminUser")))
       {
    response.sendRedirect("index.html");
}
else
       {
    %>
    <h2 class="Page-Heading">Online Examination Portal</h2>  
    <%
    out.println("<b>Welcome "+Usersession.getAttribute("Username")+"...</b>");
%>
<div id="navigation">
			<ul>
            	<li><a href="home.jsp">Home</a></li>
                <%
                if(Usersession.getAttribute("Privilage").toString().contains("adminUser"))
         {
     out.println("<li id='active'><a href = 'AdminConsoleHome.jsp'>Administration Console</a></li>");
 }
                %>
               <li><a href="main.jsp">Available Exams</a></li>
                <li><a href="PreviousResults.jsp">Previous Results</a></li>
                <li><a href="ContactUs.jsp">Contact Us</a></li>
                <li><a href="Logout.jsp">Logout</a></li>
		</ul>
	</div>    
<table border="0" width="950">
                    <tr>
                        <td align ="center">
<form name="RegisterNewExamForm" id="RegisterNewExamForm" action="RegisterNewExamination" method="POST">
<table cellpadding ="10" cellspacing="0" border ="0" style="padding-top: 40px; border: none"> 
    <th colspan ="2" align="center">
    <h2 style="color: #4778e3;">
        New Exam Registration
    </h2>
    </th>
    <tr>
        <td style="color: #4778e3;">
            <b>Exam ID</b>
        </td>
        <td>
            <input type="text" onchange="checkExamId(this.value)" name="examid" id="ExamId" value=""/><b id="ExamIdError"> Enter a 5 digit Unique ID</b>
            <input type="hidden" name="ExamIdStatus" id="IdStatus"/>
        </td>
    </tr>
    <tr>
        <td style="color: #4778e3;">
            <b> Exam Name</b>
        </td>
        <td>
            <input type="text" name="examname" id="ExamName" value="" onclick="validateExamName()" style="width: 400px"/>
        </td>
    </tr>
    <tr>
        <td style="color: #4778e3;">
            <b> Exam Description</b>
        </td>
        <td>
            <textarea rows="10" cols="50" name="examdescription" id="txtexamdescription" style="width: 400px"></textarea>
        </td>
    </tr>
    
    <tr>
        <td></td>
        <td>
            <b style="padding-left: 55px;padding-top: 5px; padding-bottom: 15px"><% 
            if(Usersession.getAttribute("ExamCreationError")!= null)
            out.print(Usersession.getAttribute("ExamCreationError")); %></b><br/>
            <a href="javascript:void(0)" onclick="javascript:document.getElementById('RegisterNewExamForm').submit()">
                <img src="images/Register-Exam-btn.png" width="160" height="42" style="margin-top:10px;; border: 0" /></a>
                    <a href="AdminConsoleHome.jsp">
                        <img src="images/Cancel-Exam-Registration-btn.png" width="160" height="42" style="margin-top:10px; border: 0" /></a>
         </td>
    </tr> 
    
</table>
    </form>
        </td>
        </tr>
                </table>
    <%
   }
%>         

    </body>
</html>
