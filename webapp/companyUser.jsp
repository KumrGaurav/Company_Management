<%-- 



<!-- usama code -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" />
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
	          
	            xtype: 'panel',
	            title: 'Company Management System',
	            height: 50,
	            bodyPadding: 10,
	            html: '<h1>Company Name: ' + companyName + '</h1>'
	        }, {
	            region: 'west',
	            xtype: 'panel',
	            title: 'Employees',
	            width: 400,
	            collapsible: true,
	            items: [{
	                xtype: 'form',
	                title: 'Add Employee',
	                bodyPadding: 10,
	                items: [{
	                    xtype: 'textfield',
	                    name: 'employeeName',
	                    fieldLabel: 'Name'
	                }],
	                buttons: [{
	                    text: 'Add',
	                    handler: function (btn) {
	                        var form = btn.up('form').getForm();
	                        if (form.isValid()) {
	                            var employeeName = form.findField('employeeName').getValue();
	                            Ext.Ajax.request({
	                                url: 'addCompanyEmployee', // adjust the URL to your server-side endpoint
	                                method:'POST',
	                                params: {
	                                     employeeName, companyName
	                                },
	                                success: function (response) {
	                                    // handle success response
	                                    Ext.Msg.alert('Success', 'Employee added successfully');
	                                    console.log('Employee added successfully!');
	                                },
	                                failure: function (response) {
	                                    // handle failure response
	                                    console.log('Error adding employee:', response);
	                                }
	                            });
	                        }
	                    }
	                }]
	            }, {
	                xtype: 'grid',
	                title: 'Employees',
	                store: Ext.create('Ext.data.Store', {
	                    fields: ['name', 'email', 'phone', 'role'],
	                    data: []
	                }),
	                columns: [{
	                    text: 'Name',
	                    dataIndex: 'name'
	                }, {
	                    text: 'Email',
	                    dataIndex: 'email'
	                }, {
	                    text: 'Phone',
	                    dataIndex: 'phone'
	                }, {
	                    text: 'Role',
	                    dataIndex: 'role'
	                }]
	            }]
	        }]
	    });
	});
</script>
</body>

</html>  --%>