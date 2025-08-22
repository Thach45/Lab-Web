<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen flex items-center justify-center bg-gray-100">
<div class="w-full max-w-md bg-white rounded-2xl shadow-xl p-8">
    <h2 class="text-3xl font-bold text-center text-gray-800 mb-6">Login</h2>

    <!-- Form login -->
    <form action="loginCookie" method="post" class="space-y-5">
        <div>
            <label for="username" class="block text-sm font-medium text-gray-700">Username</label>
            <input type="text" id="username" name="username"
                   class="mt-1 w-full px-4 py-2 border border-gray-300 rounded-xl shadow-sm focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 text-gray-800"
                   placeholder="Enter your username" required>
        </div>

        <div>
            <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
            <input type="password" id="password" name="password"
                   class="mt-1 w-full px-4 py-2 border border-gray-300 rounded-xl shadow-sm focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 text-gray-800"
                   placeholder="Enter your password" required>
        </div>

        <button type="submit"
                class="w-full py-2 px-4 bg-indigo-600 hover:bg-indigo-700 text-white font-semibold rounded-xl shadow-md transition duration-300">
            Sign In
        </button>
    </form>

    <!-- Link đăng ký -->
    <p class="mt-6 text-sm text-center text-gray-600">
        Don’t have an account?
        <a href="register.jsp" class="text-indigo-600 hover:underline font-medium">Register</a>
    </p>
</div>
</body>
</html>
