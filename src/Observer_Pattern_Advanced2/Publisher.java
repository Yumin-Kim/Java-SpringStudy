package Observer_Pattern_Advanced2;

public interface Publisher {
	
	public void add(Observer observer);
	public void delete(Observer observer);
	public void notifyObserver();

}
