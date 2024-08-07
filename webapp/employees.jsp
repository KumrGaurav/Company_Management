<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%-- <jsp:include page="header.jsp" /> --%>

<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employees</title>
    <!-- Include Ext JS Classic Theme CSS from CDN -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css" rel="stylesheet" />

    <link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css" rel="stylesheet" />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <!-- Include Ext JS Core JavaScript from CDN -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
    <%-- <script type="text/javascript" src="admin.js"></script> --%> <!-- Assuming this is your JavaScript file -->
<!--     <link href="styles.css" rel="stylesheet" />
 -->    
    <link rel="stylesheet" href="https://cdn.datatables.net/2.0.7/css/dataTables.dataTables.css" />
  
	<script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
    
    
    
    
    <style>
        /* Basic styling for the table */
        /* DataTable wrapper styling */
		/* Search bar and Add button styling */
		#employeeDashboard_wrapper {
		    max-width: 800px;
		    margin: 20px auto;
		    border-radius: 10px;
		    overflow: hidden;
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
		    background-color: #ffffff;
		    padding: 20px;
		}
		
		#employeeDashboard_filter {
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
		    margin-bottom: 20px;
		    padding-bottom: 10px;
		}
		
		#employeeDashboard_filter input[type="search"] {
		    width: 70%; /* Adjust as needed */
		    padding: 10px;
		    border: 1px solid #cccccc;
		    border-radius: 5px;
		    box-sizing: border-box;
		    background-color: #f2f2f2;
		    color: #333333;
		    font-size: 14px;
		}
		
		#employeeDashboard_filter #addTaskBtn {
		    background-color: #007bff;
		    border: none;
		    color: #ffffff;
		    padding: 10px 24px;
		    text-align: center;
		    text-decoration: none;
		    font-size: 14px;
		    cursor: pointer;
		    border-radius: 4px;
		    transition: background-color 0.3s;
		}

		
		/* DataTable wrapper title styling */
		#employeeDashboard_wrapper h2 {
		  font-size: 24px; /* Increase font size */
		  margin-top: 0; /* Remove top margin */
		  margin-bottom: 20px; /* Add bottom margin */
		  color: #333333; /* Set title color */
		}
		
		/* DataTable wrapper subtitle styling */
		#employeeDashboard_wrapper p {
		  font-size: 16px; /* Set font size */
		  margin-top: 0; /* Remove top margin */
		  margin-bottom: 10px; /* Add bottom margin */
		  color: #666666; /* Set subtitle color */
		}

        /* Table styling */
		table.dataTable {
		  width: 100%;
		  margin: 0 auto;
		  clear: both;
		  border-collapse: collapse;
		  border-spacing: 0;
		  border: 1px solid #dddddd; /* Add border for the whole table */
		  border-radius: 5px; /* Add border radius for rounded corners */
		  overflow: hidden; /* Hide overflow content */
		}
		
		/* Header cell styling */
		table.dataTable thead th {
		  background-color: #f2f2f2;
		  border: 1px solid #dddddd;
		  padding: 12px; /* Increase padding */
		  text-align: left;
		  font-weight: bold; /* Add font weight to make text bold */
		  position: sticky; /* Keep header visible while scrolling */
		  top: 0;
		  box-shadow: 0 2px 2px rgba(0, 0, 0, 0.1); /* Add shadow to header */
		  transition: background-color 0.3s ease; /* Add smooth transition */
		}
		
		/* Search bar styling */
		#employeeDashboard_filter {
		  display: flex; /* Use flexbox */
		  justify-content: flex-end; /* Align items to the right */
		  /* margin-bottom: 20px; */ /* Add some bottom margin for spacing */
		  padding-bottom: 10px;
		  
		}
		
		#employeeDashboard_filter input[type="search"] {
		  width: 250px; /* Set width of the search input */
		  padding: 10px; /* Add padding */
		  border: 1px solid #cccccc; /* Add border */
		  border-radius: 5px; /* Add border radius for rounded corners */
		  box-sizing: border-box; /* Include padding and border in the width */
		  background-color: #f2f2f2; /* Set background color */
		  color: #333333; /* Set text color */
		  font-size: 14px; /* Set font size */
		}
		
		/* Styling for search placeholder */
		#employeeDashboard_filter input[type="search"]::placeholder {
		  color: #999999; /* Set placeholder text color */
		}


		
		/* Body cell styling */
		table.dataTable tbody td {
		  border: 1px solid #dddddd;
		  padding: 12px; /* Increase padding */
		  text-align: left;
		  vertical-align: middle; /* Center text vertically */
		}
		
		/* Alternate row styling */
		table.dataTable tbody tr:nth-child(even) {
		  background-color: #f9f9f9;
		}
		
		/* Hover effect on rows */
		table.dataTable tbody tr:hover {
		  background-color: #f5f5f5; /* Add hover effect */
		}
		
		/* Transition effect for table cells */
		table.dataTable th,
		table.dataTable td {
		  transition: background-color 0.3s ease; /* Add smooth transition */
		}


        /* Styling for buttons */
        .actionBtns {
            display: inline-block;
            padding: 5px;
        }
        .actionBtns button {
            background-color: #ff0500;
            border: none;
            color: #ffffff;
            padding: 10px 24px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin-right: 5px;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        .actionBtns button:hover {
            background-color: #0056b3;
        }

        /* Header styling */
        #header {
            background-color: #00040e;
            color: #ffffff;
            padding: 10px;
            text-align: center;
            height: 50px;
        }
        #header h1 {
            margin: 0;
        }
        #header .welcomeMsg {
		  font-size: 30px;
		  margin-top : 10px;
		  margin-right: 100px; /* changed to margin-right to align to the right side */
		  font-style: italic; /* Add font style */
		  font-weight: bold; /* Add bold font weight */
		  color: #ffcc00; /* Add color */
		  text-align: right; /* Align text to the right side */
		}
        #header .btnGroup {
            float: left;
        }
        #header .btnGroup button {
            background-color: transparent;
            border: none;
            color: #ffffff;
            font-size: 16px;
            cursor: pointer;
            margin-right: 10px;
        }
        #header .btnGroup button:hover {
            text-decoration: underline;
        }
        
        
        
        /* Add/Edit Task Modal */
        #taskModal {
		    display: none; /* Initially hide the modal */
		    position: absolute; /* Position the modal absolutely */
		    top: 50%; /* Center the modal vertically */
		    left: 50%; /* Center the modal horizontally */
		    transform: translate(-50%, -50%); /* Center the modal precisely */
		    z-index: 9999; /* Ensure the modal is above other content */
		}
        #taskModal .modal-dialog {
		    width: 400px;
		    border-radius: 5px;
		    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add a subtle shadow effect */
		    margin: auto;
		    flex-direction: column;
		    align-items: center;
		    background-color: #fff; /* Set background color for the modal */
		}
		}
		#taskModal .modal-content {
			width: 400px;
			
		    border-radius: 5px;
		    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add a subtle shadow effect */
		    margin: 1rem;
		    flex-direction: column;
		    align-items: center;
		    
		    
		}
		
		#taskModal .modal-header {
		    background-color: #007bff;
		    color: #fff;
		    border-top-left-radius: 5px;
		    border-top-right-radius: 5px;
		    display: flex;
		    align-items: center;
		    justify-content: space-between;
		    width: 90%;
		    padding: 10px 20px;
		}
		
		#taskModal .modal-title {
		    margin-left: 10px;
		}
		
		#taskModal .form-group {
		    margin-bottom: 20px;
		    width: 100%;
		    text-align: center;
		    
		}
		
		#taskModal .form-control {
		    border-radius: 3px;
		    width: 95%; /* Adjust the width as needed */
		    max-width: 400px; /* Optionally, set a maximum width */
		    padding: 10px; /* Add padding to the text box */
		    box-sizing: border-box; /* Ensure padding and border are included in the width */
		}
		
		#taskModal .modal-footer {
		    display: flex;
		    align-items: center;
		    justify-content: center;
		    width: 50%;
		    padding: 10px 20px;
		}
		#taskModal .modal-footer .btn {
		    display: block; /* Change display to block */
		    text-align: center; /* Align button text to center */
		    margin: 5px auto; /* Center the buttons horizontally */
		    width: 100px;
		    border-radius: 3px;
		    padding: 10px 20px;
		}
		/* Close button style */
		#taskModal .close {
		    color: #fff;
		}
		
		/* Hover effect for close button */
		#taskModal .close:hover {
		    opacity: 0.8;
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
 <div id="employeeDashboard_wrapper">
	<!-- <div id="employeeDashboard_filter">
          <button id="addTaskBtn" class="btn btn-primary"><i class="far fa-plus-square"></i>Add New Task</button>        
    </div> -->
    <table id="employeeDashboard" class="display">
    
        <thead>
            <tr>
            	
                <th>Task Name</th>
                <th>Task Status</th>
                <th>Create Time</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        
            <%-- Populate table body with data --%>
            <s:iterator value="tasks">
                <tr>
                	
                    <%-- <td><s:property value="taskName" /></td> --%>
                    <td><a href="addComment?taskID=<s:property value='taskID'/>&taskName=<s:property value='taskName'/>&assignedTo=<s:property value='userID'/>&createTime=<s:property value='createTime'/>&userName=<s:property value='userName'/>">
                        <s:property value="taskName" />
                      </a></td>
                    <td><s:property value="taskStatus" /></td>
                    <td><s:property value="createTime" /></td>
                    <td class="actionBtns">
                        <button class="deleteBtn" data-taskID="<s:property value='taskID'/>"><i class="far fa-trash-alt"></i></button>
                        <button class="editBtn" data-taskID="<s:property value='taskID'/>" data-createdBy="<s:property value='createdByUser.firstName'/>"><i class="far fa-edit"></i></button>
                        
                    </td>
                </tr>
            </s:iterator>
        </tbody>
    </table>
    
  </div>  
  
    <!-- Add/Edit Task Modal -->
