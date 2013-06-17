<%-- 
    Document   : checkUser
    Created on : Jul 15, 2012, 2:43:31 AM
    Author     : Devanand
--%>
<%@ page import="java.sql.*" %> 
<%
String name = request.getParameter("name").toString();
System.out.println(name);
String data ="";
String dbUrl = "jdbc:odbc:Online_Exam_Portal";
//String dbClass = "com.mysql.jdbc.Driver";
try{
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               
        Connection con = DriverManager.getConnection (dbUrl,"","");
        Statement stmt = con.createStatement();
           ResultSet rs=stmt.executeQuery("select * from Login_Credentials where username='"+name+"'");
int count=0;
          while(rs.next())
          {

                   count++;
          }

                    if(count>0)
          {
             data="Not Available";
          }
          else
          {
                      data="Available";
          }
out.println(data);
//System.out.println(data);
}
catch (Exception e) {
System.out.println(e);
}
%>
    
