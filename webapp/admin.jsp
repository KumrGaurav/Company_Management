<jsp:include page="header.jsp" />
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<style>
        body, html {
            height: 100%;
            margin: 0;
        }
        .container-fluid {
            display: flex;
            flex-direction: column;
            height: 100%;
        }
        header {
            flex: 0 0 auto;
        }
        main {
            flex: 1 0 auto;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
    </style>
    <title>Admin Dashboard</title>
    <!-- CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
 <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
    %>
    <div class="container-fluid">        
            <!-- Main content -->
            <main role="main" class="col-md-12 ml-sm-auto col-lg-10 px-4">
                <header class="page-header">
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
                <h1 style="color: blue; font-size: 36px; text-align: center;">Welcome <%= session.getAttribute("employeeName") %></h1>
                <p style="color: gray; font-size: 24px; text-align: center;">You have access to the admin panel.</p>
            </main>

     </div>
    <!-- JavaScript -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script>
        $(document).ready(function() {
            $('.sidebar-toggle').on('click', function() {
                $('.sidebar').toggleClass('active');
            });
        });
    </script>
</body>
</html>
