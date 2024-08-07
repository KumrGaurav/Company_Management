<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project List</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css" rel="stylesheet" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css" rel="stylesheet" />
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- Include Ext JS Core JavaScript from CDN -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
</head>
<body>

	<div id="companies"></div>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>

	<script type="text/javascript">
	Ext.onReady(function () {
		
	    const companyName = '<%=request.getParameter("companyName")%>';

	    Ext.create('Ext.container.Viewport', {
	        layout: 'border',
	        items: [{
	            region: 'north',
	            xtype: 'panel',
	            title: 'Company Management System',
	            height: 50,
	            bodyPadding: 10,
	            html: '<h1>Company Name: ' + companyName + '</h1>'
	        },{
	            region: 'center',
	            xtype: 'panel',
	            title: 'Projects',
	            items: [{
	                xtype: 'form',
	                title: 'Add Project',
	                bodyPadding: 10,
	                items: [{
	                    xtype: 'textfield',
	                    name: 'projectName',
	                    fieldLabel: 'Project Name'
	                }, {
	                    xtype: 'textfield',
	                    name: 'employeeName',
	                    fieldLabel: 'Assign To'
	                }],
	                buttons: [{
	                    text: 'Add',
	                    handler: function (btn) {
	                        var form = btn.up('form').getForm();
	                     
	                        if (form.isValid()) {
	                            var projectName = form.findField('projectName').getValue();
	                            var employeeName = form.findField('employeeName').getValue();
	                            Ext.Ajax.request({
	                                url: 'addCompanyProject', // adjust the URL to your server-side endpoint
	                                method:'POST',
	                                params: {
	                                	projectName,employeeName,companyName},
	                                success: function (response) {
	                                    // handle success response
	                                    Ext.Msg.alert('Success', 'Project added successfully');
	                                    console.log('Project added successfully!');
	                                    loadProjects(); // Load projects after adding a new one
	                                },
	                                failure: function (response) {
	                                    // handle failure response
	                                     Ext.Msg.alert('Failure', 'Error adding project');
	                                    console.log('Error adding project:', response);
	                                }
	                            });
	                        }
	                    }
	                }]
	            }, {
	                xtype: 'grid',
	                title: 'Projects',
	                store: Ext.create('Ext.data.Store', {
	                    fields: ['projectName', 'employeeName', 'status'],
	                    data: []
	                }),
	                columns: [{
	                    text: 'Name',
	                    dataIndex: 'projectName'
	                }, {
	                    text: 'Assigned To',
	                    dataIndex: 'employeeName'
	                }, {
	                    text: 'Status',
	                    dataIndex: 'status'
	                }]
	            }]
	        }, {
	            region: 'east',
	            xtype: 'panel',
	            title: 'Tasks',
	            width: 400,
	            collapsible: true,
	            items: [{
	                xtype: 'form',
	                title: 'Add Task',
	                bodyPadding: 10,
	                items: [{
	                    xtype: 'textfield',
	                    name: 'taskName',
	                    fieldLabel: 'Task'
	                }, {
	                    xtype: 'textfield',
	                    name: 'projectName',
	                    fieldLabel: 'Project Name'
	                }, {
	                    xtype: 'textfield',
	                    name: 'employeeName',
	                    fieldLabel: 'Assigned To'
	                },{
	                    xtype: 'textfield',
	                    name: 'taskStatus',
	                    fieldLabel: 'Task Status',
	                },{
	                    xtype: "datefield",
	                    name: "createTime",
	                    fieldLabel: "Create Time",
	                    value: new Date(), // Set the value to the current date and time
	                    format: 'Y-m-d H:i:s', // Use the format that matches your Java Date format
	                    submitFormat: 'Y-m-d H:i:s', // Use the same format for form submission
	                    dateConfig: {
	                        time: true, // Enable time selection
	                        minuteStep: 1 // Set the minute step to 1 for precise time input
	                    }
	                }],
	                buttons: [{
	                    text: 'Create',
	                    handler: function (btn) {
	                        var form = btn.up('form').getForm();
	                                
	                        if (form.isValid()) {
	                        	var taskName = form.findField('taskName').getValue();
	                        	 var projectName = form.findField('projectName').getValue();
	                            var employeeName = form.findField('employeeName').getValue();
	                            var taskStatus = form.findField('taskStatus').getValue();
	                            var createTime = form.findField('createTime').getValue();
	                                
	                            Ext.Ajax.request({
	                                url: 'addCompanyTask', // adjust the URL to your server-side endpoint
	                                method:'POST',
	                                params: {
	                                	taskName,projectName,employeeName,taskStatus,createTime,companyName
	                                },
	                                success: function (response) {
	                                    // handle success response
	                                    Ext.Msg.alert('Success', 'Task added successfully');
	                                },
	                                failure: function (response) {
	                                    // handle failure response
	                                     Ext.Msg.alert('Failure', 'Error adding Task');
	                                }
	                            });
	                        }
	                    }
	                }]
	            }, {
	                xtype: 'grid',
	                title: 'Tasks',
	                store: Ext.create('Ext.data.Store', {
	                    fields: ['name', 'dueDate', 'status','assignedTo'],
	                    data: []
	                }),
	                columns: [{
	                    text: 'Name',
	                    dataIndex: 'name'
	                }, {
	                    text: 'Due Date',
	                    dataIndex: 'dueDate'
	                }, {
	                    text: 'Status',
	                    dataIndex: 'status'
	                }, {
	                    text: 'Assigned To',
	                    dataIndex: 'assignedTo'
	                }]
	            }]}]
	    });

	    // Load projects when the page loads
	    loadProjects();

	    function loadProjects() {
	        Ext.Ajax.request({
	            url: 'showCompanyProjects', // adjust the URL to your server-side endpoint
	            method: 'GET',
	            success: function (response) {
	                const projectsHtml = response.responseText;

	                // Create project container
	                const projectContainer = Ext.create('Ext.container.Container', {
	                  id: 'projectContainer',
	                  renderTo: Ext.getBody(),
	                  title: 'Projects',
	                  width: 800,
	                  height: 900,
	                  bodyPadding: 10,
	                  layout: {
	                    type: 'vbox',
	                    align: 'stretch'
	                  },
	                  items: [{
	                    xtype: 'fieldset',
	                    flex: 1,
	                    title: 'Projects',
	                    collapsible: true,
	                    defaults: {
	                      labelWidth: 80
	                    },
	                    items: [{
	                      xtype: 'component',
	                      html: projectsHtml, // Insert projects HTML directly
	                    }]
	                  }]
	                });
	            },
	            failure: function (response) {
	                console.log('Error loading projects:', response);
	            }
	        });
	    }
	});
</script>
</body>

</html>