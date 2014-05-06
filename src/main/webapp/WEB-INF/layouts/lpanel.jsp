<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div id="lpanel">

	<security:authorize access="isAnonymous()">

		<script type="text/javascript">
/* <![CDATA[ */
$.webshims.setOptions('forms', {placeholderType: 'over'} ); 
$.webshims.polyfill('forms');
/* ]]> */
</script>

		<form id="form" name="login"
			action="<c:url value='/j_spring_security_check' />" method="post">

			<label><input type="text" name="j_username" size="30"
				value="Email" placeholder="Username" /> <input type="password"
				name="j_password" size="30" value="password" placeholder="Password" />
			</label>

			<div id="ldpanel">
				<input type="checkbox" name="_spring_security_remember_me" /> <label>
					Remember me. </label>&nbsp;&nbsp;&nbsp;&nbsp; <input type="checkbox"
					name="mobile" /> Mobile.
			</div>
			<input class="submit" type="submit" name="login" value="Login"
				class="loginbutton" />

		</form>
	</security:authorize>


	<security:authorize access="isAuthenticated()">
		<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
	</security:authorize>

</div>
