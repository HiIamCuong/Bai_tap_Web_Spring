<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<br>


<hr>


<table border="1" width="100%">


	<tr>


		<th>STT</th>


		<th>Email</th>


		<th>Username</th>


		<th>Password</th>


		<th>Avatar</th>


		<th>Roleid</th>
		
		
		<th>Phone</th>
		
		
		<th>CreatedDate</th>


	</tr>


	<c:forEach items="${listuser}" var="user" varStatus="STT">


		<tr>


			<td>${STT.index+1}</td>


			<c:if test="${vid.poster.substring(0,5)=='https'}">


				<c:url value="${vid.poster}" var="imgUrl"></c:url>


			</c:if>


			<c:if test="${vid.poster.substring(0,5)!='https'}">


				<c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>


			</c:if>





			<td><img height="150" width="200" src="${imgUrl}" /></td>


			<td>${vid.title}</td>


			<td><c:if test="${vid.active==1}">


Hoạt động


</c:if> <c:if test="${vid.active!=1 }">


Khóa


</c:if></td>

	<td>${vid.description}</td>
	<td>${vid.views}</td>


			<td><a
				href="<c:url value='/admin/user/edit?id=${user.userId}'/>">Sửa</a>


				| <a
				href="<c:url value='/admin/user/delete?id=${user.userId}'/>">Xóa</a>


			</td>



		</tr>


	</c:forEach>


</table>