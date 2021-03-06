# Design-Pattern-java

## SOLID원칙
* SOLID원칙이란 객체지향 설계에서 지켜줘야 할 5개의 원칙(SRP , OCP , LSP , DIP , ISP를 말한다. >> 개념은 알지라도 실현하기는 어려운 원칙이다.
* 그럼에도 설계 원칙을 알아야 하는 이유는 시스템에 예상하지 못한 변경사항이 발생하더라고, 유연하게 대처하고 이후에 대처하고 이후에는 확장성이 있는 시스템 구조를 설계하기 위해서 존재한다.
* 좋은 설계란 시스템에 새로운 요구 사항이나 변경 사항이 있을 때 , 영향을 받는 범위가 적은 구조를 말한다.
1. SRP(Single Responsibility Principle) , 단일 책임 원칙      
	**객체는 단하나의 책임만 가져야 한다는 원칙을 말한다.**       
	객체 지향적으로 설계 할 때는 응집도를 높게 , 결합도는 낮게 설계하는 것이 좋다.    	
 	1. 응집도    
		한 프로그램의 요소가 얼마나 뭉쳐있는지 , 즉 구성 요소들 사이의 응집려을 말한다.   
	2. 결함도    
		프로그램 구성 요소들 사이가 얼마나 의존적인지 말한다.    
	* 한 객체에 책임이 많아질수록 클래스 내부에서 서로 다은 역할을 수행하는 코드끼리 강하게 결합될 가능성이 높아집니다. 즉 객체마다 책임을 제대로 나누지 않는다면 시스템은 매우 복잡해집니다. EX) 계산기를 기능을 수행 할 수 있는 클래스에 알람기능이 추가되어하는 요구사항이 존재할 때 기존 계산기 클래스에 알람 메소드를 추가하지 않고 알람 클래스를 추가하여 복잡도를 분산 시켜준다.
	* 따라서 **여러 객체들이 하나의 책임만 갖도록 잘 분배한다면 시스템에 변화가 생기더라도 그 영향을 최소화 할 수 있기 때문에** SRP원칙을 따르는 것이 중요하다.
2. OCP(Open-Closed Principle) , 개방-폐쇄 원칙     
	**기존의 코드를 변경하지 않으면서(Closed) , 기능을 추가할 수 있도록(Open) 설계가 되어야 한다는 원칙**을 말한다.     
	확장에 대해서는 개방적이고 수정에 대해서는 패쇄적이어야 한다는 의미    
	이를 만족하는 설계가 되려면, 캡술화를 통해 여러 객체에서 사용하는 같은 기능을 인터페이스에 정의하는 방법이 있다.
3. LSP(Liskov Substitution Principle) , 리스코프 치환 원칙    
	**자식 클래스는 최소한 자신의 부모 클래스에서 가능한 행위는 수행할 수 있어야 한다는 설계 원칙** 즉 자식 클래스는 언제나 부모 클래스의 역할을 대체할 수 있어야 한다는 것을 말하며 , 부모 클래스와 자식 클래스의 행위가 일관됨을 의미한다.     
	자식 클래스가 부모 클래스를 대체하기 위해서는 부모의 기능에 대해 오버라이드 되지 않도록 하면 됩니다. 즉 자식 클래스는 부모 클래스의 책임을 무시하거나 재 정의하지 않고 확장만 수행하도록 해야 LSP를 만족하게 됩니다.     
	초창기 OOP를 공부할 때 오버라이드를 OOP의 특징으로 배웠지만 LSP에 따르면 객체 지향적으로 설계를 하기 위해서는 오버라이드는 가급적 피하는 것이 좋다고 한다.
4. ISP(Interface Sergregation Principle) , 인터페이스 분리 원칙     
	**자신이 사용하지 않는 인터페이스는 구현하지 말아야 한다는 설계원칙** 즉 하나의 거대한 인터페이스보다는 여러개의 구체적인 인터페이스가 낫다는 것을 의미한다.    
	 SRP는 객체의 단일 책임을 뜻한다면 , ISP는 인터페이스의 단일 책임을 의미한다.
5. DIP(Dependency Inversion Principle) , 의존 역전 원칙    
	객체들이 서로 정보를 주고 받을 때 의존 관계가 형성되는데 **이 때 객체들은 나름대로의 원칙을 갖고 정보를 주고 받아야한다는 설계 원칙** 여기서 나름대로 원칙이란 , 추상성이 낮은 클래스보다 추상성이 높은 클래스와 의존 관계을 맺어야 한다는 것을 의미한다. 일반적으로 인터페이스를 활용하면 이 원칙을 준수할 수 있게 됩니다.(캡슐화)    

## 추상 클래스와 인터페이스 공통점
* 추상 클래스와 인터페이스는 선언만 있고 구현 내용은 없는 클래스이다.
* 인스턴스화를 할 수 없다.
* 추상 클래스를 extends로 상속받은 자식들과 인터페이스를 implements하고 자식들만 객체를 생성 할 수 있다.
* interface < extends > interface 
* Class < extends > abstract Class
* Class < implements > interface
## 추상 클래스와 인터페이스 차이
* 추상 클래스(단일 상속) //// 인터페이스(다중 상속)
* 추상 클래스의 목적은 상속을 받아서 기능을 확장시키는 것(부모 유전자를 물려받는다)
* 인테페이스의 목적은 구현하는 모든 클래스에 대해 특정한 메소드가 반드시 존재하도록 강제하는 역할(부모로부터 유전자를 물려받는 것이 아니라 사교적으로 필요에 따라 결합하는 관계)      
  즉 구현 객체가 닽은 동작을 한다느것을 보장하기 위함
