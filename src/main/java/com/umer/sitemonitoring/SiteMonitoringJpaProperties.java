package com.umer.sitemonitoring;

import java.util.Properties;

public class SiteMonitoringJpaProperties {

	public Properties createJpaProperties() {
		Properties jpaProperties=new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("javax.persistence.validation.mode", "none");
		//jpaProperties.put("hibernate.format_sql", "true");
		return jpaProperties;
	}
}
