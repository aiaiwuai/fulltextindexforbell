<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${msg}</title>
</head>
<body>
<h1>${msg }</h1>


<form method="post" action="/hello2/delete.action" >
	<input type="text" name="key" />
	<input type="submit" />
</form>
</body>
</html>