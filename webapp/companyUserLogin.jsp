<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task Details</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css" rel="stylesheet" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
<script type="text/javascript">
Ext.onReady(function() {
    Ext.create('Ext.window.Window', {
        xtype: 'login',
        title: 'Company User Page',
        closable: false,
        autoShow: true,
        bodyPadding: 10,
        items: [{
            xtype: 'form',
            items: [{
                xtype: 'textfield',
                fieldLabel: 'Company Name',
                name: 'companyName'
            }],
            buttons: [{
                text: 'Login',
                handler: function() {
                    var form = this.up('form');
                    var companyName = form.getValues().companyName;         
                    window.location.href = 'companyUser.jsp?companyName=' + companyName;
                }
            }]
        }]
    });
});
</script>
</head>
<body>
	
</body>
</html>
