package com.lutfucan.dao;

import java.util.List;

import com.lutfucan.model.Publisher;

public interface PublisherRepository {

	public void save(Publisher publisher);
	public List<Publisher> listAll();
	public Publisher findPublisherById(Long id);
}
