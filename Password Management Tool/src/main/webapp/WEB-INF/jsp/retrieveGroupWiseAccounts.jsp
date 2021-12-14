<%@ include file="header.jsp" %>
<body align="center">
    <center> <h1>Group Wise All Accounts </h1> </center>
    <form name="MasterUser" action="loginMaster" method="post">
        <div class="container">
            <table border=1>
                <c:forEach items="${user.getGroups()}" var="group">
                    <tr>
                        <th colspan="4">"${group}"</th>
                    </tr>
                    <tr>
                        <th>App Name </th>
                        <th>URL</th>
                        <th>Group</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach items="${user.getAccounts()}" var="account">
                        <c:if test = "${group.equals(account.accountGroup)}">
                            <tr>
                             <td> <c:out value="${account.appName}" ></c:out> </td>
                             <td> <c:out value="${account.url}" ></c:out> </td>
                             <td> <c:out value="${account.accountGroup}" ></c:out> </td>
                             <td> <a href="delete?${student.id}" >Delete</a> </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>

            </table>
            <a href="crudMenu" >Click here </a> for Menu...
        </div>
    </form>
<%@ include file="footer.jsp" %>

