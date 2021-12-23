<%@ include file="header.jsp" %>
<body align="center">
    <center> <h1> All Accounts </h1> </center>
<div class= "container">
        			<div class="wrap-table100">
        				<div class="table100">
            <table>
            <thead>
                <tr class="table100-head">
                 <th>App Name </th>
                 <th>URL</th>
                 <th>Group</th>
                 <th>Action</th>
                </tr>
            </thead>
                <tbody>
                 <c:forEach items="${user.getAccounts()}" var="account">
                    <tr>
                     <td><b> <c:out value="${account.appName}" ></c:out> </b></td>
                     <td><b> <c:out value="${account.url}" ></c:out> </b></td>
                     <td><b> <c:out value="${account.groupName}" ></c:out> </b></td>
                     <td>
                         <a href="editAccountCredential?appName=${account.appName}" ><i class='fas fa-edit' style="font-size:20px;color:black;"></i></a> &nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp
                         <a href="deleteAccountCredential?appName=${account.appName}" ><i class="fas fa-trash-alt" style="font-size:20px;color:black;"></i></a>
                     </td>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
    <br>
    <a href="crudMenu" >Click here </a> for Menu...
    </div>
<%@ include file="footer.jsp" %>

