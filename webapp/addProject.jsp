<!-- Taking the details from front-end by the user and saving it to the database -->
 <jsp:include page="header.jsp" />
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Add Project</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
<script type="text/javascript">
	Ext.onReady(function() {
		Ext.create('Ext.form.Panel', {
			renderTo : Ext.getBody(),
			title : 'Add Project',
			width : 400,
			bodyPadding : 10,
			defaultType : 'textfield',
			items : [ {
				fieldLabel : 'Project ID',
				name : 'projectID',
				allowBlank : false
			}, {
				fieldLabel : 'Project Name',
				name : 'projectName',
				inputType : 'text',
				allowBlank : false
			},{
				fieldLabel : 'User ID',
				name : 'userID',
				allowBlank : false
			}
			
			],
			
			buttons : [ {
			    text : 'Add Project',
			    handler : function(btn) {
			        var data = this.up('form');
			        var projectDetails = data.getForm().getValues();

			        Ext.Ajax.request({
			            url: 'saveProject', // Write this in the struts 
			            method: 'POST',
			            params: projectDetails,
			            success: function(response) {
			            	Ext.Msg.alert('Success', 'Project details saved successfully');
			            },
			            failure: function(response) {
			            	Ext.Msg.alert('Error', 'Failed to save project details');
			            }
			        });
			    }
			}]
		});
		
	});
</script>
</head>
<body>

</body>
</html>