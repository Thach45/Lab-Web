<%--
  Created by IntelliJ IDEA.
  User: macpro
  Date: 29/8/25
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Quản Trị Sản Phẩm</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Font Awesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        /* Thêm một chút hiệu ứng chuyển động mượt mà cho sidebar */
        .sidebar {
            transition: transform 0.3s ease-in-out;
        }
    </style>
</head>
<body class="bg-gray-100 font-sans">
<div class="relative min-h-screen flex flex-col md:flex-row">
    <%@ include file="../../layout/admin/sidebar.jsp" %>
    <div class="flex-1 p-6 lg:p-10 overflow-y-auto">
    <h1 class="text-3xl font-bold text-gray-800 mb-6">Bảng điều khiển</h1>

    <!-- Stat Cards -->
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <!-- Card 1: Doanh thu -->
        <div class="bg-white rounded-lg shadow-lg p-6 flex items-center justify-between">
            <div>
                <p class="text-sm font-medium text-gray-500">Tổng Doanh Thu</p>
                <p class="text-3xl font-bold text-gray-800">150,000,000đ</p>
            </div>
            <div class="bg-blue-100 text-blue-500 rounded-full p-3">
                <i class="fas fa-dollar-sign fa-2x"></i>
            </div>
        </div>
        <!-- Card 2: Đơn hàng mới -->
        <div class="bg-white rounded-lg shadow-lg p-6 flex items-center justify-between">
            <div>
                <p class="text-sm font-medium text-gray-500">Đơn Hàng Mới</p>
                <p class="text-3xl font-bold text-gray-800">35</p>
            </div>
            <div class="bg-green-100 text-green-500 rounded-full p-3">
                <i class="fas fa-shopping-cart fa-2x"></i>
            </div>
        </div>
        <!-- Card 3: Khách hàng mới -->
        <div class="bg-white rounded-lg shadow-lg p-6 flex items-center justify-between">
            <div>
                <p class="text-sm font-medium text-gray-500">Khách Hàng Mới</p>
                <p class="text-3xl font-bold text-gray-800">12</p>
            </div>
            <div class="bg-yellow-100 text-yellow-500 rounded-full p-3">
                <i class="fas fa-users fa-2x"></i>
            </div>
        </div>
        <!-- Card 4: Sản phẩm -->
        <div class="bg-white rounded-lg shadow-lg p-6 flex items-center justify-between">
            <div>
                <p class="text-sm font-medium text-gray-500">Tổng Sản Phẩm</p>
                <p class="text-3xl font-bold text-gray-800">256</p>
            </div>
            <div class="bg-red-100 text-red-500 rounded-full p-3">
                <i class="fas fa-box-open fa-2x"></i>
            </div>
        </div>
    </div>

    <!-- Main section with Chart and Recent Orders -->
    <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <!-- Chart -->
        <div class="lg:col-span-2 bg-white rounded-lg shadow-lg p-6">
            <h2 class="text-xl font-bold text-gray-700 mb-4">Thống kê doanh thu</h2>
            <div class="bg-gray-200 h-64 rounded-md flex items-center justify-center">
                <p class="text-gray-500">[Biểu đồ sẽ được hiển thị ở đây]</p>
            </div>
        </div>

        <!-- Recent Orders -->
        <div class="bg-white rounded-lg shadow-lg p-6">
            <h2 class="text-xl font-bold text-gray-700 mb-4">Đơn hàng gần đây</h2>
            <div class="space-y-4">
                <!-- Order 1 -->
                <div class="flex items-center justify-between">
                    <div>
                        <p class="font-semibold text-gray-800">#OD-1205</p>
                        <p class="text-sm text-gray-500">Nguyễn Văn A</p>
                    </div>
                    <div class="text-right">
                        <p class="font-semibold text-green-600">2,500,000đ</p>
                        <p class="text-xs text-gray-400">2 giờ trước</p>
                    </div>
                </div>
                <!-- Order 2 -->
                <div class="flex items-center justify-between">
                    <div>
                        <p class="font-semibold text-gray-800">#OD-1204</p>
                        <p class="text-sm text-gray-500">Trần Thị B</p>
                    </div>
                    <div class="text-right">
                        <p class="font-semibold text-green-600">850,000đ</p>
                        <p class="text-xs text-gray-400">5 giờ trước</p>
                    </div>
                </div>
                <!-- Order 3 -->
                <div class="flex items-center justify-between">
                    <div>
                        <p class="font-semibold text-gray-800">#OD-1203</p>
                        <p class="text-sm text-gray-500">Lê Văn C</p>
                    </div>
                    <div class="text-right">
                        <p class="font-semibold text-green-600">1,200,000đ</p>
                        <p class="text-xs text-gray-400">Hôm qua</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
