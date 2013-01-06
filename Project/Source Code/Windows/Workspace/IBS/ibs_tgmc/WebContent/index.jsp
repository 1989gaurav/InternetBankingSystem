<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Internet Banking System - Homepage</title>
</head>
<body>
<%String url="https://"+request.getServerName()+":8443/ibs/home.html";
response.sendRedirect(url);
%>
</body>
</html>