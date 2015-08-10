<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  header  시작 -->
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">ADMIN</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="#">상품 목록<span class="sr-only">(current)</span></a></li>
				<li><a href="#">상품 상세</a></li>
				<li>
					<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
					 	상품 태그설정<span class="caret"></span>
					</a>
    				<ul class="dropdown-menu" role="menu">
    					<li><a href="javascript:moveToMenu(102)">상의 설정</a></li>
    					<li><a href="javascript:moveToMenu(103)">하의 설정</a></li>
    				</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Id" />
							<input type="text" class="form-control" placeholder="Password" />
						</div>
						<button type="submit" class="btn btn-default">Login</button>
					</form>
				</li>
			</ul>
		</div>
	</div>
</nav>
<!-- header 끝 -->
