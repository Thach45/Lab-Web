<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập nhật Topping</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body class="bg-gray-100 font-sans">

<div class="relative min-h-screen flex flex-col md:flex-row">
    <%@ include file="../../../layout/admin/sidebar.jsp" %>

    <div class="flex-1 p-6 lg:p-10 overflow-y-auto">
        <div class="flex justify-between items-center mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Cập nhật Topping</h1>
            <a href="/admin/manage-topping" class="text-blue-500 hover:text-blue-700 font-semibold">
                &larr; Quay lại danh sách
            </a>
        </div>

        <div class="bg-white rounded-lg shadow-lg p-8">
            <form action="/admin/manage-topping/update/${topping.topping_id}" method="POST" class="space-y-6">

                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label for="id-display" class="block text-sm font-medium text-gray-700">ID Topping</label>
                        <input type="text" id="id-display"
                               class="mt-1 block w-full bg-gray-100 border-gray-300 rounded-md shadow-sm sm:text-sm cursor-not-allowed"
                               value="${topping.topping_id}"
                               disabled>
                    </div>

                    <div>
                        <label for="name" class="block text-sm font-medium text-gray-700">Tên topping <span class="text-red-500">*</span></label>
                        <input type="text" id="name" name="name" required
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                               value="${topping.name}"
                               placeholder="VD: Trân châu đen">
                    </div>

                    <div>
                        <label for="price" class="block text-sm font-medium text-gray-700">Giá (VND) <span class="text-red-500">*</span></label>
                        <input type="number" id="price" name="price" step="100" min="0" required
                               class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                               value="${topping.price}"
                               placeholder="VD: 5000">
                    </div>
                </div>

                <div class="mt-8 flex justify-end gap-4">
                    <a href="/admin/manage-topping" class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded-lg shadow-sm transition duration-300">
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

</body>
</html>


