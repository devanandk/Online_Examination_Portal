/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var ExamFlag = 0;
var ExamNameFlag = 0;
var submitflag = 0;
var examId = document.geElementById("ExamId");
var examName = document.getElementById("ExamName");

function registerNewExam()
{
    
    document.getElementById("ExamError").innerHTML = "Inside registerNewExam()";
    if(ExamFlag==1)
    {
     document.getElementById("ExamIdError").innerHTML = "Please check the Exam ID you have entered and try again"; 
  }
    else if(examId.value=="")
        {
            document.getElementById("ExamIdError").innerHTML = "Please enter a valid Exam ID";
        }
     else if(examName.value == "")
          {
                document.getElementById("ExamError").innerHTML = "Please enter a valid Exam Name";
            }
    else
     {
            document.getElementById('RegisterNewExamForm').submit();
      }
}

function validateExamName()
{
    if(examName.value=="")
        {
            document.getElementById("ExamError").innerHTML = "Please enter a Name for the Exam";
        }
}

function checkExamId(value)
{ 
xmlHttp=GetXmlHttpObject()
var url="checkExamId.jsp";
url=url+"?name="+value;
if(value == "")
    {
        document.getElementById("ExamIdError").innerHTML = "Please enter an Exam ID";
    }
xmlHttp.onreadystatechange=stateChanged 
xmlHttp.open("GET",url,true)
xmlHttp.send(null)
}

function stateChanged()
{ 
if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
{ 
    var showdata = xmlHttp.responseText;     

    document.getElementById("ExamIdError").innerHTML= showdata;  
    document.getElementById("IdStatus").value = showdata;
  }  
    if(showdata.contains("Available"))
    {
        ExamFlag=0;
    }
    else
    {
        ExamFlag=1;
    }
      

}

function GetXmlHttpObject()
{
var xmlHttp=null;
try{
  xmlHttp=new XMLHttpRequest();
 }
catch (e) {
 try {
  xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
  }
 catch (e){
  xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
 }
return xmlHttp;
}

