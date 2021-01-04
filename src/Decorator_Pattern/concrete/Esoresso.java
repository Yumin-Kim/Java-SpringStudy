package Decorator_Pattern.concrete;

import Decorator_Pattern.abst.AbstAdding;
import Decorator_Pattern.abst.IBeverage;

public class Esoresso extends AbstAdding {
	
	static protected int espressoCount = 0;

	public Esoresso(IBeverage base) {
		super(base);
	}
	
	@Override
	public int getTotalPrice() {
		return super.getTotalPrice() + getAddPrice();
	}
	
	private static int getAddPrice(){
		espressoCount +=1;
		int addPrice = 100;
		
		if(espressoCount > 1){
			addPrice = 70;
		}
		return addPrice;
	}

}
