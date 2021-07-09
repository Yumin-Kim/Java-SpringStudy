package Observer_Pattern_Advanced2;

public class Main {

	public static void main(String[] args) {
		NewMachine newsMachine = new NewMachine();
		AnnalSubscriber as = new AnnalSubscriber(newsMachine);
		EventSubscriber es = new EventSubscriber(newsMachine);
	
	
		newsMachine.setNewsInfo("오늘 한파", "전국 영하 10도 입니다");
		
		//newsMachine.delete(es);
		
		es.withdraw();
		
		newsMachine.setNewsInfo("벛꽃 축제 합니다", "다같이 벚꽇");
	}

}
