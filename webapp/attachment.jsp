<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attachment</title>
<style>
        .overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1000;
        }

        .popup {
            display: none;
            position: fixed;
            background-color: white;
            width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 1001;
        }
    </style>
<link rel="stylesheet" type="text/css" href="styles.css">

<jsp:include page="links.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


<script>
Ext.require([
    'Ext.data.*',
    'Ext.grid.*',
    'Ext.container.Viewport' // Ensure Viewport is required
]);


</script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>

</head>
<body>
	<%-- <jsp:include page="header.jsp" /> --%>
	<div class="container-fluid">
		<div class="row">
			<%-- <jsp:include page="sidebar.jsp" /> --%>
			<main class="content col-md-10 main">
				<h1>Welcome to Our Company!</h1>
				<br>
				<p>Attachment Page</p>
				<br>

				<div id="attachments"></div>
			</main>
		</div>
	</div>

	<%-- <jsp:include page="footer.jsp" /> --%>
	<script type="text/javascript">
	
	Ext.onReady(function () {
		 // Define the model for the Company
    	Ext.define('AttachmentModel', {
        extend: 'Ext.data.Model',
        fields: ['attachmentId', 'taskId','taskName','userName','attachmentName','attachmentFile','attachmentDateTime']
    });

    // Call the function to convert Java objects to JavaScript objects
   var attachmentStore = Ext.create('Ext.data.Store', {
        model: 'AttachmentModel',
           data: [
/*         	   {attachmentId: 1, taskName: 'Project 1',attachmentName:'hello',useerName:'surbhi',attachmentDateTime:'06/05/2024',attachmentFile:'absh'}
 */              <s:iterator value="attachments">
             {
            	 attachmentId: '<s:property value="attachmentId" />',
                 taskId: '<s:property value="taskId.taskId" />',
            	 taskName: '<s:property value="taskId.taskName" />',
                 id: '<s:property value="id.id" />',
                 userName: '<s:property value="id.firstname" />',
                 attachmentName:'<s:property value="attachmentName" />',
                 attachmentFile: '<s:property value="attachmentFile" />',
                 attachmentDateTime: '<s:property value="attachmentDate" />'
             
             }, 
             </s:iterator>         
             ]
    }); 
 

    var grid = Ext.create('Ext.grid.Panel', {
        renderTo: 'attachments',
        store: attachmentStore,
        x:270,
        y:10,
        width: '60%',
        height: 400,
        x:100,
        title: 'Attachment List',
        columns: [
        	{text: 'S_No.', xtype: 'rownumberer', width: 20 },
            {text: 'Attachment Id', dataIndex: 'attachmentId', flex: 1, hidden: true},
            {text: 'Task Name', dataIndex: 'taskName', flex: 1},
            {text: 'Attachment Description', dataIndex: 'attachmentName', flex: 2},
            {text: 'Attachment By', dataIndex: 'userName', flex: 2},
            {text: 'AttachmentDateTime', dataIndex: 'attachmentDateTime', flex: 2},
            {text: 'Attachment File', dataIndex: 'attachmentFile', flex: 2},

             {
                xtype: 'actioncolumn',
                text: 'Action',
                width: 80,
                align: 'center',
                
                items: [{
                    iconCls: 'far fa-trash-alt',
                    tooltip: 'Delete',
                    handler: function () {
                        var selectedRecord = grid.getSelectionModel().getSelection()[0];
                        if (selectedRecord) {
                            Ext.Msg.confirm('Confirmation', 'Are you sure you want to delete this record?', function (btn) {
                                if (btn === 'yes') {
                                    Ext.Ajax.request({
                                        url: 'deleteAttachment',
                                        method: 'POST',
                                        params: {
                                            id: selectedRecord.get('attachmentId') // send the employee ID to the server
                                        },
                                        success: function (response) {                       
                                            
                                            	Ext.Msg.alert('Success', 'Attachment delete successfully', function(){
                                                    window.location.reload(); // Reload the page
                                                });
                                                
                                        },
                                        failure: function (response) {
                                            Ext.Msg.alert('Error', 'Failed to delete record');
                                        } 
                                    });
                                }
                            });
                        } else {
                            Ext.Msg.alert('Error', 'Please select a Attachment to delete.');
                        }
                    
                } 
                
               }
                ] 


             } 
        ] 
        
         /*  tbar: [{
            text: 'Add',
            iconCls: 'far fa-plus-square',
            handler: function () {
                // Open a new window for adding companies
                var addAttachmentWindow = Ext.create('Ext.window.Window', {
                     title: 'Add Attachment',
                    modal: true,
                    layout: 'fit',
                    items: [{
                        xtype: 'form',
                        items: [
                        	{
                        		xtype: 'textfield',
                        		fieldLabel: 'Task Name',
                                name: 'taskName',
                                allowBlank: false
                            },  
                        	{
                            xtype: 'textfield',
                            fieldLabel: 'Attachment Description',
                            name: 'attachmentName',
                            allowBlank: false

                        },
                        {
                        	xtype: 'textfield',
                        	fieldLabel: 'Attachment By',
                            name: 'userName',
                            allowBlank: false
                        }, {
                            xtype: 'datefield',
                            fieldLabel: 'AttachmentDateTime',
                            name: 'attachmentDateTime',
                            format: 'Y-m-d H:i:s',
                            value: new Date() 
                        },
                        {
                            xtype: 'filefield',
                            id: 'fileupload',
                            name: 'attachmentFile',
                            fieldLabel: 'Choose File',
                            labelWidth: 100,
                            msgTarget: 'side',
                            allowBlank: false,
                            anchor: '100%',
                            buttonText: 'Browse...',
                            disabled: false
                        }  
                        
                        ],
                        
           
                        buttons: [{
                            text: 'Save',
                            handler: function(btn) {
            			        var data = this.up('form');
                                var attachmentDetails = data.getForm().getValues();

                                Ext.Ajax.request({
                                    url: 'saveAttachment', // Adjust the URL as needed
                                    method: 'POST',
                                    params: attachmentDetails,
                                    success: function(response) {
                                    	Ext.Msg.alert('Success', 'Attachment saved successfully', function(){
                                            window.location.reload(); // Reload the page
                                        });
                                        
                                    	addAttachmentWindow.close();

                                    },
                                    failure: function(response) {
                                        Ext.Msg.alert('Error', 'Failed to save Employee');
                                    }
                                });
                            }
                        }, {
                            text: 'Cancel',
                            handler: function () {
                            	addAttachmentWindow.close();
                            }
                        }]

                    }]
                });

                addAttachmentWindow.show();
            }
        }]  */
    } ) ; 
 } ); 
	
