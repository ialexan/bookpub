<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Bookpub</title>
</head>
<body>

	<security:authorize access="anonymous">
		<form name="login" action="<c:url value='/j_spring_security_check' />"
			method="post">
			<table border="1">
				<tr>
					<td>Email:</td>
					<td><input type="text" name="j_username" size="30" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password" size="30" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="checkbox"
						name="_spring_security_remember_me" /> Remember me on this
						computer.</td>
				</tr>
				<tr>
					<td colspan="2"><input type="checkbox" name="mobile" />
						Simulate mobile.</td>
				</tr>
				<tr>
					<td colspan="2"><input class="submit" type="submit"
						name="login" value="Login" /></td>
				</tr>
			</table>
		</form>
	</security:authorize>

	<security:authorize access="authenticated">
		<p>
			<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
		</p>
	</security:authorize>

	<h2>Book Services</h2>
	<ul>
		<li>Retrieve all books: <a href="service/books">/service/books</a></li>
		<li>Retrieve a book: <tt>/service/book/{id}</tt>, e.g. <a
			href="service/book/1000100">/service/book/1000100</a></li>
		<li>Retrieve the library (i.e. purchased books) of the current
			user: <a href="service/library">/service/library</a>
		</li>
		<li>Purchase a book: <tt>/service/purchase/book/{bookId}</tt></li>
	</ul>

	<h2>File Services</h2>
	<ul>
		<li>Retrieve a book: <tt>/service/file/{id}</tt>, e.g. <a
			href="service/file/1000100">/service/file/1000100</a></li>
	</ul>

	<h2>User Services</h2>
	<ul>
		<li>Retrieve a user: <tt>/service/user/{id}</tt>, e.g. <a
			href="service/user/1000">/service/user/1000</a></li>
		<li>Register a user: <tt>/service/user/register</tt></li>
	</ul>

</body>
</html>
