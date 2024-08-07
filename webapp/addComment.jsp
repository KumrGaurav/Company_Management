<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8"%>
 <jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task Details</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css" rel="stylesheet" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<!-- Include Ext JS Core JavaScript from CDN -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
<script type="text/javascript">
Ext.onReady(function() {
	  // Get parameters from request
	  const taskID = '<%=request.getParameter("taskID")%>';
	  const taskName = '<%=request.getParameter("taskName")%>';
	  const assignedTo = '<%=request.getParameter("userID")%>';
	  const userName = '<%=request.getParameter("userName")%>';
	  const createTime = '<%=request.getParameter("createTime")%>';
	  // Load comments
	  Ext.Ajax.request({
	    url: 'loadComments',
	    method: 'POST',
	    params: { taskID },
	    success: function(response) {
	      const commentsHtml = response.responseText;
	      
	      // Create comment form
	      const commentForm = Ext.create('Ext.form.Panel', {
	        id: 'commentForm',
	        renderTo: Ext.getBody(),
	        title: 'Task Details of : ' + taskName,
	        width: 800,
	        height: 500,
	        bodyPadding: 10,
	        layout: {
	          type: 'vbox',
	          align: 'stretch'
	        },
	        items: [{
	          xtype: 'fieldset',
	          flex: 1,
	          title: 'Task Details',
	          collapsible: true,
	          defaults: {
	            labelWidth: 80
	          },
	          items: [{
	            xtype: 'displayfield',
	            fieldLabel: 'Task Name',
	            value: taskName
	          }, {
	            xtype: 'displayfield',
	            fieldLabel: 'Assigned To',
	            
	            value: userName
	          }, {
	            xtype: 'displayfield',
	            fieldLabel: 'Create Time',
	            value: createTime
	          }]
	        }, {
	          xtype: 'container',
	          flex: 5,
	          margin: '10 0', // Add some margin for spacing
	          items: [{
	            xtype: 'component',
	            html: commentsHtml, // Insert comments HTML directly
	          }]
	        }, {
	          xtype: 'textarea',
	          name: 'comment',
	          fieldLabel: 'Comment',
	          allowBlank: false,
	          height: 120,
	          flex: 1
	        }],

	        buttons: [{
	          text: 'Save',
	          handler: function(btn) {
	            const form = this.up('form');
	            const formData = form.getForm().getValues();

	            Ext.Ajax.request({
	              url: 'saveComment',
	              method: 'POST',
	              params: { formData, taskID, assignedTo, createTime },
	              success: function(response) {
	                window.location.reload();
	              },
	              failure: function(response) {
	                Ext.Msg.alert('Error', 'Failed to save comment!');
	              }
	            });
	          }
	        }]
	      });

	      // Center the form on the screen
	      const bodyWidth = Ext.getBody().getWidth();
	      const bodyHeight = Ext.getBody().getHeight();
	      const formWidth = commentForm.getWidth();
	      const formHeight = commentForm.getHeight();
	      commentForm.setPosition((bodyWidth - formWidth) / 2, (bodyHeight - formHeight) / 2);
	    },
	    failure: function(response) {
	      console.log('Failed to load comments. Error:', response.statusText);
	      Ext.Msg.alert('Error', 'Failed to load the comment!');
	    }
	  });
	});
</script>
</head>
<body>
<%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
    %>
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
	<div id="comment-form"></div>
</body>
</html>