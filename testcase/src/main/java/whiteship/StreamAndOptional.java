package whiteship;


import data.InnerClass;
import data.Progress;
import data.Teacher;
import data.TestHOF;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamAndOptional {

    public static void main(String[] args) {
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
        innerClasses.get(1).setTeacher(new Teacher());
        Teacher teacher = innerClasses.get(1).getTeacher();
        teacher.setName("setHello 초기버전");
        Optional<String> name = teacher.getName();
        String asdasdasd = name.orElse("asdasdasd");
        System.out.println("name = " + asdasdasd);
        String s1 = name.orElseGet(() -> "Success");
        System.out.println("s1 = " + s1);
        String checkedOptinoal = name.orElseGet(() -> {
            System.out.println("Optional.empty 상태 입니다 ");
//            teacher.setName("Optional Null Point");
            return "input progrss...";
        });
        name.orElse("TEST");
        name.ifPresentOrElse(System.out::println, () -> {
            System.out.println("ifPresentOrElse 이며 비어 있으면 set할겁니다");
            teacher.setName("Hello!!!!!!!!!!!!!!!");
        });
        name.orElseGet(() -> {
            System.out.println("orElesGet Start");
            return "orEleseGet";
        });
//        System.out.println("checkedOptinoal = " + checkedOptinoal);
        String s = teacher.getName().get();
        System.out.println("s = " + s);
        System.out.println("===================flatMap 비교===================");

        List<InnerClass> booklist = new ArrayList<>();
        List<InnerClass> testlist = new ArrayList<>();
        booklist.add(new InnerClass("Book", 1L));
        testlist.add(new InnerClass("Test", 1L));

        List<List<InnerClass>> toList = new ArrayList<>();
        toList.add(booklist);
        toList.add(testlist);
        toList.stream().flatMap(Collection::stream)
                .map(InnerClass::getTitle)
                .forEach(System.out::println);
    }
}
