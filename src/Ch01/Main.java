package Ch01;

public class Main {

	public static void main(String[] args) {
		
//		singlePattern single = new singlePattern();
		singlePattern single_1 = singlePattern.getInstance();
		singlePattern single_2 = singlePattern.getInstance();
		
		System.out.println(single_1);
		System.out.println(single_2);
		
		single_1.systemVol ++;
		System.out.println(single_1.systemVol);
		System.out.println(single_2.systemVol);
		
		
	}

}
