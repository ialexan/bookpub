<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
/* <![CDATA[ */

    $(function() {
        $( "#tabs" ).tabs();
    });
    
/* ]]> */    
</script>


<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Written Books</a></li>
		<li><a href="#tabs-2">Reading List</a></li>
	</ul>

	<div id="tabs-1">

		<table border="1">
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Price</th>
				<th>Authors</th>
			</tr>
			<c:forEach items="${books}" var="book">
				<tr>
					<td>${book.id}</td>
					<td><a
						href="<c:url value='/author/viewBook?bookId=${book.id}'  />">${book.title}</a></td>
					<td>$ ${book.price}</td>
					<td><c:forEach items="${book.authors}" var="user">
                       - ${user.firstName} ${user.lastName}   
                    </c:forEach></td>
				</tr>
			</c:forEach>
		</table>

		<a href="<c:url value='/author/addBook'/>">Add a Book</a>

	</div>
	<div id="tabs-2">
		<p>This is going to show reading list of the users.</p>
	</div>
</div>

