package Composite_Pattern;

import java.util.*;

public class Folder extends Component {
	
	public Folder(String string) {
		super(string);
	}

	List<Component> children = new ArrayList<>();
	
	public boolean addComponent(Component component) {
		return children.add(component);
	}
	
	public boolean removeComponent(Component component) {
		return children.remove(component);
	}

	public Object getChildren() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
