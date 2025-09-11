package org.example.demo.services;

import org.example.demo.dao.SizeDAO;
import org.example.demo.models.Size;

import java.util.List;
import java.util.stream.Collectors;

public class SizeService {
    private final SizeDAO sizeDAO;

    public SizeService() {
        this.sizeDAO = new SizeDAO();
    }

    public List<Size> getAllSizes() {
        return sizeDAO.getAllSizes();
    }

    public List<Size> getAvailableSizes() {
        return sizeDAO.getAvailableSizes();
    }

    public List<Size> getSizesByProductId(String productId) {
        return getAllSizes().stream()
                .filter(size -> productId.equals(size.getProductId()))
                .collect(Collectors.toList());
    }
    public int totalStockByProductId(String productId) {
        return getSizesByProductId(productId).stream()
                .mapToInt(Size::getStock)
                .sum();
    }

    public Size getSizeById(String sizeId) {
        return sizeDAO.getSizeById(sizeId);
    }

    public boolean createSize(Size size) {
        return sizeDAO.createSize(size);
    }

    public boolean updateSize(Size size) {
        return sizeDAO.updateSize(size);
    }

    public boolean assignToProduct(String sizeId, String productId) {
        return sizeDAO.assignToProduct(sizeId, productId);
    }

    public boolean unassignFromProduct(String sizeId) {
        return sizeDAO.unassignFromProduct(sizeId);
    }

    public boolean deleteSize(String sizeId) {
        return sizeDAO.deleteSize(sizeId);
    }
}