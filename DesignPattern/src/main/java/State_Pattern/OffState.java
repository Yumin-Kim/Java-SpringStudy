package State_Pattern;

public class OffState implements State {
	
	private OffState() {}
	
	private static class LazyHolder{
		public static final OffState OFF = new OffState();
	}
	
	public static OffState getInstance() {
		return LazyHolder.OFF;
	}

	@Override
	public void onButton(Light light) {
		light.setState(OnState.getInstance());
		System.out.println("불켜짐 !!");
	}

	@Override
	public void offButton(Light light) {
		System.out.println("동작 없음");
	}
	

}
