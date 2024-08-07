<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 20px;
    padding: 20px;
}

table {
    border-collapse: collapse;
    width: 100%;
    margin: 20px auto;
    background-color: #fff; /* White background color for table */
    color: #000; /* Text color */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

th {
    background-color: #007bff; /* Blue for header */
    color: #fff; /* Text color for header */
}

tr:nth-child(even) {
    background-color: #fff; /* White for even rows */
}

    </style>
</head>
<body>
    <table border="1" align="center">
        <tr>
            <th>Comments</th>
        </tr>
        <s:iterator value="comments">
            <tr>
                <td><s:property /></td>
            </tr>
        </s:iterator>
    </table>
</body>
</html>

