<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Companies List</title>
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
	margin-top: 5px;
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

header {
	margin-top: 0rem;
}

#addCompanyModal .modal-header, #addCompanyModal .modal-footer,
	#editCompanyModal .modal-header, #editCompanyModal .modal-footer {
	background-color: #f8f9fa;
}

.page-header {
	padding: 2rem 1.5rem;
	background-color: #343a40; /* Dark background color */
	color: #fff; /* Text color */
	border-radius: 0.3rem;
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

h2, h4 {
	color: #007bff;
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
	<div class="row">
		<!-- Main content -->
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
			<h2 class="mb-4">Company List</h2>
			<div class="filter-container">
				<button id="addCompanyBtn" class="btn btn-primary">
					<i class="far fa-plus-square"></i> Add new company
				</button>
			</div>
			<table id="companiesTable" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>S_No.</th>
						<th>CompanyName</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="companies" var="company">
						<tr>
							<td></td>
							<td><s:property value="#company.companyName" /></td>
							<td><s:iterator value="#company.users" var="user">
									<s:if test="#user.role == 'Company User'">
										<button class="btn btn-sm btn-info edit-btn"
											data-companyid="<s:property value='#company.companyId'/>"
											data-companyname="<s:property value='#company.companyName'/>"
											data-firstname="<s:property value='#user.firstName'/>"
											data-lastname="<s:property value='#user.lastName'/>"
											data-email="<s:property value='#user.email'/>">
											<i class="far fa-edit"></i>
										</button>
									</s:if>
								</s:iterator>

								<button class="btn btn-sm btn-danger delete-btn"
									data-companyid="<s:property value='#company.companyId'/>">
									<i class="far fa-trash-alt"></i>
								</button></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>

	</div>

	<!-- </div> -->

	<!-- Add Company Modal -->
	<div class="modal fade" id="addCompanyModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="addCompanyForm">
					<div class="modal-header">
						<h4 class="modal-title">Add Company</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="addCompanyName">Company Name</label> <input
								type="text" class="form-control" name="companyName"
								id="addCompanyName" required>
						</div>
						<div class="form-group">
							<label for="addCompanyUserFirstName">First Name</label> <input
								type="text" class="form-control" name="companyUserFirstName"
								id="addCompanyUserFirstName" required>
						</div>
						<div class="form-group">
							<label for="addCompanyUserLastName">Last Name</label> <input
								type="text" class="form-control" name="companyUserLastName"
								id="addCompanyUserLastName" required>
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

	<!-- Edit Company Modal -->
	<div class="modal fade" id="editCompanyModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="editCompanyForm">
					<div class="modal-header">
						<h4 class="modal-title">Edit Company</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<input type="hidden" name="companyId" id="editCompanyId">
						<div class="form-group">
							<label for="editCompanyName">Company Name</label> <input
								type="text" class="form-control" name="companyName"
								id="editCompanyName" required>
						</div>
						<div class="form-group">
							<label for="editCompanyUserFirstName">First Name</label> <input
								type="text" class="form-control" name="companyUserFirstName"
								id="editCompanyUserFirstName" required>
						</div>
						<div class="form-group">
							<label for="editCompanyUserLastName">Last Name</label> <input
								type="text" class="form-control" name="companyUserLastName"
								id="editCompanyUserLastName" required>
						</div>
						<div class="form-group">
							<label for="editEmail">Email</label> <input type="email"
								class="form-control" name="email" id="editEmail" required>
						</div>
						<div class="form-group">
							<label for="editPassword">Password</label> <input type="password"
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
		$(document)
				.ready(
						function() {
							var table = $('#companiesTable').DataTable(
									{
										"columnDefs" : [ {
											"targets" : 0,
											"searchable" : false,
											"orderable" : false,
											"render" : function(data, type,
													full, meta) {
												return meta.row + 1;
											}
										} ],
										"order" : [ [ 1, 'asc' ] ]
									});

							$('#addCompanyBtn').on('click', function() {
								openAddModal();
							});

							$('#companiesTable tbody')
									.on(
											'click',
											'.edit-btn',
											function() {
												var companyId = $(this).data(
														'companyid');
												var companyName = $(this).data(
														'companyname');
												var firstName = $(this).data(
														'firstname');
												var lastName = $(this).data(
														'lastname');
												var email = $(this).data(
														'email');
												openEditModal(companyId,
														companyName, firstName,
														lastName, email);
											});

							$('#companiesTable tbody')
									.on(
											'click',
											'.delete-btn',
											function() {
												var companyId = $(this).data(
														'companyid');
												if (confirm('Are you sure you want to delete this company?')) {
													$
															.ajax({
																url : 'deleteCompany',
																method : 'POST',
																data : {
																	companyId : companyId
																},
																success : function(
																		response) {
																	alert('Company deleted successfully');
																	table
																			.row(
																					$(
																							this)
																							.parents(
																									'tr'))
																			.remove()
																			.draw();
																},
																error : function(
																		response) {
																	alert('Failed to delete company');
																}
															});
												}
											});

							$('#addCompanyForm').on('submit', function(e) {
								e.preventDefault();
								var companyDetails = $(this).serialize();
								$.ajax({
									url : 'saveCompany',
									method : 'POST',
									data : companyDetails,
									success : function(response) {
										alert('Company added successfully');
										window.location.reload();
									},
									error : function(response) {
										alert('Failed to add company');
									}
								});
							});

							$('#editCompanyForm').on('submit', function(e) {
								e.preventDefault();
								var companyDetails = $(this).serialize();
								$.ajax({
									url : 'updateCompany',
									method : 'POST',
									data : companyDetails,
									success : function(response) {
										alert('Company updated successfully');
										window.location.reload();
									},
									error : function(response) {
										alert('Failed to update company');
									}
								});
							});

							function openAddModal() {
								$('#addCompanyForm')[0].reset();
								$('#addCompanyModal').modal('show');
							}

							function openEditModal(companyId, companyName,
									firstName, lastName, email) {
								$('#editCompanyId').val(companyId);
								$('#editCompanyName').val(companyName);
								$('#editCompanyUserFirstName').val(firstName);
								$('#editCompanyUserLastName').val(lastName);
								$('#editEmail').val(email);
								$('#editCompanyModal').modal('show');
							}
						});
	</script>
</body>
</html>

