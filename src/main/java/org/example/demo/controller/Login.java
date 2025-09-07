package org.example.demo.controller;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.demo.dao.UserDAO;
import org.example.demo.models.UserModel;
import org.example.demo.services.AuthenticationService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private AuthenticationService userService;
    Dotenv dotenv = Dotenv.configure().load();
    @Override
    public void init() throws ServletException {
        userService = new AuthenticationService(new UserDAO());

    }
    String SECRET_KEY = dotenv.get("SECRET_KEY");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/view/login.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        UserModel user = null;
        try {
            user = userService.login(userName, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user != null) {
            String token = Jwts.builder()
                    .setSubject(userName)
                    .claim("idUser", user.getId())
                    .claim("role", user.getRole())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1h hết hạn
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                    .compact();
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            System.out.println(token);
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/home");
        }

        else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        }
    }
}
