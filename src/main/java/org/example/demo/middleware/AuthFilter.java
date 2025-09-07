package org.example.demo.middleware;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*") // filter tất cả request
public class AuthFilter implements Filter {

    private String SECRET_KEY;

    @Override
    public void init(FilterConfig filterConfig) {
        Dotenv dotenv = Dotenv.configure().load();
        SECRET_KEY = dotenv.get("SECRET_KEY");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        // Các trang public (không cần login)
        boolean isPublicPath = path.contains("/login")
                || path.contains("/register")
                || path.contains("/assets");

        if (isPublicPath) {
            chain.doFilter(request, response);
            return;
        }

        // Lấy JWT từ cookie
        String token = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("token".equals(c.getName())) {
                    token = c.getValue();
                }
            }
        }

        if (token == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            // Giải mã & kiểm tra token
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(claims);
            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            // Check quyền theo URL
            if (path.startsWith(req.getContextPath() + "/admin") && !"ADMIN".equals(role)) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập ADMIN");
                return;
            }

            if (path.startsWith(req.getContextPath() + "/user")
                    && !( "USER".equals(role) || "ADMIN".equals(role))) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập USER");
                return;
            }

            // Nếu hợp lệ thì cho đi tiếp
            chain.doFilter(request, response);

        } catch (JwtException e) {
            // Token hết hạn hoặc sai → quay về login
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
