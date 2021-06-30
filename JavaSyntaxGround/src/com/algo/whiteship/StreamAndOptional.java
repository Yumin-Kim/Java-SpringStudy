package com.algo.whiteship;

import com.algo.data.Book;
import com.algo.data.InnerClass;
import com.algo.data.Progress;
import com.algo.data.Teacher;
import com.algo.data.TestHOF;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamAndOptional {

    public void StreamAndOptionalFunc(){
        ArrayList<InnerClass> innerClasses = new ArrayList<>();
        innerClasses.add(new InnerClass("spring", 1L));
        innerClasses.add(new InnerClass("spring Data JPA", 2L));
        innerClasses.add(new InnerClass("spring Security", 3L));
        innerClasses.add(new InnerClass("spring Security", 3L));
        innerClasses.add(new InnerClass("spring Security", 3L));

        List<String> collect = innerClasses.stream().map(InnerClass::getTitle).collect(Collectors.toList());
        List<Progress> collect1 = innerClasses.stream()
                .map((innerClass) -> {
                    innerClass.setProgress(new Progress(10, "50%"));
                    return innerClass;
                })
                .map(InnerClass::getProgress).collect(Collectors.toList());
        System.out.println("collect1.size() = " + collect1.size());
        collect1.forEach(progress -> System.out.println("progress.getPercent() = " + progress.getPercent()));
        collect.forEach(System.out::println);
        Supplier<String> hello = innerClasses.get(1)::getTitle;
        System.out.println("hello = " + hello.get());

        System.out.println("=============Lomda============");
        Consumer<String> printUpperCaseinnerClass = innerClasses.get(1)::printUpperCase;
        printUpperCaseinnerClass.accept("Consumer lomda expresstion");

        Supplier<String> returnString = innerClasses.get(1)::getTitle;
        String returnSupplier = returnString.get();
        System.out.println("returnSupplier = " + returnSupplier);

        System.out.println("====================interface====================");
        TestHOF testHOF = new TestHOF();
        testHOF.printShellScript("./Shell.sh");
        testHOF.hello();

        System.out.println("==================Optional==================");
        System.out.println("===================setter사용전후 비교===================");
        innerClasses.get(1).setTeacher(new com.algo.data.Teacher());
        Teacher teacher = innerClasses.get(1).getTeacher();
        teacher.setName("Hello");
        Optional<String> name = teacher.getName();

        String checkedOptinoal = name.orElseGet(() -> {
            teacher.setName("Optional Null Point");
            return "input progrss...";
        });
        System.out.println("checkedOptinoal = " + checkedOptinoal);
        String s = teacher.getName().orElseGet(() -> "Error");
        System.out.println("s = " + s);
        System.out.println("===================flatMap 비교===================");
        innerClasses.get(2).setBook(new Book(100));

        List<InnerClass> booklist = new ArrayList<>();
        List<InnerClass> testlist = new ArrayList<>();
        booklist.add(new InnerClass("Book", 1L));
        testlist.add(new InnerClass("Test", 1L));

        List<List<InnerClass>> toList =new ArrayList<>();
        toList.add(booklist);
        toList.add(testlist);
        toList.stream().flatMap(Collection::stream)
                .map(InnerClass::getTitle)
                .forEach(System.out::println);
    }
}
