package jpacore.jpashop.test;

import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceProxy implements BookService {

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    BookService bookService;

    @Override
    public void rent() {
        System.out.println("BookServiceProxy implements BookService start Point");
        bookService.rent();
        System.out.println("BookServiceProxy implements BookService end Point");
    }

    @Override
    public void rentToUser() {
        System.out.println("BookServiceProxy implements BookService start Point");
        bookService.rentToUser();
        System.out.println("BookServiceProxy implements BookService end Point");
    }
}
