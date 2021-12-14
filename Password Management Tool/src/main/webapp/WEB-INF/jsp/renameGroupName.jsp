<%@ include file="header.jsp" %>
<body align="center">
    <center> <h1> Rename Group Name </h1> </center>
    <form name="renameGroupName" action="renameGroupName" method="post">
        <div class="container">
            <input list="oldGroupName" name="oldGroupName" placeholder="Select group">
              <datalist id="oldGroupName">
                  <c:forEach items="${user.getGroups()}" var="group">
                        <option value="${group}">
                  </c:forEach>
              </datalist>
              <input type="text" placeholder="Give a new Group Name" name="newGroupName" required>
              <button type="submit"><b>Rename Group</b></button><br>
            <a href="crudMenu" >Click here </a> for Menu...
        </div>
    </form>
<%@ include file="footer.jsp" %>

