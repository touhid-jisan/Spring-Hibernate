package com.touhid.spring5webapp.bootstrap;

import com.touhid.spring5webapp.model.Author;
import com.touhid.spring5webapp.model.Book;
import com.touhid.spring5webapp.model.Publisher;
import com.touhid.spring5webapp.repositories.AuthorRepository;
import com.touhid.spring5webapp.repositories.BookReposiotry;
import com.touhid.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationListener;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookReposiotry bookReposiotry;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookReposiotry bookReposiotry, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookReposiotry = bookReposiotry;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {

        Publisher publisher1 = new Publisher();
        publisher1.setName("foo");
        publisher1.setAddress("bar");

        publisherRepository.save(publisher1);

        Author author1 = new Author("Touhid", "jisan");
        Book book1 = new Book("Title 1", "Isbn 1", publisher1);
        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);

        authorRepository.save(author1);
        bookReposiotry.save(book1);


        Author author2 = new Author("Authror2 First", "Author2 last");

        Book book2 = new Book("Title2", "isbm2", publisher1);
        author2.getBooks().add(book2);

        authorRepository.save(author2);
        bookReposiotry.save(book2);

    }
}
