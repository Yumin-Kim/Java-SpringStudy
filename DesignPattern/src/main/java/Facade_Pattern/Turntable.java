package Facade_Pattern;

public class Turntable implements Switch {

	@Override
	public void on() {
		System.out.println("Turntable이 움직인다");
	}

	@Override
	public void off() {
		System.out.println("Turntable이 멈추었습니다.");
	}

}
