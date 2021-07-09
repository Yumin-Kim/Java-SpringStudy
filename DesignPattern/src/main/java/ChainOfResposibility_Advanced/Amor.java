package ChainOfResposibility_Advanced;

public abstract class Amor implements Defense {
	
	private Defense nextDefense;
	
	public void setNextArmor(Defense nextDefense) {
		this.nextDefense = nextDefense;
	}

	@Override
	public void depense(Attack attack) {
		
		process(attack);
		
		if(nextDefense != null)
			nextDefense.depense(attack);
	}
	
	abstract protected void process(Attack attack);

}
