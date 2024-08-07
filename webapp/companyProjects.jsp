<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task List</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
}

.table-wrapper {
	background-color: #ffffff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
<table id="tasksTable" border="1" align="center">
  <tr>
    <th>Task Name</th>
    <th>Assigned To</th>
    <th>Created By</th>
    <th>Modified By</th>
    <th>Task Status</th>
    <th>Create Time</th>
    <th>Edit</th>
  </tr>
  <s:iterator value="tasks">
    <tr>
      <td><s:property value="taskName" /></td>
      <td><s:property value="id.firstname" /></td>
      <td><s:property value="createdByUser.firstName" /></td>
      <td><s:property value="modifiedByUser.firstName" /></td>
      <td><s:property value="taskStatus" /></td>
      <td><s:property value="createTime" /></td>
      <td><button class="editTaskBtn" data-task-id="<s:property value='taskID' />">Edit</button></td>
    </tr>
  </s:iterator>
</table>

<script type="text/javascript">
  $(document).ready(function() {
    var editor = new $.fn.dataTable.Editor({
      ajax: {
        url: 'editTask', // your AJAX endpoint for editing tasks
        type: 'POST'
      },
      table: '#tasksTable',
      fields: [
        { label: 'Task Name', name: 'taskName' },
        { label: 'Assigned To', name: 'assignedTo' },
        { label: 'Created By', name: 'createdBy' },
        { label: 'Modified By', name: 'odifiedBy' },
        { label: 'Task Status', name: 'taskStatus' },
        { label: 'Create Time', name: 'createTime' }
      ]
    });

    $('#tasksTable').DataTable({
      dom: 'Bfrtip',
      buttons: [
        { extend: 'edit', editor: editor }
      ]
    });

    $('.editTaskBtn').on('click', function() {
      var taskId = $(this).data('task-id');
      editor.edit(taskId);
    });
  });
</script>
</body>
</html>