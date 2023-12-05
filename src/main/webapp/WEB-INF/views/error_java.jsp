<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/x-icon" href="/resources/img/home/x-icon.jpg">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="stylesheet" href="/resources/css/error.css" type="text/css"/>
	<title>Java Error Page</title>
</head>

<body>
	<div class="container">
		<h1>Java Error</h1>
		<p>Sorry, Java has thrown an exception.</p>
		<p>To continue, click the Back button in your browser.</p>
	
		<h2>Details</h2>
		<p>Type: ${pageContext.exception["class"]}</p>
		<p>Message: ${pageContext.exception.message}</p>
		<br>
	    <a href="/home"><i class="fa-solid fa-arrow-left"></i>&nbsp;HOME PAGE</a>
	</div>
	
	<footer>
    	<hr>
        <p>&copy; 2023 Jacobin.com</p>
    </footer>
</body>

</html>
