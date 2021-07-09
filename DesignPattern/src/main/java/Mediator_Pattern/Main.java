package Mediator_Pattern;

import Mediator_Pattern.Contract.Colleague;
import Mediator_Pattern.Contract.Mediator;

/*
 	Mediator Pattern
 	미디에이터 패턴을 통해 복잡한 관계를 간단한 관계로 구현한다.
 	모든 클래스 간의 복잡한 로직을 캡술화하여 하나의 클래스에 위임하여 처리하는 패턴이다
 	m:n의 관계에서 m:1의 관계로 복잡도를 떨어뜨려 유지 보수 및 재사용의 홛장성에 유리한 패턴이다.
 	
 	
 */
public class Main {
	
	public static void main (String atgs[]) {
		
		Mediator mediator = new ChatMediator();
		
		Colleague colleague1 = new ChatColleague();
		Colleague colleague2 = new ChatColleague();
		Colleague colleague3 = new ChatColleague();
		
		colleague1.join(mediator);
		colleague2.join(mediator);
		colleague3.join(mediator);
		
		colleague1.sendData("AAA");
		colleague2.sendData("BBB");
		colleague3.sendData("CCC");

	}

}
