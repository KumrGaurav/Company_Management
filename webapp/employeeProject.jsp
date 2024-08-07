<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<jsp:include page="header.jsp" />
	
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Project List</title>
<!-- Include Bootstrap CSS from CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Include DataTables CSS from CDN -->
<link
	href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.datatables.net/buttons/1.7.0/css/buttons.dataTables.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<!-- Include jQuery and DataTables JS from CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.7.0/js/dataTables.buttons.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.7.0/js/buttons.html5.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.7.0/js/buttons.print.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="styles.css">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
}

.container {
	margin-top: 50px;
}

.dt-button {
	margin-bottom: 10px;
}

.table-wrapper {
	background-color: #ffffff;
	padding: 50px;
	margin-left: 300px;
	margin-top: 40px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	height: 85%;
	width: 75%; //
	overflow-x: auto;
}

#addProjectModal .modal-header, #addProjectModal .modal-footer,
	#editProjectModal .modal-header, #editProjectModal .modal-footer {
	background-color: #f8f9fa;
}

.btn {
	margin-right: 5px;
}

.filter-container {
	display: flex;
	justify-content: space-between;
	margin-bottom: 10px;
}

.filter-container input, .filter-container select {
	margin-right: 50px;
}

h2 {
	color: #007bff; /* or any other blue shade you prefer */
}

h4 {
	color: #007bff; /* or any other blue shade you prefer */
}
</style>
</head>
<body>
<%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
    %>
	<!-- <div class="container"> -->
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
		<div class="table-wrapper">
			<h2 class="mb-4">Project List</h2>
			<table id="projectsTable" class="table table-striped table-bordered"
				style="width: 100%">
				<thead>
					<tr>
						<th>S_No.</th>
						<th>projectName</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="projects">
						<tr>
							<td></td>
							<td>
								<form action="projectTasks" method="post">
									<input type="hidden" name="projectId" value="<s:property value='projectID'/>" />
									<button type="submit" class="btn btn-link">
										<s:property value="projectName" />
									</button>
								</form>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	<!-- </div> -->


 <script type="text/javascript">
		$(document).ready(function() {
			var table = $('#projectsTable').DataTable({
				"columnDefs" : [ {
					"targets" : 0,
					"searchable" : false,
					"orderable" : false,
					"render" : function(data, type, full, meta) {
						return meta.row + 1;
					}
				} ],
				"order" : [ [ 1, 'asc' ] ]
			});
			/* $('#project-link').on('click', function(e) {
		        e.preventDefault(); // Prevent default link behavior

		        var prId = $(this).data('projectid');

		        // Make AJAX request to the action URL with projectID parameter
		        $.ajax({

		            method: 'POST',
		            data: prId,
		            success: function(response) {
		                // Handle success response, if needed
		                console.log(projectID);
		                console.log('Success: ', response);
		            },
		            error: function(xhr, status, error) {
		                // Handle error response, if needed
		              
		                console.error('Error: ', error);
		            },
		            url: 'projectTasks'
		        });
		    }); */
		});
	</script>
</body>
</html>
