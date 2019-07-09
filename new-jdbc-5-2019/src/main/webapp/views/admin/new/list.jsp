<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Danh sách bài viết</title>
	</head>

	<body>
		<div class="main-content">
		<form action="<c:url value='/admin-new'/>" id="formSubmit" method="get">
			
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Trang chủ</a>
							</li>
						</ul>
						<!-- /.breadcrumb -->
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<div class="widget-box table-filter">
									<div class="table-btn-controls">
										<div class="pull-right tableTools-container">
											<div class="dt-buttons btn-overlap btn-group">
												<a flag="info"
												   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
												   title='Thêm bài viết' href='<c:url value="/admin-new?type=edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
												</a>
												<button id="btnDelete" type="button"
														class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
												</button>
												<a flag="info"
												   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
												   title='Tìm kiếm'>
															<input id="search" type="text" placeholder="Tìm kiếm" aria-label="Tìm kiếm">
															<br>
															<ul id="listSearch">
															<c:forEach var="list" items="${model.listResult}">
															<li>${list.title}</li>
															</c:forEach>
															</ul>
												</a>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>Tên bài viết</th>
														<th>Mô tả ngắn</th>
														<th>Thao tác</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${model.listResult}">
														<tr>
															<td>${item.title}</td>
															<td>${item.shortDescription}</td>
															<td>
															<c:url value="/admin-new" var="editURL">
															<c:param name="type" value="edit"></c:param>
															<c:param name="id" value="${item.id}"></c:param>
															</c:url>
															<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip" title="Sửa bài viết"
															 	href='${editURL}'></a>
																<span><i class="fa fa-pencil-square-o" aria-hidden="true"></i></i>
																</span>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<ul id = "pagination" class="pagination"></ul>
					<input type="hidden" value="" id="page" name="page"/>
					<input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
					<input type="hidden" value="" id="sortBy" name="sortBy"/>
					<input type="hidden" value="" id="sortName" name="sortName"/>
					<input type="hidden" value="" id="type" name="type"/>
					<script>
					var totalPages =${model.totalPage};
					var currentPage = ${model.page};
					var maxPageItem =2;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages: totalPages,
				visiblePages :10,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if(currentPage != page){
					$('#page').val(page);
					$('#maxPageItem').val(maxPageItem);
					$('#sortName').val('title');
					$('#sortBy').val('desc');
					$('#type').val('list');
					$('#formSubmit').submit();
				}
				}
			});
		});
		$(document).ready(function(){
						  $('#search').on('keyup', function() {
						    var value = $(this).val().toLowerCase();
						    $('#listSearch li').filter(function() {
						      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
						    });
						  });
						});
	</script>
	
		</form>
		</div>
		<!-- /.main-content -->
</body>

	</html>


 <!-- gui page ve server       <input type="hidden" value="" id="page" name="page"/> name giong trong abstractmodel
 									<input type="hidden" value="" id="maxPageItem" name="maxPageItem"/> -->