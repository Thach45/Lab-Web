<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Boba Bliss - Cửa hàng trà sữa</title>

    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;600;700;800&display=swap" rel="stylesheet">

    <!-- Animate On Scroll Library (for scroll effects) -->
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin=""/>

    <style>
        /* Applying the custom font and smooth scrolling */
        html {
            scroll-behavior: smooth;
        }
        body {
            font-family: 'Be Vietnam Pro', sans-serif;
        }
        /* Custom gradient for the hero section */
        .hero-gradient {
            background: linear-gradient(135deg, #FFF1F2 0%, #FFE4E6 100%);
        }
        /* Custom shadow for cards */
        .card-shadow {
            box-shadow: 0 10px 15px -3px rgba(236, 72, 153, 0.1), 0 4px 6px -2px rgba(236, 72, 153, 0.05);
        }
        .card-shadow:hover {
            box-shadow: 0 20px 25px -5px rgba(236, 72, 153, 0.15), 0 10px 10px -5px rgba(236, 72, 153, 0.08);
        }
    </style>
</head>
<body class="bg-gray-50">

<!-- Header Section -->
<%@ include file="../layout/header.jsp" %>
<!-- Hero Section -->
<section class="hero-gradient pt-24 pb-20">
    <div class="container mx-auto px-6 flex flex-col lg:flex-row items-center">
        <div class="lg:w-1/2 text-center lg:text-left mb-10 lg:mb-0" data-aos="fade-right">
            <h1 class="text-5xl md:text-6xl font-extrabold text-gray-800 mb-6 leading-tight">
                Thổi bừng sảng khoái<br>trong từng ly trà sữa
            </h1>
            <p class="text-gray-600 text-lg mb-8 max-w-lg mx-auto lg:mx-0">Pha chế từ tâm, sử dụng nguyên liệu thượng hạng để mang đến cho bạn trải nghiệm trà sữa tuyệt vời nhất.</p>
            <a href="#menu" class="bg-pink-500 text-white py-3 px-8 rounded-full text-lg font-bold hover:bg-pink-600 transition-transform hover:scale-105 duration-300 shadow-xl shadow-pink-500/40">Xem thực đơn ngay!</a>
        </div>
        <div class="lg:w-1/2" data-aos="fade-left" data-aos-delay="200">
            <img src="../../assets/img/trasua.png" alt="Ly trà sữa hấp dẫn" class="rounded-3xl shadow-2xl w-full max-w-md mx-auto">
        </div>
    </div>
</section>

<!-- Featured Products Section -->
<section id="menu" class="py-20">
    <div class="container mx-auto px-6">
        <div class="text-center mb-12" data-aos="fade-up">
            <h2 class="text-4xl font-extrabold text-gray-800">Best-Sellers Của Tháng ✨</h2>
            <p class="text-gray-500 mt-2">Những hương vị được yêu thích nhất, bạn đã thử chưa?</p>
        </div>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-10">
            <c:forEach var="p" items="${product}">
                <div class="bg-white rounded-2xl overflow-hidden card-shadow transition-all duration-300" data-aos="fade-up">
                    <div class="relative">
                        <img src="${p.image_url}" alt="${p.name}" class="w-full h-64 object-cover">
                        <c:if test="${p.stock > 0}">
                            <span class="absolute top-4 left-4 bg-pink-500 text-white text-sm font-bold px-3 py-1 rounded-full">Còn hàng</span>
                        </c:if>
                        <c:if test="${p.stock == 0}">
                            <span class="absolute top-4 left-4 bg-gray-500 text-white text-sm font-bold px-3 py-1 rounded-full">Hết hàng</span>
                        </c:if>
                        <c:if test="${p.best_seller}">
                            <span class="absolute top-4 right-4 bg-green-500 text-white text-sm font-bold px-3 py-1 rounded-full">Best Seller</span>
                        </c:if>
                    </div>
                    <div class="p-6">
                        <h3 class="text-2xl font-bold text-gray-800 mb-2">${p.name}</h3>
                        <p class="text-gray-600 mb-2">Size: ${p.size}</p>
                        <p class="text-gray-600 mb-4 h-20">${p.description}</p>
                        <div class="text-2xl font-extrabold text-pink-500">${p.price} VNĐ</div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- About Us Section -->
<section id="about" class="bg-white py-20">
    <div class="container mx-auto px-6 flex flex-col md:flex-row items-center gap-12">
        <div class="md:w-1/2" data-aos="zoom-in-right">
            <img src="../../assets/img/trasua.png" alt="Không gian quán trà sữa" class="rounded-2xl shadow-xl">
        </div>
        <div class="md:w-1/2" data-aos="zoom-in-left" data-aos-delay="100">
            <h2 class="text-4xl font-extrabold text-gray-800 mb-4">Câu chuyện của BobaBliss</h2>
            <p class="text-gray-600 mb-4 leading-relaxed">
                Ra đời từ niềm đam mê bất tận, BobaBliss mong muốn mang đến không chỉ là một thức uống, mà còn là một trải nghiệm văn hóa, một niềm vui nho nhỏ trong cuộc sống.
            </p>
            <p class="text-gray-600 leading-relaxed">
                Chúng tôi cam kết sử dụng lá trà hảo hạng, sữa tươi thanh trùng và nguyên liệu tự nhiên, an toàn nhất. Mỗi ly trà sữa đều được pha chế bằng cả trái tim và sự tận tâm.
            </p>
        </div>
    </div>
</section>

<!-- Contact Section with Map -->
<section id="contact" class="py-20 bg-gray-50">
    <div class="container mx-auto px-6">
        <div class="text-center mb-12" data-aos="fade-up">
            <h2 class="text-4xl font-extrabold text-gray-800">Ghé thăm chúng tôi</h2>
            <p class="text-gray-500 mt-2">Chúng tôi luôn sẵn sàng phục vụ bạn!</p>
        </div>
        <div class="bg-white rounded-2xl shadow-xl overflow-hidden lg:grid lg:grid-cols-2">
            <!-- Leaflet Map Container -->
            <div id="map" class="h-80 lg:h-full z-10" data-aos="fade-right"></div>

            <!-- Contact Info -->
            <div class="p-8 lg:p-12 flex flex-col justify-center" data-aos="fade-left" data-aos-delay="100">
                <h3 class="text-2xl font-bold text-gray-800 mb-4">Thông tin liên hệ</h3>
                <div class="space-y-4 text-gray-600">
                    <p class="flex items-start">
                        <svg class="w-6 h-6 text-pink-500 mr-3 mt-1 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path></svg>
                        <span>Tòa tháp tài chính Bitexco, 2 Hải Triều, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh</span>
                    </p>
                    <p class="flex items-center">
                        <svg class="w-6 h-6 text-pink-500 mr-3 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z"></path></svg>
                        <span>0123 456 789</span>
                    </p>
                    <p class="flex items-center">
                        <svg class="w-6 h-6 text-pink-500 mr-3 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"></path></svg>
                        <span>hello@bobabliss.com</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<%@ include file="../layout/footer.jsp" %>

<!-- Animate On Scroll Library JS -->
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js" integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>

<script>
    // Initialize Animate On Scroll
    AOS.init({
        duration: 800,
        once: true,
    });

    // Mobile Menu Toggle
    const mobileMenuButton = document.getElementById('mobile-menu-button');
    const mobileMenu = document.getElementById('mobile-menu');

    mobileMenuButton.addEventListener('click', () => {
        mobileMenu.classList.toggle('hidden');
    });

    // Change header style on scroll
    const header = document.getElementById('header');
    window.onscroll = function() {
        if (window.pageYOffset > 50) {
            header.classList.add("shadow-lg");
        } else {
            header.classList.remove("shadow-lg");
        }
    };

    // --- Leaflet Map Initialization ---
    // Coordinates for Bitexco Financial Tower, HCMC
    const mapCoordinates = [10.7716, 106.7045];

    // Initialize the map

    const map = L.map('map').setView(mapCoordinates, 17);

    // Add the OpenStreetMap tiles
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);

    // Add a marker to the map
    const marker = L.marker(mapCoordinates).addTo(map);

    // Add a popup to the marker
    marker.bindPopup("<b>BobaBliss</b><br>Tòa tháp Bitexco.").openPopup();

</script>

</body>
</html>
