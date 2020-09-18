# Spring Security - Restrict Access Based on Roles



![](https://githubpictures.000webhostapp.com/pictures/security-restrict-example.png)



**Development Process:**

- **Step 1:** Create supporting controller code and view pages

- **Step 2:** Update user roles

- **Step 3:** Restrict Access based on Roles

  

  Step 1:

  S

  **Step 2: Update our User Roles**

  ![](https://githubpictures.000webhostapp.com/pictures/securty-update-user-role.png)

  > **DemoSecurityConfig,java**
  >
  > ```java
  > @Override
  > protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  > 
  > 	// add our users for in memory authetication
  > 	UserBuilder users = Users.withDefaultPasswordEncoder();
  > 	
  > 	auth.inMemoryAuthentication()
  >         .withUser(users.username("john").password("1234").roles("Employee"))
  >         .withUser(users.username("touhid").password("5678").roles("Employee", "Manager"))
  >         .withUser(users.username("jisan").password("abcd").roles("Employee", "Admin"))
  > }
  > ```
  >
  > ****

  

  **Step 3: Restrict Access based on Roles**

  - Update Spring Security Java Configuration file ( .java)

  - General Syntax

    > For Single Role
    >
    > ```java
    > antMatchers(<< add path to match on>>).hasrRole(<< aurthorized role >>)
    > ```
    >
    > ****

    > For Multiple Role
    >
    > ```java
    > antMatchers(<< add path to match on >>).hasAnyRole(<< list of authorized roles >>)
    > ```
    >
    > ****

    

    ![](https://githubpictures.000webhostapp.com/pictures/security-restrict-path-1.png)

    

    ![](https://githubpictures.000webhostapp.com/pictures/security-restrict-path-2.png)

    

    ![](Spring Security - Restrict Access Based on Roles.assets/security-restrict-path-3.png)

    

    > a
    >
    > ```java
    > @Override
    > protected void configure(HttpSecurity http) throws Exception {
    > 	
    > 	http.authorizeRequests()
    >         .antMatcher("/").hasRole("EMPLOYEE")
    >         .antMatcher("/leaders/**").hasRole("MANAGER")
    >         .antMatcher("/systems/**").hasRole("ADMIN")
    >         .and()
    >         .formLogin()
    >         ....
    > }
    > ```
    >
    > ****



# THE END