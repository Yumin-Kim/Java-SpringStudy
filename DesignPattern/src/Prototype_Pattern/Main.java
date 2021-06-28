package Prototype_Pattern;

/*
 	Prototype Pattern - Prototype 사전적인 의미 - 원형 본 표준이라는 의미를 지니고 있다.
 	프로토타입 패턴을 통해서 복잡한 인스턴스를 복사 할 수 있다
 	프로토 타입 패턴은 생산 비용이 높은 인스턴스를 복사를 통해서 쉡게 생성 할 수 있도록 하는 패턴
 	
 	인스턴스 생산 비용이 높은 경우
 	종류가 너무 많아서 클래스로 정리되지 않는 경우
 	클래스로부터 인스턴스 생성이 어려운 경우
 	
 */

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {

		Circle circle1 = new Circle(2,4,8);
		Circle circle2 = circle1.copy();
		System.out.println(circle1.getX() + " , " + circle1.getY() + " , " + circle1.getZ()  );
		
		System.out.println(circle2.getX() + " , " + circle2.getY() + " , " + circle2.getZ()  );
		
	}

}
