package user;

import java.io.File;

import framework.Autowired;
import framework.TvContext;
import tvspi.TV;

public class TvUser_DI {
	
	@Autowired
	private TV tv;
	
	public static void main(String[] args) {
		File configFile = new File("resources/RootContext.xml");
		TvContext tvContext = new TvContext(configFile);
		
		TvUser_DI tva = new TvUser_DI();
		// DI (의존성 주입)
		tvContext.injectDependency(tva);
		tva.watchTv();
	}
	
	private void watchTv() {
		tv.powerOn();
	}
}
