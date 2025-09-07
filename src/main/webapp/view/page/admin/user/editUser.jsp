<%--
  Created by IntelliJ IDEA.
  User: macpro
  Date: 7/9/25
  Time: 15:30
  Description: Trang chỉnh sửa người dùng
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh Sửa Người Dùng - ${user.username}</title>
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
        <div class="flex justify-between items-center mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Chỉnh Sửa Người Dùng</h1>
            <a href="/admin/manage-user" class="text-blue-500 hover:text-blue-700 font-semibold">
                &larr; Quay lại danh sách
            </a>
        </div>

        <div class="bg-white rounded-lg shadow-lg p-8">
            <form action="/admin/manage-user/update/${user.id}" method="post" class="space-y-6">
                
                <input type="hidden" name="id" value="${user.id}">

                <c:if test="${not empty errorMessage}">
                    <div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-6" role="alert">
                        <p class="font-bold">Có lỗi xảy ra:</p>
                        <p>${errorMessage}</p>
                    </div>
                </c:if>

                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label for="id-display" class="block text-sm font-medium text-gray-700">ID Người dùng</label>
                        <input type="text" id="id-display"
                               class="mt-1 block w-full bg-gray-100 border-gray-300 rounded-md shadow-sm sm:text-sm cursor-not-allowed"
                               value="${user.id}"
                               disabled>
                    </div>

                    <div>
                        <label for="username-display" class="block text-sm font-medium text-gray-700">Tên đăng nhập</label>
                        <input type="text" id="username-display"
                               class="mt-1 block w-full bg-gray-100 border-gray-300 rounded-md shadow-sm sm:text-sm cursor-not-allowed"
                               value="${user.username}"
                               disabled>
                        <input type="hidden" name="username" value="${user.username}">
                    </div>

                    <div>
                        <label for="email" class="block text-sm font-medium text-gray-700">Email <span class="text-red-500">*</span></label>
                        <input type="email" id="email" name="email" required
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                               value="${user.email}"
                               placeholder="Nhập email">
                    </div>

                    <div>
                        <label for="fullName" class="block text-sm font-medium text-gray-700">Họ và tên</label>
                        <input type="text" id="fullName" name="fullName"
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                               value="${user.fullName}"
                               placeholder="Nhập họ và tên">
                    </div>

                    <div>
                        <label for="phone" class="block text-sm font-medium text-gray-700">Số điện thoại</label>
                        <input type="tel" id="phone" name="phone"
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                               value="${user.phone}"
                               placeholder="Nhập số điện thoại">
                    </div>

                    <div>
                        <label for="role" class="block text-sm font-medium text-gray-700">Vai trò <span class="text-red-500">*</span></label>
                        <select id="role" name="role" required
                                class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm bg-white px-3 py-2">
                            <option value="">Chọn vai trò</option>
                            <option value="USER" ${user.role == 'USER' ? 'selected' : ''}>Người dùng</option>
                            <option value="MANAGER" ${user.role == 'MANAGER' ? 'selected' : ''}>Quản lý</option>
                            <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>Quản trị viên</option>
                        </select>
                    </div>

                    <div>
                        <label for="isActive" class="block text-sm font-medium text-gray-700">Trạng thái tài khoản</label>
                        <div class="mt-1 flex items-center space-x-4">
                            <label class="flex items-center">
                                <input type="radio" name="isActive" value="true" ${user.active == true ? 'checked' : ''}
                                       class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300">
                                <span class="ml-2 text-sm text-gray-700">Hoạt động</span>
                            </label>
                            <label class="flex items-center">
                                <input type="radio" name="isActive" value="false" ${user.active != true ? 'checked' : ''}
                                       class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300">
                                <span class="ml-2 text-sm text-gray-700">Bị khóa</span>
                            </label>
                        </div>
                    </div>

                    <div>
                        <label for="password" class="block text-sm font-medium text-gray-700">Mật khẩu mới</label>
                        <input type="password" id="password" name="password"
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                               placeholder="Để trống nếu không muốn thay đổi">
                        <p class="text-xs text-gray-500 mt-1">Để trống nếu không muốn thay đổi mật khẩu</p>
                    </div>

                    <div>
                        <label for="confirmPassword" class="block text-sm font-medium text-gray-700">Xác nhận mật khẩu mới</label>
                        <input type="password" id="confirmPassword" name="confirmPassword"
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                               placeholder="Xác nhận mật khẩu mới">
                    </div>

                    <div class="md:col-span-2">
                        <label for="address" class="block text-sm font-medium text-gray-700">Địa chỉ</label>
                        <textarea id="address" name="address" rows="3"
                                  class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                                  placeholder="Nhập địa chỉ">${user.address}</textarea>
                    </div>
                </div>

                <!-- Form Actions -->
                <div class="mt-8 flex justify-end gap-4">
                    <a href="/admin/manage-user" class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded-lg shadow-sm transition duration-300">
                        Hủy bỏ
                    </a>
                    <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-lg shadow-sm transition duration-300 flex items-center">
                        <i class="fas fa-save mr-2"></i> Lưu thay đổi
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    const mobileMenuButton = document.getElementById('mobile-menu-button');
    const sidebar = document.getElementById('sidebar');

    if (mobileMenuButton && sidebar) {
        mobileMenuButton.addEventListener('click', () => {
            sidebar.classList.toggle('-translate-x-full');
        });
    }

    // Form Validation
    document.querySelector('form').addEventListener('submit', function(e) {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        
        // Chỉ validate nếu có nhập mật khẩu mới
        if (password || confirmPassword) {
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
        }
    });
</script>

</body>
</html>
