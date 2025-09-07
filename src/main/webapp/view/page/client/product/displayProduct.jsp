<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thực Đơn Sáng Tạo - Boba Bliss</title>

    <script src="https://cdn.tailwindcss.com"></script>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;600;700;800&display=swap" rel="stylesheet">

    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">

    <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js"></script>

    <style>
        html { scroll-behavior: smooth; }
        body { font-family: 'Be Vietnam Pro', sans-serif; background-color: #FDFCFB; }

        /* Custom styles for better UI */
        .card-hover-effect {
            transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
        }
        .card-hover-effect:hover {
            transform: translateY(-8px);
            box-shadow: 0 20px 25px -5px rgb(0 0 0 / 0.1), 0 8px 10px -6px rgb(0 0 0 / 0.1);
        }
        /* Custom checkbox style */
        .custom-checkbox:checked {
            background-image: url("data:image/svg+xml,%3csvg viewBox='0 0 16 16' fill='white' xmlns='http://www.w3.org/2000/svg'%3e%3cpath d='M12.207 4.793a1 1 0 010 1.414l-5 5a1 1 0 01-1.414 0l-2-2a1 1 0 011.414-1.414L6.5 9.086l4.293-4.293a1 1 0 011.414 0z'/%3e%3c/svg%3e");
            border-color: #c026d3;
            background-color: #c026d3;
        }
    </style>
</head>
<body x-data="{ 'isFilterOpen': false }" @keydown.escape="isFilterOpen = false">

<%@ include file="../../../layout/client/header.jsp" %>

<main class="">

    <section class="bg-stone-100 py-8">
        <div class="container mx-auto px-6 text-center" data-aos="fade-down">
            <h1 class="text-3xl md:text-5xl font-extrabold text-stone-800">Thực Đơn Của Chúng Tôi</h1>
            <p class="text-stone-500 mt-2">Khám phá những hương vị tuyệt hảo đang chờ bạn</p>
        </div>
    </section>

    <div class="container mx-auto px-6 py-12 lg:py-16">
        <div class="lg:grid lg:grid-cols-4 lg:gap-12">

            <aside class="hidden lg:block lg:col-span-1" data-aos="fade-right">
                <div class="sticky top-28 p-6 bg-white rounded-xl shadow-sm border border-stone-200/80">
                    <h3 class="text-xl font-bold text-stone-800 pb-4 border-b border-stone-200">Bộ lọc</h3>

                    <div class="mt-6">
                        <h4 class="font-semibold text-stone-700 mb-3">Danh mục sản phẩm</h4>
                        <div class="space-y-2 text-stone-600">
                            <label class="flex items-center cursor-pointer">
                                <input type="checkbox" class="h-5 w-5 rounded border-stone-300 text-fuchsia-600 focus:ring-fuchsia-500 custom-checkbox">
                                <span class="ml-3">Trà Sữa Truyền Thống</span>
                            </label>
                            <label class="flex items-center cursor-pointer">
                                <input type="checkbox" class="h-5 w-5 rounded border-stone-300 text-fuchsia-600 focus:ring-fuchsia-500 custom-checkbox" checked>
                                <span class="ml-3">Trà Trái Cây</span>
                            </label>
                            <label class="flex items-center cursor-pointer">
                                <input type="checkbox" class="h-5 w-5 rounded border-stone-300 text-fuchsia-600 focus:ring-fuchsia-500 custom-checkbox">
                                <span class="ml-3">Macchiato</span>
                            </label>
                            <label class="flex items-center cursor-pointer">
                                <input type="checkbox" class="h-5 w-5 rounded border-stone-300 text-fuchsia-600 focus:ring-fuchsia-500 custom-checkbox">
                                <span class="ml-3">Đá xay</span>
                            </label>
                        </div>
                    </div>

                    <div class="mt-8">
                        <h4 class="font-semibold text-stone-700 mb-3">Mức giá</h4>
                        <div class="space-y-2 text-stone-600">
                            <label class="flex items-center cursor-pointer">
                                <input type="radio" name="price" class="h-5 w-5 text-fuchsia-600 focus:ring-fuchsia-500">
                                <span class="ml-3">Tất cả</span>
                            </label>
                            <label class="flex items-center cursor-pointer">
                                <input type="radio" name="price" class="h-5 w-5 text-fuchsia-600 focus:ring-fuchsia-500">
                                <span class="ml-3">Dưới 40.000đ</span>
                            </label>
                            <label class="flex items-center cursor-pointer">
                                <input type="radio" name="price" class="h-5 w-5 text-fuchsia-600 focus:ring-fuchsia-500">
                                <span class="ml-3">40.000đ - 60.000đ</span>
                            </label>
                            <label class="flex items-center cursor-pointer">
                                <input type="radio" name="price" class="h-5 w-5 text-fuchsia-600 focus:ring-fuchsia-500">
                                <span class="ml-3">Trên 60.000đ</span>
                            </label>
                        </div>
                    </div>

                    <button class="mt-8 w-full bg-fuchsia-600 text-white font-bold py-3 px-4 rounded-lg hover:bg-fuchsia-700 transition-colors duration-300">
                        Áp dụng
                    </button>
                </div>
            </aside>

            <div class="lg:col-span-3">
                <div class="flex justify-between items-center mb-6" data-aos="fade-in">
                    <p class="text-stone-600">Hiển thị <span class="font-semibold">12</span> trên <span class="font-semibold">48</span> sản phẩm</p>

                    <button @click="isFilterOpen = true" class="lg:hidden bg-white border border-stone-300 px-4 py-2 rounded-lg flex items-center gap-2 text-stone-700">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor"><path fill-rule="evenodd" d="M3 3a1 1 0 011-1h12a1 1 0 011 1v3a1 1 0 01-.293.707L12 11.414V15a1 1 0 01-.293.707l-2 2A1 1 0 018 17v-5.586L3.293 6.707A1 1 0 013 6V3z" clip-rule="evenodd" /></svg>
                        Bộ lọc
                    </button>

                    <div class="hidden lg:block">
                        <select class="border border-stone-300 rounded-lg p-2 focus:ring-2 focus:ring-fuchsia-400 focus:border-fuchsia-400">
                            <option>Sắp xếp theo: Mặc định</option>
                            <option>Sắp xếp theo: Bán chạy</option>
                            <option>Sắp xếp theo: Giá tăng dần</option>
                            <option>Sắp xếp theo: Giá giảm dần</option>
                        </select>
                    </div>
                </div>

                <div class="grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-3 gap-x-6 gap-y-10">
                    <c:forEach var="p" items="${products}">
                        <div class="bg-white rounded-xl overflow-hidden shadow-sm border border-stone-200/80 card-hover-effect flex flex-col" data-aos="fade-up">
                            <div class="relative">
                                <img src="${p.image_url}" alt="${p.name}" class="w-full h-60 object-cover">
                                <c:if test="${p.best_seller}">
                                    <span class="absolute top-3 right-3 bg-amber-400 text-stone-800 text-xs font-bold px-3 py-1 rounded-full">Bán chạy</span>
                                </c:if>
                                <c:if test="${p.stock == 0}">
                                    <span class="absolute top-0 left-0 w-full h-full bg-black/50 flex items-center justify-center text-white font-bold text-lg">Hết Hàng</span>
                                </c:if>
                            </div>
                            <div class="p-5 flex flex-col flex-grow">
                                <h3 class="text-lg font-bold text-stone-800 mb-1">${p.name}</h3>
                                <p class="text-stone-500 text-sm mb-4 line-clamp-2">${p.description}</p>

                                <div class="mt-auto pt-4 border-t border-stone-200/80 flex justify-between items-center">
                                    <p class="text-xl font-extrabold text-fuchsia-600">${p.price}đ</p>
                                    <button class="bg-stone-800 text-white h-10 w-10 rounded-full flex items-center justify-center hover:bg-fuchsia-600 transition-colors duration-300">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor"><path d="M3 1a1 1 0 000 2h1.22l.305 1.222a.997.997 0 00.01.042l1.358 5.43-.893.892C3.74 11.846 4.632 14 6.414 14H15a1 1 0 000-2H6.414l1-1H14a1 1 0 00.894-.553l3-6A1 1 0 0017 3H6.28l-.31-1.243A1 1 0 005 1H3zM16 16.5a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0zM6.5 18a1.5 1.5 0 100-3 1.5 1.5 0 000 3z" /></svg>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="mt-12 flex justify-center" data-aos="fade-up">
                    <nav class="flex items-center justify-center gap-2">
                        <a href="#" class="inline-flex items-center justify-center h-10 w-10 rounded-lg bg-white border border-stone-200 text-stone-500 hover:bg-stone-100 transition-colors">&laquo;</a>
                        <a href="#" class="inline-flex items-center justify-center h-10 w-10 rounded-lg bg-fuchsia-600 text-white font-semibold shadow-sm">1</a>
                        <a href="#" class="inline-flex items-center justify-center h-10 w-10 rounded-lg bg-white border border-stone-200 text-stone-700 hover:bg-stone-100 transition-colors">2</a>
                        <a href="#" class="inline-flex items-center justify-center h-10 w-10 rounded-lg bg-white border border-stone-200 text-stone-700 hover:bg-stone-100 transition-colors">3</a>
                        <span class="inline-flex items-center justify-center h-10 w-10 text-stone-500">...</span>
                        <a href="#" class="inline-flex items-center justify-center h-10 w-10 rounded-lg bg-white border border-stone-200 text-stone-500 hover:bg-stone-100 transition-colors">&raquo;</a>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</main>

<div
        x-show="isFilterOpen"
        x-transition:enter="transition ease-out duration-300"
        x-transition:enter-start="opacity-0"
        x-transition:enter-end="opacity-100"
        x-transition:leave="transition ease-in duration-300"
        x-transition:leave-start="opacity-100"
        x-transition:leave-end="opacity-0"
        class="fixed inset-0 bg-black/50 z-40"
        @click="isFilterOpen = false"
        aria-hidden="true">
</div>
<aside
        x-show="isFilterOpen"
        x-transition:enter="transition ease-in-out duration-300"
        x-transition:enter-start="transform translate-x-full"
        x-transition:enter-end="transform translate-x-0"
        x-transition:leave="transition ease-in-out duration-300"
        x-transition:leave-start="transform translate-x-0"
        x-transition:leave-end="transform translate-x-full"
        class="fixed top-0 right-0 h-full w-full max-w-sm bg-white z-50 shadow-2xl p-6 overflow-y-auto"
>
    <div class="flex justify-between items-center pb-4 border-b border-stone-200">
        <h3 class="text-xl font-bold text-stone-800">Bộ lọc</h3>
        <button @click="isFilterOpen = false" class="text-stone-500 hover:text-stone-800">&times;</button>
    </div>
</aside>


<%@ include file="../../../layout/client/footer.jsp" %>

<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script>
    AOS.init({
        duration: 600,
        once: true,
        disable: 'phone'
    });
</script>

</body>
</html>