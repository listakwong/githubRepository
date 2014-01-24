package net.tak.util;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * A generic and pooled Properties manager for storing system parameters of the
 * whole application
 */
public class PropertiesManager {
	protected static Hashtable<String, PropertiesManager> PropertiesManagerPool;
	private String _applicationName = null;
	private Properties properties = null;
	private static final Logger logger = Logger.getLogger(PropertiesManager.class);

	static {
		PropertiesManagerPool = new Hashtable<String, PropertiesManager>();
	}

	/**
	 * Please call getInstance(String applicationName) instead of constructor
	 */
	protected PropertiesManager(String applicationName) {
		properties = new Properties();
		_applicationName = applicationName;
		refresh();
	}

	/**
	 * To get a pooled manager to properties file <applicationName>.properties
	 * 
	 * @param applicationName
	 * @return PropertiesManager
	 */
	public static PropertiesManager getInstance(String applicationName) {
		// retrieve corresponding PropertiesManager from PropertiesManagerPool
		// and insert to pool if not found
		// if (not found) {
		// create datasource object for this Connection Manager
		// according to applicationName from Property file
		PropertiesManager cm = null;
		if (!PropertiesManagerPool.containsKey(applicationName)) {
			logger.info("create new instance for " + applicationName);
			cm = new PropertiesManager(applicationName);
			PropertiesManagerPool.put(applicationName, cm);
		} else {
			cm = (PropertiesManager) PropertiesManagerPool.get(applicationName);
		}
		return cm;
	}

	/**
	 * refresh() should not be called directly especially during production as
	 * it will impact performance and is not thread safe. use at your own risk.
	 * 
	 * @param applicationName
	 */
	synchronized public void refresh() {
		InputStream in = null;
		String propertiesFileName = "/" + _applicationName + ".properties";
		try {
			in = PropertiesManager.class.getResourceAsStream(propertiesFileName);
			if (in == null) {
				logger.error(propertiesFileName + " file not found");
			} else {
				properties.load(in);
			}

		} catch (Exception e) {
			logger.error("Error loading " + propertiesFileName + " : " + e.getMessage(), e);
		} finally {
			try {
				in.close();
			} catch (Exception eIgnored) {
				logger.error("Error closing " + propertiesFileName + " : " + eIgnored.getMessage(), eIgnored);
			}
			in = null;
		}
	}

	/**
	 * get a property from current properties file
	 * 
	 * @param key
	 * @return value
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public int getPropertyAsInt(String key) {
		String temp = this.getProperty(key);
		return Integer.parseInt(temp);
	}

	/**
	 * get the Properties object from manager for direct manipulation
	 * 
	 * @return Properties
	 */
	public Properties getProperties() {
		return properties;
	}

	public String getApplicationName() {
		return _applicationName;
	}
}