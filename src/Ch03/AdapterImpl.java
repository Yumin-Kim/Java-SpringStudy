package Ch03;

public class AdapterImpl implements Adapter {

	@Override
	public float twiceOf(float f) {
		return (float) UsedClass.doubled((double)f);
	}

	@Override
	public float halfOf(float f) {
		System.out.println("Debug....");
		return (float) UsedClass.half((double)f);
	}

}
