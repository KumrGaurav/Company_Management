<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<h2 align="center">User List</h2>
<table border="1" align="center">
    <tr>
        <th>ID</th>
        <th>Password</th>
    </tr>
    <s:iterator value="users">
        <tr>
            <td><s:property value="id"/></td>
            <td><s:property value="password"/></td>
        </tr>
    </s:iterator>
</table>
</body>
</html>