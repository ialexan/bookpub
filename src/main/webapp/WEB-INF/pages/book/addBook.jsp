<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<p>Please Enter Information for Book:</p>

<form:form modelAttribute="book" enctype="multipart/form-data">


	<table class="general">
		<tr>
			<th><strong>Title:</strong></th>
			<td><form:input type="text" name="title" class="forminput"
					path="title" /></td>
		</tr>
		<tr>
			<th><strong>description:</strong></th>
			<td><form:input type="text" name="description" class="forminput"
					path="description" /></td>
		</tr>
		<tr>
			<th><strong>price:</strong></th>
			<td><form:input type="text" name="price" class="forminput"
					path="price" /></td>
		</tr>
		<tr>
			<th><Strong>Book Cover Page:</Strong></th>
			<td><input type="file" name="uploadedFile" size="50" /></td>
		</tr>
		<tr>
			<th></th>
			<td><input type="submit" value="Add" name="add"
				class="subbutton" /></td>
		</tr>
	</table>


</form:form>



