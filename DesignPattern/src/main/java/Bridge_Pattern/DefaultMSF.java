package Bridge_Pattern;

public class DefaultMSF implements MorseCodeFunction {

	@Override
	public void dot() {
		System.out.print(".");
	}

	@Override
	public void dash() {
		System.out.print("-");
	}

	@Override
	public void space() {
		System.out.print(" ");
	}

}
