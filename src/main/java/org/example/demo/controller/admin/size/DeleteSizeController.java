package org.example.demo.controller.admin.size;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.services.SizeService;

import java.io.IOException;

@WebServlet("/admin/manage-size/delete")
public class DeleteSizeController extends HttpServlet {
    private SizeService sizeService;

    @Override
    public void init() throws ServletException {
        sizeService = new SizeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sizeId = req.getParameter("id");
        sizeService.deleteSize(sizeId);
        resp.sendRedirect("/admin/manage-size");
    }
}