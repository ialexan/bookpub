<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

Books

<table border="1">

	<tr>
		<th>Id</th>
		<th>Cover</th>
		<th>Title</th>
		<th>Price</th>
		<th>Author</th>
	</tr>
	<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.id}</td>
			<td><img src="<c:url value='/service/file/'/>${book.coverThumbnail.id}" alt="Thumbnail Cover"></td>
			<td><a
				href="<c:url value='/author/viewBook?bookId=${book.id}'  />">${book.title}</a></td>
			<td>$ ${book.price}</td>
			<td><c:forEach items="${book.authors}" var="user">
                       - ${user.firstName} ${user.lastName}   
                    </c:forEach></td>
                   
		</tr>
	</c:forEach>
</table>
