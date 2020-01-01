package com.lutfucan.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lutfucan.dao.PublisherRepository;
import com.lutfucan.model.Publisher;
import com.lutfucan.config.HibernateUtil;

public class PublisherRepositoryImpl implements PublisherRepository{

	@Override
	public void save(Publisher publisher) {
		Transaction transaction = null;
		
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.save(publisher);
	        
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	}
	
	public static void saveOrUpdate(Publisher publisher) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(publisher);

            // get entity from database
            Publisher publisher2= session.get(Publisher.class, 1);

            // do changes 
            publisher2.setName("Ram");

            // update the student object
            session.saveOrUpdate(publisher2);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	
	public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a persistent object
            Publisher publisher = session.get(Publisher.class, id);
            if (publisher != null) {
                session.delete(publisher);
            }

            // Delete a transient object
            Publisher publisher2 = new Publisher();
            publisher2.setId(2L);
            session.delete(publisher2);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	@Override
	public List<Publisher> listAll() {
		List<Publisher> publisherList = new ArrayList<>();
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			publisherList = session.createQuery("from Publisher", Publisher.class).list();
	    } catch (Exception e) {
	        transaction.rollback();
	        e.printStackTrace();
	    }
		return publisherList;
	}
	
	public void remove(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a persistent object
            Publisher publisher = session.get(Publisher.class, id);
            if (publisher != null) {
                session.remove(publisher);
            }

            // Delete a transient object
            Publisher publisher2 = new Publisher();
            publisher2.setId(2L);
            session.remove(publisher2);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	@Override
	public Publisher findPublisherById(Long id) {
		Transaction transaction = null;
		 Publisher publisher = new Publisher();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method
            publisher = session.byId(Publisher.class).getReference(id);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return publisher;
	}
	
	

}
