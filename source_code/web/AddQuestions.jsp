<%-- 
    Document   : AddQuestions
    Created on : Jul 21, 2012, 8:24:23 PM
    Author     : Devanand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Questions - Administration Console</title>
        <link href="menuStyle.css" rel="stylesheet" type="text/css" />
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
                            
<form id="QuestionEntryForm" action="ProcessQuestion" method="post">

<h3 style="color: #4778e3">QUESTION ENTRY FORM</h3>
<table border="0" cellpadding="10" cellspacing="0" style="width: 950px">
    <tr>
        <td>
            Question <% out.print(Usersession.getAttribute("RegisterQNo")); %> of 25
        </td>
        <td style="color: red;" align="center">
            <% if(Usersession.getAttribute("QuestionEntryException")!= null)
                out.print(Usersession.getAttribute("QuestionEntryException")); %>
        </td>
        <td align="right">
            Exam ID : <% out.print(Usersession.getAttribute("NewExamId")); %>
        </td>
    </tr>
</table>
<table border ="0" cellpadding="10" cellspacing="0" style="width: 950px">
    <tr>
        <td colspan="2">
            <b>Question :</b><br/>
            <textarea rows="4" cols="100" name="addNewQuestion" id="txtexamdescription" style="width: 900px"><% if(Usersession.getAttribute("QuestionEnteredValue")!= null)
                out.print(Usersession.getAttribute("QuestionEnteredValue")); %></textarea>
        </td>
    </tr>
    <tr>
        <td>
            <b>Option A :</b><br/>
            <textarea rows="3" cols="100" name="addOptionA" id="txtexamdescription" style="width: 400px"><% if(Usersession.getAttribute("OptionAEnteredValue")!= null)
                out.print(Usersession.getAttribute("OptionAEnteredValue")); %></textarea>
        </td>
        <td>
            <b>Option B :</b><br/>
            <textarea rows="3" cols="100" name="addOptionB" id="txtexamdescription" style="width: 400px"><% if(Usersession.getAttribute("OptionBEnteredValue")!= null)
                out.print(Usersession.getAttribute("OptionBEnteredValue")); %></textarea>
        </td>
    </tr>
    <tr>
        <td>
            <b>Option C :</b><br/>
            <textarea rows="3" cols="100" name="addOptionC" id="txtexamdescription" style="width: 400px"><% if(Usersession.getAttribute("OptionCEnteredValue")!= null)
                out.print(Usersession.getAttribute("OptionCEnteredValue")); %></textarea>
        </td>
        <td>
            <b>Option D :</b><br/>
            <textarea rows="3" cols="100" name="addOptionD" id="txtexamdescription" style="width: 400px"><% if(Usersession.getAttribute("OptionDEnteredValue")!= null)
                out.print(Usersession.getAttribute("OptionDEnteredValue")); %></textarea>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <b>Choose Correct Answer:</b>
            <br/>
            <input type="radio" name="CorrectAnswerRadio" value="OptionA"> Option A  
            <input type="radio" name="CorrectAnswerRadio" value="OptionB"> Option B  
            <input type="radio" name="CorrectAnswerRadio" value="OptionC"> Option C  
            <input type="radio" name="CorrectAnswerRadio" value="OptionD"> Option D  
        </td>
    </tr>
</table>
        <a href="javascript:void(0)" 
           onclick="javascript:document.getElementById('QuestionEntryForm').submit()"><img src="images/next-question-btn.png" style="border: 0"/></a>
</form>
    
</td>
</tr>
</table>
    <%
   }
%>         
    </body>
</html>
