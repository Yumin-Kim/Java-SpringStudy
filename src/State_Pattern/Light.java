package State_Pattern;

public class Light{
	
	private State state;
	
	public Light() {
		state = OffState.getInstance();
	}

	public void setState(State state) {
		this.state = state;
	}

	public void onButton() {
		this.state.onButton(this);
	}

	public void offButton() {
		this.state.offButton(this);
	}

}
