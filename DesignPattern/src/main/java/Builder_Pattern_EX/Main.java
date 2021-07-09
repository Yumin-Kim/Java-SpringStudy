package Builder_Pattern_EX;
/*
 	많은 변수를 가진 객체의 생성을 가독성 높도록 코딩 할 수 있다.
 	가독성 및 많은 멤버 변수
 	
 */
public class Main {
	public static void main(String[] args) {
		Computer computer = ComputerBuilder
				.startWithCpu("i7")
				.setRam("8g")
				.setStorage("128g ssd")
				.build();
		System.out.println(computer.toString());
	}
}
