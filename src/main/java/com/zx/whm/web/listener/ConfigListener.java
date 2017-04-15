package com.zx.whm.web.listener;

import com.zx.whm.common.util.cache.DictCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听
 * @author fangll
 *
 */
public class ConfigListener implements ServletContextListener {
	private  static Logger logger = LoggerFactory.getLogger(ConfigListener.class);
	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		DictCache.loadDataFromFile();
		logger.debug("ConfigListener start");
	}
}
