package Ch01;

public class Main {

	public static void main(String[] args) {
		// 유일하게 존재하는 인스턴스 접근을 통제 , 객체가 단 하나만 생성되기 때문에 변수영역의 메모리를 줄일 수 있다.
//		singlePattern single = new singlePattern();
		singlePattern single_1 = singlePattern.getInstance();
		singlePattern single_2 = singlePattern.getInstance();
		
		System.out.println(single_1);
		System.out.println(single_2);
		
		single_1.systemVol ++;
		System.out.println(single_1.systemVol);
		System.out.println(single_2.systemVol);
		
		
	}

}
