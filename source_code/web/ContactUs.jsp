<%-- 
    Document   : ContactUs
    Created on : Jul 21, 2012, 2:14:47 AM
    Author     : Devanand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Us - Online Examination Portal</title>
   <link href="menuStyle.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
      <%   
HttpSession Usersession = request.getSession(false);
if(Usersession.getAttribute("Username") == null || Usersession.getAttribute("Privilage") == null)
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
     out.println("<li><a href = 'AdminConsoleHome.jsp'>Administration Console</a></li>");
 }
                %>
                <li><a href="main.jsp">Available Exams</a></li>
                <li><a href="PreviousResults.jsp">Previous Results</a></li>
                <li id="active"><a href="ContactUs.jsp">Contact Us</a></li>
                <li><a href="Logout.jsp">Logout</a></li>
		</ul>
	</div>    

                <table border="0" width="950">
                    <tr>
                        <td>
                           
				
				<h2 class="Page-Heading" style="font-size: 34px;padding-bottom: 15px">Contact Us</h2>
                                Feel free to reach out us at any time to clear your queries at...<br/><br/>
					 5/8,Kanoor Kalam<br>
                     Pudunagaram<br>
                     Palakkad Dist<br>
                     678503<br />
                     
                     <b> Email : </b>helpme@toptrigger.com<br>
                     <b> Phone : </b>04923-222222

				  <br />
				  	
					
					</p>
 
                            
                        </td>
                    </tr>
                </table>
                  <%
   }
%>      
    </body>
</html>
