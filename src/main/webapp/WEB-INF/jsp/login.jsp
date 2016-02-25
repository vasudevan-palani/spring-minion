<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Minion - Account Management</title>

<!-- Bootstrap Core CSS -->
<link href="/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="/js/jquery.min.js"></script>
<script src="/js/angular.min.js"></script>
<script src="/js/ui-bootstrap-tpls-1.1.2.min.js"></script>

<script src="/js/minion.js"></script>

</head>

<body ng-app="minion">

	<div class="container" ng-controller="LoginController">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Minion : Please Sign In</h3>
					</div>
					<table>
						<tr>
							<td>
								<div style="float: left">
									<img src="../images/minion.png" height="200px" />
								</div>
							</td>
							<td>
								<div class="panel-body">
									<form role="form" action="/login" method="POST">
										<fieldset>
											<div class="form-group">
												<c:if test="${errorMessage != null && errorMessage != \"\"}">
													<label class="label label-danger"> <c:out
															value="${errorMessage}" />
													</label>
												</c:if>
											</div>
											<div class="form-group">
												<input class="form-control" placeholder="Employee Id"
													name="empId" autofocus ng-model="data.empId">
											</div>
											<div class="form-group">
												<input class="form-control" placeholder="Password"
													name="password" type="password" value=""
													ng-model="data.password">
											</div>
											<div class="checkbox">
												<label> <input name="remember" type="checkbox"
													value="Remember Me" ng-model="rememberMe">Remember
													Me
												</label>
											</div>
											<!-- Change this to a button or input when using this as a form -->
											<input type="submit" class="btn btn-lg btn-success btn-block"></input>
										</fieldset>
									</form>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="../bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>
