<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>

<link href="${pageContext.request.contextPath}/stylesheet/styles.css" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>

<script>
Ext.onReady(function() {
    Ext.create('Ext.window.Window', {
        title: 'User Login',
        width: 300,
        height: 200,
        layout: 'fit',
        items: [{
            xtype: 'form',
            bodyPadding: 10,
            defaultType: 'textfield',
            items: [{
                fieldLabel: 'Username',
                name: 'firstName',
                allowBlank: false
            }, {
                fieldLabel: 'Password',
                name: 'password',
                inputType: 'password',
                allowBlank: false
            }],
            buttons: [{
                text: 'Login',
                handler: function() {
                    var form = this.up('form');
                    var userDetails = form.getForm().getValues();
                    var userName = userDetails.firstName;
                    var password = userDetails.password;
                    
                    console.log("username is : ",userName);
                    Ext.Ajax.request({
                        url: 'authentication',
                        method: 'POST',
                        params: {userName:userName,password:password},
                        success: function(response) {
                            var userName = userDetails.firstName; // assuming firstName is the username
                            window.location.href = 'dashboard.jsp?userName=' + userName;
                        },
                        failure: function(response) {
                            Ext.Msg.alert('Error', 'User not found');
                        }
                    });
                }
            }]
        }]
    }).show();
});
</script>

</head>
<body>
</body>
</html>