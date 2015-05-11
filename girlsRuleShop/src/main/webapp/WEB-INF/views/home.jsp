<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/jquery/jquery.min.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
</head>
<body>
	<!--  header  시작 -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Hello world</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">화면 1<span class="sr-only">(current)</span></a></li>
					<li><a href="#">화면 2</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Id">
								<input type="text" class="form-control" placeholder="Password">
							</div>
							<button type="submit" class="btn btn-default">Login</button>
						</form>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- header 끝 -->
	<!--  body 시작 -->
	<div class="row">
		<div class="col-md-12">안뇽 힘내라웅  > < 보고팡 팡팡팡!!</div>
	</div>
	<!-- <tiles:insertAttribute name="body" /> -->
	<!-- body 끝 -->

</body>
</html>
