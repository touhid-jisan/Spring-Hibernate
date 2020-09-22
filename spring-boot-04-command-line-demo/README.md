#### Running from the Command-Line

Spring Boot is self contained unit Nothing else to install

Jar file includes application code and includes the server

Running from command line **2 options**

- **Option 1:** Use **java -jar**

  ```
  $ ./mvnw package
  ```

  ```
  $ java -jar mycoolapp.jar
  ```

  **./mvnw package** create the jar file and 

  2nd command line **runs the project** 

- **Option 2:** Use Spring Boot maven plugin

  - **mvnw** allows us to run a Maven project

  - No need to have Maven installed or present on the path 

  - If correct version of Maven is NOT found on the computer Automatically downloads correct version and runs Maven

  - Two files are provided

    1. **mvnw.cmd** for Windows

       ```
       > mvnw clean compile test
       ```

    2. **mvnw.sh** for Linux / Mac

       ```
       $ mvnw spring-boot:run
       ```

       ```
       $ ./mvnw clean compile test
       ```

  

  

**Maven Wrapper files**

- If we already have Maven installed previously then we can ignore / delete the **mvnw** files

- Just use Maven 

  ```
  $ mvn clean compile test
  ```

  