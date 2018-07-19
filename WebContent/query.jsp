<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查询商品</title>
 <script type="text/javascript"></script>
 <script type="text/javascript"></script>
</head>
<body>
	<form action="/web/ProductServlet" method="post">
		查询关键字:<input type="text" name="keyword" value="${sessionScope.keyword}"/>
		<button type="submit">给我搜</button>
		<input type="hidden" name="type" value="query">
	</form>
	<!-- request,session,application都称为内置对象(不用创建(系统已经创建)直接使用) -->
	<table width="800" border="1">
	    <!-- tr代表行 td代表列 th:标题 -->
		<tr>
			<th>编号</th>
			<th>名称</th>
			<th>价格</th>
			<th>备注</th>
			<th>日期</th>
			<th>操作</th>
		</tr>
		 <!-- items:循环的集合    var:循环的每个对象-->
		 <!-- session.getAttribute("proList") -->
		 <!-- el表达式默认 request ==> session ===> application -->
		<c:forEach items="${requestScope.proList}" var="p">
			 <!-- 循环一行 -->
			 <tr>
				<td>${p.id}</td>
				<td>${p.name}</td>
				<td>${p.price}</td>
				<td>${p.remark }</td>
				<td>${p.date}</td>
				<td><a href="/web/ProductServlet?id=${p.id}&type=delete">删除</a>
				  |ajax删除
				  |<a href="/web/ProductServlet?id=${p.id}&type=getById">更新</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>







