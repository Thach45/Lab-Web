<%--
  Created by IntelliJ IDEA.
  User: macpro
  Date: 27/8/25
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<header id="header" class="bg-white/80 backdrop-blur-lg shadow-sm sticky top-0 z-50 transition-all duration-300">
    <nav class="container mx-auto px-6 py-4 flex justify-between items-center">
        <a href="#" class="text-3xl font-extrabold text-pink-500">BobaBliss🥤</a>

        <!-- Desktop Navigation Links -->
        <div class="hidden md:flex space-x-8 items-center font-medium text-gray-600">
            <a href="/product" class="hover:text-pink-500 transition duration-300">Thực đơn</a>
            <a href="#about" class="hover:text-pink-500 transition duration-300">Về chúng tôi</a>
            <a href="#contact" class="hover:text-pink-500 transition duration-300">Liên hệ</a>
        </div>
        <c:choose>
            <c:when test="${not empty userName}">
              <a href="#" class="hidden md:block bg-pink-500 text-white py-2 px-5 rounded-full font-bold hover:bg-pink-600 transition-transform hover:scale-105 duration-300 shadow-lg shadow-pink-500/30">Đặt hàng ngay</a>
                <a href="/logout" class="hidden md:block bg-pink-500 text-white py-2 px-5 rounded-full font-bold hover:bg-pink-600 transition-transform hover:scale-105 duration-300 shadow-lg shadow-pink-500/30">Đăng xuất</a>
            </c:when>
            <c:otherwise>
               <a href="/loginCookie"  class="hidden md:block bg-pink-500 text-white py-2 px-5 rounded-full font-bold hover:bg-pink-600 transition-transform hover:scale-105 duration-300 shadow-lg shadow-pink-500/30">Đăng nhập</a>
            </c:otherwise>
        </c:choose>

        <!-- Mobile Menu Button -->
        <div class="md:hidden">
            <button id="mobile-menu-button" class="text-gray-700 focus:outline-none">
                <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16m-7 6h7"></path></svg>
            </button>
        </div>
    </nav>
    <!-- Mobile Menu -->
    <div id="mobile-menu" class="hidden md:hidden px-6 pb-4 space-y-2">
        <a href="#menu" class="block text-gray-700 hover:text-pink-500">Thực đơn</a>
        <a href="#about" class="block text-gray-700 hover:text-pink-500">Về chúng tôi</a>
        <a href="#contact" class="block text-gray-700 hover:text-pink-500">Liên hệ</a>
        <a href="#" class="block bg-pink-500 text-white text-center mt-2 py-2 px-4 rounded-full font-bold">Đặt hàng ngay</a>
    </div>
</header>
