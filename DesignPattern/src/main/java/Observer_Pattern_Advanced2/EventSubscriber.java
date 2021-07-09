package Observer_Pattern_Advanced2;

public class EventSubscriber implements Observer {
	
	private String newsString;
	private Publisher publisher;
	
	public EventSubscriber (Publisher publisher) {
		this.publisher = publisher;
		publisher.add(this);
	}
	
	public void withdraw() {
		publisher.delete(this);
	}

	@Override
	public void update(String title, String news) {
		newsString = title + "\n================\n" + news;
		diplay();
	}
	
	public void diplay() {
		System.out.println("\n\n ==== 이벤트 유저 ====");
		System.out.println("\n\n"+ newsString);
	}

}
