<%@ include file="header.jsp" %>
<body align="center">
    <center> <h1> Master Registration Form </h1> </center>
    <form name="MasterUser" action="registerUser" method="post">
        <div class="container">
        <b>
            <label>Username : </label>
            <input type="text" placeholder="Enter Username" name="userName" required>
            <label>Password : </label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="reset" class="cancelbtn"><b>Clear</b></button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            <button type="submit"><b>Register</b></button><br>
            <div style="width: 20%"/>
            <a href="masterLogin" >Click here </a> to LogIn...
        </b>
        </div>
    </form>
<%@ include file="footer.jsp" %>

