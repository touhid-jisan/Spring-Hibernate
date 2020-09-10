# Spring Configuration with Java Source Code (no xml)



![](https://githubpictures.000webhostapp.com/pictures/3_ways_xml_configuration.png)



### Development Process:

- **Step 1:** Create a Java Class and Annotate as @Configuration

- **Step 2:** Add Component Scanning support: @ComponentScan **(optional)**

- **Step 3:** Read Spring Java Configuration Class

- **Step 4:** Retrieve bean from Spring Container 

  Step 1,2 

  > SportConfig.java
  >
  > This java file work as  <component-scan> in xml file
  >
  > ```java
  > @Configuration // step 1
  > @ComponentScan("com.touhid.springdemo") // step 2 : optional
  > public class SportConfig {
  > 	...
  > }
  > ```
  >
  > ****

  

  **Step 3:  Read Spring Java Configuration Class**

  > ```java
  > AnnotaionConfigApplicationContext context = new AnnotationContextApplicationContext(SprotConfig.class);		
  > ```
  >
  > ****

  **Step 4: Retrieve bean from Spring Container**

  > ```
  > Coach theCoach = context.getBean("tennisCoach")
  > ```
  >
  >  





**To be continued**...