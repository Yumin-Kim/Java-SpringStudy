package Factory_Method_Pattern_Concrete_EX;

import Factory_Method_Pattern_InnerFramework.Item;

public class MpPotion implements Item {

	@Override
	public void use() {
		System.out.println("마력 회복중");
	}

}
