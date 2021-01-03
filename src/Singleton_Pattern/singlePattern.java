package Singleton_Pattern;

public class singlePattern {

	public int systemVol = 5;
	
	private static singlePattern single;
	
	public static singlePattern getInstance() {
		
		if(single == null) 
			single = new singlePattern();
		
		return single;
		
	}
	
	private singlePattern() {
		
	}
	
	public int getVolum() {
		return systemVol;
	}
	
	public void setVolum(int systemVol) {
		this.systemVol = systemVol;
	}

}
