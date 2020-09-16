# Spring Security JDBC



**Database Support in Spring Security: **

- Spring Security can read user account info from database
- By default we have to follow Spring Security's predefined table schema

 

**Customize Database Access with Spring Security:**

- Can also customized the table schema
- Useful if we have to custom tables specific to our project / custom
- We will be responsible for developing the code to access the data
  - JDBC, Hibernate.. etc



**Database Support in Spring Security:**

Development Process:

- **Step 1:** Develop SQL script to set up database tables
- **Step 2:** Add database sipport to Maven POM file
- **Step 3:** Create JDBC properties
- **Step 4:** Define DataSource in Spring Configuration
- **Step 5: **Update Spring Security Configuration to use JDBC



**Default Spring Security Data Schema:**

![](https://githubpictures.000webhostapp.com/pictures/spring-security-default-schema.png)





**Development Process**

**Step 1: Develop SQL Script to setup database tables**

```mysql
CREATE TABLE users (
    username varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    enabled tintint(1) NOT NULL,
    
    PRIMARY KEY (username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

​	**Spring Security Password Storage:**

- In Spring Security 5, passwords are stored usinf a specific format 

  ```mysql
  {id}encodedPassword
  ```

   

  |   ID   |       DESCRIPTION       |
  | :----: | :---------------------: |
  |  noop  |   Plain text password   |
  | bcrypt | BCrypt password hashing |

  > 1. create user table

  ```mysql
  INSERT INTO users 
  VALUES 
  	('touhid', '{noop}test123',1),
  	('islam', '{noop}test123',1),
  	('jisan', '{noop}test123',1);
  
  ```

  {noop} tel ls Spring Security the passwords are stored as plain text (noop)

  > 2. Create authorities table
  >
  >    ```mysql
  >    CREATE TABLE authorities (
  >    	username varchar(50) NOT NULL,
  >    	authority varchar(50) NOT NULL,
  >        
  >        UNIQUE KEY authorities_idx_1 (username, authority),
  >        CONSTRAINT authorities_ibfk_1
  >        FOREIGN KEY (username) REFERENCES users(username) 
  >    )ENGINE=InnoDB DEFAULT CHARSET=latin1;
  >    ```
  >
  >    insert values into authorities
  >
  >    ```mysql
  >    INSERT INTO authorities 
  >    VALUES
  >    ('touhid','ROLE_MANAGER'),
  >    ('touhid','ROLE_ADMIN'),
  >    ('touhid','ROLE_USER'),
  >    ('islam','ROLE_MANAGER'),
  >    ('islam','ROLE_USER'),
  >    ('jisan','ROLE_ADMIN'),
  >    ('jisan','ROLE_USER'),
  >    ('touhid-user', 'ROLE_USER');
  >    ```
  >
  >    ****



**Step 2: Add Database Support to Maven POM file**

1. JDBC Driver
2. DB Connection Pool  

```xml
//JDBC Driver
<dependency>
	<groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.45</version>
</dependency>

// DB Connection Pool
<dependency>
	<groupId>com.mchange</groupId>
    <artifactId>c3p0</artifactId>
    <version>0. 9.5.2</version>
</dependency>
```



**Step 3: Create JDBC Properties File**

>  persistence-mysql.properties
>
> ```properties
> #JDBC Connection Properties
> jdbc.driver = com.mysql.jdbc.driver
> jdbc.url = jdbc:mysql://localhost:3306/spring-security-demo?useSSL=false
> jdbc.username = hbstudent
> jdbc.password = hbstudent
> 
> # Connection pool porperties
> connection.pool.initialPoolSize = 5
> connection.pool.minPoolSize = 5
> connection.pool.maxPoolSize = 20
> connection.pool.maxIdleTime = 3000
> ```
>
> ****

**Step 4: Define DataSource in Spring Configuration**

> DemoConfigApp.java
>
> ```java
> 
> @Configuration
> @EnableWebMvc
> @ComponentScan(basePackages = "com.touhid.springsecurity.demo")
> @PropertySource("classpath:persistence-mysql.properties")
> public class DemoAppConfig() {
>     
>     // will hold data read from properties file
>     @Autowired
>     private Environment env;
>     
>     private Logger logger = Logger.getLogger(getClass().getName());
>     
>     @Bean
>     public DataSource securitydDataSource() {
>         // create connection pool
>         ComboPooledDataSource securityDataSource = new ComboPooledDataSources();
>         
>         // set the jdbc driver
>         try{
>             securityDataSource.setDriverClass(env.getProperty("jdbc.driver"))
>         } catch(PropertyVetoException exc) {
>             throw new RunTimeException(exc);
>         }
>         
>         // set database connection props
>         securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
>         securityDataSource.setUser(env.getProperty("jdbc.user"));;
>         securityDataSource.setPassword(env.getProperty("jdbc.password"));
>         
>         // set connection pool props
>         securityDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
>         securityDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
>         securityDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
>         securityDataSource.setMaxIdelTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
>         
>         return securityDataSourcr;
>     }  
> }
> ```
>
> ****

**Step 5: Update Spring Security to use JDBC (NO HARD CODED) :**

> DemoSecurityConfig.java
>
> ```javA
> @Configuration
> @EnableWebSecurity
> public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
> 	
> 	/* if we use jdbc connection */
>     // injects our data source 
> 	@Autowired 
> 	private DataSource securityDataSource;
> 	
> 	@Override
> 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
> 		auth.jdbcAuthentication().dataSource(securityDataSource);
>     } 
>     	
> 	@Override
> 	protected void configure(HttpSecurity http) throws Exception {
> 
> 		http.authorizeRequests()
> 			.antMatchers("/").hasRole("USER")
> 			.antMatchers("/leaders/**").hasRole("MANAGER")
> 			.antMatchers("/system/**").hasRole("ADMIN")
> 			.and()
> 			.formLogin()
> 			.loginPage("/showMyLoginPage")
> 			.loginProcessingUrl("/authenticateTheUser")
> 			.permitAll()
> 			.and()
> 			.logout().permitAll()
> 			.and()
> 			.exceptionHandling().accessDeniedPage("/access-denied");
>     }
> }
> ```
>
> ****



# The End...