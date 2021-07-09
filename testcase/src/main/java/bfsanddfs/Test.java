package bfsanddfs;

import java.util.Arrays;

public class Test {
    private String name;
    private String value;


    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private void extracted() {
        Test test01 = new Test();
        Test test02 = new Test();
        test01.setName("Hello");
        test01.setValue("value");

        test02.setValue("Value01");

        Class<? extends Test> aClass = test01.getClass();
        Arrays.stream(test02.getClass().getDeclaredFields()).forEach((v)-> {

            v.setAccessible(true);
            try {
                System.out.println("v.getName() = " + v.getName());
                System.out.println("v = " + v.get(test02));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        System.out.println(aClass);
    }


}
