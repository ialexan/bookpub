<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<h2>${book.title}</h2>


<ul>
	<li>Description: ${book.description}</li>
	<li>Price: $${book.price} </li>
</ul>


Chapters of the book:
<table border="1">

	<tr>
		<th>Title</th>
		<th>Content</th>
	</tr>
	<c:forEach items="${book.chapters}" var="chapter">
		<tr>
			<td>${chapter.title}</td>
			<td>${chapter.content}</td>
		</tr>
	</c:forEach>
</table>


<c:if test="${userIsAuthor=='y'}">
	<a href="<c:url value='/author/addChapter?bookId=${book.id}'/>">Add
		a Chapter</a>
</c:if>


<img src="<c:url value='/service/file/'/>${book.coverThumbnail.id}" alt="Thumbnail Cover">




<!--  
<script>
/* <![CDATA[ */

$(function() {
    
    var img = new Image();
    var fileId = 2000052; 
    
    img.onload = function() {   
        $("#client_image_preview").attr("src", "<c:url value='/service/file/'/>"+fileId);
    }
    
    img.src = "<c:url value='/service/file/'/>"+fileId;

    $('#image_preview').dialog({
        height: 'auto',
        width: 'auto',
        modal: true,
        dialogClass: 'titleless',
        buttons: { "CLOSE": function() { $(this).dialog("destroy"); } }
    });

});

/* ]]> */

</script>

<div id="image_preview" title="Client Photo Preview">
   <p><img src="loading.gif" alt="client image" id="client_image_preview" /></p>
</div>
-->