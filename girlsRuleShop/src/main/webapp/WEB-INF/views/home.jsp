<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<body>

<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


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
			<div class="modal js-loading-bar" style="top:20%">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
						파일 업로드
						</div>
						<div class="modal-body">
							<div class="progress progress-popup">
								<div class="progress-bar progress-bar-striped active" id="progressbar"
									aria-valuenow="{percent}" aria-valuemin="0" aria-valuemax="100"
									style="width: {percent">
									<span id="statustxt">0%</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="panel-body">
				 <form id="uploadForm" enctype="multipart/form-data" class="col-md-10">
					<label>파일 업로드 </label> <input id="input-1a" data-show-caption="true" data-show-preview="false" type="file" class="file" name="file1">
				</form>
				<button style="margin-top: 25px;" class="btn btn-primary"
					onclick="download();" type="submit">파일 다운로드</button>
			</div>
			<table class="table table-hover" style="font-size: 14px;">
				<thead>
					<tr style="background-color: #f5f5f5;">
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
						<tr onclick="showModal('${result.productId}');">
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
			<div class="row" style="text-align: center;">
				<ul class="pagination" id="pagingDiv">${paging}</ul>
			</div>
		</div>
	</div>
	<!-- <tiles:insertAttribute name="body" /> -->
	<!-- body 끝 -->
	<script>
		function goPage(page) {
			$("#curPage").val(page);
			$.ajax({
				type : "POST",
				url : "list",
				headers : {
					"Accept" : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				data : JSON.stringify($("#search").serializeObject()),
				success : onSuccess,
				error : onError
			});
		}

		function onSuccess(data) {
			var html_ = "";
			for (var i = 0; i < data.list.length; i++) {
				var item = data.list[i];
				html_ += "<tr>";
				html_ += "<td>" + item.productId + "</td>";
				html_ += "<td>" + item.productType + "</td>";
				html_ += "<td>" + item.fabric + "</td>";
				html_ += "<td>" + item.color + "</td>";
				html_ += "<td>" + item.stockCnt + "</td>";
				html_ += "<td>" + item.sizeInfo + "</td>";
				html_ += "<td>" + item.baseSize + "</td>";
				html_ += "</tr>";
			}
			$("#main").html(html_);
			$("#pagingDiv").html(data.paging);
		}
		function onError(error) {
			alert("Error");
			"/Users/choesin-yeong/Downloads/kartik-v-bootstrap-fileinput-e0f18d6/js/fileinput_locale_LANG.js"
			console.log(error);
		}

		function download() {
			window.location = "download";
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
				var $modal = $('.js-loading-bar');
				$modal.modal('hide');
				if (data == "true") {
					alert("업로드 성공하였습니다.");
					$("#input-1a").fileinput("clear");
				} else {
					alert("업로드 처리도중 오류가 발생하였습니다.");
				}
			},
			error : function(data){
				$('#statustxt').html(0 + '%');
				progressbar.width(0 + '%');
				var $modal = $('.js-loading-bar');
				$modal.modal('hide');
				alert("업로드 처리 도중 오류가 발생하였습니다. 재시도 해주세요!");
			},
			beforeSend : function() {
				var $modal = $('.js-loading-bar');
				$modal.modal('show');
			},
			uploadProgress : function(event, position, total, percentComplete) { //on progress
				if(eval(percentComplete) > 97){
					percentComplete = 98;
				}
				progressbar.width(percentComplete + '%'); //update progressbar percent complete
				$('#statustxt').html(percentComplete + '%'); //update status text
			}
		});
	
		$.fn.serializeObject = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};
		
		function showModal(id){
			alert(id);
			 $('#myModal').modal('show');
		}
	</script>
</body>
