package State_Pattern;

public class SleepState implements State {
	
	private SleepState() {}
	
	private static class LazyHolder{
		public static final SleepState SLEEP = new SleepState();
	}
	
	public static State getInstance() {
		return LazyHolder.SLEEP;
	}

	@Override
	public void onButton(Light light) {
		light.setState(OnState.getInstance());
		System.out.println("잠자기 모드 해제(on State)");
	}

	@Override
	public void offButton(Light light) {
		light.setState(OffState.getInstance());
		System.out.println("불 꺼짐");
	}

	

}
