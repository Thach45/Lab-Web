package org.example.demo.controller.admin.size;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.Size;
import org.example.demo.services.SizeService;

import java.io.IOException;

@WebServlet("/admin/manage-size/create")
public class CreateSizeController extends HttpServlet {
    private SizeService sizeService;

    @Override
    public void init() throws ServletException {
        sizeService = new SizeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/view/page/admin/size/createSize.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String productId = req.getParameter("productId");
        int stock = Integer.parseInt(req.getParameter("stock"));
        String name = req.getParameter("name");

        Size size = new Size();
        size.setProductId(productId);
        size.setStock(stock);
        size.setName(name);

        sizeService.createSize(size);
        resp.sendRedirect("/admin/manage-size");
    }
}