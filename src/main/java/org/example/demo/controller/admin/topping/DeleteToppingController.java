package org.example.demo.controller.admin.topping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.services.ToppingService;

import java.io.IOException;

@WebServlet("/admin/manage-topping/delete/*")
public class DeleteToppingController extends HttpServlet {
    private ToppingService toppingService;

    @Override
    public void init() throws ServletException {
        toppingService = new ToppingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String id = pathInfo.substring(1);
            toppingService.delete(id);
        }
        resp.sendRedirect("/admin/manage-topping");
    }
}


