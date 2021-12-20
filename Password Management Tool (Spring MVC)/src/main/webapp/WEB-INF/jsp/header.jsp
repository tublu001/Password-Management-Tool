<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<link href="/css/style.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/css/util.css">
	<link rel="stylesheet" type="text/css" href="/css/main.css">
	<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<!--===============================================================================================-->
<title>Password Management Tool</title>
</head>
<table width="100%" cellpadding="10" cellspacing="0" >
 <tr bgcolor="#EDDD9E">
 <td width="280" valign="center">
 <a href="/PMT/">
 <img src="../images/logo1.png" width="100%" height="100%">
</a>
</td>
 <td valign="top">
 <c:if test="${user.userName!=null}"><font color="purple">
    <h2 align="right"> <font color="purple"> <c:out value="Master User:"></c:out> </font><font color="blue"> <c:out value="${user.userName}" ></c:out> </font> </h2>
 </c:if>
 <c:if test="${user.userName==null}">
    <h1 align="right"><font color="purple"> <c:out value="Welcome To Password Management System" ></c:out> </font></h1>
    <h3 align="right">PMT V2.1</h3>
 </c:if>
<c:if test="${user.userName!=null}">
    <h3 align="right"><a href="logout">Logout</a></h3>
</c:if>
</td>
 </tr>
 </table>