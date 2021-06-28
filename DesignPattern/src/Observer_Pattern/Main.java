package Observer_Pattern;

import Observer_Pattern.Button.OnClickListener;

/*
 	Observer Pattern
 	객체 지향 설계를 하다보면 객체들 사이에서 다양한 처리를 할 경우가 많다. 
 	예를 들어 한 객체의 상태가 바뀌 경우 다른 객체들에게 변경됐다고 알려주느 경우를 들 수 있다.
 	상태를 가지고 있는 주체 객체와 같 변경을 
 */

public class Main {

	public static void main(String[] args) {
		
		Button button = new Button();
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(Button button) {
				System.out.println(button+" is Clicked");
			}
		});
		
		button.onClick();

	}

}
