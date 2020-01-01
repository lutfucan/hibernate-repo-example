package com.lutfucan.dao;

import java.util.List;

import com.lutfucan.model.Author;

public interface AuthorRepository {

	public void save(Author author);
	public List<Author> listAll();
	public Author findAuthorById(Long id);
}
