package Abstract_Factory_Pattern_UsedProduct;

import Abstract_Factory_Pattern_Module.BikeFactory;
import Abstract_Factory_Pattern_Module.Body;
import Abstract_Factory_Pattern_Module.Wheel;

public class SamFactory implements BikeFactory{

	@Override
	public Body createBody() {
		return new SamBody();
	}

	@Override
	public Wheel createWheel() {
		return new SamWheel();
	}

}
