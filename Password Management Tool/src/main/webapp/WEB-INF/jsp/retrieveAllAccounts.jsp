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
    <center> <h1> All Accounts </h1> </center>
    <form name="MasterUser" action="loginMaster" method="post">
        <div class="container">
            <table border=1>
                <tr>
                 <th>App Name </th>
                 <th>URL</th>
                 <th>Group</th>
                 <th>Action</th>
                </tr>

                 <c:forEach items="${accounts}" var="account">
                    <tr>
                     <td> <c:out value="${account.appName}" ></c:out> </td>
                     <td> <c:out value="${account.url}" ></c:out> </td>
                     <td> <c:out value="${account.accountGroup}" ></c:out> </td>
                     <td> <a href="deleteAccountCredential?appName=${account.appName}" >Delete</a> </td>
                    </tr>
                </c:forEach>
            </table>
            <a href="crudMenu" >Click here </a> for Menu...
        </div>
    </form>
</body>
</html>

