package org.example.demo.services;

import org.example.demo.dao.ToppingDAO;
import org.example.demo.models.Topping;

import java.util.List;

public class ToppingService {
    private final ToppingDAO toppingDAO;

    public ToppingService() {
        this.toppingDAO = new ToppingDAO();
    }

    public List<Topping> getAll() {
        return toppingDAO.findAll();
    }

    public Topping getById(String id) {
        return toppingDAO.findById(id);
    }

    public Topping create(Topping topping) {
        return toppingDAO.create(topping);
    }

    public void update(Topping topping) {
        toppingDAO.update(topping);
    }

    public void delete(String id) {
        toppingDAO.delete(id);
    }
}


