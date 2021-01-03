package Prototype_Pattern;

public class Circle extends Shape {
	private int x, y, z;
	
	public Circle(int x , int y , int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public Circle copy() throws CloneNotSupportedException {
		Circle circle = (Circle) clone();
		circle.x++;
		circle.y++;
		circle.z++;
		
		return circle;
	}

}
