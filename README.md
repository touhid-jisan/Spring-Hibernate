# Theory

### Iversion of Control (IoC)

- The approach of outsourcing the construction and management of objects.

- Outsource will be handled by **Object Factory**

  

  ![](README.assets/IoC1.png)

  

#### Solution

<img src="README.assets/ObjectFactory_IoC.png" alt="Object factory" style="zoom:50%;" />

> Spring Provides an ObjectFactory. We can have our application talk to Spring "Hey!!! Give me an object based on a Configuration File or Annotation". Then Spring will give me appropriate implementations  





****

****



### Dependency Injection:

<img src="README.assets/DI.png" style="zoom:50%;" />

> When writing a complex Java application, application classes should be as independent as possible of other Java classes to increase the possibility to reuse these classes and to test them independently of other classes while unit testing. Dependency Injection (or sometime called wiring) helps in gluing these classes together and at the same time keeping them independent.



#### **Injection Types (Most Common) :**

- Constructor Injection
- Setter Injection



****

****





## **Theory 3: Spring Container**

#### Primary Functions:

- Create and manage objects (Inversion of Control)
- Inject Objects dependencies (Dependency Injection)

#### Configuring Spring Container: 

1. [XML Configuration file (legacy, but most legacy app still use it)](https://github.com/touhid-jisan/Spring-Hibernate/blob/master/Practice/XML%20Configuration%20Spring%20Container.md)
2. Java Annotations (modern)
3. Java Source Code (modern)