</script>
 <button id="openPopup" class="add_button">Add</button>
    <div id="popupOverlay" class="overlay"></div>
    <div id="addAttachmentPopup" class="popup">
        <h2>Add Attachment</h2>
        <form id="attachmentForm" enctype="multipart/form-data">
            <label for="taskName">Task Name:</label><br>
            <input type="text" id="taskName" name="taskName" required><br>
            
            <label for="attachmentName">Attachment Description:</label><br>
            <input type="text" id="attachmentName" name="attachmentName" required><br>
            
            <label for="userName">Attachment By:</label><br>
            <input type="text" id="userName" name="userName" required><br>
            
            <label for="attachmentDateTime">Attachment Date Time:</label><br>
            <input type="datetime-local" id="attachmentDateTime" name="attachmentDateTime" required><br>
            
            <label for="attachmentFile">Choose File:</label><br>
            <input type="file" id="file" name="file" required><br>
            
            <button type="button" id="saveButton">Save</button>
            <button type="button" id="cancelButton">Cancel</button>
        </form>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var openPopupButton = document.getElementById('openPopup');
            var popupOverlay = document.getElementById('popupOverlay');
            var addAttachmentPopup = document.getElementById('addAttachmentPopup');

            openPopupButton.addEventListener('click', function() {
                popupOverlay.style.display = 'block';
                addAttachmentPopup.style.display = 'block';
            });

            var saveButton = document.getElementById('saveButton');
            saveButton.addEventListener('click', function() {
                var attachmentForm = document.getElementById('attachmentForm');
               
             // Assuming you have a reference to your form element called 'formElement'
                var formData = new FormData(attachmentForm);
                /* fetch('saveAttachment', {
                    method: 'POST',
                    body: formData
                })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Failed to save attachment');
                    }
                })
                .then(data => {
                    // Success
                    alert('Success: Attachment saved successfully');
                    window.location.reload(); // Reload the page
                })
                .catch(error => {
                    // Failure
                    alert('Error: ' + error.message);
                }); */
                fetch('saveAttachment', {
                    method: 'POST',
                    body: formData
                })
                .then(response => {
                    if (response.ok) {
                        // If the response is successful (status code 200-299), no need to parse JSON
                        // You can just proceed with the next steps
                        alert('Success: Attachment saved successfully');
                        window.location.reload(); // Reload the page
                    } else {
                        throw new Error('Failed to save attachment');
                    }
                })
                .catch(error => {
                    // Failure
                    alert('Error: ' + error.message);
                });

            });

            var cancelButton = document.getElementById('cancelButton');
            cancelButton.addEventListener('click', function() {
                popupOverlay.style.display = 'none';
                addAttachmentPopup.style.display = 'none';
            });
        });
    </script>
</body>
</html>