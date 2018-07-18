<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加商品</title>
</head>
<body>
	<form action="/web/ProductServlet" method="get">
		查询关键字:<input type="text" name="keyword" />
		<button type="submit">给我搜</button>
	</form>
	<!-- request,session,application都称为内置对象(不用创建(系统已经创建)直接使用) -->
	<%
		out.print(request + ",<br />" + session + ",<br />" + application + "<br />");
		out.print(request.getAttribute("req") + "<br />" + session.getAttribute("ses") + "<br />"
				+ application.getAttribute("app"));
	%>

</body>
</html>