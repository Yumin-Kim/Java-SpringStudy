package Proxy_Pattern;

public class Proxy implements Subject {
	
	public Real_Subject subject;
	public String name;
	
	@Override
	public void Show_Name() {
		System.out.println("proxy가 대신해서 처리 할 수 있어요.");
        System.out.println("my name is"+ name);
	}

	@Override
	public void set_Name(String name) {

		System.out.println("proxy가 대신해서 처리 할 수  있어요");
		if(subject != null) 
			subject.set_Name(name);
		this.name = name;

	}

	@Override
	public void Complicated_Work() {
		  subject = new Real_Subject();
	      subject.Complicated_Work();

	}

}
