<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% pageContext.setAttribute("path",request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>department</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${path}/view/css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<div id="header">
					<div id="rightheader">
						<p>
							2009/11/20
							<br />
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="#">main</a>
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						Welcome!
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								Name
							</td>
							<td>
								note
							</td>
							<td>
								Operation
							</td>
							<td colspan="2">
								操作
							</td>
						</tr>
						<c:forEach items="${pageInfo.list}" varStatus="stu" var="department">
						<tr class="row1">
							<td>${stu.count}</td>
							<td>${department.depName}</td>
							<td>${department.depNote}</td>
							<td>${department.operation}</td>
							<td>
								<a href="<c:url value='/employee/showAll'/>?depId=${department.id}">员工详情</a>
								<a href="<c:url value="/department/findOne"/>?id=${department.id}">update</a>
							</td>
						</tr>
						</c:forEach>
					</table>
					<p>
						<input type="button" class="button" value="Add Department" onclick="location='${path}/view/addDepartment.jsp'"/>
						<!-- 上一页 -->
						<center><c:if test="${pageInfo.hasPreviousPage}">
							<a href='<c:url value="/department/showAll"/>?pageNum=${pageInfo.pageNum-1}'>&lt;&lt;&nbsp;&nbsp;&nbsp;</a>
						</c:if>
						<c:if test="${!pageInfo.hasPreviousPage}">
							<a >&lt;&lt;&nbsp;&nbsp;&nbsp;</a>
						</c:if>

						<!-- 显示所有页号，若是当前页就高亮显示，并且没有链接 -->
						<c:forEach var="page_num" items="${pageInfo.navigatepageNums}">
							<c:if test="${page_num == pageInfo.pageNum}">
								<font color='red'>${page_num}</font>
							</c:if>
							<c:if test="${page_num != pageInfo.pageNum}">
								<a href="<c:url value='/department/showAll'/>?pageNum=${page_num}">${page_num}</a>
							</c:if>
						</c:forEach>

						<!-- 下一页 -->
						<c:if test="${pageInfo.hasNextPage}">
							<a href='<c:url value="/department/showAll"/>?pageNum=${pageInfo.pageNum+1}'>&nbsp;&nbsp;&nbsp;&gt;&gt;</a>
						</c:if>
						<c:if test="${!pageInfo.hasNextPage}">
							<a>&nbsp;&nbsp;&nbsp;&gt;&gt;</a>
						</c:if></center>
					</p>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
				ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
