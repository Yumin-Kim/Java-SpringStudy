package Builder_Pattern;

public class ComputerFactory {
	
	private Computer computer;
	private BluePrint blueprint;
	
	public ComputerFactory() {
	}

	public void make() {
		// TODO Auto-generated method stub
		blueprint.setCpu();
		blueprint.setRam();
		blueprint.setStorage();
		
	}

	public void setBluePrint(BluePrint blueprint) {
		this.blueprint = blueprint;
	}

	public Computer getComputer() {
		return blueprint.getComputer();
	}

}
