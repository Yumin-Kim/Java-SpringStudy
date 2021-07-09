package Factory_Method_Pattern_Concrete_EX;

import Factory_Method_Pattern_InnerFramework.Item;

public class HpPotion implements Item {

	@Override
	public void use() {
		System.out.println("체력 회복");
	}

}
