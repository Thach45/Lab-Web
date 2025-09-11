<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Sản Phẩm Mới</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 font-sans">

<div class="relative min-h-screen flex flex-col md:flex-row">
    <%@ include file="../../../layout/admin/sidebar.jsp" %>
    <div class="flex-1 p-6 lg:p-10 overflow-y-auto">
        <div class="flex justify-between items-center mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Thêm sản phẩm mới</h1>
            <a href="/admin/manage-product" class="text-blue-500 hover:text-blue-700">&larr; Quay lại danh sách</a>
        </div>

        <div class="bg-white rounded-lg shadow-lg p-8">
            <form action="/admin/manage-product/create" method="POST" enctype="multipart/form-data" id="productForm">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <!-- Product Name -->
                    <div class="md:col-span-2">
                        <label for="name" class="block text-sm font-medium text-gray-700">Tên sản phẩm</label>
                        <input type="text" name="name" id="name" required
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm" 
                               placeholder="Nhập tên sản phẩm">
                    </div>

                    <!-- Price -->
                    <div>
                        <label for="price" class="block text-sm font-medium text-gray-700">Giá bán</label>
                        <input type="number" name="price" id="price" required
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm" 
                               placeholder="Nhập giá bán">
                    </div>

                    <!-- Category -->
                    <div>
                        <label for="categoryId" class="block text-sm font-medium text-gray-700">Danh mục</label>
                        <select name="categoryId" id="categoryId" required
                                class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm bg-white px-3 py-2">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.categoryId}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Sizes Section -->
                    <div class="md:col-span-2">
                        <div class="border rounded-lg p-4">
                            <div class="flex justify-between items-center mb-4">
                                <h3 class="text-lg font-medium text-gray-900">Quản lý Size</h3>
                                <button type="button" onclick="addSizeRow()" 
                                        class="bg-blue-500 hover:bg-blue-600 text-white px-3 py-1 rounded-md text-sm flex items-center">
                                    <i class="fas fa-plus mr-2"></i> Thêm Size
                                </button>
                            </div>
                            
                            <div id="sizesContainer" class="space-y-4">
                                <!-- Size rows will be added here -->
                            </div>
                        </div>
                    </div>

                    <!-- Best Seller -->
                    <div class="flex items-center pt-6">
                        <input id="best_seller" name="best_seller" type="checkbox" 
                               class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded">
                        <label for="best_seller" class="ml-2 block text-sm text-gray-900">Sản phẩm bán chạy?</label>
                    </div>

                    <!-- Image -->
                    <div class="md:col-span-2">
                        <label for="productImage" class="block text-sm font-medium text-gray-700">Hình ảnh sản phẩm</label>
                        <input type="file" name="productImage" id="productImage" required
                               class="mt-1 block w-full text-sm text-gray-500
                               file:mr-4 file:py-2 file:px-4
                               file:rounded-full file:border-0
                               file:text-sm file:font-semibold
                               file:bg-blue-50 file:text-blue-700
                               hover:file:bg-blue-100">
                    </div>

                    <!-- Description -->
                    <div class="md:col-span-2">
                        <label for="description" class="block text-sm font-medium text-gray-700">Mô tả sản phẩm</label>
                        <textarea id="description" name="description" rows="4" required
                                  class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm" 
                                  placeholder="Nhập mô tả chi tiết cho sản phẩm..."></textarea>
                    </div>
                </div>

                <!-- Form Actions -->
                <div class="mt-8 flex justify-end gap-4">
                    <a href="/admin/manage-product" 
                       class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded-lg shadow transition duration-300">
                        Hủy bỏ
                    </a>
                    <button type="submit" 
                            class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-lg shadow transition duration-300">
                        <i class="fas fa-save mr-2"></i> Lưu sản phẩm
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    let sizeCounter = 0;

    function addSizeRow() {
        const container = document.getElementById('sizesContainer');
        const newRow = document.createElement('div');
        newRow.className = 'grid grid-cols-12 gap-4 items-center bg-gray-50 p-4 rounded-lg';
        newRow.id = `size-row-${sizeCounter}`;
        
        newRow.innerHTML = `
            <div class="col-span-5">
                <input type="text" name="sizes[${sizeCounter}].name" required
                       placeholder="Tên size (VD: S, M, L)"
                       class="block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
            </div>
            <div class="col-span-5">
                <input type="number" name="sizes[${sizeCounter}].stock" required
                       placeholder="Số lượng"
                       class="block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                       onchange="updateTotalStock()">
            </div>
            <div class="col-span-2 flex justify-end">
                <button type="button" onclick="removeSize(${sizeCounter})"
                        class="text-red-500 hover:text-red-700">
                    <i class="fas fa-trash"></i>
                </button>
            </div>
        `;
        
        container.appendChild(newRow);
        sizeCounter++;
    }

    function removeSize(index) {
        const row = document.getElementById(`size-row-${index}`);
        row.remove();
        updateTotalStock();
    }

    function updateTotalStock() {
        const stockInputs = document.querySelectorAll('input[name$="].stock"]');
        let total = 0;
        stockInputs.forEach(input => {
            total += parseInt(input.value) || 0;
        });
    }

    // Add first size row by default
    document.addEventListener('DOMContentLoaded', function() {
        addSizeRow();
    });
</script>

</body>
</html>