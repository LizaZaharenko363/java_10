<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JMS Messaging Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            text-align: center;
        }
        .links {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 20px;
        }
        .links a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .links a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Lab 10</h1>

<div class="links">
    <a href="SendMessage.jsp">Send Message</a>
    <a href="ReceiveMessage.jsp">Receive Message</a>
</div>
</body>
</html>