package user;

import samsungs.STv;
import tvAdapter.LTvAdapter;
import tvspi.TV;

public class TvUser_SPI{
	public static void main(String[] args) {
		TV tv = new STv();	
		tv.powerOn();
		
		TV ltv = new LTvAdapter();
		ltv.powerOn();
	}
}
