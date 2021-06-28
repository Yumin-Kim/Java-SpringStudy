package State_Pattern;

/*
 	State Pattern
 	상태 패턴을 활용하여 상태르 객체로 나태나고 행동 구현을 한다.
 	state pattern은 객체가 특정 상태에 따라 행위를 달리 하는 상황에서
 	자신이 직접 상태를 체크하여 상태에 따라 행위를 호출하지 않고
 	상태를 객체화 하여 상태가 행동을 할 수 있도록 위임하는 패턴
 	
 	Stategy pattern과 유사하게 보일 수 있지만 Stategy Pattern은 상속을 대체 하려는 목적
 	State Pattern은 코드내에서 조건문을 대체하려는 목적으로 사용한다.
 	
 */

public class Main {
	
	public static void main(String args[]) {
		Light light = new Light();
		light.offButton();
		light.offButton();
		light.onButton();
		light.onButton();
		light.onButton();
		light.offButton();
		light.onButton();
		light.offButton();
		light.onButton();
		
	}

}
