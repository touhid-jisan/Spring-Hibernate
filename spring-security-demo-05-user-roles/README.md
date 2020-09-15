# Spring Security - Display User ID and Roles



**Spring Security :**

- Spring Security provides JSP custom tags for accessing user id and roles



**Development Process:**

- **Step 1:** Update POM file for Spring Security JSP Tag Library

- **Step 2:** Add Spring Security JSP Tag Library to JSP page

- **Step 3:** Display User ID

- **Step 4:** Display User Roles

  

  **Step 1: Update POM file for Spring Security JSP Tag Library**

  > **pom.xml**
  >
  > ```xml
  > <dependency>
  > 	<groupId>org.springframework.security</groupId>
  >     <artifactId>spring-security-taglibs</artifactId>
  >     <version>${springsecurity.version</version>
  > </dependency>
  > ```
  >
  > ****

  

  **Step 2: Add Spring Security JSP Tag Library to JSP page**

  > **in jsp**
  >
  > ```jsp
  > <%@ taglib prefix="security"
  >     		uri="http://www.springframework.org/security/tags" %>
  > ```
  >
  > ****

  

  **Step 3: Display User ID**

  > **home.jsp**
  >
  > ```jsp
  > <%@ taglib prefix="security" 
  > 			uri="http://www.springframework.org/security/tags" %>
  > ...
  > ....
  > USER : <security:authentication property="principal.username" />
  > ```
  >
  > ****

  

   **Step 4: Display User Roles**

  > **home.jsp**
  >
  > ```jsp
  > <%@ taglib prefix="security"
  > 			uri="http://www.springframework.org/security/tags" %>
  > ...
  > ...
  > Roles: <security:authentication property="principal.authorities" />
  > ```
  >
  > ****

  

# **The End **

