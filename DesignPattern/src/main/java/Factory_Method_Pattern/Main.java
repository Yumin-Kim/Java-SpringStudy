package Factory_Method_Pattern;

import Factory_Method_Pattern_Concrete_EX.HpCreator;
import Factory_Method_Pattern_Concrete_EX.MpCreator;
import Factory_Method_Pattern_InnerFramework.Item;
import Factory_Method_Pattern_InnerFramework.ItemCreater;

/*
 	Factory Method Pattern
 	팩토리 메소드 패턴에서 템플릿 메소드 패턴의 사용됨을 안다.
 	팩토리 메소드 패턴에서의 구조와 구현의 분리를 이해하고 구조와 구현의 분리의 장점을 안다.
 	
 	추상 클래스와 인터페이스의 차이
 	추상 클래스로 인한 상속을 진행하게 되는데 상속은 슈퍼 클래스의 기능을 이용하거나 확장하기 위해서 사용되고 다중 상속의 모호성 때문에 하나만 상속 받을 수 있다.
 	인터페이스는 해당 인터페이스를 구현한 객체들에 대하거 동일한 동작을 약속하기 위한 존재
 	
 	
 */

public class Main {
	
	public static void main(String args[]) {
		ItemCreater creator;
		Item item;
		
		creator = new HpCreator();
		item = creator.create();
		item.use();
		creator = new MpCreator();
		item = creator.create();
		item.use();
	}

}
