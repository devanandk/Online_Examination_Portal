<%-- 
    Document   : checkExamId
    Created on : Jul 19, 2012, 5:18:16 PM
    Author     : Devanand
--%>
<%@ page import="java.sql.*" %> 
<%
String name = request.getParameter("name").toString();
System.out.println(name);
String data ="";
 boolean isInputNumOnly = false;
String dbUrl = "jdbc:odbc:Online_Exam_Portal";
//String dbClass = "com.mysql.jdbc.Driver";
try{
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               
           isInputNumOnly = name.matches("^[0-9]{5}$");
           if(isInputNumOnly)
                             {
        Connection con = DriverManager.getConnection (dbUrl,"","");
        Statement stmt = con.createStatement();
           ResultSet rs=stmt.executeQuery("select * from Exam_List where ExamId="+name+"");
int count=0;
          while(rs.next())
          {

                   count++;
          }

                    if(count>0)
          {
             data="Exam ID already exists.";
          }
          else
          {
                      data="Available";
          }
           }
else
       {
    data = "Exam ID must be have 5 digits";
}
out.println(data);
//System.out.println(data);
}
catch (Exception e) {
out.println(e.getMessage()+isInputNumOnly);
}
%>