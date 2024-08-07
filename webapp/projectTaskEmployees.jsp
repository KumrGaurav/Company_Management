<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>User List</title>
</head>
<body>

<h2 align="center">Employees List</h2>
<table border="1" align="center">
    <tr>
        <th>Employee Name</th>
        
    </tr>
    <s:iterator value="employees">
        <tr>
            <td><s:property /></td>
        
        </tr>
    </s:iterator>
</table>
</body>
</html>