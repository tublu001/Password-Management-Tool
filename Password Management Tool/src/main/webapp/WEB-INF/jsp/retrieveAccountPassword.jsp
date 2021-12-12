<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<title>Password Management Tool</title>
<style>
Body {
  font-family: Calibri, Helvetica, sans-serif;
  background-color: pink;
}
button {
       background-color: #4CAF50;
       width: 25%;
        color: black;
        padding: 15px;
        border: none;
        cursor: pointer;
         }
 form {
        border: 3px;
    }
 input[type=text], input[type=password] {
        width: 100%;
        margin: 8px 0;
        padding: 12px 20px;
        display: inline-block;
        border: 2px solid green;
        box-sizing: border-box;
    }
 button:hover {
        opacity: 0.7;
    }
  .cancelbtn {
        background-color: #4CAF50;
               width: 25%;
                color: black;
                padding: 15px;
                border: none;
                cursor: pointer;
    }


 .container {
        margin: auto;
        width: 50%;
        border: 3px solid green;
        padding: 25px;
        background-color: lightblue;
    }
</style>
</head>
<body align="center">
    <center> <h1> Retrieve Password Accounts </h1> </center>
    <form name="fetchPassword" action="retrieveAccountPassword" method="post">
        <div class="container">
            <input list="appName" name="appName" placeholder="Select app">
              <datalist id="appName">
                  <c:forEach items="${user.getAccounts()}" var="account">
                        <option value="${account.appName}">
                  </c:forEach>
              </datalist>
              <input type="submit">
            <a href="crudMenu" >Click here </a> for Menu...
        </div>
    </form>
</body>
</html>

