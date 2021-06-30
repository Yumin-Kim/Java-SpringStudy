package com.algo.syntax;

/**
 * TCP글 인용
 * Object 클래스
 * 자바에서 Object 클래스는 모든 클래스의 부모 클래스가 되는 클래스이다
 * 자바의 모든 클래스는 자동으로 Object 클래스의 모든 필드와 메소드를 상속받게된다.
 * 즉 자바의 모든 클래스는 별도로 extends 키워드를 사용햐여 Object 클래스의 상속을 명시하지 않아도 Object클래스의 모든 멤버를 자유롭게 사용할 수 있다.
 * 자바의 모든 객체에서 toString() , clone()과 같은 메소드를 바로 사용할 수 있는 이유가 해당 Object 클래스의 메소드이기 때문이다.
 *  총 11개의 메소드만으로 구성되어 있다.
 *  toString()호출시 해당 객체의 인스터스 주소 값이 출력된다.
 *  equals()
 *  인스터스의주소값을 비교 즉 참조 변수를 비교하기때문에 똑같은 객체로 서로 다른 변수를 할당해도 false나옴
 *
 * 다형성
 * 하나의 객체가 여러가지 타입을 가질수 있음
 * 자바에서는 이러한 다형성을 부모 클래스 타입의 참조 변수로 자식 클래스타입의 인스턴스를 참조할 수 있도록 구현
 * 다형성은 상속 추상화와 더불어 객체 지향 프로그래밍 구성 중요한 특징
 *
 * 참조 변수의 다형성
 * 자바에서는 다형성을 위해 부모 클래스 타입의 참조변수로 자식 클래스 타입의 인스턴스를 참조
 * 참조 변수가 사용할 수 있는 멤버의 개수가 실제 인스턴스의 멤버 개수보다 같거나 적어야 참조할 수 있다.
 *
 * instanceof연산자
 * 다형성으로 인해 런타임에 참조 변수가 실제로 참조하고 있는 인스턴스의 타입을 확인 할 필요성이 생긴다.
 * 자바에서는 instaceof연산자를 제공하여 참조 변수가 참조하고 있는 인스턴스의 실제 타입을 확인할 수 있도록 해준다.
 *
 *
 */

class Parent {
    private int a = 10;		// private 필드
    public int b = 20;		// public 필드
}

public class Ch01 extends Parent {
    public int c = 30;		// public 필드
    private  int b = 100;
    public void display() {

        // System.out.println(a);	// 상속받은 private 필드 참조
        System.out.println(b);        // 상속받은 public 필드 참조
        System.out.println(this.b + "this.b");        // 상속받은 public 필드 참조
        System.out.println(super.b + "super.b");        // 상속받은 public 필드 참조
        System.out.println(c);        // 자식 클래스에서 선언한 public 필드 참조
    }
}

