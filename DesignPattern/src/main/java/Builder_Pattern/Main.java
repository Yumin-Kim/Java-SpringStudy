package Builder_Pattern;

/*
 	Builder Pattern - 사전적의미 Bulider 건축업자 시공자 건조자 건설자와 같은 의미를 지니고 있다.
 	복잡한 단계가 필요한 안스턴스 생성을 빌더 패턴을 통해서 구현 할 수 있다.
 	복잡한 단계를 거쳐야 생성되는 객체의 구현을 서브 클래스에게 넘겨주는 패턴
 */
public class Main {

	public static void main(String[] args) {

		ComputerFactory factory = new ComputerFactory();
		
		factory.setBluePrint(new LgGramBluePrint());
		
		factory.make();
		
		Computer computer = factory.getComputer();
		
		System.out.println(computer.toString());
		
	}

}
