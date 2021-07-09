package Stategy_Pattern;

public class ContactClass {
	
	
	//접근점
	private Istategy statergy;
	
	// 서로 교환 할 수 잇도록 제공
	public void setStaregy(Istategy stategy) {
		this.statergy = stategy;
	}
	
	public void attck() {
		//델리게이트 
		if(statergy == null) {
			System.out.println("Not Found statergy");
		}else {
			statergy.attack();
		}
	}
	
}
