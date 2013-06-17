/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var PasswordFlag=0;
var UsernameFlag=0;
var AgeFlag=0;
var NameFlag=0;
var EmailFlag=0;
var PhoneFlag=0;

var isValidAge = /^(([1]{1}[3-9]{1})|([23456789]{1}[0-9]{1}))$/;
var isValidName = /^[a-zA-Z]+(\s[a-zA-Z]+)*$/;
var isValidEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
var isValidPhone = /^[0-9]{7,12}$/;

function validateName()
    {
var name = document.getElementById("txtName").value;
        
        if(name=="")
        {
            document.getElementById("nameError").innerHTML="Please enter your full name here.";
            NameFlag=1;
        } 
        else if(document.getElementById("txtName").value.search(isValidName))
            {
                document.getElementById("nameError").innerHTML="Name may only contain alphabets...";
                NameFlag=1;
            }
        else
    {
        document.getElementById("nameError").innerHTML="";
        NameFlag=0;
    }
    }

function validateAge()
    {
var age = document.getElementById("txtAge").value;
    if(age=="")
        {
            document.getElementById("ageError").innerHTML="Please enter your age here.";
            AgeFlag=1;
        } 
        else if(document.getElementById("txtAge").value.search(isValidAge))
            {
                document.getElementById("ageError").innerHTML="Age must be between 13 to 99";
                AgeFlag=1;
            }
        else
    {
        document.getElementById("ageError").innerHTML="";
        AgeFlag=0;
    }    

    var iAge=0;
    iAge = Integer.parseInt(age.toString());
    if(iAge>0)
        {
            document.getElementById("ageError").innerHTML="";
            AgeFlag=0;
        }
else
    {
        document.getElementById("ageError").innerHTML="Please enter a valid age...";
        AgeFlag=1;
    }
}
    
function validateAddress()
    {
var address = document.getElementById("txtAddress").value;
        
        if(address=="")
        {
            document.getElementById("addressError").innerHTML="Please enter your address here.";
        }    
        else
    {
        document.getElementById("addressError").innerHTML="";
    }
    }
    
function validatePhone()
    {
var phone = document.getElementById("txtPhone").value;
        
        if(phone=="")
        {
            document.getElementById("phoneError").innerHTML="Please enter a valid Phone no.";
            PhoneFlag=1;
        }
        else if(document.getElementById("txtPhone").value.search(isValidPhone))
            {
                document.getElementById("phoneError").innerHTML="Phone no. must be 7 to 12 digits long";
                PhoneFlag=1;
            }
        else
    {
        document.getElementById("phoneError").innerHTML="";
        PhoneFlag=0;
    }
    }
    
function validateEmail()
    {
        var email = document.getElementById("txtEmail").value;
        
        if(email=="")
        {
            document.getElementById("emailError").innerHTML="Please enter a valid Email ID";
            EmailFlag=1;
        }
        else if(document.getElementById("txtEmail").value.search(isValidEmail))
            {
                document.getElementById("emailError").innerHTML="Please enter a valid Email ID...";
                EmailFlag=1;
            }
        else
    {
        document.getElementById("emailError").innerHTML="";
        EmailFlag=0;
    }
    }
    
function validatePassword()
    {
        //var password ="";
        //var confirmPassword = "";
        
        var password = document.getElementById("txtPassword").value;
        var confirmPassword = document.getElementById("txtConfirmPassword").value;
        
        if(password != confirmPassword)
        {
            document.getElementById("passwordError").innerHTML="The passwords do not match!!";//+password+" >> "+confirmPassword.toString();
            document.getElementById("txtPassword").value="";
            document.getElementById("txtConfirmPassword").value="";
            PasswordFlag=1;
        }
        else
        {
            document.getElementById("passwordError").innerHTML="";
            PasswordFlag=0;
        }
    }
    
function registerUser()
    {
        var name = document.getElementById("txtName").value;
        var age = document.getElementById("txtAge").value;
        var address = document.getElementById("txtAddress").value;
        var phone = document.getElementById("txtPhone").value;
        var email = document.getElementById("txtEmail").value;
        var username = document.getElementById("txtUsername").value;
        var password = document.getElementById("txtPassword").value;
        var confirmPassword = document.getElementById("txtConfirmPassword").value;
        
        if(name=="")
            {
                document.getElementById("errMain").innerHTML="Name field may not be left blank...!!";
            }
            else if(age=="")
                {
                    document.getElementById("errMain").innerHTML="Age field may not be left blank...!!";
                }
                else if(address=="")
                    {
                        document.getElementById("errMain").innerHTML="Address field may not be left blank...!!";
                    }
                    else if(phone=="")
                    {
                        document.getElementById("errMain").innerHTML="Phone No. field may not be left blank...!!";
                    }
                    else if(email=="")
                    {
                        document.getElementById("errMain").innerHTML="Email ID field may not be left blank...!!";
                    }
        else if(username=="")
          {
              document.getElementById("errMain").innerHTML="Username field may not be left blank...!!";
          }
          else if(UsernameFlag==1)
              {
                  document.getElementById("errMain").innerHTML="The username you entered has already been taken...";
              }
        else if(PasswordFlag==1)
            {
                document.getElementById("errMain").innerHTML="Please check the password you have entered...";
            }
        else if(password == "")
            {
                document.getElementById("errMain").innerHTML="Password may not be left blank...!!";
            }
            else if(confirmPassword == "")
            {
                document.getElementById("errMain").innerHTML="Please confirm the password you have entered...";
            }
            else if(AgeFlag==1)
                {
                    document.getElementById("errMain").innerHTML="Please check the age you have entered...";
                }
                else if(PhoneFlag==1)
                    {
                        document.getElementById("errMain").innerHTML="Please check the phone no. you have entered...";
                    }
                else if(EmailFlag==1)
                    {
                        document.getElementById("errMain").innerHTML="Please check the Email ID you have entered...";
                    }
           
        else
            {
                 document.getElementById('registerForm').submit();
            }
    }
     
function check(value){ 
xmlHttp=GetXmlHttpObject()
var url="checkUser.jsp";
url=url+"?name="+value;
xmlHttp.onreadystatechange=stateChanged 
xmlHttp.open("GET",url,true)
xmlHttp.send(null)
}
function stateChanged(){ 
if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){ 
    var showdata = xmlHttp.responseText; 
    
    document.getElementById("usernameError").innerHTML= showdata;
    } 
    if(showdata.toString().contains("Available"))
    {
        UsernameFlag=1;
    }
    else
        {
            UsernameFlag=0;
        }
}
function GetXmlHttpObject(){
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

