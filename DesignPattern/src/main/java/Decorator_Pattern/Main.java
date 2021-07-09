package Decorator_Pattern;

import java.util.Scanner;

import Decorator_Pattern.abst.IBeverage;
import Decorator_Pattern.concrete.Base;
import Decorator_Pattern.concrete.Esoresso;
import Decorator_Pattern.concrete.Milk;

/*
 	데코레이터 패턴
 	동적으로 책임 추가가 필요할때 데코레이터 패턴을 사용할 수 있다.
 	
 */
public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		IBeverage bevarage = new Base();
		boolean done = false;
		while(!done) {
			System.out.println("음료 현재 가격 : " +  bevarage.getTotalPrice());
			System.out.print("선택 : 1 : 샷 추사 : / 2 : 우유 추가");
			switch(sc.nextInt()) {
			case  0 :
				done = true;
				break;
			case 1 :
				bevarage = new Esoresso(bevarage);
				break;
			case 2 :
				bevarage = new Milk(bevarage);
				break;
			}
			
		}
		System.out.println("음료 가격 : " + bevarage.getTotalPrice());
		sc.close();
		
	}

}
