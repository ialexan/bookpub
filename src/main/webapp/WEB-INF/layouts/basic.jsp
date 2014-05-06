<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />

<title><tiles:insertAttribute name="title"
		defaultValue="BOOKPUB" defaultValueType="string" /></title>

<link href="<c:url value='/css/layout.css' />" type="text/css"
	rel="stylesheet" />
<link href="<c:url value='/css/style.css' />" type="text/css"
	rel="stylesheet" />

<link href="<c:url value='${jqueryui.css}' />" type="text/css"
	rel="stylesheet" />

<tiles:useAttribute id="cssUrls" name="cssUrls" ignore="true" />
<c:forEach items="${cssUrls}" var="cssUrl">
	<link href="<c:url value='${cssUrl}' />" type="text/css"
		rel="stylesheet" />
</c:forEach>

<script type="text/javascript" src="<c:url value='${jquery.url}' />"></script>

<script type="text/javascript" src="<c:url value='${jqueryui.url}' />"></script>

<script type="text/javascript"
	src="<c:url value='${webshims.lib}/extras/modernizr-custom.js' />"></script>

<script type="text/javascript"
	src="<c:url value='${webshims.lib}/polyfiller.js' />"></script>

<script type="text/javascript" src="<c:url value='/js/functions.js' />"></script>
<tiles:useAttribute id="jsUrls" name="jsUrls" ignore="true" />

<c:forEach items="${jsUrls}" var="jsUrl">
	<script type="text/javascript" src="<c:url value='${jsUrl}' />"> </script>
</c:forEach>

</head>


<body>


	<div id="bookpub_top">
		<tiles:insertAttribute name="header" defaultValue="header.jsp"
			defaultValueType="template" />
	</div>


	<div id="bookpub_container">
		<tiles:insertAttribute name="content" />
	</div>


	<div id="bookpub_ftr">
		<tiles:insertAttribute name="footer" defaultValue="footer.jsp"
			defaultValueType="template" />
	</div>


</body>
</html>
