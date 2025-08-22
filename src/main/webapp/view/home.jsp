<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Landing Page</title>
    <!-- Tailwind CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 text-gray-800">

<!-- Navbar -->
<nav class="flex justify-between items-center p-6 bg-white shadow">
    <div class="text-2xl font-bold text-indigo-600">MyBrand</div>
    <div class="space-x-6">
        <a href="#features" class="hover:text-indigo-600">Features</a>
        <a href="#pricing" class="hover:text-indigo-600">Pricing</a>
        <a href="#contact" class="hover:text-indigo-600">Contact</a>
    </div>
    <a href="/login" class="px-4 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700">Login</a>
</nav>

<!-- Hero Section -->
<section class="text-center py-20 bg-gradient-to-r from-indigo-500 to-purple-600 text-white">
    <h1 class="text-5xl font-extrabold mb-6">Welcome to My Landing Page 🚀</h1>
    <p class="text-xl mb-8">A modern, responsive landing page built with TailwindCSS</p>
    <a href="#get-started" class="px-6 py-3 bg-white text-indigo-600 font-semibold rounded-lg shadow hover:bg-gray-100">
        Get Started
    </a>
</section>

<!-- Features -->
<section id="features" class="py-20 max-w-6xl mx-auto">
    <h2 class="text-3xl font-bold text-center mb-12">Our Features</h2>
    <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
        <div class="p-6 bg-white rounded-xl shadow hover:shadow-lg">
            <h3 class="text-xl font-semibold mb-4">🔥 Fast</h3>
            <p>Lightning-fast load time optimized with modern technology.</p>
        </div>
        <div class="p-6 bg-white rounded-xl shadow hover:shadow-lg">
            <h3 class="text-xl font-semibold mb-4">🔒 Secure</h3>
            <p>Security is our top priority to protect your data.</p>
        </div>
        <div class="p-6 bg-white rounded-xl shadow hover:shadow-lg">
            <h3 class="text-xl font-semibold mb-4">⚡ Scalable</h3>
            <p>Easily scale your business with our cloud-ready architecture.</p>
        </div>
    </div>
</section>

<!-- Pricing -->
<section id="pricing" class="py-20 bg-gray-100">
    <h2 class="text-3xl font-bold text-center mb-12">Pricing</h2>
    <div class="flex justify-center gap-8">
        <div class="p-8 bg-white rounded-xl shadow-lg w-64 text-center">
            <h3 class="text-xl font-semibold mb-4">Basic</h3>
            <p class="text-2xl font-bold mb-6">$9/mo</p>
            <ul class="mb-6 space-y-2">
                <li>✔ 10 Projects</li>
                <li>✔ Basic Support</li>
            </ul>
            <a href="#" class="px-4 py-2 bg-indigo-600 text-white rounded-lg">Choose</a>
        </div>
        <div class="p-8 bg-white rounded-xl shadow-lg w-64 text-center border-2 border-indigo-600">
            <h3 class="text-xl font-semibold mb-4">Pro</h3>
            <p class="text-2xl font-bold mb-6">$29/mo</p>
            <ul class="mb-6 space-y-2">
                <li>✔ Unlimited Projects</li>
                <li>✔ Premium Support</li>
                <li>✔ Analytics</li>
            </ul>
            <a href="#" class="px-4 py-2 bg-indigo-600 text-white rounded-lg">Choose</a>
        </div>
    </div>
</section>

<!-- Footer -->
<footer id="contact" class="bg-gray-900 text-gray-400 py-8 text-center">
    <p>© 2025 MyBrand. All rights reserved.</p>
</footer>

</body>
</html>

