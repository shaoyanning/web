<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>更新商品</title>
</head>
<body>
	<!-- 无法建立到 localhost:8080 服务器的连接:Tomcat并没有启动，或者启动成功但是端口不对 
   <!-- 404: tomcat已经启动成功,但是没有访问资源 -->
	<!-- 500 服务器内部错误: 资源能够被找到,但是解析资源的是抛出异常 -->
	<form action="/web/ProductServlet" method="post">
		商品名:<input type="text" name="name" value="${requestScope.product.name}" /><br /> 
		价格:<input type="text"
			name="price" value="${requestScope.product.price}" /><br /> 备注:
		<textarea name="remark" rows="5" cols="20">${requestScope.product.remark}</textarea>
		<button type="submit">更新商品</button>
		<input type="hidden" name="type" value="update">
		<input type="hidden" name="id" value="${requestScope.product.id}" >
	</form>
</body>
</html>