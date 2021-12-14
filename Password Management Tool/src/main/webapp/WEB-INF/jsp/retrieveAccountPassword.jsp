<%@ include file="header.jsp" %>
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
              <button type="submit"><b>Get Password</b></button><br>
            <a href="crudMenu" >Click here </a> for Menu...
        </div>
    </form>
<%@ include file="footer.jsp" %>
