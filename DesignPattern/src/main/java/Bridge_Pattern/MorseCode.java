package Bridge_Pattern;

public class MorseCode {
	
	private MorseCodeFunction function;
	
	public MorseCode(MorseCodeFunction function) {
		this.function = function;
	}
	
	public void setFunction(MorseCodeFunction function) {
		this.function = function;
	}
	//델리게이트 구현	부 
	public void dot() {
		function.dot();
	}

	public void dash() {
		function.dash();
	}

	public void space() {
		function.space();
	}
}
