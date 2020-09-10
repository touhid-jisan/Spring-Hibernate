# <u>Spring Configuration with Java Annotations (modern)</u>



## Function 1: Creating and Managing Objects (Inversion of Control)



#### What are Java Annotations?

- Special labels/markers added to Java Classes
- Provide meta-data about the class
- Processed at compile time or run-time for special processing 

#### Why Spring Configuration with Annotations?

- XML configuration can be verbose
- Configure your Spring beans with Annotations
- Annotations minimizes the XML configuration



#### Scanning for Component Classes

- Spring will scan Java Classes for special annotations
- Automatically register the beans in the Spring Container

#### Development Process:

- **Step 1:** Enable **Component Scanning** in Spring Config file.

- **Step 2:** Enable **Component Scanning** in Spring Config file.

- **Step 3:** Retrieve bean from Spring Container

  

  ##### Step 1: Enable **Component Scanning** in Spring Config file.

  > xml file
  >
  > ```xml
  > <beans ...>
  > 	<context:component-scan base-package="com.touhid.springdemo" />
  > </beans>
  > ```
  >
  > Spring will scan this package recursively
  >
  > ****

  ##### Step 2: Enable **Component Scanning** in Spring Config file.

  > TrackCoach.java
  >
  > ```java
  > // bean id = thatSillyCoach
  > 
  > @Componenet("thatSillyCoach")
  > public class TennisCoach implements Coach{
  > 	
  >     @Override
  >     public String getDailyWorkout() {
  >         return "Practice Backhand";
  >     }
  > }
  > 
  > // if we don't define bean id then the default bean id  is : TennisCoach = tennisCoach
  > @Componenet
  > public class TennisCoach implements Coach{
  >     @Override
  >     public String getDailyWorkout() {
  >         return "Practice Backhand";
  >     }
  > }
  > ```
  >
  > Register this Spring bean autometically
  >
  > **Default Bean Id:**
  >
  > ![](https://githubpictures.000webhostapp.com/pictures/defaultbeanid.png)
  >
  > ****

  ##### Step 3: Retrieve bean from Spring Container

  > Same coding as before ... nothing changes
  >
  > ```java
  > // retrieve spring config file
  > ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
  > 
  > // get the bean from spring container
  > Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
  > ```
  >
  > ****





## Function 2: Dependency Injection With "Annotations" and "Autowiring"



#### **What is Spring AutoWiring?**

- For dependency injection, Spring can use auto wiring.
- Spring will look for a class that matches the property
  - matches by type: class or interface
- Spring will inject it automatically, hence it is autowired

#### **Autowiring Example:**

- Inject FortuneService into a Coach implementation
- Spring will scan **@Componants**
- Amy one implements FortuneService?
- If so, lets inject them. For example: HappyFortuneService



### Development Process: -Constructor Injection-

- **Step 1:** Define the dependency interface and class

- **Step 2:** Create a constructor in class for injections

- **Step 3:** Configure the dependency injection with @Autowired Annotation

- **Step 4:** Retrieve bean from Spring Container

  

  ##### Step 1: Define the dependency interface and class

  > FortuneService.java
  >
  > ```java
  > public interface FortuneService {
  > 	public String getFortune();
  > }
  > ```
  >
  > HappyFortuneService.java
  >
  > ```java
  > @Componenet
  > public class HappyFortuneService {
  > 	public String getFortune() {
  >         return "---";
  >     }
  >  }
  > ```
  >
  > ****

  ##### Step 2,3: Create a constructor in class for injections and Configure the dependency injection with @Autowired Annotation

  > TennisCoach,java
  >
  > ```java
  > @Component
  > public class TennisCoach implements Coach {
  > 	privet FortuneService fortuneService;
  > 	
  > 	@Autowired
  > 	public TennisCoach(FortuneService theFortuneService) {
  > 		fortuneService = theFortuneService;
  > 	}
  > }
  > ```
  >
  > ****

  ##### Step 4: Retrieve bean from Spring Container

  > Same coding as before ... nothing changes
  >
  > ```java
  > // retrieve spring config file
  > ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
  > 
  > // get the bean from spring container
  > Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
  > ```
  >
  > ****

  

  

### Development Process: -Setter Injection-

**Autowiring Example**

- Injecting FortuneService into Coach Implementation
- Spring will scan **@Components**
- Anyone implements FortuneService interface?
- If so, lets inject them. For example: HappyFortuneService

**Dependency Injection:**

- **Step 1: **Create Setter method in class for injection

- **Step 2:** Configure the dependency injection with **@Autowired** Annotation.

- **Step 3:** Retrieve bean from Spring Container

  **Step 1, 2:**

  > TennisCoach.java
  >
  > ```java
  > @Component 
  > public class TennisCoach implements Coach {
  > 	private FortuneService fortuneService;
  > 	
  > 	public TennisCoach () {}
  > 	
  >     @Autowired
  > 	public void setFortuneService(FotuneService fortuneService) {
  > 		this.fortuneService = fotuneService;
  > 	}
  > }
  > ```
  >
  > ****

  **Step 3: Retrieve bean from Spring Container**

  > ```java
  > // retrieve spring config file
  > ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
  > 
  > // get the bean from spring container
  > Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
  > ```
  >
  > ****

  

  

### Method Injection

> TennisCoach.java		
>
> ```java
> @Component 
> public class TennisCoach implements Coach {
> 	private FortuneService fortuneService;
> 	
> 	public TennisCoach () {}
> 	
>     @Autowired
> 	public void doSomeCrazyStuff(FotuneService fortuneService) {
>         System.out.println("Inside dosomeCrazyStaff");
> 		this.fortuneService = fotuneService;
> 	}
> }
> ```
>
> ****

### Field Injection

Development Process

- Configure the dependency injection with Autowired Annotation

  - Applied directly to the field

  - No need for setter methods

    > TennisCoach.java
    >
    > ```java
    > public class TennisCoach implements Coach {
    > 	
    > 	@Autowired
    > 	private FortuneService fortuneService;
    > 	
    > 	public TennisCoach() {}
    >     
    >     //no need for setter methods
    > }
    > 
    > ```
    >
    > this field is private still Spring will set this value behind the scene. The first thing Spring will do is construct the class by calling the default constructor then it will inject the FortuneService implementation directly into the class making use of some Java technology called **Reflection**. 
    >
    > ****

    



## Qualifiers for Dependency Injection***

> What happens if there's **multiple implementations** out there? I mean, which one will **Spring** pick? Will it pick the first one it finds?	Will it pick the last one? So, if we had multiple **FortuneService implementations** like the **HappyFortuneService**, and then what if we had like **RandomFortuneService** /**DatabaseFortuneService** and **RESTFortuneService**. How will Spring know which one to pick?
>
> So basically, in order to resolve this, we need to be able to give Spring a unique bean, because Spring can't figure it out on it's own  because there's more than one. So you have to kind of be very specific, and you have to qualify or tell Spring which specific bean you want them to use. So what you'll do is actually make use of this annotation called **Qualifier**.
>
> So in this example for **TennisCoach**, I'll use **@Qualifier**, and then I'll actually give the bean ID of the bean that I wanted to use.
>
> So our **happyFortuneService** class? is lower case **happyFortuneService**. So that's how we use the **qualifier**.
>
> We simply specify the bean ID of the component or the bean that we want Spring to inject. And this'll help resolve that problem of too many implementations.
>
> All right, so that's the **@Qualifier**.
>
> We simply be specific, and we give the actual bean name. Now, we can actually use this @Qualifier annotation on all our different injection types.
>
> So we can use it for **constructor injection.** We can also use it for **setter injection** and finally, for **field injection.**
>
> ```java
> @Component
> public class TennisCoach implements Coach{
> 	
> 	@Autowired
> 	Qualifire("happyFortuneService")
> 	private FortuneService fortuneService;
> }
> ```
>
> ****





### Bean Scopes with Annotations

> Singletone Scope - We dont need to specify it. Singleton give the same reference. For example **theCoach** and **alphaCoach** points to same area of memory
>
> ```java
> @Component
> @Scope("singleton")  // default is singleton we dont need to specify it
> public class TennisCoach implements Coach {
> 	....
> }
> ```
>
> 
>
> For Prototype we need to specify it. Prototype basically says its gonna create new object for each request
>
> ```
> @Component
> @Scope("prototype")  
> public class TennisCoach implements Coach {
> 	....
> }
> ```
>
> ****



### Bean Life Cycle Mehthod 

**@PostConstruct** (in xml config its **init-method="doMyStartupStaff()"**)

> For @PostConstruct , the mehod will execute **after** constructor and **after** injection of dependencies.
>
> ```java
> @Component
> public class TennisCoach implements Coach {
> 	
> 	@PostConstruct 
> 	public void doMyStartupStuff() {
> 		...
> 	}
> }
> ```
>
> ****

**@PreDestroy** (in xml config its **init-method="doMyStartupStuff()"**)

> For @PostConstruct , the mehod will execute **before** bean is destroyed
>
> ```java
> @Component
> public class TennisCoach implements Coach {
> 	
> 	@PreDestroy
> 	public void doMyStartupStuff() {
> 		...
> 	}
> }
> ```
>
> ****

