package Proxy_Pattern;
/*
 	Proxy Pattern
 	실제 기능을 수행하는 객체 대신 가상의 객체 proxy class를 사용해 로직의 흐름을 제어하는 디자인 패턴
 	원래 하려던 기능을 수행하며 그외의 부가적인작업(로깅 , 인증  , 네트워크 통신 등 )을 수행 용이
 	비용이 많이 드는 연산을 실제로 필요한 시점에 수행할 수 있습니다.
 	사용자 입장에서는 프록시 객체나 실제 객체나 사용법은 유사하므로 사용성 높다.
 	
 	Virtual Proxy
 	주체 클래스가 리소스 집약적인 경우이다
 	실제 객체의 사용 시점을 제어 할 수 있다. 
 	
 	Protection Proxy
 	주체 클래스에 대한 접근을 제어하기 위한 경우
 	프록시 클래스에서 클라이언트가 주체 클래스에 대한 접근을 허용할지 말지 결정 하도록 할 수 있다.
 	
 	Remote Proxy 
 	프록시 클래스는 로컬에 두고 주체 클래스는 Remote로 존재하는 경우
 	
 	Client : Proxy 패턴 사용
 	Subject : proxy 와 RealSubject가 가져야 할 공통 인터페이스를 정의
 	Proxy : RealSubject에 대해 대리 수행을 실행
 	RealSubject : 실제 클래스에 해당
 	
 	
 */
public class Main {

	public static void main(String[] args) {

		Proxy proxy1 = new Proxy();
		
		proxy1.set_Name("홍길동");
		System.out.println("==========================");
		
		proxy1.Show_Name();
		System.out.println("==========================");
		
		proxy1.Complicated_Work();
		
	}

}
