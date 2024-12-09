<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>audio</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="../../css/general/general.css">
    <link rel="stylesheet" href="../../css/login/login.css">
</head>
<body>
    <div class="login-container">
        <form action="${pageContext.request.contextPath}/login" method="POST" class="login-form">
            <h2>Login</h2>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Username" value="" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Password" value="" required>
            </div>
            <button type="submit" class="login-btn">Submit</button>
        </form>
    </div>
</body>
</html>