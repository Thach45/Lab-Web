// Product Filter and Pagination JavaScript
document.addEventListener('DOMContentLoaded', function() {
    // Xử lý sự kiện sắp xếp
    const sortSelect = document.getElementById('sortSelect');
    if (sortSelect) {
        sortSelect.addEventListener('change', function() {
            const sortBy = this.value;
            const urlParams = new URLSearchParams(window.location.search);
            urlParams.set('sortBy', sortBy);
            urlParams.set('page', '1'); // Reset về trang 1 khi sắp xếp
            window.location.href = '?' + urlParams.toString();
        });
    }

    // Xử lý sự kiện lọc desktop
    const desktopFilterForm = document.getElementById('desktopFilterForm');
    if (desktopFilterForm) {
        desktopFilterForm.addEventListener('submit', function(e) {
            e.preventDefault();
            applyFilters('desktop');
        });
    }

    // Xử lý sự kiện lọc mobile
    const mobileFilterForm = document.getElementById('mobileFilterForm');
    if (mobileFilterForm) {
        mobileFilterForm.addEventListener('submit', function(e) {
            e.preventDefault();
            applyFilters('mobile');
            // Đóng mobile filter
            const alpineData = document.querySelector('[x-data]');
            if (alpineData && alpineData.__x && alpineData.__x.$data) {
                alpineData.__x.$data.isFilterOpen = false;
            }
        });
    }

    function applyFilters(source = 'desktop') {
        const urlParams = new URLSearchParams(window.location.search);
        
        console.log('Filter source:', source);
        
        let category = 'all';
        let priceRange = 'all';
        
        if (source === 'mobile') {
            // Sử dụng mobile form
            const mobileCategory = document.querySelector('#mobileFilterForm input[name="category"]:checked');
            const mobilePriceRange = document.querySelector('#mobileFilterForm input[name="priceRange"]:checked');
            
            console.log('Mobile Category:', mobileCategory);
            console.log('Mobile Price Range:', mobilePriceRange);
            
            if (mobileCategory) {
                category = mobileCategory.value;
            }
            if (mobilePriceRange) {
                priceRange = mobilePriceRange.value;
            }
            
            console.log('Using Mobile Form - Category:', category, 'Price Range:', priceRange);
        } else {
            // Sử dụng desktop form
            const desktopCategory = document.querySelector('#desktopFilterForm input[name="category"]:checked');
            const desktopPriceRange = document.querySelector('#desktopFilterForm input[name="priceRange"]:checked');
            
            console.log('Desktop Category:', desktopCategory);
            console.log('Desktop Price Range:', desktopPriceRange);
            
            if (desktopCategory) {
                category = desktopCategory.value;
            }
            if (desktopPriceRange) {
                priceRange = desktopPriceRange.value;
            }
            
            console.log('Using Desktop Form - Category:', category, 'Price Range:', priceRange);
        }
        
        console.log('Final Category:', category);
        console.log('Final Price Range:', priceRange);
        
        urlParams.set('category', category);
        urlParams.set('priceRange', priceRange);
        urlParams.set('page', '1'); // Reset về trang 1 khi lọc
        
        console.log('Final URL:', '?' + urlParams.toString());
        window.location.href = '?' + urlParams.toString();
    }
});
