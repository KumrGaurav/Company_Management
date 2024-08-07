<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee Dashboard</title>
     <!-- CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
    %>
  
	<!-- <div class="container">
 -->
	<main role="main" class="col-lg-6 ml-sm-auto col-lg-10 px-4">
		<header class="page-header" style="position: relative;">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
				<div class="collapse navbar-collapse">

					<!-- Back button -->
					<button type="button" class="btn btn-secondary mr-2"
						onclick="window.history.back();">Back</button>

					<!-- Logout button -->
					<form action="logout" method="post" class="form-inline ml-auto">
						<button type="submit" class="btn btn-danger">Logout</button>
					</form>
				</div>
			</nav>
		</header>

	</main>
 <h1 style="color: blue; font-size: 36px; text-align: center;">Welcome <%= session.getAttribute("employeeName") %></h1>
                <p style="color: gray; font-size: 24px; text-align: center;">You have access to the Employee dashboard.</p>
</body>
</html>