package Flyweight_Pattern;
/*
 	Flyweight Pattern
 	플라이웨이트 패턴을 통해서 메모리를 가볍게 유지하는것
 	바용이 큰 자원을 공통으로 사용할 수 있도록 만드는 패턴
 	비용이 크다는 의미는 
 	1. 중복 생성 될 가능성이 높은 경우
 	2. 자원 생성 비용은 큰데 사용 빈도가 낮은 경우
 	
 	즉 생성된 객체를 생성한 후 저장하여 재활용하는 팩토리를 기반으로 합니다
 	객체가 요청 될 때마다 팩토리는 객체가 이미 생성 되었는지 확인하기 위해서 객체를 찾는다.
 	있는 경우 기존 오브젝트가 리턴됩니다. 그렇지 않으면 새 오브젝트가 작성 , 저장 및 리턴 됩니다.
 	메모리 공간의 낭비를 줄일 수 있다.
 */
public class Main {

	public static void main(String[] args) {
		
		String[] colors = {"Red","Green" ,"Blue","Yellow"};
		
		for(int i = 0 ; i < 10 ; i++) {
			Circle circle = (Circle) ShapeFactory.getCircle(colors[(int) (Math.random() *4)]);
			circle.setX((int) (Math.random() *100));
			circle.setY((int) (Math.random() *4));
			circle.setRadius((int) (Math.random() *10));
			circle.draw();
		}
		
	}

}
