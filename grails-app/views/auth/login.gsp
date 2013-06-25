<html>
<head>
<meta name="layout" content="main" />
<r:require modules="bootstrap" />
</head>
<body>

	<div class="container">


		<g:if test="${flash.message}">
			<div class="alert  alert-error">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Err!</strong>
				${flash.message}
			</div>
		</g:if>
		<g:form action="signIn" class="form-signin">
			<input type="hidden" name="targetUri" value="${targetUri}" />
			<h2 class="form-signin-heading">Please sign in</h2>
			<input type="text" class="input-block-level" placeholder="Username"
				value="${username}" name="username" />
			<input type="password" class="input-block-level"
				placeholder="Password" name="password" value="" />
			<label class="checkbox"> <input type="checkbox"
				name="rememberMe" value="${rememberMe}"> Remember me
			</label>
			<button class="btn btn-large btn-primary" type="submit">Sign
				in</button>
		</g:form>

	</div>
</body>
</html>

