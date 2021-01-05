package ChainOfResposibility_Advanced;

public class Main {

	public static void main(String[] args) {
		
		Attack attack = new Attack();
		attack.setAmount(100);
		
		Amor armorA = new Amor() {
			@Override
			protected void process(Attack attack) {
				int amount = attack.getAmount();
				amount += 0.8;
				attack.setAmount(amount);
			}
		};
		
		Amor armorB = new Amor() {
			@Override
			protected void process(Attack attack) {
				int amount = attack.getAmount();
				amount -= 10;
				if(amount < 0 )
					amount = 0;
				attack.setAmount(amount);
			}
		};
		
		armorA.setNextArmor(armorB);
		armorA.depense(attack);
		
		System.out.println(attack.getAmount());
		
		armorA.setNextArmor(new Amor() {
			@Override
			protected void process(Attack attack) {
				int amount = attack.getAmount();
				amount -= 50;
				if(amount < 0 )
					amount = 0;
				attack.setAmount(amount);
			}
			
		});
		
		attack.setAmount(100);

		armorA.depense(attack);
		
		System.out.println(attack.getAmount());
		
	}

}
