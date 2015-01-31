<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="statics/css/default.css" />" rel="stylesheet">
<title>Curriculum</title>
</head>
<body>
	<div id="toFormat">${curriculum}</div>
	<script src="<c:url value="statics/js/formatter.js" />"></script>
	<script src="<c:url value="statics/js/default.js" />"></script>
</body>
</body>
</html>