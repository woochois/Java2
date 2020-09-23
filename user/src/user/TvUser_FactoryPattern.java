package user;

import samsungs.STv;
import tvAdapter.LTvAdapter;
import tvspi.TV;

public class TvUser_FactoryPattern{
	
	public static void main(String[] args) {
		TV tv;
		if (args.length == 0)
			tv = createTV(null);
		else
			tv = createTV(args[0]);
		if (tv != null)
			tv.powerOn();
	}
	
	private static TV createTV(String beanName) {
		if ("STv".equals(beanName))
			return new STv();
		else if ("LTv".equals(beanName))
			return new LTvAdapter();
		else
			return null;
	}
}
