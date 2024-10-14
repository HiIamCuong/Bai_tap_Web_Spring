<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<form action="<c:url value="/admin/video/update"/>" method="post"
	enctype="multipart/form-data">


	<input type="text" name="vidid" value="${vid.videoId}"
		hidden="hidden"> <label for="fname">Video title:</label><br>


	<input type="text" id="vidtitle" name="vidtitle" value="${vid.title}"><br>
	<label for="lname">Link images:</label><br> <input type="text"
		id="poster" name="poster" value="${vid.poster}"><br>



	<c:if test="${vid.poster.substring(0,5)=='https'}">


		<c:url value="${vid.poster }" var="imgUrl"></c:url>


	</c:if>


	<c:if test="${cate.poster.substring(0,5)!='https'}">


		<c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>


	</c:if>



	<img height="150" width="200" src="${imgUrl}" /><br> <label
		for="lname">Upload images:</label><br> <input type="file"
		id="poster1" name="poster1"><br> <label for="html">Status</label><br>


	<input type="radio" id="ston" name="active" value="1"
		${vid.active==1?'checked':'' }> <label for="css">Hoạt
		động</label><br> <input type="radio" id="stoff" name="active" value="0"
		${vid.active!=1?'checked':'' }> <label for="javascript">Khóa</label>
	<br> <label for="fname">Description: </label><br> <input
		type="text" id="viddescription" name="viddescription"
		value="${vid.description}"><br> <label for="fname">Description:
	</label><br> <input type="text" id="vidviews" name="vidviews"
		value="${vid.views}"> <br> <br> <input type="submit"
		value="Update">


</form>