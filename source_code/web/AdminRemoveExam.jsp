<%-- 
    Document   : AdminRemoveExam
    Created on : Jul 21, 2012, 3:21:06 AM
    Author     : Devanand
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <link href="menuStyle.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
              
<%   
HttpSession Usersession = request.getSession(false);
if(Usersession.getAttribute("Username") == null || Usersession.getAttribute("Privilage") == null || !(Usersession.getAttribute("Privilage").toString().contains("adminUser")))
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
                          <form action="RemoveExam" id="RemoveExamForm" method="post">

        <h2 style="color: #4778e3">Active Exams Directory</h2>  
      
           <%
           
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             Connection con=DriverManager.getConnection("jdbc:odbc:Online_Exam_Portal","","");
             Statement ps=con.createStatement();

             ResultSet rs=ps.executeQuery("Select * from Exam_List"); 
             int ExamId;
             int bgcolorFlag = 0;

if(rs.next())
    {      
%>

        Please select an exam below and click Remove Exam to clear Examination Data<br/><br/> 
         <table cellspacing="0" cellpadding="15" border="0" width="600" >
             <tr style="background-color: #a9a8f0; font-size: 20px; font-weight: bold">
                 <td></td>
             <td><b>Exam ID</b></td>
             <td><b>Exam Name</b></td>
             </tr>
<%          do  
            {
            bgcolorFlag++;
           ExamId = rs.getInt("ExamId");
%>
     

                  <tr style="background-color:<% if((bgcolorFlag%2)==0) out.print("#cac9f2"); else out.print("#e1e1f0");
%>"> <td align="center">
                          <input type="radio" id="ExamIdToRemoveRadio" name="ExamIdToRemoveRadio" value="<% out.print(ExamId); %>"/>

 </td>
                          


                        <td><b><% out.println(ExamId); %></b><br>
                      <td><% out.println(rs.getString("ExamName")); %><br>
                       </td>
                       </tr>
                     


<%
           }while(rs.next());
                         
%>

         </table>
<br>
<b style="color: red;" align="center">
            <% if(Usersession.getAttribute("RemoveExamException")!= null)
                out.print(Usersession.getAttribute("RemoveExamException")); %></b>
        <br/>
<!--<i id="err"></i> -->
<a href="javascript:void(0)" onclick="javascript:document.getElementById('RemoveExamForm').submit()">
    <img src="images/Remove-Exam.png" style="margin-top:5px;margin-bottom: 5px; padding-top: 15px; border: 0"/></a>

    
   <!--  <a href="javascript:removeUser()">
         <img src="images/Remove.PNG" width="100" height="40" style="margin-top:5px;margin-bottom: 5px"/>
         </a>-->
          <a href="AdminConsoleHome.jsp">
         <img src="images/Cancel-Exam-Registration-btn.png" style="margin-top:5px;margin-bottom: 5px; padding-top: 15px; border: 0"/></a>
         
<%
}
             else
                                 {
%>     

               <h2 style="padding-top: 20px; color: #4778e3;">It seems we do not have any active exams right now...</h2>
<h4>You can add a new exam from the Add Exam Option in the Administration Console home page...</h4>
        

                            
                             
    <%
       }
             %>
     </form>
                        </td>
                    </tr>
                </table>
             <%
   }
%>      
    </body>
</html>
