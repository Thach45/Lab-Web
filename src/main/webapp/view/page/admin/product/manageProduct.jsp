<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: macpro
  Date: 29/8/25
  Time: 10:20
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
    <%@ include file="../../../layout/admin/sidebar.jsp" %>
<%--    Main feature--%>
    <div class="flex-1 p-6 lg:p-10 overflow-y-auto">
        <h1 class="text-3xl font-bold text-gray-800 mb-6">Quản lý sản phẩm</h1>

        <!-- Action Bar: Search and Add -->
        <div class="flex flex-col md:flex-row justify-between items-center mb-6 gap-4">
            <div class="relative w-full md:w-1/3">
                    <span class="absolute inset-y-0 left-0 flex items-center pl-3">
                        <i class="fas fa-search text-gray-400"></i>
                    </span>
                <input type="text" placeholder="Tìm kiếm sản phẩm..." class="w-full pl-10 pr-4 py-2 border rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
            <button class="w-full md:w-auto bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-lg shadow transition duration-300">

                <a href="/admin/manage-product/create">
                    <i  class="fas fa-plus mr-2">
                    </i>
                        Thêm sản phẩm mới
                </a>



            </button>
        </div>

        <!-- Products Table -->
        <div class="bg-white rounded-lg shadow-lg overflow-hidden">
            <div class="overflow-x-auto">
                <table class="w-full table-auto">
                    <thead class="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
                    <tr>
                        <th class="py-3 px-6 text-left">ID</th>
                        <th class="py-3 px-6 text-left">Sản phẩm</th>
                        <th class="py-3 px-6 text-center">Giá</th>
                        <th class="py-3 px-6 text-center">Số lượng</th>
                        <th class="py-3 px-6 text-center">Trạng thái</th>
                        <th class="py-3 px-6 text-center">Hành động</th>
                    </tr>
                    </thead>
                    <tbody class="text-gray-600 text-sm font-light">
                    <c:forEach var="p" items="${products}" varStatus="status">
                         <tr class="border-b border-gray-200 hover:bg-gray-100">
                        <td class="py-3 px-6 text-left whitespace-nowrap">${status.index+1}</td>
                        <td class="py-3 px-6 text-left">
                            <div class="flex items-center">
                                <img class="w-10 h-10 rounded-full mr-4" src="${p.image_url}" alt="${p.name}">
                                <span class="font-medium">${p.name}</span>
                            </div>
                        </td>
                        <td class="py-3 px-6 text-center">${p.price}</td>
                        <td class="py-3 px-6 text-center">${p.stock}</td>
                        <c:choose>
                            <c:when test="${p.stock == 0}">
                                <td class="py-3 px-6 text-center">
                                    <span class="bg-red-200 text-red-600 py-1 px-3 rounded-full text-xs">Hết hàng</span>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="py-3 px-6 text-center">
                                    <span class="bg-green-200 text-green-600 py-1 px-3 rounded-full text-xs">Còn hàng</span>
                                </td>
                            </c:otherwise>
                        </c:choose>

                        <td class="py-3 px-6 text-center">
                            <div class="flex item-center justify-center">
                                <a class="w-6 mr-2 transform hover:text-purple-500 hover:scale-110" title="Xem"><i class="fas fa-eye"></i></a>
                                <a href="/admin/manage-product/update/${p.id}" class="w-6 mr-2 transform hover:text-yellow-500 hover:scale-110" title="Sửa"><i class="fas fa-pencil-alt"></i></a>
                                <a class="w-6 mr-2 transform hover:text-red-500 hover:scale-110" title="Xóa"><i class="fas fa-trash-alt"></i></a>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Pagination -->
            <div class="py-4 px-6 border-t-2 border-gray-200">
                <div class="flex flex-col sm:flex-row items-center justify-between">
                    <p class="text-sm text-gray-600 mb-2 sm:mb-0">Hiển thị 1 đến 3 của 15 kết quả</p>
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
    // Lấy các element cần thiết
    const mobileMenuButton = document.getElementById('mobile-menu-button');
    const sidebar = document.getElementById('sidebar');

    // Thêm sự kiện click cho nút menu mobile
    mobileMenuButton.addEventListener('click', () => {
        // Toggle class '-translate-x-full' để hiện/ẩn sidebar
        sidebar.classList.toggle('-translate-x-full');
    });
</script>

</body>
</html>

