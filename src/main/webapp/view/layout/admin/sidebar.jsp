<%--
  Created by IntelliJ IDEA.
  User: macpro
  Date: 29/8/25
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="relative min-h-screen flex flex-col md:flex-row">
    <!-- Mobile menu button -->
    <div class="bg-gray-800 text-gray-100 flex justify-between md:hidden">
        <a href="#" class="block p-4 text-white font-bold">Trang Admin</a>
        <button id="mobile-menu-button" class="p-4 focus:outline-none focus:bg-gray-700">
            <i class="fas fa-bars"></i>
        </button>
    </div>

    <!-- Sidebar -->
    <div id="sidebar" class="sidebar bg-gray-800 text-white w-64 space-y-6 py-7 px-2 absolute inset-y-0 left-0 transform -translate-x-full md:relative md:translate-x-0 z-20">
        <!-- Logo -->
        <a href="#" class="text-white flex items-center space-x-2 px-4">
            <i class="fas fa-cogs fa-2x"></i>
            <span class="text-2xl font-extrabold">Admin Panel</span>
        </a>

        <!-- Navigation -->
        <nav>
            <a href="/admin/dashboard" class="block py-2.5 px-4 rounded transition duration-200 hover:bg-gray-700 hover:text-white">
                <i class="fas fa-tachometer-alt mr-3"></i> Bảng điều khiển
            </a>
            <a href="/admin/manage-product" class="block py-2.5 px-4 rounded transition duration-200 bg-gray-900 font-bold">
                <i class="fas fa-box-open mr-3"></i> Quản lý sản phẩm
            </a>
            <a href="admin/manage-order" class="block py-2.5 px-4 rounded transition duration-200 hover:bg-gray-700 hover:text-white">
                <i class="fas fa-shopping-cart mr-3"></i> Quản lý đơn hàng
            </a>
            <a href="/admin/manage-user" class="block py-2.5 px-4 rounded transition duration-200 hover:bg-gray-700 hover:text-white">
                <i class="fas fa-users mr-3"></i> Quản lý khách hàng
            </a>
            <a href="#" class="block py-2.5 px-4 rounded transition duration-200 hover:bg-gray-700 hover:text-white">
                <i class="fas fa-cogs mr-3"></i> Cài đặt
            </a>
        </nav>
    </div>
</div>