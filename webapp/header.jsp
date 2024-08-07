<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="styles.css">
<meta charset="ISO-8859-1">
<title>Header</title>
</head>
<body>
<!-- 	<header class="bg-light position-fixed h-100" style="width: 200px;">
 -->    <!-- <div class="container"> -->
        <div class="row">
         <nav class="col-md-2 bg-light sidebar">
                <div class="sidebar-sticky">
<!--             <div class="col-md-20">
 -->            
                <ul class="nav flex-column">
                	<%
					    // Assuming you have a session attribute "userRole" that stores the user's role
					    String  userRole = (String ) session.getAttribute("role");
                		System.out.println(userRole);
					
                	if (userRole != null && userRole.equalsIgnoreCase("employee")) { 
					%>
                         <li class="nav-item">
                        <a class="nav-link projects-link" href="employeeProject">
                            <i class="fas fa-project-diagram mr-2"></i> Projects
                        </a>
                    </li>
					<%
					    } else if(userRole != null && userRole.equalsIgnoreCase("admin")){
					%>
                    <li class="nav-item">
                        <a class="nav-link active" href="adminDashboard">
                            <i class="fas fa-tachometer-alt mr-2"></i> Home
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link companies-link" href="listCompanies">
                            <i class="fas fa-briefcase mr-2"></i> Companies
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link projects-link" href="showProjectGrid">
                            <i class="fas fa-project-diagram mr-2"></i> Projects
                        </a>
                    </li>
<!--                     <li class="nav-item">
                        <a class="nav-link tasks-link" href="tasks">
                            <i class="fas fa-tasks mr-2"></i> Tasks
                        </a>
                    </li> -->
                    <!-- Add more links as needed -->
                    <li class="nav-item">
                        <a class="nav-link tasks-link" href="listAllEmployees">
                            <i class="fas fa-users mr-2"></i> Employees
                        </a>
                    </li>
              
                    
                    <%
					    } else {
					%>
					<li class="nav-item">
                        <a class="nav-link active" href="companyUserDashboard">
                            <i class="fas fa-tachometer-alt mr-2"></i> Home
                        </a>
                    </li>
					 <li class="nav-item">
                        <a class="nav-link projects-link" href="showProjectGrid">
                            <i class="fas fa-project-diagram mr-2"></i> Projects
                        </a>
                    </li>
<!--                     <li class="nav-item">
                        <a class="nav-link tasks-link" href="tasks">
                            <i class="fas fa-tasks mr-2"></i> Tasks
                        </a>
                    </li> -->
                    <!-- Add more links as needed -->
                    <li class="nav-item">
                        <a class="nav-link tasks-link" href="companyUserEmployees">
                            <i class="fas fa-users mr-2"></i> Employees
                        </a>
                    </li>
					
					<% } %>
					</ul>
            </div>
            </nav>
        </div>
 <!--    </div> -->
<!-- </header>
 -->


</body>
</html>