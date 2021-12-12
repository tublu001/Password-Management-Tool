<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <center> <h1> Store New Account Credential </h1> </center>
    <form name="MasterUser" action="storeAccount" method="post">
        <div class="container">
        <b>
            <label>Enter App Name : </label>
            <input type="text" placeholder="Enter App Name" name="appName" required>
            <label>Enter URL : </label>
            <input type="text" placeholder="Enter URL" name="url" required>
            <label>Enter password : </label>
            <input type="password" placeholder="Enter password" name="password" required>
            <label>Enter Group Name : </label>
            <input type="text" placeholder="Enter Group Name" name="accountGroup" required>

            <button type="reset" class="cancelbtn"><b>Clear</b></button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            <button type="submit"><b>Store</b></button><br>
            <div style="width: 20%"/>
            <a href="/PMT/crudMenu" >Click here </a> for Menu...
        </b>
        </div>
    </form>
</body>
</html>

