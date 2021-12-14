<%@ include file="header.jsp" %>
<body align="center">
    <center> <h1> Master Login Form </h1> </center>
    <form name="MasterUser" action="loginMaster" method="post">
        <div class="container">
        <b>
            <label>Username : </label>
            <input type="text" placeholder="Enter Username" name="username" required>
            <label>Password : </label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="reset" class="cancelbtn"><b>Clear</b></button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            <button type="submit"><b>Login</b></button><br>
            <div style="width: 20%"/>
            <a href="masterSignUp" >Click here </a> to SignUp...
        </b>
        </div>
    </form>
<%@ include file="footer.jsp" %>

