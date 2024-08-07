<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>User List</title>
</head>
<body>

<h2 align="center">Projects List</h2>
<table border="1" align="center">
    <tr>
        <th>Project ID</th>
        <th>Project Name</th>
        <th>User ID</th>
    </tr>
    <s:iterator value="projects">
        <tr>
            <td><s:property value="projectID"/></td>
            <td><s:property value="projectName"/></td>
             <td><s:property value="userID"/></td>
        </tr>
    </s:iterator>
</table>
</body>
</html>