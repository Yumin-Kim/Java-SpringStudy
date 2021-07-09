package Command_Pattern;

public class OkGoogle {

	private Command command;
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void talk() {
		command.run();
	}
	
}
