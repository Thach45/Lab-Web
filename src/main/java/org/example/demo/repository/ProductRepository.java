package org.example.demo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.demo.entitys.Product;
import java.util.List;

public class ProductRepository {
    private final EntityManagerFactory emf;
    private EntityManager em;

    public ProductRepository() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    private void refreshEntityManager() {
        if (em != null) {
            em.close();
        }
        em = emf.createEntityManager();
    }

    public void save(Product product) {
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            em.clear(); // Clear persistence context after commit
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public Product findById(String id) {
        refreshEntityManager(); // Refresh to get latest data
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        refreshEntityManager(); // Refresh to get latest data
        return em.createQuery("SELECT p FROM Product p", Product.class)
                 .setHint("jakarta.persistence.cache.storeMode", "REFRESH")
                 .getResultList();
    }

    public void update(Product product) {
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
            em.clear(); // Clear persistence context after commit
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void delete(String id) {
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
            em.getTransaction().commit();
            em.clear(); // Clear persistence context after commit
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
