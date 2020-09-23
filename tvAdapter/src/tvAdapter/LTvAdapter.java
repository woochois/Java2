package tvAdapter;

import lg.LTv;
import tvspi.TV;

public class LTvAdapter implements TV{
	
	private LTv ltv = new LTv();
	
	@Override
	public void powerOn() {
		ltv.turnOn();
	}
}
