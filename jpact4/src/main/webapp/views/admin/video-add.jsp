<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
	<label for="fname">Video title:</label><br> <input type="text"
		id="categoryname" name="vidtitle"><br> <label
		for="lname">Link images:</label><br> <input type="text"
		id="poster" name="poster"><br> <label for="lname">Upload
		images:</label><br> <input type="file" id="images1" name="images1"><br>
		
		<label for="fname">Decription:</label><br> <input type="text" id="Decription" name="Decription"><br>
		
		<label for="fname">Views:</label><br> <input type="text" id="views" name="views"><br>


	<label for="html">Active</label><br> <input type="radio" id="ston"
		name="active" value="1"> <label for="css">Hoạt động</label><br>


	<input type="radio" id="stoff" name="active" value="0"> <label
		for="javascript">Khóa</label> <br>
	<br> <input type="submit" value="Insert">


</form>