<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Topping</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body class="bg-gray-100 font-sans">

<div class="relative min-h-screen flex flex-col md:flex-row">
    <%@ include file="../../../layout/admin/sidebar.jsp" %>

    <div class="flex-1 p-6 lg:p-10 overflow-y-auto">
        <h1 class="text-3xl font-bold text-gray-800 mb-6">Quản lý Topping</h1>

        <div class="flex flex-col md:flex-row justify-between items-center mb-6 gap-4">
            <div class="relative w-full md:w-1/3">
                <span class="absolute inset-y-0 left-0 flex items-center pl-3">
                    <i class="fas fa-search text-gray-400"></i>
                </span>
                <input id="searchInput" type="text" placeholder="Tìm kiếm topping..." class="w-full pl-10 pr-4 py-2 border rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-indigo-500">
            </div>
            <a href="/admin/manage-topping/create" class="w-full md:w-auto bg-indigo-500 hover:bg-indigo-600 text-white font-bold py-2 px-4 rounded-lg shadow transition duration-300 flex items-center justify-center">
                <i class="fas fa-plus mr-2"></i>
                Thêm topping mới
            </a>
        </div>

        <div class="bg-white rounded-lg shadow-lg overflow-hidden">
            <div class="overflow-x-auto">
                <table class="w-full table-auto">
                    <thead class="bg-gray-200 text-gray-700 uppercase text-sm leading-normal">
                    <tr>
                        <th class="py-3 px-6 text-left">#</th>
                        <th class="py-3 px-6 text-left">Tên</th>
                        <th class="py-3 px-6 text-left">Giá</th>
                        <th class="py-3 px-6 text-center">Hành động</th>
                    </tr>
                    </thead>
                    <tbody class="text-gray-700 text-sm font-light">
                    <c:forEach var="t" items="${toppings}" varStatus="status">
                        <tr class="border-b border-gray-200 hover:bg-gray-100" data-searchable>
                            <td class="py-3 px-6 text-left whitespace-nowrap">
                                <span class="font-medium">${status.index + 1}</span>
                            </td>
                            <td class="py-3 px-6 text-left">
                                <strong>${t.name}</strong>
                            </td>
                            <td class="py-3 px-6 text-left">
                                ${t.price}
                            </td>
                            <td class="py-3 px-6 text-center">
                                <div class="flex item-center justify-center gap-4">
                                    <a href="/admin/manage-topping/update/${t.topping_id}" class="text-gray-500 hover:text-yellow-500 transition duration-300" title="Sửa">
                                        <i class="fas fa-pencil-alt fa-lg"></i>
                                    </a>
                                    <a href="javascript:void(0);" onclick="confirmDelete('/admin/manage-topping/delete/${t.topping_id}')" class="text-gray-500 hover:text-red-500 transition duration-300" title="Xóa">
                                        <i class="fas fa-trash-alt fa-lg"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    function confirmDelete(deleteUrl) {
        if (confirm("Bạn có chắc chắn muốn xóa topping này không?")) {
            window.location.href = deleteUrl;
        }
    }
</script>

</body>
</html>


