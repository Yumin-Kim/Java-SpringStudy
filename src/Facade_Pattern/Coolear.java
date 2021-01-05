package Facade_Pattern;

public class Coolear implements Switch {
	
	@Override
	public void on() {
		System.out.println("쿨러 작동 시작..");
	}

	@Override
	public void off() {
		// TODO Auto-generated method stub
		System.out.println("쿨러 작동 끝...");
	}

}
