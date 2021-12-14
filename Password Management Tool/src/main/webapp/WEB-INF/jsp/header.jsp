<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<link href="/css/style.css" rel="stylesheet">
<title>Password Management Tool</title>
</head>
<table width="100%" cellpadding="5" cellspacing="0" >
 <tr bgcolor="#EDDD9E">
 <td width="280" valign="top">
 <a href="/PMT/">
 <img src="../images/logo1.png" width="250" height="120">
</a>
</td>
 <td valign="top"><h1 align="right"><c:out value="Master User:- ${user.userName}" ></c:out></h1>
 <h3 align="right">PMT V2.1</h3></td>
 </tr>
 </table>