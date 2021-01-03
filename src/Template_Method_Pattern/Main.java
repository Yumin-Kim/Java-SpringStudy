package Template_Method_Pattern;

import Template_Method_Pattern_UsedClass.DefaultTemplateHelper;

/*
 template Method Pattern
 알고리즘의 구조를 메소드에 정의하고 하위 클래스에서 알고리즘 구조의 변경없이 알고리즘을 재정의 하는 패턴
 
 구현하려는 알고리즘이 일정한 프로세스가 있다.
 구현하려는 알고리즘이 변경 가능성이 있다.
 
 알고리즘을 여러 단계로 나뉜다.
 나눠진 알고리즘의 단계를 메소드로 선언한다.
 알고리즘을 수행할 템플릿 메소드를 만든다.
 하위 클래스에서 나눠진 메소드들을 구현한다.
 
 UserClass - pakeage는 외부의 라이브러리라고 생각!!
 부가적인 코드에 변경 사항들이 존재할시
 추상으로 정의된 클래스를 상속받은 클래스에 부가적인 알고리즘을 구현한다.

  
 
  */
public class Main {
	public static void main (String args[]) {
		DefaultTemplateHelper helper = new DefaultTemplateHelper();
		helper.requestConnection("아이디 접속 정보");
	}
}
