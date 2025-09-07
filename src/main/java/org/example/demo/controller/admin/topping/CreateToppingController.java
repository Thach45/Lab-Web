package org.example.demo.controller.admin.topping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.Topping;
import org.example.demo.services.ToppingService;

import java.io.IOException;

@WebServlet("/admin/manage-topping/create")
public class CreateToppingController extends HttpServlet {
    private ToppingService toppingService;

    @Override
    public void init() throws ServletException {
        toppingService = new ToppingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/view/page/admin/topping/createTopping.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        Topping topping = new Topping();
        topping.setName(name);
        topping.setPrice(price);
        toppingService.create(topping);

        resp.sendRedirect("/admin/manage-topping");
    }
}


