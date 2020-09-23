package user;

import framework.Autowired;
import tvspi.TV;

public class TvUser_IoC {
	
	@Autowired
	private TV tv;

	private void watchTv() {
		tv.powerOn();
	}
}
