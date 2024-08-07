<%-- <%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>HomePage</title>
</head>
<body>
	<h1>Congratulations... You reached the homepage</h1>
</body>
</html> --%>
<%@ page language = "java" contentType = "text/html; charset = ISO-8859-1"
   pageEncoding = "ISO-8859-1"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
   <head>
      <title>File Upload</title>
   </head>
   
   <body>
      <form action = "upload" method = "post" enctype = "multipart/form-data">
         <label for = "myFile">Upload your file</label>
         <input type = "file" name = "myFile" />
         <input type = "submit" value = "Upload"/>
      </form>
   </body>
</html>