package framework;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TvContext {
	private Map<String, Object> beanContext = new HashMap<>();

	public TvContext(File configFile) {
		loadBeans(configFile);
	}

	/*
	 * XML 해석
	 */
	private void loadBeans(File configFile) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document doc = docBuilder.parse(configFile);
			doc.getDocumentElement().normalize(); // 객체끼리 연결

			Element beanConfig = doc.getDocumentElement();
			String beanName = beanConfig.getAttribute("class");
			createBean(beanName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createBean(String beanName) {
		try {
			Class<?> beanClass = Class.forName(beanName);
			Object beanObject = beanClass.newInstance();
			beanContext.put(beanName, beanObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getBean(String beanName) {
		return beanContext.get(beanName);
	}

	public <T> void injectDependency(T targetObj) {
		Field[] fields = targetObj.getClass().getDeclaredFields();
		for (Field field : fields) {
			Autowired annotation = field.getAnnotation(Autowired.class);
			if (annotation != null) {
				Object property = beanContext.values().iterator().next();
				field.setAccessible(true);
				try {
					field.set(targetObj, property);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void startService(Class<?> targetClass) {
		// TODO Auto-generated method stub
		
	}
}