<div class="modal fade" id="taskModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="taskForm">
                <div class="modal-header">
                    <h4 class="modal-title">Add Task</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="taskID" id="taskID">
                    <div class="form-group">
                        <label for="taskName">Task Name</label>
                        <input type="text" class="form-control" name="taskName" id="taskName" required>
                    </div>
                    <div class="form-group">
                        <label for="taskStatus">Task Status</label>
                        <input type="text" class="form-control" name="taskStatus" id="taskStatus" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="createTime">Create Time</label>
                        <input type="text" class="form-control" name="createTime" id="createTime" required>
                    </div>
                    
                    <input type="hidden" id="assignedTo" name="assignedTo" />
                    <input type="hidden" id="createdBy" name=createdBy />
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>


    
    
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Include DataTables JavaScript -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    
    <script type="text/javascript">
        $(document).ready(function() {
        	
        	//$('#taskModal').hide();
            // Initialize DataTable
         

        var dataTable = $('#employeeDashboard').DataTable();
        

	        // Delete functionality
	        $('#employeeDashboard').on('click', '.deleteBtn', function() {
			    let deleteBtn = $(this); // Store reference to the button element
			    let taskId = deleteBtn.data('taskid');
			
			    if (confirm('Are you sure you want to delete this record?')) {
			        // Perform delete action
			        $.ajax({
			            url: 'deleteTask', // Adjust URL as needed
			            type: 'POST',
			            data: { taskID: taskId },
			            
			            success: function(response) {
	                        // Remove the row from the DataTable
	                        alert('Task Deleted successfully.');
			            	dataTable.row(deleteBtn.parents('tr')).remove().draw();
	                    },
	                    error: function(xhr, status, error) {
	                        console.error(xhr.responseText);
	                        alert('Error deleting task.');
	                        dataTable.row(deleteBtn.parents('tr')).remove().draw();
	                    }

			        });
			    }
			});
	        

	        $('#employeeDashboard tbody').on('click', '.editBtn', function() {
	        	$('#taskModal').fadeIn();
	        	
	        	 let taskID = $(this).data('taskid');
	        	 console.log(taskID);
	        	 let taskName = $(this).closest('tr').find('td:eq(0)').text();
	        	 let projectName = $(this).closest('tr').find('td:eq(1)').text();
	        	 let taskStatus = $(this).closest('tr').find('td:eq(2)').text();
	        	 console.log($(this).closest('tr').html());
	        	 let createdBy = $(this).data('createdby'); // Assuming createdBy is in the 6th column
	        	 console.log(createdBy);

	          // Get the current date and time
	             let currentDate = new Date();

				// Get the current time in the desired format 'Y-m-d H:i:s'
				let formattedDate = currentDate.getFullYear() + '-' +
				                    ('0' + (currentDate.getMonth() + 1)).slice(-2) + '-' +
				                    ('0' + currentDate.getDate()).slice(-2) + ' ' +
				                    ('0' + currentDate.getHours()).slice(-2) + ':' +
				                    ('0' + currentDate.getMinutes()).slice(-2) + ':' +
				                    ('0' + currentDate.getSeconds()).slice(-2);
				
				console.log(formattedDate);


	             // Set the value of the input field to the formatted current date and time
	             

	            
	            let currentUser = '<%= session.getAttribute("currentUser") %>';
	            let currentUserID = '<%= session.getAttribute("id") %>';
				
	            $('#taskID').val(taskID);
	            $('#taskName').val(taskName);
	            $('#projectName').val(projectName);
	            $('#taskStatus').val(taskStatus);
	            $('#modifiedBy').val(currentUser);
	            $('#assignedTo').val(currentUser);
	            $('#createdBy').val(createdBy);
	            $('#createTime').val(formattedDate);
	            
	            $('#taskModal .modal-title').text('Edit Task');
	            //$('#taskModal').modal('show');
	        });

	        
	        $('#taskForm').submit(function(e) {
	            e.preventDefault();
	            
	            let formData = $(this).serialize();
	            console.log(formData);
	            var url = $('#taskID').val() ? 'updateTask' : 'save'; // Adjust URLs as needed
	            $.ajax({
	                url: url,
	                method: 'POST',
	                data: formData , // Ensure this matches the parameter name expected by the server
	                    // other parameters...
	                
	                success: function(response) {
	                    alert('Task saved successfully');
	                    $('#taskModal').fadeOut();
	                    window.location.reload();
	                    // Reload the table or update the row accordingly
	                },
	                error: function(response) {
	                    alert('Failed to saved Task');
	                }
	            });
	        });

	        
			
	        
	     	// Initially hide the form
	        $('#taskModal').hide();

	     	// Event handler for closing the form
	        $('.close').click(function() {
	        	$('#taskModal').fadeOut();// Hide the form
	        });
	     	
	        $('.btn-secondary').click(function() {
	            // Fade out the form modal
	            $('#taskModal').fadeOut();
	        });

            // Event handler for back button click
            $('#backBtn').click(function() {
                // Navigate back to the previous page
                history.back();
            });

            // Event handler for logout button click
            $('#logoutBtn').click(function() {
            	
            	// End the session
            	  sessionStorage.clear();
            	  localStorage.clear();
                // Logout functionality
                window.location.href = "login.jsp"; // Replace with your logout URL
            });
        });
    </script>
    
   
</body>

</html>