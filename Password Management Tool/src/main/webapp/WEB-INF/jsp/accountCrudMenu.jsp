<%@ include file="header.jsp" %>
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
<%@ include file="footer.jsp" %>

