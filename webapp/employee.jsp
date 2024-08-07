<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employees List</title>
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
<script type="text/javascript" src="admin.js"></script>
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

#addEmployeeModal .modal-header, #addEmployeeModal .modal-footer,
	#editEmployeeModal .modal-header, #editEmployeeModal .modal-footer {
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
	<!-- 	<div class="container">
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
	<div class="table-wrapper">
		<h2 class="mb-4">Employees List</h2>
		<div class="filter-container">

			<button id="addEmployeeBtn" class="btn btn-primary">
				<i class="far fa-plus-square"></i> Add new Employee
			</button>
			<!-- <button id="import" class="btn btn-primary">
				<i class="far fa-plus-square"></i> Import Employees
			</button> -->
			<form action="importFile" method="post" enctype="multipart/form-data">
				<input type="file" name="file" /> 
				<input type="submit" value="Upload" />
			</form>
		</div>
		<table id="employeesTable" class="table table-striped table-bordered"
			style="width: 100%">
			<thead>
				<tr>
					<th>S_No.</th>
					<th>EmployeeFirstName</th>
					<th>EmployeeLastName</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="employees">
					<tr>
						<td></td>
						<td><s:property value="firstName" /></td>
						<td><s:property value="lastName" /></td>
						<td><s:property value="email" /></td>
						<td>
							<button class="btn btn-sm btn-info edit-btn"
								data-employeeid="<s:property value='id'/>">
								<i class="far fa-edit"></i>
							</button>
							<button class="btn btn-sm btn-danger delete-btn"
								data-employeeid="<s:property value='id'/>">
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
	<!--  <form action="importFile" method="post" enctype="multipart/form-data" id="importForm">
    <input type="file" name="file" />
    <input type="submit" value="Upload" />
</form> -->
	<!-- Add Employee Modal -->
	<div class="modal fade" id="addEmployeeModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="addEmployeeForm">
					<div class="modal-header">
						<h4 class="modal-title">Add Employee</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="addEmployeeFirstName">First Name</label> <input
								type="text" class="form-control" name="employeeFirstName"
								id="addEmployeeFirstName" required>
						</div>
						<div class="form-group">
							<label for="addEmployeeLastName">Last Name</label> <input
								type="text" class="form-control" name="employeeLastName"
								id="addEmployeeLastName" required>
						</div>
						<div class="form-group">
							<label for="addEmail">Email</label> <input type="email"
								class="form-control" name="email" id="addEmail" required>
						</div>
						<div class="form-group">
							<label for="addPassword">Password</label> <input type="password"
								class="form-control" name="password" id="addPassword" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Save</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Edit Employee Modal -->
	<div class="modal fade" id="editEmployeeModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="editEmployeeForm">
					<div class="modal-header">
						<h4 class="modal-title">Edit Employee</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<input type="hidden" name="employeeId" id="editEmployeeId">
						<div class="form-group">
							<label for="editEmployeeFirstName">First Name</label> <input
								type="text" class="form-control" name="employeeFirstName"
								id="editEmployeeFirstName" required>
						</div>
						<div class="form-group">
							<label for="editEmployeeLastName">Last Name</label> <input
								type="text" class="form-control" name="employeeLastName"
								id="editEmployeeLastName" required>
						</div>
						<div class="form-group">
							<label for="editEmail">Email</label> <input type="email"
								class="form-control" name="email" id="editEmail" required>
						</div>
						<div class="form-group">
							<label for="addPassword">Password</label> <input type="password"
								class="form-control" name="password" id="editPassword" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Save</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			var table = $('#employeesTable').DataTable({
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

			$('#addEmployeeBtn').on('click', function() {
				$('#addEmployeeModal').modal('show');
			});
			$('#import').on('click', function() {
				$('#importForm').modal('show');
			});

			$('#employeesTable tbody').on('click', '.edit-btn', function() {
				var employeeId = $(this).data('employeeid');
				var rowData = table.row($(this).parents('tr')).data();
				$('#editEmployeeId').val(employeeId);
				$('#editEmployeeFirstName').val(rowData[1]);
				$('#editEmployeeLastName').val(rowData[2]);
				$('#editEmail').val(rowData[3]);
				$('#editPassword').val(rowData[4]);
				$('#editEmployeeModal').modal('show');
			});

			$('#employeesTable tbody').on('click', '.delete-btn', function() {
				var deleteBtn = $(this);
				var employeeId = deleteBtn.data('employeeid');
				if (confirm('Are you sure you want to delete this employee?')) {
					$.ajax({
						url : 'deleteEmployee',
						method : 'POST',
						data : {
							employeeId : employeeId
						},
						success : function(response) {
							alert('Employee deleted successfully');
							table.row(deleteBtn.parents('tr')).remove().draw();
						},
						error : function(response) {
							alert('Failed to delete employee');
						}
					});
				}
			});

			$('#addEmployeeForm').on('submit', function(e) {
				e.preventDefault();
				var employeeDetails = $(this).serialize();
				$.ajax({
					url : 'saveEmployee',
					method : 'POST',
					data : employeeDetails,
					success : function(response) {
						alert('Employee added successfully');
						window.location.reload();
					},
					error : function(response) {
						alert('Failed to add employee');
					}
				});
			});

			$('#editEmployeeForm').on('submit', function(e) {
				e.preventDefault();
				var employeeDetails = $(this).serialize();
				$.ajax({
					url : 'updateEmployee',
					method : 'POST',
					data : employeeDetails,
					success : function(response) {
						alert('Employee updated successfully');
						window.location.reload();
					},
					error : function(response) {
						alert('Failed to update employee');
					}
				});
			});
		});
	</script>
</body>
</html>
