package Memento_Pattern;

/*
 	Memento Pattern
 	메멘토 패턴은 객체의 상태 정보를 저장하고 사용자의 필요에 의하여 원하는 시점의 데이터를 복원 할 수 있는 패턴을 의미합니다.
 	객체의 상태 정보를 가지는 클래스를 따로 생성하여 객체의 상태를 저장하거나 인전 상태로 복원할 수 있게 해주는 패턴
 	메멘터 패턴은 바둑  , 오목등 무리기 기능을 구현할 때 사용되기도 한다.
 	단 이전 상태의 객체를 저장하기 위한 Originator가 클 경우 많은 메모리가 필요합니다.
 	예제 클래스 구성도
 	User : 메멘토 패턴이 적용 된 Information 객체를 실제로 사용하는 사용자
 	Information : 상태를 저장하고 복원 할 데이터를 가지고 있는 클래스
 	Memento: 특정 시점의 Information의 상태 정보를 저장하는 클래스
 	Care Taker : 상태 정보가 저장되어 있는 Memento들을 관리하는 클래스 , 내부에 stack 자료형 변수를 가짐으로써 Memento 객체를 저장하고 복원 
 */

public class Main {

	public static void main(String[] args) {
		System.out.println("Statr Memento");
		
		User user = new User();
		user.exe();
		
	}

}
