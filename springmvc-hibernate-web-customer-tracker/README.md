![](CRM crud.assets/CRM-ArchitectureDemo.png)





## Customer Data Access Object

- Responsible for interfacing with the database

- This is a common design pattern : **Data Access Object (DAO)**

  ![](CRM crud.assets/CustomerDataObject.png)



## Customer Dara Access Object

- For Hibernate our DAO needs a Hibernate SessionFactory



![](CRM crud.assets/hibernate_session_factory.png)



## Hibernate Session Factory

- Hibernate SessionFactory needs a Data Source

  - The data source defines database connection info

    ![](CRM crud.assets/customer data access object.png)

**Data Source :**  Data Source tells us hot to connect with database

```xml
  <!-- Step 1: Define Database DataSource / connection pool -->
	<bean id="myDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
          destroy-method="close">
        <property name="driverClass" value="com.mysql.cj.jdbc.Driver" />
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&amp;serverTimezone=UTC" />
        <property name="user" value="hbstudent" />
        <property name="password" value="hbstudent" /> 

        <!-- these are connection pool properties for C3P0 -->
        <property name="minPoolSize" value="5" />
        <property name="maxPoolSize" value="20" />
        <property name="maxIdleTime" value="30000" />
	</bean>  
```

**Session Factory:** actually depends on data source

```xml
 <!-- Step 2: Setup Hibernate session factory -->
	<bean id="sessionFactory"
		class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
		<property name="dataSource" ref="myDataSource" />
		<property name="packagesToScan" value="com.touhid.springdemo.entity" />
		<property name="hibernateProperties">
		   <props>
		      <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
		      <prop key="hibernate.show_sql">true</prop>
		   </props>
		</property>
   </bean>	  
```



# Spring @Transactional

- automatically begin and end transaction for hibernate code
- no need for to do this in code

```java
@Transactional
public List<Customer> getCustomer() {
    // get the current hibernate4 session
    Session currentSession = sessionFactory.getCurrentSession();
    
    // create quyery
    Query<Customer> theQuery = currentSession.createQuery("from customer". Customer.class);
    
    //get the result list
    List<Customer> customers = theQuery.getResultList();
    return customers;
}
```



### 	Specialized Annotations for DAOs

- Applied to DAO implementations
- Spring will automatically register the DAO implementations 
- Spring also provides translations of any JDBC related exception

```java
@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    public List<Customer> getCustomers() {
        ... 
    }
}
```

```java
package com.luv2code.springdemo.dao;
import java.util.List;
import com.luv2code.springdemo.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers();	
}
```



# Add bootstrap manually in /resources folder

```xml
	<!-- add bootstrap manually in resourses folder -->
	<mvc:resources location="/resources/" mapping="/resources/**"></mvc:resources>
```

### In jsp file add this bootstrap location

```jsp
<head>
	<title>List Customers</title>
	<!-- reference our style sheet -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
```



# Constraint the Request Mapping - Get and Post 

```java
@RequestMapping(path="/processFormGet" , method="RequestMehtod.GET")
public String processFormGet( ) {

}
@RequestMapping(path="/processFormPost" , method="RequestMehtod.POST")
public String processFormPost( ) {

}
```

- The mapping Only handles GET method
- Any other HTTP REQUEST method will get rejected

## New Annotation Short-Cut

**@GetMapping("")** and **@PostMapping("")**

```JAVA
@GerMapping("/processFormGet")
public String processFormGet(....) {
	.....			
}

@GerMapping("/processFormPost")
public String processFormPost(....) {
	.....			
}
```



# Specialized Annotation for Service



#### Add a Service Layer

> Customer Controller <------> Customer Service <------> Customer DAO <------> DB



##### @Service Annotation

- @Service will automatically register the Service implementation - thanks to component scanning. 

For example :

**Customer Service:**

1. Define Service Interface
2. Define Service Implementation
   1. Inject the CustomerDAO