

# XML Configuration (legacy, but most legacy app still use it)





## Function 1: Creating and Managing Objects (Inversion of Control)

**Development Process**

- **Step 1:** Configure Spring Beans 
- **Step 2:** Create Spring Container
- **Step 3:** Retrieve Beans from Spring Container



- ### Step 1: Configure Spring Beans 

  > applicationContext.xml
  >
  > ```xml
  > <beans ...>
  > 	<bean id="myCoach" class="com.touhid.springdemo.BaseballCoach">
  >     </bean>
  > </beans>
  > // we can change BaseballCoach To other Objects like TrackCoach
  > ```

- ### Step 2: Create Spring Container

  - Spring Container is generally known as **ApplicationContext**
  - Specialized implementation :
    - ClassPathXmlApplicationContext
    - AnnotationConfigApplicationContext
    - GenericWebApplicationContext
    - others.... 

  > ```java
  > ClassPathXmlApplicationContext context = 
  > 	new ClassPathXmlApplicationContext("applicationContext.xml");
  > ```

- ### Step 3: Retrieve Beans from Container

  > ```java
  > // create a spring container
  > ClassPathXmlApplicationContext context = 
  > 	new ClassPathXmlApplicationContext("applicationContext.xml");
  > 
  > // retrieve bean from spring container
  > Coach theCoach = context.getBean("myCoach", Coach.class);
  > ```





## Function 2: Inject Objects dependencies (Dependency Injection)



#### Development Process (Constructor Injection) :

- **Step 1:** Define the dependency interface class

- **Step 2:** Create a constructor in your class for injections

- **Step 3:** Configure the dependency injection in Spring config file   

  

  

    ![](/home/boogeyman/Desktop/qa/Iversion of Control (IoC).assets/object.png)

  

  ### Step 1: Define the dependency Interface and The implementaion class

  > FortucneService.java
  >
  > ```java
  > public interface FortuceService {
  > 	public String getFortune();
  > }
  > ```
  >
  > HappyFortuneService.java
  >
  > ```java
  > public class HappyFortuneService implements FortuneService {
  > 	public String getFortune () {
  > 		return "Something"
  > 	}
  > }
  > ```
  >
  > ****

  

  ### Step 2: Create a constructor in your class for injections

  > BaseballCoach.java
  >
  > ```java
  > public class BaseballCoach implements Coach {
  > 	private FortuneService fortuneService;
  > 	
  > 	public BaseballCoach(FortuneService theFortuneService) {
  > 		fortuneService = theFortuneService;
  > 	}
  > }
  > ```
  >
  > ****

  

  ### Step 3: Configure the dependency injection in Spring config file   

  > applicationContext.xml
  >
  > ```xml
  > // defince dependency / helper
  > <bean id="myFortuneService" class="com.touhid.springdemo.HappyFortuneService"></bean>
  > 
  > // inject the dependency / helper using "constructor injection"
  > <bean id="myCoach" class="com.touhid.springdemo.BaseballCoach" >
  > 	<constructor-arg ref="myFortuneService"/>
  > </bean>
  > ```
  >
  > ![](/home/boogeyman/Desktop/qa/Iversion of Control (IoC).assets/spring_backfround.png)
  >
  > ****

  

#### Development Process (Setter Injection) :

> Inject dependency by calling setter method.

- **Step 1:** Create setter methods in your class for injections.

- **Step 2: **Configure the dependency injection in spring config file.

  

  ### Step 1: Create Setter methods in you class for injection

  > CricketCoach.java
  >
  > ```java
  > public class CricketCoach implements Coach {
  > 	private FortuneService fortuneService;
  >     
  >     public CricketCoach() {}
  >     
  >     public void setFortuneService(FortuneService fortuneService) {
  >         this.fortuneService = fortuneService;
  >     }
  >     ...
  > }
  > ```
  >
  > ****

  ### Step 2: Configure the dependency injection in spring config file

  > applicationContext.xml
  >
  > ```xml
  > // defince dependency / helper
  > <bean id="myFortuneService" class="com.touhid.springdemo.HappyFortuneService"></bean>
  > 
  > // inject the dependency / helper using "setter injection"
  > <bean id="myCoach" class="com.touhid.springdemo.CricketCoach" >
  > 	<property name="fortuneService" ref="myFortuneService" />
  > </bean>
  > ```
  >
  > ![a](https://githubpictures.000webhostapp.com/pictures/setter-Method.png)
  >
  > 
  >
  > **How Spring Processes Config File**
  >
  > ![](/home/boogeyman/Desktop/qa/Iversion of Control (IoC).assets/springsetter.png)
  >
  > ****

  

#### Development Process (Injecting Literal Values)

- Step 1: Create Setter methods for the fields in class for injections

- Step 2: Configure the injection in Spring config file.

  

  ### Step 1: Create Setter methods  for the fields in class for injections

  > CricketCoach.java
  >
  > ```java
  > public class CricketCoach implements Coach {
  > 	private String emailAddress;
  > 	private Strin team;
  > 	
  > 	private void setEmailAddress (String emailAddress) {
  > 		...
  > 	}
  > 	private void team (String team) {
  > 		...
  > 	}
  > }
  > ```

  

  ### Step 2: Configure the injection in Spring config file.

  > applicationContext.java
  >
  > ```xml
  > <bean id="myCricketCoach" class="com.touhid.springdemo.CricketCoach">
  > 	<property name="emailAddress" value="touhidulislamjisan@gmail.com" />
  > 	<property name="team" value="Khulna Riders" />
  > </bean>
  > ```
  >
  > ****



#### Development Process (Injecting Values from Properties File)

- **Step 1:** Create Properties File

- **Step 2:** Load Properties File in Spring Config File

- **Step 3:** Reference values from Properties File

  ### Step 1: Create Properties File

  > sport.properties
  >
  > ```properties
  > foo.email=touhidulislamjisan@gmail.com
  > foo.team=Khulna Riders
  > ```
  >
  > ****

  ### Step 2: Load Properties File in Spring Config File

  > applicationContext.xml
  >
  > ```xml
  > <context:property-placeholder location="classpath:sprort.properties"
  > ```
  >
  > ****

  ### Step 3: Reference values from Properties File

  > applicationContext.xml
  >
  > ```xml
  > <bean id="myCricketCoach" class="com.touhid.springdemo.CricketCoach">
  > 	<property name="emailAddress" value="${foo.email}" />
  > 	<property name="team" value="${foo.team}" />
  > </bean>
  > ```
  >
  > ****

  

​			