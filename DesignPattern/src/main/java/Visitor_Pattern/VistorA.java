package Visitor_Pattern;

public class VistorA implements Vistor {
	
	private int number;
	
	public VistorA() {
		this.number = 0;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public void visit(Vistable vistable) {
		if(vistable instanceof VistableA) {
			number+= ((VistableA)vistable).numberOfMember;
		}
		
	}

}
