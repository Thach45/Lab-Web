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
    <title>Thêm Sản Phẩm Mới</title>
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
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-3xl font-bold text-gray-800">Thêm sản phẩm mới</h1>
        <a href="/admin/manage-product" class="text-blue-500 hover:text-blue-700">&larr; Quay lại danh sách</a>
    </div>

    <div class="bg-white rounded-lg shadow-lg p-8">
        <form action="/admin/manage-product/create" method="POST" enctype="multipart/form-data">
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <!-- Product Name -->
                <div class="md:col-span-2">
                    <label for="name" class="block text-sm font-medium text-gray-700">Tên sản phẩm</label>
                    <input type="text" name="name" id="name" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm" placeholder="Ví dụ: iPhone 15 Pro Max">
                </div>

                <!-- Price -->
                <div>
                    <label for="price" class="block text-sm font-medium text-gray-700">Giá bán</label>
                    <input type="number" name="price" id="price" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm" placeholder="Ví dụ: 32000000">
                </div>

                <!-- Stock -->
                <div>
                    <label for="stock" class="block text-sm font-medium text-gray-700">Số lượng tồn kho</label>
                    <input type="number" name="stock" id="stock" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm" placeholder="Ví dụ: 99">
                </div>

                <!-- Size -->
                <div>
                    <label for="size" class="block text-sm font-medium text-gray-700">Size</label>
                    <select name="size" id="size" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm bg-white px-3 py-2">
                        <option value="S">S</option>
                        <option value="M">M</option>
                        <option value="L">L</option>
                        <option value="XL">XL</option>
                    </select>
                </div>

                <!-- Best Seller -->
                <div class="flex items-center pt-6">
                    <input id="best_seller" name="best_seller" type="checkbox" class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded">
                    <label for="best_seller" class="ml-2 block text-sm text-gray-900">Sản phẩm bán chạy?</label>
                </div>

                <!-- Image URL -->
                <div class="md:col-span-2">
                    <label for="productImage" class="block text-sm font-medium text-gray-700">Hình ảnh sản phẩm</label>
                    <input type="file" name="productImage" id="productImage" class="mt-1 block w-full text-sm text-gray-500
                    file:mr-4 file:py-2 file:px-4
                    file:rounded-full file:border-0
                    file:text-sm file:font-semibold
                    file:bg-blue-50 file:text-blue-700
                    hover:file:bg-blue-100" required>
                </div>

                <!-- Description -->
                <div class="md:col-span-2">
                    <label for="description" class="block text-sm font-medium text-gray-700">Mô tả sản phẩm</label>
                    <textarea id="description" name="description" rows="4" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm" placeholder="Nhập mô tả chi tiết cho sản phẩm..."></textarea>
                </div>
            </div>

            <!-- Form Actions -->
            <div class="mt-8 flex justify-end gap-4">
                <button type="button" class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded-lg shadow transition duration-300">
                    Hủy bỏ
                </button>
                <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-lg shadow transition duration-300">
                    Lưu sản phẩm
                </button>
            </div>
        </form>
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

