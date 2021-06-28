package State_Pattern;

public class OnState implements State {
	
	private OnState() {}
	
	private static class LazyHolder{
		public static final OnState ON = new OnState();
	}
	
	public static OnState getInstance() {
		return LazyHolder.ON;
	}

	@Override
	public void onButton(Light light) {
		light.setState(SleepState.getInstance());
		System.out.println("잠자기 모드");
	}

	@Override
	public void offButton(Light light) {
		light.setState(OffState.getInstance());
		System.out.println("불 꺼짐");
	}


}
