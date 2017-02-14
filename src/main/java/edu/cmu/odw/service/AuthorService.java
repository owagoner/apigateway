package edu.cmu.odw.service;

import org.springframework.stereotype.Service;

import edu.cmu.odw.model.Author;


@Service
public interface AuthorService {

    public Author findByName(String name);
    
    public Author findById(Long id);
    
    public Author save(Author author);
}
