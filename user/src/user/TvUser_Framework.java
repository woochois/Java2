package user;

import java.io.File;

import framework.TvContext;
import tvspi.TV;

public class TvUser_Framework {
	public static void main(String[] args) {
		File configFile = new File("resources/RootContext.xml");
		TvContext tvContext = new TvContext(configFile);
		TV tv = (TV)tvContext.getBean(args[0]);
		tv.powerOn();
	}
}
