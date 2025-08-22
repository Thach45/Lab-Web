<%--
  Created by IntelliJ IDEA.
  User: macpro
  Date: 22/8/25
  Time: 09:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center h-screen bg-gray-100">
<div class="bg-white p-8 rounded-2xl shadow-md w-96">
    <h2 class="text-2xl font-bold text-center mb-6">Login Session</h2>
    <form action="loginSession" method="post" class="space-y-4">
        <div>
            <label class="block mb-1 text-gray-700">Username</label>
            <input type="text" name="username" class="w-full px-3 py-2 border rounded-lg focus:ring focus:ring-blue-200" required>
        </div>
        <div>
            <label class="block mb-1 text-gray-700">Password</label>
            <input type="password" name="password" class="w-full px-3 py-2 border rounded-lg focus:ring focus:ring-blue-200" required>
        </div>
        <button type="submit" class="w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600">Login</button>
    </form>

    <p class="text-center text-red-500 mt-4">
        ${error != null ? error : ""}
    </p>
</div>
</body>
</html>
