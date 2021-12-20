<%@ include file="header.jsp" %>
<body align="center">
    <center> <h1> Edit Account Credential </h1> </center>
    <form name="MasterUser" action="editAccount" method="post">
        <div class="container">
        <b>
            <label>Enter App Name : </label>
            <input type="text"  name="appName" value="${account.appName}" style="background: #ccc;cursor: not-allowed;border-width: 1px;" readonly>
            <label>Enter URL : </label>
            <input type="text" name="url" value="${account.url}" required>
            <label>Enter password : </label>
            <input type="password"  name="password" value="${account.password}" required>
            <label>Enter Group Name : </label>
            <input type="text" name="groupName" value="${account.accountGroup}" required>

            <button type="reset" class="cancelbtn"><b>Clear</b></button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            <button type="submit"><b>Update</b></button><br>
            <div style="width: 20%"/>
            <a href="/PMT/crudMenu" >Click here </a> for Menu...
        </b>
        </div>
    </form>
<%@ include file="footer.jsp" %>

