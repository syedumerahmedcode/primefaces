package com.umer.sitemonitoring;

import java.util.Properties;

public class SiteMonitoringJpaProperties {

	private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String JAVAX_PERSISTENCE_VALIDATION_MODE = "javax.persistence.validation.mode";

	private static final String CREATE_DROP = "create-drop";
	private static final String TRUE = "true";
	private static final String NONE = "none";

	public Properties createJpaProperties() {
		Properties jpaProperties=new Properties();
		jpaProperties.put(HIBERNATE_HBM2DDL_AUTO, CREATE_DROP);
		jpaProperties.put(HIBERNATE_SHOW_SQL, TRUE);
		jpaProperties.put(JAVAX_PERSISTENCE_VALIDATION_MODE, NONE);
		return jpaProperties;
	}
}
