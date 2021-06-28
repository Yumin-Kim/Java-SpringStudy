package ChainOfResposibility;

/*
 	Chain of Resposibility 책임 사슬 패턴
 	다양한 처리 방식을 유연하게 연결 할 수 있다.
 	객체를 연결리스트와 같은 사슬 방식으로 연결한 후에 요청을 수행하지 못하는 객체라면 다음 객체에 넘기며 책임을 넘기는 형태의 패턴을 말한다
 */

public class Main {
	
	public static void main (String[] args) {

		Calculator plus = new PlusCalculator();
		Calculator sub = new SubCalculator();
		
		plus.setNextCalculator(sub);
		
		Request request1 = new Request(1,2,"+");
		Request request2 = new Request(10,2,"-");
		
		plus.process(request1);
		plus.process(request2);
		
		
	}

}
