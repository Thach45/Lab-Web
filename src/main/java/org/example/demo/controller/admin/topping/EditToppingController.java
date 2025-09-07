package org.example.demo.controller.admin.topping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.Topping;
import org.example.demo.services.ToppingService;

import java.io.IOException;

@WebServlet("/admin/manage-topping/update/*")
public class EditToppingController extends HttpServlet {
    private ToppingService toppingService;

    @Override
    public void init() throws ServletException {
        toppingService = new ToppingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            resp.sendRedirect("/admin/manage-topping");
            return;
        }
        String id = pathInfo.substring(1);
        Topping topping = toppingService.getById(id);
        if (topping == null) {
            resp.sendRedirect("/admin/manage-topping");
            return;
        }
        req.setAttribute("topping", topping);
        req.getRequestDispatcher("/view/page/admin/topping/editTopping.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String pathInfo = req.getPathInfo();
        String id = pathInfo != null && pathInfo.length() > 1 ? pathInfo.substring(1) : null;
        if (id == null) {
            resp.sendRedirect("/admin/manage-topping");
            return;
        }
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        Topping topping = new Topping();
        topping.setTopping_id(id);
        topping.setName(name);
        topping.setPrice(price);
        toppingService.update(topping);

        resp.sendRedirect("/admin/manage-topping");
    }
}


