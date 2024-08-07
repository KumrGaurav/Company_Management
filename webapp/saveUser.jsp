<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Add User</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
<script type="text/javascript">
	Ext.onReady(function() {
		Ext.create('Ext.form.Panel', {
			renderTo : Ext.getBody(),
			title : 'Add User',
			width : 400,
			bodyPadding : 10,
			defaultType : 'textfield',
			items : [ {
				fieldLabel : 'ID',
				name : 'id',
				allowBlank : false
			}, {
				fieldLabel : 'Password',
				name : 'password',
				inputType : 'password',
				allowBlank : false
			}
			],
			buttons : [ {
			    text : 'Add User',
			    handler : function(btn) {
			        var data = this.up('form');
			        var userDetails = data.getForm().getValues();
			        console.log("UserDetails :",userDetails);
			        Ext.Ajax.request({
			            url: 'saveUser', 
			            method: 'POST',
			            params: userDetails,
			            success: function(response) {
			            	console.log("The response is :",response);
			            	Ext.Msg.alert('Success', 'User saved successfully');
			            },
			            failure: function(response) {
			            	Ext.Msg.alert('Error', 'Failed to save user');
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