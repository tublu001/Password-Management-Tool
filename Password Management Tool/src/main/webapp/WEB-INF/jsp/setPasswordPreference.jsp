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
<%@ include file="header.jsp" %>
    <center> <h1> Set your password preference </h1> </center>
    <form name="passwordPreference" action="passwordPreference" method="post">
        <div class="container">
        <b>
            <input type="checkbox" name="includeUpperLetters" value="yes"> includeUpperLetters<BR>
            <input type="checkbox" name="includeLowerLetters" value="yes"> includeLowerLetters<BR>
            <input type="checkbox" name="includeNumbers" value="yes"> includeNumbers<BR>
            <input type="checkbox" name="includeSymbols" value="yes"> includeSymbols<BR>

            <select name="preferredPasswordLength" id="preferredPasswordLength">
                <optgroup label="Weak">
                  <option value="5">5</option>
                  <option value="8">8</option>
                </optgroup>
                <optgroup label="Medium">
                  <option value="12">12</option>
                  <option value="15">15</option>
                </optgroup>
                <optgroup label="Strong">
                  <option value="20">20</option>
                  <option value="25">25</option>
                </optgroup>
            </select>
              <br><br>

            <button type="reset" class="cancelbtn"><b>Clear</b></button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            <button type="submit"><b>Store</b></button><br>
            <div style="width: 20%"/>
            <a href="/PMT/crudMenu" >Click here </a> for Menu...
        </b>
        </div>
    </form>
</body>
</html>

