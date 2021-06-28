package Facade_Pattern;

public class MicrowaveFacade {
	
	Coolear cooler;
	Magnetron magnetron;
	TimeChecker timeCheck;
	Turntable turntable;
	Mode mode;
	Switch[] switches;
	String food;
	boolean isActive = false;
	
    public MicrowaveFacade(Coolear cooler, Magnetron magnetron, TimeChecker timeCheck, Turntable turntable, Mode mode) {
        super();
        this.cooler = cooler;
        this.magnetron = magnetron;
        this.timeCheck = timeCheck;
        this.turntable = turntable;
        this.mode = mode;
        switches = new Switch[]{cooler, magnetron, timeCheck,turntable};
    }
 
    public MicrowaveFacade(Mode mode) {
        super();
        cooler = new Coolear();
        magnetron = new Magnetron();
        mode = new Mode();
        timeCheck = new TimeChecker(mode.getValue(), this);
        turntable = new Turntable();
        this.mode = mode;
        switches = new Switch[]{cooler, magnetron, turntable, timeCheck};
 
        
        
    }
    
    
    public void on() {
        
    
        for(int i=0; i<switches.length; ++i) {
            switches[i].on();
        }
        isActive = true;
        
        
    }
    
    public void off() {
        
        for(int i=0; i<switches.length; ++i) {
            switches[i].off();
        }
        
        isActive = false;
 
        
    }
 
    public void getMode() {
        System.out.println("현재 모드는... " + mode.getName());
 
    }
 
    public void setMode(Mode mode) {
        this.mode = mode;
    }

}
