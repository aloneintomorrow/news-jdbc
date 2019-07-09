<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/22/2019
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:url var="APIurl" value="/api-admin-new"/>
<html>
<head>
    <title>Chỉnh Sửa Bài Viết</title>
</head>
<body>
<script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
<form id="formSubmit" class="formSubmit">
  <fieldset>
    <div id="legend">
      <legend class="">Chỉnh sửa bài viết</legend>
    </div>
    <div class="control-group">
				<div class="controls">
					<label for="sel1">Thể loại:</label> 
					<select class="form-control" id="categoryCode" name="categoryCode">
                        <option>Chọn thể loại bài viết</option>
						<c:forEach var="category" items="${categories}">
							<c:if test="${not empty model.categoryCode}">
                            <option value="${category.code}"<c:if test="${model.categoryCode == category.code}">selected="selected"</c:if>>${category.name}</option>
                            </c:if>
                            <c:if test="${empty model.categoryCode}">
                            <option value="${category.code}">${category.name}</option>
                            </c:if>
						</c:forEach>
					</select>
				</div>
			</div>
    <div class="control-group">
      <label class="control-label"  for="title">Tiêu đề</label>
      <div class="controls">
        <input type="text" id="title" name="title" value="${model.title}">    
      </div>
    </div>
 
    <div class="control-group">
      <label class="control-label" for="thumbnail">Hình ảnh</label>
      <div class="controls">
        <input type="text" id="thumbnail" name="thumbnail" value="${model.thumbnail}">
      </div>
    </div>
 
    <div class="control-group">  
      <label class="control-label" for="shortDescription">Mô tả ngắn</label>
      <div class="controls">
        <input type="text" id="shortDescription" name="shortDescription" value="${model.shortDescription}">
    
      </div>
    </div>
 
    <div class="control-group">
      <label class="control-label"  for="content">Nội dung</label>
      <div class="controls">
        <input type="text" id="content" name="content" value="${model.content}">
      </div>
    </div>
    <div class="control-group">
      <div class="controls">
      <c:if test="${not empty model.id}">
        <button class="btn btn-success" value="Thêm bài viết" name="btnEditOrAdd" id="btnEditOrAdd">
        Sửa bài viết</button>
        </c:if>
        <c:if test="${empty model.id}">
        <button class="btn btn-success" value="Sửa bài viết" name="btnEditOrAdd" id="btnEditOrAdd" >
        Thêm bài viết</button>
        </c:if>
      </div>
    </div>
    <input type="hidden" id="id" name="id" value="${model.id}"> 
</form>
<script>
$('#btnEditOrAdd').click(function(e){
  e.preventDefault();
  var data={};
  var formData = $('#formSubmit').serializeArray();
  $.each(formData,function(i,v){
    data["" + v.name + ""] = v.value;
  });
  var id = $('#id').val();
  if(id ==""){
    addNew(data);
  }else{
    updateNew(data);
  }
});
  function addNew(data) {
		$.ajax({
			url : '${APIurl}',
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(data),
			success : function(result) {
				console.log(result);
			},
			error : function(error) {
				console.log(error);
			}
		});
	}
	function updateNew(data) {
		$.ajax({
			url : '${APIurl}',
			type : 'PUT',
			contentType : 'application/json',
			data : JSON.stringify(data),
			success : function(result) {
				console.log(result);
			},
			error : function(error) {
				console.log(error);
			}
		});
	}
</script>
</body>
</html>
