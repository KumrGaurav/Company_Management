<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task Details</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css" rel="stylesheet" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css" rel="stylesheet" />
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
<script type="text/javascript">
Ext.onReady(function() {
    const projectID = 1;
    const projectName = "Project 1";
    var userID = '<%=session.getAttribute("id")%>';
    var currentUser = '<%=session.getAttribute("currentUser")%>';
    console.log("The current user is: ", currentUser)
	
   
    
    Ext.Ajax.request({
        url: 'projectTask',
        method: 'POST',
        params: { projectID, projectName },
        success: function(response) {
            const tasks = Ext.decode(response.responseText);
            const taskStore = Ext.create('Ext.data.Store', {
                model: 'MyApp.model.Tasks',
                data: tasks
            });

            const taskGrid = Ext.create('Ext.grid.Panel', {
                title: 'Tasks',
                store: taskStore,
                columns: [
                    {text: 'ID', dataIndex: 'id', width: 50},
                    {text: 'Task Name', dataIndex: 'taskName', width: 150},
                    {text: 'User Name', dataIndex: 'userName', width: 150},
                    {text: 'Create Time', dataIndex: 'createTime', width: 150},
                    {text: 'Task Status', dataIndex: 'taskStatus', width: 150},
                    {text: 'Project Name', dataIndex: 'projectName', width: 150}
                ],
                height: 300,
                width: 800,
                renderTo: Ext.getBody()
            });
        },
        failure: function(response) {
            console.log("response",response.responseText);
        }
    });

    //... rest of your code...
});

</script>
</head>
<body>
	<div id="comment-form"></div>
</body>
</html>
