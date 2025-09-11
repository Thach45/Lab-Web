package org.example.demo.controller.admin.size;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.Size;
import org.example.demo.services.SizeService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-size")
public class ManageSizeController extends HttpServlet {
    private SizeService sizeService;

    @Override
    public void init() throws ServletException {
        sizeService = new SizeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Size> sizes = sizeService.getAllSizes();
        req.setAttribute("sizes", sizes);
        req.getRequestDispatcher("/view/page/admin/size/manageSize.jsp").forward(req, resp);
    }
}
