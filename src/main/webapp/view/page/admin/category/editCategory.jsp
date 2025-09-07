<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh Sửa Danh Mục - ${category.name}</title>
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

    <%@ include file="../../../layout/admin/sidebar.jsp" %>

    <div class="flex-1 p-6 lg:p-10 overflow-y-auto">
        <div class="flex justify-between items-center mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Chỉnh Sửa Danh Mục</h1>
            <a href="/admin/manage-category" class="text-blue-500 hover:text-blue-700 font-semibold">
                &larr; Quay lại danh sách
            </a>
        </div>

        <div class="bg-white rounded-lg shadow-lg p-8">
            <form action="/admin/manage-category/update" method="POST" class="space-y-6">

                <input type="hidden" name="categoryId" value="${category.categoryId}">

                <c:if test="${not empty errorMessage}">
                    <div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-6" role="alert">
                        <p class="font-bold">Có lỗi xảy ra:</p>
                        <p>${errorMessage}</p>
                    </div>
                </c:if>

                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label for="categoryId-display" class="block text-sm font-medium text-gray-700">ID Danh mục</label>
                        <input type="text" id="categoryId-display"
                               class="mt-1 block w-full bg-gray-100 border-gray-300 rounded-md shadow-sm sm:text-sm cursor-not-allowed"
                               value="${category.categoryId}"
                               disabled>
                    </div>

                    <div>
                        <label for="name" class="block text-sm font-medium text-gray-700">Tên danh mục <span class="text-red-500">*</span></label>
                        <input type="text" id="name" name="name" required
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                               value="${category.name}"
                               placeholder="VD: Trà trái cây">
                    </div>

                    <div class="md:col-span-2">
                        <label for="description" class="block text-sm font-medium text-gray-700">Mô tả</g:if>
                            <textarea id="description" name="description" rows="4"
                                      class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                                      placeholder="Nhập mô tả chi tiết cho danh mục">${category.description}</textarea>
                    </div>
                </div>

                <div class="mt-8 flex justify-end gap-4">
                    <a href="/admin/manage-category" class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded-lg shadow-sm transition duration-300">
                        Hủy bỏ
                    </a>
                    <button type="submit"
                            class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-lg shadow-sm transition duration-300 flex items-center">
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
</script>

</body>
</html>