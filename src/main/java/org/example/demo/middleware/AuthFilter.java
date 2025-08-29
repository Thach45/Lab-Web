package org.example.demo.middleware;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*") // filter tất cả request
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Lấy session
        HttpSession session = req.getSession(false);

        // Lấy đường dẫn hiện tại
        String path = req.getRequestURI();

        // Các trang public (không cần login)
        boolean isPublicPath = path.contains("/login")
                || path.contains("/register")
                || path.contains("/assets")
                || path.contains("/admin");

        // Nếu đã login hoặc là đường dẫn public thì cho đi tiếp
        if (isPublicPath || (session != null && session.getAttribute("username") != null)) {
            chain.doFilter(request, response);
        } else {
            // Nếu chưa login thì redirect về trang login
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
