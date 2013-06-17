<%-- 
    Document   : register
    Created on : Jun 28, 2012, 11:04:59 AM
    Author     : Devanand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Online Examination Portal - New User Registration</title>

<link href="login-box.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"
    src="scripts/userRegistrationValidation.js">
</script>
<script type="text/javascript">
   
</script>
</head>

<body> 
<div style="padding: 10px 0 0 100px;">
<form name="registerForm" id="registerForm" method="post" action="RegisterNewUser">
<table width="100%" id="register-box">
    <tbody>
      <tr>
        <td><span style="font-family: arial,verdana; font-size: 10pt;">
<h2>Please fill out the form below to register yourself:</h2>
           

            <div>
              
                  <table cellspacing="1" cellpadding="3" border="0">
                  <tbody>
                       <tr>
                      <td id="login-box-name">Name:</td>
                      <td>
                        <input type="text" class="form-login" name="name" id="txtName" onChange="validateName()" />
                        <i id="nameError"></i>
                        </td>
                    </tr>
                     <tr>
                      <td id="login-box-name">Age:</td>
                      <td>
                          <input type="text" class="form-login" name="age" id="txtAge" onchange="validateAge()"  />
                        <i id="ageError"></i>
                      </td>
                    </tr>
                    <tr>
                      <td id="login-box-name">Address:</td>
                      <td>
                          <input type="text" class="form-login" name="address" id="txtAddress" onchange="validateAddress()"  />
                      <i id="addressError"></i>
                      </td>
                    </tr>
                   <tr>
                      <td id="login-box-name">Phone No.:</td>
                      <td>
                          <input type="text" class="form-login" name="phoneno" id="txtPhone" onchange="validatePhone()"  />
                      <i id="phoneError"></i>
                      </td>
                    </tr>
                     <tr>
                      <td id="login-box-name" >Email:</td>
                      <td>
                        <input type="text" class="form-login" name="email" id="txtEmail" onchange="validateEmail()"  />
                      <i id="emailError"></i>
                      </td>
                    </tr>

                   <tr>
                      <td id="login-box-name"> Username:</td>
                      <td>
                          <input type="text" class="form-login" name="username" id="txtUsername" onchange="check(this.value)" />
                      <i id="usernameError"></i>
                      </td>
                    </tr>
                    <tr>
                      <td id="login-box-name">Password:</td>
                      <td>
                          <input type="password" class="form-login" id="txtPassword" name="password" />
                       <i id="passwordError"></i>
                      </td>
                    </tr>
                    <tr>
                      <td id="login-box-name">Confirm Password:</td>
                      <td>
                          <input type="password" class="form-login" name="password2" id="txtConfirmPassword" onchange="validatePassword()" />
                     <i id="confirmpasswordError"></i>
                      </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><i id="errMain"></i></td>
                    </tr>
                         <tr>
                      <td></td>
                      <td>
                          <a href="javascript:registerUser()">
                            <img src="images/register-btn.PNG" width="103" height="42" style="margin-left:0px; border: 0" /></a>
                        
                        <a href="index.html"><img src="images/cancel-btn.PNG" width="103" height="42" 

style="margin-left:10px; border: 0" /></a></td>
                    </tr>
                  </tbody>
                </table>
              </div></span></td>
      </tr>
    </tbody>
  </table>
</form>
</div>
</body>
</html>