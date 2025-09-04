package org.example.demo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.demo.entitys.UserModel;
import java.util.List;

public class UserRepository {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public UserRepository() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public void save(UserModel user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public UserModel findById(String id) {
        return em.find(UserModel.class, id);
    }

    public UserModel findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM UserModel u WHERE u.username = :username", UserModel.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<UserModel> findAll() {
        return em.createQuery("SELECT u FROM UserModel u", UserModel.class).getResultList();
    }

    public void update(UserModel user) {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
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
            UserModel user = em.find(UserModel.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void close() {
        em.close();
        emf.close();
    }
}
