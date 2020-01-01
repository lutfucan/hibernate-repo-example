package com.lutfucan.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lutfucan.dao.AuthorRepository;
import com.lutfucan.model.Author;
import com.lutfucan.config.HibernateUtil;

public class AuthorRepositoryImpl implements AuthorRepository {

	@Override
	public void save(Author author) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(author);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public static void saveOrUpdate(Author author) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(author);

			Author author2 = session.get(Author.class, 1);

			author2.setName("Ram");

			session.saveOrUpdate(author2);

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
			transaction = session.beginTransaction();

			Author author = session.get(Author.class, id);
			if (author != null) {
				session.delete(author);
			}

			Author author2 = new Author();
			author2.setId(2L);
			session.delete(author2);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public List<Author> listAll() {
		List<Author> authorList = new ArrayList<>();
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			authorList = session.createQuery("from Author", Author.class).list();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return authorList;
	}

	public void remove(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Author author = session.get(Author.class, id);
			if (author != null) {
				session.remove(author);
			}
			Author author2 = new Author();
			author2.setId(2L);
			session.remove(author2);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public Author findAuthorById(Long id) {
		Transaction transaction = null;
		Author author = new Author();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			author = session.byId(Author.class).getReference(id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return author;
	}

}
