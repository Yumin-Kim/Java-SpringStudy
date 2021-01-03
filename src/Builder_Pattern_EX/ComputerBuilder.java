package Builder_Pattern_EX;

public class ComputerBuilder {
	
	private Computer computer;
	
	private ComputerBuilder() {
		computer = new Computer("Default","Default","Default");
	}
	
	public static ComputerBuilder startWithCpu(String string) {
		ComputerBuilder computerBuilder = new ComputerBuilder();
		computerBuilder.computer.setCpu(string);
		return computerBuilder;
	}

	public ComputerBuilder setRam(String string) {
		computer.setRam(string);
		return this;
	}

	public ComputerBuilder setStorage(String string) {
		computer.setStorage(string);
		return this;
	}

	public Computer build() {
		return this.computer;
	}

}
