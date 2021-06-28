package Mediator_Pattern;

import Mediator_Pattern.Contract.Colleague;
import Mediator_Pattern.Contract.Mediator;

public class ChatMediator extends Mediator {

	@Override
	public void mediate(String data) {
		for(Colleague colleague : colleagues) {
			colleague.handle(data);
		}
	}

}
