<%@ include file="header.jsp" %>
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
            <input type="text" placeholder="Enter Group Name" name="groupName" required>

            <button type="reset" class="cancelbtn"><b>Clear</b></button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            <button type="submit"><b>Store</b></button><br>
            <div style="width: 20%"/>
            <a href="/PMT/crudMenu" >Click here </a> for Menu...
        </b>
        </div>
    </form>
<%@ include file="footer.jsp" %>

