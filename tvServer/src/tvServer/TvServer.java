package tvServer;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import framework.TvContext;

public class TvServer {

	public static void main(String[] args) {
		new TvServer().startService();
	}

	private void startService() {
		File configFile = new File("resources/server.xml");
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document doc = docBuilder.parse(configFile);
			doc.getDocumentElement().normalize(); // 객체끼리 연결

			Element beanConfig = doc.getDocumentElement();
			String DP = beanConfig.getAttribute("deployedProject");
			Class<?> DPC = Class.forName(DP);
			URL url = DPC.getClassLoader().getResource("resources/pom.xml");
			
			InputStream inputStream = url.openStream();
			Document pomDoc = docBuilder.parse(inputStream);
			pomDoc.getDocumentElement().normalize();
			Element pomConfig = pomDoc.getDocumentElement();
			String usedFramework = pomConfig.getAttribute("framework");

			Class<?> frameworkClass = Class.forName(usedFramework);
			URL DPRCurl = DPC.getClassLoader().getResource("resources/RootContext.xml");
			InputStream isUserRootContext = DPRCurl.openStream();			
			Document DPRCdoc = docBuilder.parse(isUserRootContext);
			DPRCdoc.getDocumentElement().normalize();
			Element DPRCele = DPRCdoc.getDocumentElement();
			String targetClassName = DPRCele.getAttribute("class");
			Class<?> targetClass = Class.forName(targetClassName);
			
			((TvContext) frameworkClass.newInstance()).startService(targetClass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
