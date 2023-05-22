package com.example.crud_jee.DAO;

import com.example.crud_jee.models.Product;
import com.example.crud_jee.utils.ConnectionUtil;
import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    @Override
    public boolean addProduct(Product product) {
        try (Session session = ConnectionUtil.getSessionAnnotationFactory().getCurrentSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeProduct(Product product) {
        try (Session session = ConnectionUtil.getSessionAnnotationFactory().getCurrentSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(product);
            tx.commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> getProducts() {
        try (Session session = ConnectionUtil.getSessionAnnotationFactory().openSession()) {
            Query result = session.createQuery("from Product");
            return result.getResultList();
        } catch (HibernateException hibernateException) {
            System.out.println("problem fetching products");
            hibernateException.printStackTrace();
            return new ArrayList<Product>();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (Session session = ConnectionUtil.getSessionAnnotationFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(product);
            tx.commit();
        } catch (HibernateException he) {
            System.out.println("problem fetching products");
            he.printStackTrace();
        }
    }

    public Product getProductById(int id) {
        try (Session session = ConnectionUtil.getSessionAnnotationFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Product product = session.get(Product.class, id);
            tx.commit();
            return product;
        } catch (HibernateException he) {
            System.out.println("problem fetching products");
            he.printStackTrace();
            return null;
        }
    }
}
