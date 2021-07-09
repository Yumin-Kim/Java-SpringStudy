package Mediator_Pattern;

import Mediator_Pattern.Contract.Colleague;

public class ChatColleague extends Colleague {

	@Override
	public void handle(String data) {
		System.out.println(this + " _ " + data);
	}

}
