package com.umer.sitemonitoring;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories
public class SpringConfiguration {
	
	private static final String PACKAGE_TO_SCAN_FOR_ENTITY = "com.umer.sitemonitoring.entity";
	private static final String HSQL_MEM_PASSWORD = "";
	private static final String HSQL_MEM_USERNAME = "sa";
	private static final String HSQLDB_MEM_JDBC_URL = "jdbc:hsqldb:mem:test";

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory=new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		SiteMonitoringJpaProperties monitoringJpaPropertiesjpaProperties=new SiteMonitoringJpaProperties();
		Properties jpaProperties = monitoringJpaPropertiesjpaProperties.createJpaProperties();
		entityManagerFactory.setJpaProperties(jpaProperties);
		entityManagerFactory.setPackagesToScan(PACKAGE_TO_SCAN_FOR_ENTITY);
		entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
		return entityManagerFactory;
		
	}

//	private Properties createJpaProperties() {
//		Properties jpaProperties=new Properties();
//		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
//		jpaProperties.put("hibernate.show_sql", "true");
//		jpaProperties.put("javax.persistence.validation.mode", "none");
//		//jpaProperties.put("hibernate.format_sql", "true");
//		return jpaProperties;
//	}

	@Bean
	public JpaTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(HSQLDB_MEM_JDBC_URL);
		dataSource.setUsername(HSQL_MEM_USERNAME);
		dataSource.setPassword(HSQL_MEM_PASSWORD);
		return dataSource;
	}

}
