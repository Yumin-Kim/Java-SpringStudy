package Memento_Pattern;

public class User {
	
	Information info;
	CareTaker careTaker;
	public void exe() {
		info = new Information("Data1",10);
		careTaker = new CareTaker();
		careTaker.push(info.CreateMento());
		
		info.setData1("Data2");
		info.setData2(20);
		
		System.out.println("현재 Data1 : " + info.getData1());
		System.out.println("현재 Data2 : " + info.getData2());
		
		info.RestorMemento(careTaker.pop());
		
		System.out.println("복구된 Data1 : " + info.getData1());
		System.out.println("복구된 Data2 : " + info.getData2());
	}

}
