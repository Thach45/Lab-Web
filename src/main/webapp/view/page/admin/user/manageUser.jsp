<%--
  Created by IntelliJ IDEA.
  User: macpro
  Date: 7/9/25
  Time: 15:20
  Description: Trang quản lý người dùng
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Quản Trị Người Dùng</title>
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
        <h1 class="text-3xl font-bold text-gray-800 mb-6">Quản lý Người Dùng</h1>

        <div class="flex flex-col md:flex-row justify-between items-center mb-6 gap-4">
            <div class="relative w-full md:w-1/3">
                <span class="absolute inset-y-0 left-0 flex items-center pl-3">
                    <i class="fas fa-search text-gray-400"></i>
                </span>
                <input type="text" placeholder="Tìm kiếm người dùng..." class="w-full pl-10 pr-4 py-2 border rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-indigo-500">
            </div>
            <a href="/admin/manage-user/create" class="w-full md:w-auto bg-indigo-500 hover:bg-indigo-600 text-white font-bold py-2 px-4 rounded-lg shadow transition duration-300 flex items-center justify-center">
                <i class="fas fa-plus mr-2"></i>
                Thêm người dùng mới
            </a>
        </div>

        <div class="bg-white rounded-lg shadow-lg overflow-hidden">
            <div class="overflow-x-auto">
                <table class="w-full table-auto">
                    <thead class="bg-gray-200 text-gray-700 uppercase text-sm leading-normal">
                    <tr>
                        <th class="py-3 px-6 text-left">#</th>
                        <th class="py-3 px-6 text-left">Thông tin</th>
                        <th class="py-3 px-6 text-left">Email</th>
                        <th class="py-3 px-6 text-center">Vai trò</th>
                        <th class="py-3 px-6 text-center">Trạng thái</th>
                        <th class="py-3 px-6 text-center">Ngày tạo</th>
                        <th class="py-3 px-6 text-center">Hành động</th>
                    </tr>
                    </thead>
                    <tbody class="text-gray-700 text-sm font-light">
                    <c:forEach var="user" items="${users}" varStatus="status">
                        <tr class="border-b border-gray-200 hover:bg-gray-100">
                            <td class="py-3 px-6 text-left whitespace-nowrap">
                                <span class="font-medium">${status.index + 1}</span>
                            </td>
                            <td class="py-3 px-6 text-left">
                                <div class="flex items-center">
                                    <div class="w-10 h-10 bg-indigo-500 rounded-full flex items-center justify-center text-white font-bold text-sm mr-4">
                                        ${user.username.charAt(0).toUpperCase()}
                                    </div>
                                    <div>
                                        <div class="font-medium text-gray-900">${user.username}</div>
                                        <div class="text-gray-500 text-xs">ID: ${user.userId}</div>
                                    </div>
                                </div>
                            </td>
                            <td class="py-3 px-6 text-left">
                                <div class="text-gray-900">${user.email}</div>
                            </td>
                            <td class="py-3 px-6 text-center">
                                <c:choose>
                                    <c:when test="${user.role == 'ADMIN'}">
                                        <span class="bg-red-100 text-red-800 py-1 px-3 rounded-full text-xs font-medium">
                                            <i class="fas fa-crown mr-1"></i>Admin
                                        </span>
                                    </c:when>
                                    <c:when test="${user.role == 'MANAGER'}">
                                        <span class="bg-yellow-100 text-yellow-800 py-1 px-3 rounded-full text-xs font-medium">
                                            <i class="fas fa-user-tie mr-1"></i>Manager
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="bg-blue-100 text-blue-800 py-1 px-3 rounded-full text-xs font-medium">
                                            <i class="fas fa-user mr-1"></i>User
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="py-3 px-6 text-center">
                                <c:choose>
                                    <c:when test="${user.isActive}">
                                        <span class="bg-green-100 text-green-800 py-1 px-3 rounded-full text-xs font-medium">
                                            <i class="fas fa-check-circle mr-1"></i>Hoạt động
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="bg-red-100 text-red-800 py-1 px-3 rounded-full text-xs font-medium">
                                            <i class="fas fa-times-circle mr-1"></i>Bị khóa
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="py-3 px-6 text-center">
                                <div class="text-gray-500 text-xs">
                                    ${user.createdAt}
                                </div>
                            </td>
                            <td class="py-3 px-6 text-center">
                                <div class="flex item-center justify-center gap-4">
                                    <a href="/admin/manage-user/view/${user.userId}" class="text-gray-500 hover:text-blue-500 transition duration-300" title="Xem chi tiết">
                                        <i class="fas fa-eye fa-lg"></i>
                                    </a>
                                    <a href="/admin/manage-user/edit/${user.userId}" class="text-gray-500 hover:text-yellow-500 transition duration-300" title="Chỉnh sửa">
                                        <i class="fas fa-pencil-alt fa-lg"></i>
                                    </a>
                                    <c:choose>
                                        <c:when test="${user.isActive}">
                                            <a href="javascript:void(0);" onclick="confirmDeactivate(${user.userId})" class="text-gray-500 hover:text-orange-500 transition duration-300" title="Khóa tài khoản">
                                                <i class="fas fa-lock fa-lg"></i>
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:void(0);" onclick="confirmActivate(${user.userId})" class="text-gray-500 hover:text-green-500 transition duration-300" title="Mở khóa tài khoản">
                                                <i class="fas fa-unlock fa-lg"></i>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                    <a href="javascript:void(0);" onclick="confirmDelete(${user.userId})" class="text-gray-500 hover:text-red-500 transition duration-300" title="Xóa">
                                        <i class="fas fa-trash-alt fa-lg"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="py-4 px-6 border-t-2 border-gray-200">
                <div class="flex flex-col sm:flex-row items-center justify-between">
                    <p class="text-sm text-gray-600 mb-2 sm:mb-0">Hiển thị 1 đến 10 của ${totalUsers} kết quả</p>
                    <div class="flex items-center">
                        <button class="text-sm bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded-l transition duration-300">
                            Trước
                        </button>
                        <button class="text-sm bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded-r transition duration-300">
                            Sau
                        </button>
                    </div>
                </div>
            </div>
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

    // --- Confirm Delete Script ---
    function confirmDelete(userId) {
        if (confirm("Bạn có chắc chắn muốn xóa người dùng này không? Hành động này không thể hoàn tác.")) {
            window.location.href = '/admin/manage-user/delete/' + userId;
        }
    }

    // --- Confirm Deactivate Script ---
    function confirmDeactivate(userId) {
        if (confirm("Bạn có chắc chắn muốn khóa tài khoản này không?")) {
            window.location.href = '/admin/manage-user/deactivate/' + userId;
        }
    }

    // --- Confirm Activate Script ---
    function confirmActivate(userId) {
        if (confirm("Bạn có chắc chắn muốn mở khóa tài khoản này không?")) {
            window.location.href = '/admin/manage-user/activate/' + userId;
        }
    }
</script>

</body>
</html>
