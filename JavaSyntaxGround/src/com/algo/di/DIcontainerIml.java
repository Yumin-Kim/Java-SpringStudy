package com.algo.di;

public class DIcontainerIml {
    public void loadDIcontainer(){
        System.out.println("==================리플랙션을 활용한 DI 개발하보기 ==================");
        try {
            System.out.println("DI Start point");
            BookService bookService = ContainerService.getObject(BookService.class);
            bookService.login();
            System.out.println("DI End point");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
