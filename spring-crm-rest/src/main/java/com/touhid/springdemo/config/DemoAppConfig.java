package com.touhid.springdemo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.touhid.springdemo")
@PropertySource({"classpath:persistence-mysql.properties"})
public class DemoAppConfig implements WebMvcConfigurer{

	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	@Bean
	public DataSource myDataSource() {
		ComboPooledDataSource myDataSource = new ComboPooledDataSource();
		
		try {
			myDataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		// lets log user and url
		logger.info("jdbc url: " + env.getProperty("jdbc.url"));
		logger.info("jdbc user: " + env.getProperty("jdbc.user"));
		
		// set database connection props
		myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		myDataSource.setUser(env.getProperty("jdbc.user"));
		myDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		myDataSource.setInitialPoolSize(getIntParsedProperty("connection.pool.initialPoolSize"));
		myDataSource.setMinPoolSize(getIntParsedProperty("connection.pool.minPoolSize"));
		myDataSource.setMaxPoolSize(getIntParsedProperty("connection.pool.maxPoolSize"));
		myDataSource.setMaxIdleTime(getIntParsedProperty("connection.pool.maxIdleTime"));
		
		return myDataSource;
	}
	
	private Properties setHibernateProperties() {
		
		// set hibernate properties
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;
	}

	private int getIntParsedProperty(String string) {
		
		return Integer.parseInt(env.getProperty(string));
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		// create session factorys
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(setHibernateProperties());
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}	
	
	
}
