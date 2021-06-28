package Visitor_Pattern;
import java.util.*;
/*
 	방문자 패턴
 	방문자 패턴을 이용하여 객체에서 처리를 분리해서 사용할 수 있다.
 	
 */

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Vistable> vistables = new ArrayList<Vistable>();
		
		vistables.add(new VistableA(1));
		vistables.add(new VistableA(2));
		vistables.add(new VistableA(3));
		vistables.add(new VistableA(4));
		vistables.add(new VistableA(5));
		
		VistorA visitor = new VistorA();
		for (Vistable va : vistables) {
			va.accept(visitor);
		}
		System.out.println(visitor.getNumber());
		
	}

}
