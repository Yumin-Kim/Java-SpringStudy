package Builder_Pattern;

public class LgGramBluePrint extends BluePrint {
	
	Computer computer =null;
	
	public LgGramBluePrint () {
		computer = new Computer("Default","Default","Default");
	}

	@Override
	public void setCpu() {
		computer.setCpu("i7");
	}

	@Override
	public void setRam() {
		computer.setRam("18g");
	}

	@Override
	public void setStorage() {
		computer.setStorage("256 ssd");
	}

	@Override
	public Computer getComputer() {
		return computer;
	}
	

}
