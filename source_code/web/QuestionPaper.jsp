<%-- 
    Document   : QuestionPaper
    Created on : Jul 18, 2012, 2:30:17 AM
    Author     : Devanand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question Paper</title>
        <% 
        try{
        HttpSession UserSession = request.getSession(false);
        int iQuestionNo = 0;
        Cookie[] ExamCookies = request.getCookies();
            for(int cookieCount = 0; cookieCount < ExamCookies.length; cookieCount++)
            {
                if(ExamCookies[cookieCount].getName().equals("QuestionNo"))
                {
                    iQuestionNo = Integer.parseInt(ExamCookies[cookieCount].getValue());
                }
            }
        String SelectedOption = "";
        String OptionName= "Question"+iQuestionNo+"SelectedOption";
        if(UserSession.getAttribute(OptionName)!=null)
                   {
            SelectedOption = UserSession.getAttribute(OptionName).toString();
                   }
      
        %>
        <script type="text/javascript">
            function setCookie(c_name,value,exdays)
{
var exdate=new Date();
exdate.setDate(exdate.getDate() + exdays);
var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
document.cookie=c_name + "=" + c_value;
}

function getCookie(c_name)
{
var i,x,y,ARRcookies=document.cookie.split(";");
for (i=0;i<ARRcookies.length;i++)
{
  x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
  y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
  x=x.replace(/^\s+|\s+$/g,"");
  if (x==c_name)
    {
        document.getElementById("QuestionNoSpace").innerHTML = unescape(y);
    }
  }
}
        </script>
    </head>
    <body>
        <form name="QuestionPaperForm" id="QuestionPaperForm" action="RunExam" method="POST">
            <b id="QuestionNoSpace"><script> getCookie("QuestionNo"); </script>. </b>
            <span id="QuestionSpace" style="width: 100%"><% out.print(UserSession.getAttribute("CurrentQuestion")); %></span>
        <br/><br/>
        
        <input type="radio" name="OptionsRadio" id="OptionsRadio" value="OptionA"
              <% 
              if(SelectedOption.contains("OptionA"))
                       {
            out.print("checked='checked'");
                        }
              
            %>
               />
            <span id="OptionA-Space"><% out.print(UserSession.getAttribute("OptionA")); %></span><br/><br/>
            
        <input type="radio" name="OptionsRadio" id="OptionsRadio" value="OptionB"
               <% 
              if(SelectedOption.contains("OptionB"))
                       {
            out.print("checked='checked'");
                        }
            %>
            />
            <span id="OptionB-Space"><% out.print(UserSession.getAttribute("OptionB")); %></span><br/><br/>
            
        <input type="radio" name="OptionsRadio" id="OptionsRadio" value="OptionC"
               <% 
              if(SelectedOption.contains("OptionC"))
                       {
            out.print("checked='checked'");
                        }
            %>
            />
            <span id="OptionC-Space"><% out.print(UserSession.getAttribute("OptionC")); %></span><br/><br/>
            
        <input type="radio" name="OptionsRadio" id="OptionsRadio" value="OptionD"
               <% 
              if(SelectedOption.contains("OptionD"))
                       {
            out.print("checked='checked'");
                        }
                       
            %>
            />
            <span id="OptionD-Space"><% out.print(UserSession.getAttribute("OptionD")); %></span><br/><br/>
        </form>
   <!--     <br/><br/><span id="Debug-Space">
            <% 
            //out.println(""
             //       + "Question No. : "+iQuestionNo+""
              //      + "<br>Value Obtained :  "+UserSession.getAttribute(OptionName)+""
                //    + "<br>Option Name : "+OptionName); 
            %>
        </span><br/><br/>
            -->
        <%
 }
catch(Exception err)
{
   err.printStackTrace();
}
%>
    </body>
</html>
