package com.mycompany.jpatest;

import java.util.List;
import javax.persistence.*;

/**
 * @author Rene
 */
public class CustomerDBMapper {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JPATest");

    public void addCustomer(int id, String fName, String lName) {
        // The EntityManager class allows operations such as create, read, update,
        // delete
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        // Used to issue transactions on the EntityManager
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new customer
            Customer cust = new Customer(id, fName, lName);

            // Save the customer object
            em.persist(cust);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println(ex.getMessage());
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public void getCustomer(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT c FROM Customer c WHERE c.id = :custID";

        // Issue the query and get a matching Customer
        TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
        tq.setParameter("custID", id);

        Customer cust;
        try {
            // Get matching customer object and output
            cust = tq.getSingleResult();
            System.out.println(cust);
        } catch (NoResultException ex) {
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public void getCustomers() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Customer c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Customer> tq = em.createQuery(strQuery, Customer.class);
        List<Customer> custs;
        try {
            // Get matching customer object and output
            custs = tq.getResultList();
            custs.forEach(cust -> System.out.println(cust));
            System.out.println("");
        } catch (NoResultException ex) {
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public void changeFName(int id, String fname) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Customer cust;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find customer and make changes
            cust = em.find(Customer.class, id);
            cust.setFName(fname);

            // Save the customer object
            em.persist(cust);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println(ex.getMessage());
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public void deleteCustomer(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Customer cust;

        try {
            et = em.getTransaction();
            et.begin();
            cust = em.find(Customer.class, id);
            em.remove(cust);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println(ex.getMessage());
        } finally {
            // Close EntityManager
            em.close();
        }
    }
}
