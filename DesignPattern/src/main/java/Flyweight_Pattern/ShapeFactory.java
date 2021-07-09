package Flyweight_Pattern;

import java.util.HashMap;

public class ShapeFactory {
	
	private static final HashMap<String , Circle> circleMap = new HashMap<>();
	
	public static Shape getCircle(String color) {
		Circle circle = circleMap.get(color);
		if(circle == null) {
			circle = new Circle(color);
			circleMap.put(color, circle);
			System.out.println("==== Create New Class : " + color + "색 원 ===");
		}
		return circle;
	}

}
