package com.algo.data;


import lombok.Data;

import java.util.Optional;

@Data
public class InnerClass {
    private String title;
    private Long id;
    private Progress progress;
    private Teacher teacher;
    private Book book;
    private boolean closed;

    public int col;

    public Optional<Book> getBook() {
        return Optional.ofNullable(book);
    }


    public void setBook(Book book) {
        this.book = book;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void printUpperCase(String hello){
        System.out.println(hello);
    }

    public InnerClass(String title, Long id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
