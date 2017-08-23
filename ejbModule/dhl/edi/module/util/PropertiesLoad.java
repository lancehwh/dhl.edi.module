package dhl.edi.module.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.jboss.logging.Logger;

public class PropertiesLoad {
	
	public static final Logger LOGGER = Logger.getLogger(PropertiesLoad.class);
	private Properties properties = null;
	//public static final String PROPERTIES_FILE="/Shuttle_EDI.properties";
	private String propertiesFileName = null;
	
	public PropertiesLoad(String propertiesFileName){
		this.propertiesFileName = propertiesFileName;
	}
	
	public void load() {
		properties = new Properties();
		String jbossConfigPath = System.getProperty("jboss.server.config.dir");

		try {
			//properties.load(new FileInputStream(jbossConfigPath + PROPERTIES_FILE));
			properties.load(new FileInputStream(jbossConfigPath + propertiesFileName));
			
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			
		}
		
	}
	
	public Properties getProperties() {
		//if (properties == null)
			load();
		
		return properties;
	}
}
