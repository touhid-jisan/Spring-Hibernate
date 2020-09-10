# Theory

## <u>Iversion of Control (IoC)</u>

- The approach of outsourcing the construction and management of objects.

- Outsource will be handled by **Object Factory**

  

  ![](https://githubpictures.000webhostapp.com/pictures/IoC1.png)

  

#### Solution:

<img src="https://githubpictures.000webhostapp.com/pictures/ObjectFactory_IoC.png" style="zoom:50%;" />

> Spring Provides an ObjectFactory. We can have our application talk to Spring "Hey!!! Give me an object based on a Configuration File or Annotation". Then Spring will give me appropriate implementations  





****

****



## <u>Dependency Injection:</u>



<img src="https://githubpictures.000webhostapp.com/pictures/DI.png" style="zoom:50%;" />



> When writing a complex Java application, application classes should be as independent as possible of other Java classes to increase the possibility to reuse these classes and to test them independently of other classes while unit testing. Dependency Injection (or sometime called wiring) helps in gluing these classes together and at the same time keeping them independent.



#### **Injection Types (Most Common) :**

- Constructor Injection
- Setter Injection



****

****



## <u>Bean Scopes:</u>

- Scopes refers to lifecycle of a bean.
- How long does the bean live?
- How many instrance are Created?
- How is bean Shared



**What is Singleton?**

- Spr  ing Container create only one instance of the bean by default.
- It is cached memory.
- All request for the bean 
  - Will return SHARED Reference to the same bean





![(README.assets/singleton.png)](https://githubpictures.000webhostapp.com/pictures/singleton.png)

- Points to the same object .
- Memory location for "**theCoach**" & "**alphaCoach**" is same.

**Prototype:**

- Creates new reference 

![](https://githubpictures.000webhostapp.com/pictures/prototype.png)

- thaCoach and alphaCoach points to the different object.
- Memory location for "theCoach" and "alphaCoach" is differenct.



 **Additional Spring Bean Scopes:**

| Scope          | Description                                                 |
| -------------- | ----------------------------------------------------------- |
| singleton      | Create a single shared instance of the bean. Default scope. |
| prototype      | Creates a new bean instance for each container request.     |
| request        | Scoped to an HTTP web request. Only used for web apps.      |
| session        | Scoped to an HTTP web session. Only used for web apps.      |
| global-session | Scoped to a global HTTP web session. Only used for web apps |

 ![](https://githubpictures.000webhostapp.com/pictures/beanlifecycle.png)





****

****



## <u>Spring Container</u>

#### Primary Functions:

- Create and manage objects (Inversion of Control)
- Inject Objects dependencies (Dependency Injection)

#### Configuring Spring Container: 

1. [XML Configuration file (legacy, but most legacy app still use it)](https://github.com/touhid-jisan/Spring-Hibernate/blob/master/Practice/XML%20Configuration%20Spring%20Container.md)
2. [Java Annotations (modern)](https://github.com/touhid-jisan/Spring-Hibernate/blob/master/Practice/Java%20Annotations%20(modern).md)
3. Java Source Code (modern)

