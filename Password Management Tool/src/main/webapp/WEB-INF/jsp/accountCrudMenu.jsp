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
    <center> <h1> Welcome to PMT </h1> </center>
    <form name="UserCrudMenu" action="UserCrudForm" method="post">
        <div class="container">
             <input type="radio" id="StoreAccount" name="selection" value="storeNewAccount"  align="left">
                <label for="storeNewAccount">Store A New Account</label><br>

             <input type="radio" id="retrieveAllAccounts" name="selection" value="retrieveAllAccounts"  align="left">
                 <label for="retrieveAllAccounts">Manage All Accounts</label><br>

             <input type="radio" id="retrieveGroupWiseAccounts" name="selection" value="retrieveGroupWiseAccounts"  align="left">
                 <label for="retrieveGroupWiseAccounts">Retrieve Group Wise Accounts</label><br>

             <input type="radio" id="retrieveAccountPassword" name="selection" value="retrieveAccountPassword"  align="left">
                 <label for="retrieveAccountPassword">Retrieve Account Password</label><br>

             <input type="radio" id="renameGroupName" name="selection" value="renameGroupName">
                 <label for="renameGroupName">Rename Group Name</label><br>
<!--
             <input type="radio" id="deleteAccountCredential" name="selection" value="deleteAccountCredential">
                 <label for="deleteAccountCredential">Delete Account Credential</label><br>
-->

             <input type="radio" id="setPasswordPreference" name="selection" value="setPasswordPreference">
                 <label for="setPasswordPreference">Set Password Preference</label><br>
          <input type="submit" value="Submit">
        </div>
    </form>
</body>
</html>

