package Factory_Method_Pattern_Concrete_EX;

import Factory_Method_Pattern_InnerFramework.Item;
import Factory_Method_Pattern_InnerFramework.ItemCreater;

public class MpCreator extends ItemCreater {

	@Override
	protected void requestItemsInfo() {
		System.out.println("데이터 베이스에서 마력 물약 정보를 확인중입니다");
	}

	@Override
	protected void createItemLog() {
		System.out.println("마력 물약을 새로 생성 햇습니다" + new java.util.Date());
	}

	@Override
	protected Item createItme() {
		return new MpPotion();
	}

}
