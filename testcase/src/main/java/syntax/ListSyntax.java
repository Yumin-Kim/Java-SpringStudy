package syntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * List 자료형중
 * LinkedList
 * ArrayList
 */

public class ListSyntax {
    public void ArrayListFunc(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("apple");
        arrayList.add("apple2");
        arrayList.add(1, "Text");
        System.out.println(arrayList.get(1));
        System.out.println(arrayList.size());
        System.out.println(arrayList.contains("Test"));
        System.out.println(arrayList.remove("apple"));
        System.out.println(arrayList.remove(1));
        System.out.println(arrayList.get(0));
    }

    public void LinkedListFunc(){
        LinkedList linkedList = new LinkedList<String>();
        linkedList.add("apple");
        linkedList.add("banana");
        linkedList.add("strawberry");
        Iterator i = linkedList.descendingIterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }

}
