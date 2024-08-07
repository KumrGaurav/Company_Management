<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Details</title>
<!-- Include TinyMCE JS from CDN -->
<script src="https://cdn.tiny.cloud/1/zdkjdhcnclqq9bptclljtpdhv0s5vpu5gzexmapuvuyyeay3/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
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

#addTaskModal .modal-header, #addTaskModal .modal-footer, #editTaskModal .modal-header,
	#editTaskModal .modal-footer {
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
	<div class="table-wrapper">
		<h2 class="mb-4">Task Details</h2>
		<div class="filter-container">
			<div>
				<button id="addTaskBtn" class="btn btn-primary">
					<i class="far fa-plus-square"></i> Add New Task
				</button>
			</div>
		</div>
		<table id="tasksTable" class="table table-striped table-bordered"
			style="width: 100%">
			<thead>
				<tr>
					<th>Task Name</th>
					<th>Assigned To</th>
					<th>Created By</th>
					<th>Modified By</th>
					<th>Task Status</th>
					<th>Create Time</th>
					<th style="width:80px;">Action</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<!-- </div>
 -->
	<!-- Add Task Modal -->
	<div class="modal fade" id="addTaskModal" tabindex="-1" role="dialog"
		aria-labelledby="addTaskModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="addTaskModalLabel">Add Task</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="addTaskForm">
						<div class="form-group">
							<label for="addTaskName">Task Name</label> <input type="text"
								class="form-control" id="addTaskName" name="taskName" required>
						</div>
						<div class="form-group">
							<label for="addTaskStatus">Task Status</label> <input type="text"
								class="form-control" id="addTaskStatus" name="taskStatus"
								required>
						</div>
						<div class="form-group">
							<label for="addAssignedTo">Assigned To</label> <input type="text"
								class="form-control" id="addAssignedTo" name="assignedTo"
								required>
								<%-- <!-- Options will be populated by Ajax -->
							</select> --%>
						</div>
						<div class="form-group">
                        <label for="addTaskDetails">Task Details</label>
                        <textarea class="form-control tinymce" id="addTaskDetails" name="taskDetails"></textarea>
                    </div>
						<div class="form-group">
							<label for="addCreateTime">Create Time</label> <input
								type="datetime-local" class="form-control" id="addCreateTime"
								name="createTime" required>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="saveAddTaskBtn">Save</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Edit Task Modal -->
	<div class="modal fade" id="editTaskModal" tabindex="-1" role="dialog"
		aria-labelledby="editTaskModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="editTaskModalLabel">Edit Task</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="editTaskForm">
						<input type="hidden" id="editTaskID" name="taskID">
						<div class="form-group">
							<label for="editTaskName">Task Name</label> <input type="text"
								class="form-control" id="editTaskName" name="taskName" required>
						</div>
						<div class="form-group">
							<label for="editTaskStatus">Task Status</label> <input
								type="text" class="form-control" id="editTaskStatus"
								name="taskStatus" required>
						</div>
						<div class="form-group">
							<label for="editAssignedTo">Assigned To</label> <select
								class="form-control" id="editAssignedTo" name="assignedTo"
								required>
								<!-- Options will be populated by Ajax -->
							</select>
						</div>
						<div class="form-group">
                        <label for="editTaskDetails">Task Details</label>
                        <textarea class="form-control tinymce" id="editTaskDetails" name="taskDetails"></textarea>
                    </div>
						<div class="form-group">
							<label for="editCreateTime">Create Time</label> <input
								type="datetime-local" class="form-control" id="editCreateTime"
								name="createTime" required>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="saveEditTaskBtn">Save</button>
				</div>
			</div>
		</div>
	</div>

	<script>
$(document).ready(function() {
    const projectID = '<%=request.getParameter("projectID")%>';
    const projectName = '<%=request.getParameter("projectName")%>';
    const currentUser = '<%=session.getAttribute("currentUser")%>';

    
    tinymce.init({
        selector: 'textarea.tinymce', // Apply TinyMCE to all textareas with class "tinymce"
        menubar: false,
        plugins: [
            'advlist autolink lists link image charmap print preview anchor',
            'searchreplace visualblocks code fullscreen',
            'insertdatetime media table paste code help wordcount'
        ],
        toolbar: 'undo redo | formatselect | bold italic backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help'
    });
    
    // Initialize DataTable
    const table = $('#tasksTable').DataTable({
        ajax: {
            url: 'projectTask',
            type: 'POST',
            data: { projectID, projectName },
            dataSrc: ''
        },
        columns: [
            { data: 'taskName', render: function(data, type, row) {
                console.log({data});
                console.log({row});
            	return '<a href="addComment.jsp?taskID='+row.id+'">'+data+'</a>';
            }},
            { data: 'userName' },
            { data: 'createdBy' },
            { data: 'modifiedBy' },
            { data: 'taskStatus' },
            { data: 'createTime' },
            {
                data: null,
                className: 'dt-center',
                defaultContent: `
                    <button class="btn btn-sm btn-info edit-btn">
                        <i class="far fa-edit"></i>
                    </button> 
                    <button class="btn btn-sm btn-danger delete-btn">
                        <i class="far fa-trash-alt"></i>
                    </button>`,
                orderable: false
            }
        ]
    });

    // Load employee list for the select boxes
   /*  function loadEmployeeList() {
        $.ajax({
            url: 'retrieveEmployeeList',
            type: 'POST',
            data: { projectID },
            success: function(data) {
                const employees = JSON.parse(data);
                const addSelect = $('#addAssignedTo');
                const editSelect = $('#editAssignedTo');
                addSelect.empty();
                editSelect.empty();
                employees.forEach(function(employee) {
                    const option = new Option(employee.name, employee.id);
                    addSelect.append(option);
                    editSelect.append(option.cloneNode(true));
                });
            },
            error: function() {
                alert('Failed to load employee list.');
            }
        });
    }
    loadEmployeeList(); */

    // Handle form submission for adding tasks
    $('#saveAddTaskBtn').click(function() {
        const formData = $('#addTaskForm').serialize();
        $.post('addProjectTask', formData, function(response) {
            $('#addTaskModal').modal('hide');
            table.ajax.reload();
        }).fail(function() {
            alert('Failed to add task.');
        });
    });

    // Handle form submission for editing tasks
    $('#saveEditTaskBtn').click(function() {
        const formData = $('#editTaskForm').serialize();
        $.post('editProjectTask', formData, function(response) {
            $('#editTaskModal').modal('hide');
            table.ajax.reload();
        }).fail(function() {
            alert('Failed to edit task.');
        });
    });

    // Handle edit button click
    $('#tasksTable tbody').on('click', '.edit-btn', function() {
        const data = table.row($(this).parents('tr')).data();
        $('#editTaskID').val(data.id);
        $('#editTaskName').val(data.taskName);
        $('#editTaskStatus').val(data.taskStatus);
        $('#editAssignedTo').val(data.userName);
        $('#editCreateTime').val(data.createTime.replace(' ', 'T'));
        $('#editTaskModal').modal('show');
    });

    // Handle delete button click
    $('#tasksTable tbody').on('click', '.delete-btn', function() {
        const data = table.row($(this).parents('tr')).data();
        if (confirm('Are you sure you want to delete this task?')) {
            $.post('deleteTask', { taskId: data.id, projectID, currentUser }, function(response) {
                table.ajax.reload();
            }).fail(function() {
                alert('Failed to delete task.');
            });
        }
    });

    // Show add task modal
    $('#addTaskBtn').on('click', function() {
        $('#addTaskForm')[0].reset();
        $('#addTaskModal').modal('show');
    });
});
</script>
</body>
</html>
