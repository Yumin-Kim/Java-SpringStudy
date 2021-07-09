package Memento_Pattern;

public class Information {
	
	private String Data1;
	private int Data2;
	
	public Information(String data1,int data2) {
		this.Data1 = data1;
		this.Data2 = data2;
	}
	
	public Memento CreateMento() {
		return new Memento(this.Data1,this.Data2);
	}
	
	public void RestorMemento(Memento memento) {
		this.Data1 = memento.getData1();
		this.Data2 = memento.getData2();
	}

	public String getData1() {
		return Data1;
	}

	public void setData1(String data1) {
		Data1 = data1;
	}

	public int getData2() {
		return Data2;
	}

	public void setData2(int data2) {
		Data2 = data2;
	}

}
