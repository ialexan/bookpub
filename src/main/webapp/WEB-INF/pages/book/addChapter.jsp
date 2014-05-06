<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h1>${chapter.book.title}</h1>

<p>Please Enter Information For the Chapter:</p>

<form:form modelAttribute="chapter">

    <input type="hidden" name="bookId" value="${chapter.book.id}" />   

	<table class="general">
		<tr>
			<th>Title:</th>
			<td><form:input type="text" path="title" class="forminput" /></td>
		</tr>

        <tr>
            <th>Description:</th>
            <td><form:input type="text" path="description" class="forminput" /></td>
        </tr>

        <tr>
            <th>Content:</th>
            <td><form:textarea cols="80"  rows="10" path="content"  /></td>
        </tr>

		<tr>
			<th></th>
			<td><input type="submit" name="add" value="Add"
				class="subbutton" /></td>
		</tr>
	</table>


</form:form>

<script type="text/javascript">
/* <![CDATA[ */
  CKEDITOR.replaceAll();
/* ]]> */
</script>