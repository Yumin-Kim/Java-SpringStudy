package Decorator_Pattern.abst;

public abstract class AbstAdding implements IBeverage{
	
	private IBeverage base;
	
	public AbstAdding(IBeverage base) {
		super();
		this.base = base;
	}
	
	public int getTotalPrice() {
		return base.getTotalPrice();
	}
	
	protected IBeverage getBase() {
		return base;
	}
	
}
