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
	margin-left: 350px;
	margin-top: 40px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	height: 85%;
	width: 65%; //
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
                <header class="page-header" style=" position: relative;">
                    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                        <div class="collapse navbar-collapse">
                           
                            <!-- Back button -->
                                <button type="button" class="btn btn-secondary mr-2" onclick="window.history.back();">Back</button>
             
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
			<div class="filter-container">
				<div>
					<button id="addProjectBtn" class="btn btn-primary">
						<i class="far fa-plus-square"></i> Assign new Project
					</button>
				</div>
			</div>
			<table id="projectsTable" class="table table-striped table-bordered"
				style="width: 100%">
				<thead>
					<tr>
						<th>S_No.</th>
						<th>projectName</th>
						<th>AssignedTo</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="projects">
						<tr>
							<td></td>
							<td><a
								href="projectTask.jsp?projectID=<s:property value='projectID'/>"><s:property
										value="projectName" /></a></td>

							<td><s:property value="id.firstname" /></td>
							<td>
								<button class="btn btn-sm btn-info edit-btn"
									data-projectid="<s:property value='projectID'/>">
									<i class="far fa-edit"></i>
								</button>
								<button class="btn btn-sm btn-danger delete-btn"
									data-projectid="<s:property value='projectID'/>">
									<i class="far fa-trash-alt"></i>
								</button>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
<!-- 	</div>
 -->
	<!-- Add Project Modal -->
	<div class="modal fade" id="addProjectModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="addProjectForm">
					<div class="modal-header">
						<h4 class="modal-title">Assign Project</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="addProjectName">Project Name</label> <input
								type="text" class="form-control" name="projectName"
								id="addProjectName" required>
						</div>
						<div class="form-group">
							<label for="addAssignedTo">Assigned To</label> <input type="text"
								class="form-control" name="assignedTo" id="addAssignedTo"
								required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Assign</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Edit Project Modal -->
	<div class="modal fade" id="editProjectModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="editProjectForm">
					<div class="modal-header">
						<h4 class="modal-title">Edit Project</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<input type="hidden" name="projectID" id="editProjectID">
						<div class="form-group">
							<label for="editProjectName">Project Name</label> <input
								type="text" class="form-control" name="projectName"
								id="editProjectName" required>
						</div>
						<div class="form-group">
							<label for="editAssignedTo">Assigned To</label> <input
								type="text" class="form-control" name="assignedTo"
								id="editAssignedTo" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Update
							Project</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
					</div>
				</form>
			</div>
		</div>
	</div>

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

			$('#addProjectBtn').on('click', function() {
				$('#addProjectModal').modal('show');
			});

			$('#projectsTable tbody').on('click', '.edit-btn', function() {
				var projectID = $(this).data('projectid');
				var rowData = table.row($(this).parents('tr')).data();
				$('#editProjectID').val(projectID);
				$('#editProjectName').val(rowData[2]);
				$('#editAssignedTo').val(rowData[3]);
				$('#editProjectModal').modal('show');
			});

			$('#projectsTable tbody').on('click', '.delete-btn', function() {
				var deleteBtn = $(this);
				var projectID = deleteBtn.data('projectid');
				if (confirm('Are you sure you want to delete this project?')) {
					$.ajax({
						url : 'deleteProject',
						method : 'POST',
						data : {
							projectID : projectID
						},
						success : function(response) {
							alert('Project deleted successfully');
							table.row(deleteBtn.parents('tr')).remove().draw();
						},
						error : function(response) {
							alert('Failed to delete project');
						}
					});
				}
			});

			$('#addProjectForm').on('submit', function(e) {
				e.preventDefault();
				var projectDetails = $(this).serialize();
				$.ajax({
					url : 'saveProject',
					method : 'POST',
					data : projectDetails,
					success : function(response) {
						alert('Project added successfully');
						window.location.reload();
					},
					error : function(response) {
						alert('Failed to add project');
					}
				});
			});

			$('#editProjectForm').on('submit', function(e) {
				e.preventDefault();
				var projectDetails = $(this).serialize();
				$.ajax({
					url : 'updateProject',
					method : 'POST',
					data : projectDetails,
					success : function(response) {
						alert('Project updated successfully');
						window.location.reload();
					},
					error : function(response) {
						alert('Failed to update project');
					}
				});
			});
		});
	</script>
</body>
</html>
