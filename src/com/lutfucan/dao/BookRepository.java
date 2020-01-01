package com.lutfucan.dao;

import java.util.List;

import com.lutfucan.model.Book;

public interface BookRepository {

	public void save(Book book);
	public List<Book> listAll();
	public Book findBookById(Long id);
}
