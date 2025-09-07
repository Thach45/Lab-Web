<%--
  Created by IntelliJ IDEA.
  User: macpro
  Date: 7/9/25
  Time: 15:25
  Description: Trang tạo người dùng mới
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tạo Người Dùng Mới</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <style>
        .sidebar {
            transition: transform 0.3s ease-in-out;
        }
    </style>
</head>
<body class="bg-gray-100 font-sans">

<div class="relative min-h-screen flex flex-col md:flex-row">

    <%-- Include Sidebar --%>
    <%@ include file="../../../layout/admin/sidebar.jsp" %>

    <%-- Main Content --%>
    <div class="flex-1 p-6 lg:p-10 overflow-y-auto">
        <div class="max-w-4xl mx-auto">
            <div class="mb-6">
                <h1 class="text-3xl font-bold text-gray-800 mb-2">Tạo Người Dùng Mới</h1>
                <p class="text-gray-600">Thêm người dùng mới vào hệ thống</p>
            </div>

            <form action="/admin/manage-user/create" method="post" class="bg-white rounded-lg shadow-lg p-8">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <!-- Thông tin cơ bản -->
                    <div class="md:col-span-2">
                        <h3 class="text-lg font-semibold text-gray-800 mb-4 flex items-center">
                            <i class="fas fa-user mr-2 text-indigo-500"></i>
                            Thông tin cơ bản
                        </h3>
                    </div>

                    <div>
                        <label for="username" class="block text-sm font-medium text-gray-700 mb-2">
                            Tên đăng nhập <span class="text-red-500">*</span>
                        </label>
                        <input type="text" id="username" name="username" required
                               class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
                               placeholder="Nhập tên đăng nhập">
                    </div>

                    <div>
                        <label for="email" class="block text-sm font-medium text-gray-700 mb-2">
                            Email <span class="text-red-500">*</span>
                        </label>
                        <input type="email" id="email" name="email" required
                               class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
                               placeholder="Nhập email">
                    </div>

                    <div>
                        <label for="password" class="block text-sm font-medium text-gray-700 mb-2">
                            Mật khẩu <span class="text-red-500">*</span>
                        </label>
                        <div class="relative">
                            <input type="password" id="password" name="password" required
                                   class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
                                   placeholder="Nhập mật khẩu">
                            <button type="button" onclick="togglePassword('password')" 
                                    class="absolute right-3 top-2 text-gray-400 hover:text-gray-600">
                                <i class="fas fa-eye" id="password-eye"></i>
                            </button>
                        </div>
                    </div>

                    <div>
                        <label for="confirmPassword" class="block text-sm font-medium text-gray-700 mb-2">
                            Xác nhận mật khẩu <span class="text-red-500">*</span>
                        </label>
                        <div class="relative">
                            <input type="password" id="confirmPassword" name="confirmPassword" required
                                   class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
                                   placeholder="Xác nhận mật khẩu">
                            <button type="button" onclick="togglePassword('confirmPassword')" 
                                    class="absolute right-3 top-2 text-gray-400 hover:text-gray-600">
                                <i class="fas fa-eye" id="confirmPassword-eye"></i>
                            </button>
                        </div>
                    </div>

                    <div>
                        <label for="fullName" class="block text-sm font-medium text-gray-700 mb-2">
                            Họ và tên
                        </label>
                        <input type="text" id="fullName" name="fullName"
                               class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
                               placeholder="Nhập họ và tên">
                    </div>

                    <div>
                        <label for="phone" class="block text-sm font-medium text-gray-700 mb-2">
                            Số điện thoại
                        </label>
                        <input type="tel" id="phone" name="phone"
                               class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
                               placeholder="Nhập số điện thoại">
                    </div>

                    <!-- Vai trò và trạng thái -->
                    <div class="md:col-span-2 mt-6">
                        <h3 class="text-lg font-semibold text-gray-800 mb-4 flex items-center">
                            <i class="fas fa-cog mr-2 text-indigo-500"></i>
                            Cài đặt tài khoản
                        </h3>
                    </div>

                    <div>
                        <label for="role" class="block text-sm font-medium text-gray-700 mb-2">
                            Vai trò <span class="text-red-500">*</span>
                        </label>
                        <select id="role" name="role" required
                                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500">
                            <option value="">Chọn vai trò</option>
                            <option value="USER">Người dùng</option>
                            <option value="MANAGER">Quản lý</option>
                            <option value="ADMIN">Quản trị viên</option>
                        </select>
                    </div>

                    <div>
                        <label for="isActive" class="block text-sm font-medium text-gray-700 mb-2">
                            Trạng thái tài khoản
                        </label>
                        <div class="flex items-center space-x-4">
                            <label class="flex items-center">
                                <input type="radio" name="isActive" value="true" checked
                                       class="form-radio text-indigo-600">
                                <span class="ml-2 text-sm text-gray-700">Hoạt động</span>
                            </label>
                            <label class="flex items-center">
                                <input type="radio" name="isActive" value="false"
                                       class="form-radio text-indigo-600">
                                <span class="ml-2 text-sm text-gray-700">Bị khóa</span>
                            </label>
                        </div>
                    </div>

                    <!-- Địa chỉ -->
                    <div class="md:col-span-2">
                        <label for="address" class="block text-sm font-medium text-gray-700 mb-2">
                            Địa chỉ
                        </label>
                        <textarea id="address" name="address" rows="3"
                                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
                                  placeholder="Nhập địa chỉ"></textarea>
                    </div>
                </div>

                <!-- Buttons -->
                <div class="flex justify-end space-x-4 mt-8 pt-6 border-t border-gray-200">
                    <a href="/admin/manage-user" 
                       class="px-6 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition duration-300">
                        <i class="fas fa-arrow-left mr-2"></i>
                        Quay lại
                    </a>
                    <button type="submit" 
                            class="px-6 py-2 bg-indigo-500 text-white rounded-lg hover:bg-indigo-600 transition duration-300">
                        <i class="fas fa-save mr-2"></i>
                        Tạo người dùng
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    // --- Sidebar Toggle Script ---
    const mobileMenuButton = document.getElementById('mobile-menu-button');
    const sidebar = document.getElementById('sidebar');

    if (mobileMenuButton && sidebar) {
        mobileMenuButton.addEventListener('click', () => {
            sidebar.classList.toggle('-translate-x-full');
        });
    }

    // --- Toggle Password Visibility ---
    function togglePassword(fieldId) {
        const field = document.getElementById(fieldId);
        const eye = document.getElementById(fieldId + '-eye');
        
        if (field.type === 'password') {
            field.type = 'text';
            eye.classList.remove('fa-eye');
            eye.classList.add('fa-eye-slash');
        } else {
            field.type = 'password';
            eye.classList.remove('fa-eye-slash');
            eye.classList.add('fa-eye');
        }
    }

    // --- Form Validation ---
    document.querySelector('form').addEventListener('submit', function(e) {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        
        if (password !== confirmPassword) {
            e.preventDefault();
            alert('Mật khẩu xác nhận không khớp!');
            return false;
        }
        
        if (password.length < 6) {
            e.preventDefault();
            alert('Mật khẩu phải có ít nhất 6 ký tự!');
            return false;
        }
    });
</script>

</body>
</html>
