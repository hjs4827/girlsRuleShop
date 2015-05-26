<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/fileinput.min.css" />
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.form.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<script src="js/bootstrap_fileinput/fileinput.min.js"></script>
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
				<a class="navbar-brand" href="#">ADMIN</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">상품 목록<span class="sr-only">(current)</span></a></li>
					<li><a href="#">상품 상세</a></li>
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
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<form id="search">
					<input type="hidden" id="curPage" name="curPage" value="1">
					<div class="form-group">
						<label class="control-label">검색</label>
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Search for..."> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button">Go!</button>
							</span>
						</div>
						<!-- 
						<label class="control-label">검색</label>
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Search for..."> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button">Go!</button>
							</span>
						</div>
						-->
					</div>
				</form>
			</div>
			<div class="panel-body">
				<form id="uploadForm" enctype="multipart/form-data" class="col-md-8">
					<label>파일 업로드(<span id="statustxt">0%</span>)
					</label> <input id="input-1a" type="file" name="file1" id="file1"
						class="file" data-show-preview="false">
				</form>
				<button style="margin-top: 25px;" class="btn btn-primary"
					type="submit">파일 다운로드</button>
			</div>
			<table class="table table-hover" style="font-size: 14px;">
				<thead>
					<tr style="background-color:#f5f5f5;">
						<th>상품 ID</th>
						<th>상품 타입</th>
						<th>소재</th>
						<th>색상</th>
						<th>수량</th>
						<th>사이즈</th>
						<th>기본 사이즈</th>
					</tr>
				</thead>
				<tbody id="main">
					<c:forEach var="result" items="${list}">
						<tr>
							<td>${result.productId }</td>
							<td>${result.productType }</td>
							<td>${result.fabric }</td>
							<td>${result.color }</td>
							<td>${result.stockCnt }</td>
							<td>${result.sizeInfo }</td>
							<td>${result.baseSize }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="row" style="text-align: center;" >
				<ul class="pagination" id="pagingDiv">
					${paging}
				</ul>
			</div>
		</div>
	</div>
	<!-- <tiles:insertAttribute name="body" /> -->
	<!-- body 끝 -->
	<script>
		function goPage(page){
			$("#curPage").val(page);
			  $.ajax({
                  type : "POST",
                  url : "list",
                  headers: { 
                      "Accept" : "application/json; charset=utf-8",
                      "Content-Type": "application/json; charset=utf-8"
                  },
                  data :  JSON.stringify($("#search").serializeObject()),
                  success : onSuccess,
                  error : onError
    		 });
		}
		
		function onSuccess(data){
			var html_ = "";
			for(var i = 0 ; i < data.list.length ; i++){
				var item = data.list[i];
				html_ += "<tr>";
				html_ += "<td>"+item.productId+"</td>";
				html_ += "<td>"+item.productType+"</td>";
				html_ += "<td>"+item.fabric+"</td>";
				html_ += "<td>"+item.color+"</td>";
				html_ += "<td>"+item.stockCnt+"</td>";
				html_ += "<td>"+item.sizeInfo+"</td>";
				html_ += "<td>"+item.baseSize+"</td>";
				html_ += "</tr>";
			}
			$("#main").html(html_);
			$("#pagingDiv").html(data.paging);
		}
		function onError(error){
			alert("Error");
			console.log(error);
		}
	
	
		var progressbox = $('#progressbox');
		var progressbar = $('#progressbar');
		var statustxt = $('#statustxt');
		var submitbutton = $("input[type='submit']");
		var completed = '0%';

		$('#uploadForm').ajaxForm({
			url : "upload",
			type : "post",
			success : function(data) {
				if (data == "true") {
					alert("업로드 성공하였습니다.");
				} else {
					alert("업로드 도중 오류가 발생하였습니다.");
				}
			},
			uploadProgress : function(event, position, total, percentComplete) { //on progress
				progressbar.width(percentComplete + '%') //update progressbar percent complete
				$('#statustxt').html(percentComplete + '%'); //update status text
				if (percentComplete > 50) {
					statustxt.css('color', '#fff'); //change status text to white after 50%
				}
			},
			complete : function(response) { // on complete
				// output.html("완료"); //update element with received data
				// myform.resetForm();  // reset form
			}
		});
		
		$.fn.serializeObject = function()
		{
		    var o = {};
		    var a = this.serializeArray();
		    $.each(a, function() {
		        if (o[this.name] !== undefined) {
		            if (!o[this.name].push) {
		                o[this.name] = [o[this.name]];
		            }
		            o[this.name].push(this.value || '');
		        } else {
		            o[this.name] = this.value || '';
		        }
		    });
		    return o;
		};
	</script>
</body>
</html>
