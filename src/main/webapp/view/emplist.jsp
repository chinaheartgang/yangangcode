<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% pageContext.setAttribute("path",request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>emplist</title>
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
						${requestScope.department.depName}
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
								Salary
							</td>
							<td>
								Age
							</td>
							<td>
								Operation
							</td>
						</tr>
						<c:forEach items="${pageInfo.list}" var="employee" varStatus="stu">
						<tr class="row1">
							<td>${stu.count}</td>
							<td>${employee.eName}</td>
							<td>${employee.salary}</td>
							<td>${employee.age}</td>
							<td>
								<a href="<c:url value="/employee/remove"/>?id=${employee.id}&depId=${employee.departmentId}">delete emp</a>&nbsp;
								<a href="<c:url value="/employee/findOne"/>?id=${employee.id}">update emp</a>
							</td>
						</tr>
						</c:forEach>
					</table>
					<%--<c:set var="depId" value="${employee.depId}" scope="page"/>--%>
					<p>
						<input type="button" class="button" value="Add Employee" onclick="location='../view/addEmp.jsp?depId=${department.id}'"/>
					<center>
						<!-- 上一页 -->
						<c:if test="${pageInfo.hasPreviousPage}">
							<a href='<c:url value="/employee/showAll"/>?pageNum=${pageInfo.pageNum-1}'>&lt;&lt;&nbsp;&nbsp;&nbsp;</a>
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
								<a href="<c:url value='/employee/showAll'/>?pageNum=${page_num}">${page_num}</a>
							</c:if>
						</c:forEach>
						<!-- 下一页 -->
						<c:if test="${pageInfo.hasNextPage}">
							<a href='<c:url value="/employee/showAll"/>?pageNum=${pageInfo.pageNum+1}'>&nbsp;&nbsp;&nbsp;&gt;&gt;</a>
						</c:if>
						<c:if test="${!pageInfo.hasNextPage}">
							<a>&nbsp;&nbsp;&nbsp;&gt;&gt;</a>
						</c:if>
					</center>
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
