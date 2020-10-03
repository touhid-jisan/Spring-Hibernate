package com.touhid.spring5webapp.controllers;

import com.touhid.spring5webapp.repositories.BookReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private BookReposiotry bookReposiotry;

    @Autowired
    public BookController(BookReposiotry bookReposiotry) {
        this.bookReposiotry = bookReposiotry;
    }

    @RequestMapping("/books")
    public String getBooks(Model model) {

        model.addAttribute("books", bookReposiotry.findAll());
        return "books";
    }
}
