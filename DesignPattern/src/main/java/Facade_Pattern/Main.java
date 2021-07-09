package Facade_Pattern;

/*
 	Facade Pattern
 	복잡한 과정을 간단하게 제공
 	어떠한 서브시스템의 일련의 인테페이스에 대한 통합된 인터페이스를 제공한다.
 	퍼사드에서 고수준 인터페이스를 정의하기 때문에 서브시스템을 더 쉽게 사용할 수 있다.
 	퍼사드의 의미는 프랑스어로 건물의 외관이라는 뜻을 가지며 건물의 외벽에서 보면 안의 구조는 보이지 않는다.
 	퍼사드 패턴은 많은 서브시스템(내부 구조)을 거대한 클래스(외벽)로 만들어 감싸서 편리한 인터페이스를 제공해 줍니다
 */

public class Main {
	public static void main (String[] args) {
		 MicrowaveFacade microwave = new MicrowaveFacade(Mode.FAST);
	        microwave.on();
	        try {
	            Thread.sleep(10000);
	            microwave.off();
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}

}
