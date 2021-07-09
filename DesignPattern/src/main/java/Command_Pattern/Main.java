package Command_Pattern;
/*
 	Command_Pattern 사전적인 의미 명령하다
 	실행될 기능을 캡슐화함으로써 주어진 여러 기능을 실행 할 수 있게 재사용성이 높은 클래스를 설계 패턴
 	즉 이벤트가 발생 했을 때 실행될 기능이 다양하면서도 변경이 필요한 경우에 이벤트를 발생시키는 클래스를 변경하지 않고 재사용하고자 할 때 유용하다.
 	실행될 기능을 캡슐화함으로써 기능의 실행을 요구하는 호출자 클래스와 실제 기능을 실행하는 수신자 클래스 사이의 의존성을 제거한다.
 	따라서 실행될 기능의 변경에도 호츌자 클래스를 수정 없이 그대로 사용 
 	
 */
public class Main {

	public static void main(String[] args) {
		Heater heater = new Heater();
		Lamp lamp = new Lamp();
		
		Command heaterOnCommand = new HeaterOnCommand(heater);
		Command lampOnCommand = new LampOnCommand(lamp);
		OkGoogle okgoogle = new OkGoogle();
		
		okgoogle.setCommand(heaterOnCommand);
		okgoogle.talk();
		
		okgoogle.setCommand(lampOnCommand);
		okgoogle.talk();
	}

}
