<!DOCTYPE html>
<html>
<head>
<title>WattDepot Administration</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link rel="stylesheet" href="/webroot/dist/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="/webroot/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet/less" type="text/css" href="/webroot/dist/css/style.less">
<script src="/webroot/dist/js/less-1.3.0.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row row-offcanvas row-offcanvas-left">
            <div class="col-xs-6 col-sm-3">
                <h2>Users</h2>
                <table>
                    <thead>
                        <tr><th>Id</th><th>First Name</th><th>Last Name</th><th>Admin Status</th><th>Email address</th></tr>
                    </thead>
                    <tbody>
                    <#list users as u>
    <tr><td>${u.id}</td><td>${u.firstName}</td><td>${u.lastName!}</td><td><#if u.admin>Is Admin</#if></td><td>${u.email!}</td></tr>
                    </#list>
                    </tbody>
                </table>
                <form name="new_user" action="/wattdepot/admin/user" method="post">
                    id: <input type="text" name="id"><br>
                    first name: <input type="text" name="firstname"><br>
                    last name: <input type="text" name="lastname"><br>
                    password: <input type="password" name="password"><br>
                    email: <input type="email" name="email"<br> 
                    <input type="submit" value="Add New User">
                </form>
                
            </div>
        </div>
    </div>
</body>
</html>