package Stategy_Pattern;
/*
	인터페이스에 대한 이해도 필요
	인터페이스는 선언과 구현을 분리하기 위해서 사용한다.
	델리 게이트 의미(위임하다)
	특정 객체의 기능을 사용하기 위해서 다른 객체의 기능을 호출하는것
	스트레티지 패턴
	여러 알고리즘을 하나의 추상적인 접근점[인터페이스]을 만들어 접근점에서 서로 교환 가능 하도록 하는 패턴
	스로리지 패턴의 예제
	사용자가 캐릭터와 무기르 생성할 수 있다.
	무기는 칼과 검을 만들 수 있다.
	결론
	하나의 추상적으로 선언된 접근접을 통해서 미래에 구현될 class에서 interface만 구현하고 기존에 있던 class에는 변동 사항을 제외 시켜주는 패턴을 보여주고 있다.
*/

public class S_Main {
	public static void main(String args[]) {
		
		ContactClass Cclass = new ContactClass();
		Cclass.attck();
		
		Cclass.setStaregy(new Aobj());
		Cclass.attck();
		
		Cclass.setStaregy(new Bobj());
		Cclass.attck();
		
		Cclass.setStaregy(new Cobj());
		Cclass.attck();
		
	}
}
