package com.lutfucan.demo;

import com.lutfucan.dao.AuthorRepository;
import com.lutfucan.dao.impl.AuthorRepositoryImpl;
import com.lutfucan.model.Author;

public class Demo {

	public static void main(String[] args) {
		AuthorRepository authorRepository = new AuthorRepositoryImpl();
		Author author = new Author();
		author.setName("Deneme");
		authorRepository.save(author);
	}
}
