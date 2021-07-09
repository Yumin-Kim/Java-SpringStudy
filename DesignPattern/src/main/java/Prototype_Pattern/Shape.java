package Prototype_Pattern;

public class Shape implements Cloneable {
	//일러스트 툴에 window 탭
	private String id;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

}
