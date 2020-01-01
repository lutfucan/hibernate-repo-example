package com.lutfucan.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lutfucan.dao.BookRepository;
import com.lutfucan.model.Book;
import com.lutfucan.config.HibernateUtil;

public class BookRepositoryImpl implements BookRepository{

	@Override
	public void save(Book book) {
		Transaction transaction = null;
		
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.save(book);
	        
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	}
	
	public static void saveOrUpdate(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(book);

            // get entity from database
            Book book2= session.get(Book.class, 1);

            // do changes 
            book2.setName("Ram");

            // update the student object
            session.saveOrUpdate(book2);

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
            Book book = session.get(Book.class, id);
            if (book != null) {
                session.delete(book);
            }

            // Delete a transient object
            Book book2 = new Book();
            book2.setId(2L);
            session.delete(book2);

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
	public List<Book> listAll() {
		List<Book> bookList = new ArrayList<>();
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			bookList = session.createQuery("from Book", Book.class).list();
	    } catch (Exception e) {
	        transaction.rollback();
	        e.printStackTrace();
	    }
		return bookList;
	}
	
	public void remove(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a persistent object
            Book book = session.get(Book.class, id);
            if (book != null) {
                session.remove(book);
            }

            // Delete a transient object
            Book book2 = new Book();
            book2.setId(2L);
            session.remove(book2);

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
	public Book findBookById(Long id) {
		Transaction transaction = null;
		 Book book = new Book();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method
            book = session.byId(Book.class).getReference(id);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return book;
	}
	
	

}
