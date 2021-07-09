package Visitor_Pattern;

public class VistableA implements Vistable {
	
	int numberOfMember;

	public VistableA(int numberOfMember) {
		this.numberOfMember = numberOfMember;
	}
	
	@Override
	public void accept(Vistor vistor) {
		vistor.visit(this);
	}

}
