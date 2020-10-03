package com.touhid.spring5webapp.repositories;

import com.touhid.spring5webapp.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface BookReposiotry extends CrudRepository<Book, Long> {
}
