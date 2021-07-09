package Mediator_Pattern.Contract;

import java.util.*;

public abstract class Mediator {
	
	protected List<Colleague> colleagues;
	
	public Mediator() {
		colleagues = new ArrayList<>();
	}
	
	public boolean addColleague(Colleague colleague) {
		if(colleague != null) 
			return colleagues.add(colleague);
		else
			return false;
	}
	
	public abstract void mediate(String data);

}
