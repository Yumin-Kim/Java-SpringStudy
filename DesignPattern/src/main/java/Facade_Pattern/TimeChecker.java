package Facade_Pattern;

import java.util.*;

public class TimeChecker implements Switch {
	
	public static long TIME_INTERVAL = 1000;
	private final int EXPIRED_TIME;
	private Timer timer;
	private TimerTask task;
	int count = 0;
	MicrowaveFacade microwave;
	
	public TimeChecker (int milsec , MicrowaveFacade microwave) {
		super();
		this.EXPIRED_TIME = milsec;
		this.count = EXPIRED_TIME/1000;
		
		timer = new Timer();
		
		this.microwave = microwave;
		
		task = new TimerTask() {
			
			@Override
			public void run() {
				if(count > 0 )
					System.out.println("Timer .. " + (count--) + "second");
				else {
					System.out.println("Success cook");
					timer.cancel();
					microwave.off();
				}
			}
		};
	}

	@Override
	public void on() {
		timer.schedule(task, 0,TIME_INTERVAL);
	}

	@Override
	public void off() {
		timer.cancel();
	}

}
