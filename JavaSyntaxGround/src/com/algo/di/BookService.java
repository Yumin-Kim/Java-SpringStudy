package com.algo.di;

import com.algo.whiteship.Book;

public class BookService {
    @Inject
    private BookRepository bookRepository;

    public void login(){
        System.out.println("BookService");
        bookRepository.save("Hello");
    }

}
