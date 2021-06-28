package Abstract_Factory_Pattern;

import Abstract_Factory_Pattern_Module.BikeFactory;
import Abstract_Factory_Pattern_Module.Body;
import Abstract_Factory_Pattern_Module.Wheel;
import Abstract_Factory_Pattern_UsedProduct.SamFactory;

/*
 	Abstract Factory Pattern
 	관련 있는 객체의 생성을 가상화 할 수 있다.
 	
 */

public class Main {

	public static void main(String[] args) {

		BikeFactory factory = new SamFactory();
		
		Body body = factory.createBody();
		Wheel wheel = factory.createWheel();
		
		System.out.println(body.getClass());
		System.out.println(wheel.getClass());
		
		
	}

}
