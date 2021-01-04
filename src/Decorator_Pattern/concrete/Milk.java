package Decorator_Pattern.concrete;

import Decorator_Pattern.abst.AbstAdding;
import Decorator_Pattern.abst.IBeverage;

public class Milk extends AbstAdding {

	public Milk(IBeverage base) {
		super(base);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getTotalPrice() {
		return super.getTotalPrice()+50;
	}
	

}
