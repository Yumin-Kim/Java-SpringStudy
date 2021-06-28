package Adapter_Pattern;

/*
 어댑터 패턴 - 사전적인 어댑터의 의미 : 기계 가구 등을 다목적으로 사용하기 위한 부가기구
 알고리즘을 요구사항에 맞추어서 사용할 수 있다.
 interface로 구현된 어댑터는 건드리지 않고 어댑터를 implements한 class만 수정하는기법으로
 사용중인 클래스에 문제가 발생하여 log를 찍어봐야하는 상황이 발생한다면 어댑터를 implements한 class만 수정한다.
 사전적인 의미의 어댑터의 기능과 유사하게 다목적으로 사용할 수 있게끔 선언및 구현한다.
 기존에 구현되어 있는 알고리즘을 변경하지 않고 어댑터 패턴을 활용하여 원하는 요구사항에 따라 구현한다.
 
  */
public class Main {

	public static void main(String[] args) {
		Adapter adapter = new AdapterImpl();
	
		System.out.println(adapter.twiceOf(100f));
		System.out.println(adapter.halfOf(100f));
		
	}

}
