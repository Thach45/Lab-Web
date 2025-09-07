package org.example.demo.controller.admin.topping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.Topping;
import org.example.demo.services.ToppingService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-topping")
public class AdminManageTopping extends HttpServlet {
    private ToppingService toppingService;

    @Override
    public void init() throws ServletException {
        toppingService = new ToppingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Topping> toppings = toppingService.getAll();
        req.setAttribute("toppings", toppings);
        req.getRequestDispatcher("/view/page/admin/topping/manageTopping.jsp").forward(req, resp);
    }
}


