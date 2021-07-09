package Factory_Method_Pattern_InnerFramework;

public abstract class ItemCreater {
	
	//Template Method 형식으로 구현되었다.
	public Item create() {
		Item item;
		
		requestItemsInfo();
		item = createItme();
		createItemLog();
		
		return item;
	}
	
	abstract protected void requestItemsInfo();
	abstract protected void createItemLog();
	abstract protected Item createItme();
}
