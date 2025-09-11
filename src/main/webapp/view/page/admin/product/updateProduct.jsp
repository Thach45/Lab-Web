<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập nhật Sản phẩm</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 font-sans">

<div class="relative min-h-screen flex flex-col md:flex-row">
    <%@ include file="../../../layout/admin/sidebar.jsp" %>
    <div class="flex-1 p-6 lg:p-10 overflow-y-auto">
        <div class="flex justify-between items-center mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Cập nhật sản phẩm</h1>
            <a href="/admin/manage-product" class="text-blue-500 hover:text-blue-700">&larr; Quay lại danh sách</a>
        </div>

        <div class="bg-white rounded-lg shadow-lg p-8">
            <form action="/admin/manage-product/update/${product.id}" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${product.id}">
                <input type="hidden" name="image_url_old" value="${product.image_url}">
                
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <!-- Product Name -->
                    <div class="md:col-span-2">
                        <label for="name" class="block text-sm font-medium text-gray-700">Tên sản phẩm</label>
                        <input type="text" name="name" id="name" required
                               value="${product.name}"
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
                    </div>

                    <!-- Price -->
                    <div>
                        <label for="price" class="block text-sm font-medium text-gray-700">Giá bán</label>
                        <input type="number" name="price" id="price" required
                               value="${product.price}"
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
                    </div>

                    <!-- Category -->
                    <div>
                        <label for="category" class="block text-sm font-medium text-gray-700">Danh mục</label>
                        <select name="category" id="category" required
                                class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm bg-white px-3 py-2">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.categoryId}" 
                                        ${category.categoryId == product.name ? 'selected' : ''}>
                                    ${category.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Size Selection -->
                    <div>
                        <label for="size" class="block text-sm font-medium text-gray-700">Size</label>
                        <select name="size" id="size" required onchange="showStockInput(this.value)"
                                class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm bg-white px-3 py-2">
                                <c:forEach var="sizeOption" items="${sizes}">
                                    <option value="${sizeOption.sizeId}">
                                            ${sizeOption.name}
                                    </option>
                                </c:forEach>

                        </select>
                    </div>

                    <!-- Stock Input (Hidden by default) -->
                    <!-- Stock Overview -->
                    <!-- Stock Overview -->
                    <div class="md:col-span-2 mt-6">
                        <h2 class="text-lg font-semibold text-gray-800 mb-3">Quản lý tồn kho</h2>

                        <!-- Tổng stock -->
                        <p class="mb-2 text-sm text-gray-700">
                            <span class="font-medium">Tổng số lượng: </span>
                            <c:set var="totalStock" value="0"/>
                            <c:forEach var="s" items="${sizes}">
                                <c:set var="totalStock" value="${totalStock + s.stock}"/>
                            </c:forEach>
                            <span class="text-blue-600 font-bold">${totalStock}</span>
                        </p>

                        <!-- Bảng chỉnh sửa stock từng size -->
                        <div class="overflow-x-auto">
                            <table class="min-w-full border border-gray-200 rounded-lg">
                                <thead class="bg-gray-100">
                                <tr>
                                    <th class="px-4 py-2 text-left text-sm font-medium text-gray-700">Size</th>
                                    <th class="px-4 py-2 text-left text-sm font-medium text-gray-700">Số lượng</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="s" items="${sizes}">
                                    <tr class="border-t">
                                        <td class="px-4 py-2 text-sm text-gray-800">${s.name}</td>
                                        <td class="px-4 py-2">
                                            <input type="number"
                                                   name="stocks[${s.sizeId}]"
                                                   value="${s.stock}"
                                                   min="0"
                                                   class="w-24 border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>



                    <!-- Best Seller -->
                    <div class="flex items-center pt-6">
                        <input id="best_seller" name="best_seller" type="checkbox" 
                               ${product.best_seller ? 'checked' : ''}
                               class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded">
                        <label for="best_seller" class="ml-2 block text-sm text-gray-900">Sản phẩm bán chạy?</label>
                    </div>

                    <!-- Current Image -->
                    <div class="md:col-span-2">
                        <label class="block text-sm font-medium text-gray-700 mb-2">Ảnh hiện tại</label>
                        <img src="${product.image_url}" alt="${product.name}" class="h-32 w-32 object-cover rounded-lg">
                    </div>

                    <!-- New Image -->
                    <div class="md:col-span-2">
                        <label for="productImage" class="block text-sm font-medium text-gray-700">Cập nhật ảnh mới (không bắt buộc)</label>
                        <input type="file" name="productImage" id="productImage"
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
                                  class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm">${product.description}</textarea>
                    </div>
                </div>

                <!-- Form Actions -->
                <div urlclass="mt-8 flex justify-end gap-4">
                    <a href="/admin/manage-product" 
                       class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded-lg shadow transition duration-300">
                        Hủy bỏ
                    </a>
                    <button type="submit" 
                            class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-lg shadow transition duration-300">
                        <i class="fas fa-save mr-2"></i> Lưu thay đổi
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function showStockInput(size) {
        const stockContainer = document.getElementById('stockInputContainer');
        if (size) {
            stockContainer.style.display = 'block';
        } else {
            stockContainer.style.display = 'none';
        }
    }
</script>

</body>
</html>