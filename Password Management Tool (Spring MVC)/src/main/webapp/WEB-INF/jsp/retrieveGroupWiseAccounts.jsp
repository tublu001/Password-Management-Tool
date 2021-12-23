<%@ include file="header.jsp" %>
<body align="center">
    <center> <h1>Group Wise All Accounts </h1> </center>
    <form name="MasterUser" action="loginMaster" method="post">
        <div class="container">
        <div class="wrap-table100">
                				<div class="table100">
            <table border=1>
                <c:forEach items="${user.getGroups()}" var="group">
                <thead>
                    <tr class="table100-head">
                        <th colspan="4">"${group}"</th>
                    </tr>
                    <tr class="table100-head">
                        <th>App Name </th>
                        <th>URL</th>
                        <th>Group</th>
                        <th>Action</th>
                    </tr>
                </thead>
                    <c:forEach items="${user.getAccounts()}" var="account">
                        <c:if test = "${group.equals(account.accountGroup)}">
                            <tr>
                             <td><b> <c:out value="${account.appName}" ></c:out> </b></td>
                             <td><b> <c:out value="${account.url}" ></c:out> </b></td>
                             <td><b> <c:out value="${account.groupName}" ></c:out> </b></td>
                             <td>
                                 <a href="editAccountCredential?appName=${account.appName}" ><i class="fas fa-edit" style="font-size:20px;color:black;"></i></a> &nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp
                                 <a href="deleteAccountCredential?appName=${account.appName}" ><i class="fas fa-trash-alt" style="font-size:20px;color:black;"></i></a>
                             </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>

            </table>
            </div>
            </div>
            <a href="crudMenu" >Click here </a> for Menu...
        </div>
    </form>
<%@ include file="footer.jsp" %>

