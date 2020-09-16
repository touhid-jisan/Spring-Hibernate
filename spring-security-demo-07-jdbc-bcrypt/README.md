# Spring Security - Password Encryption 

Password Storage - Best Practice - The beast practice is store password in an encrypted format 



**Spring Security Team Recommendation:** 

- Spring Security recommends using the popular bcrypt algorithm
- bcrypt:
  - Performs one-way encrypted hashing
  - Adds a random salt to the password for additional protection
  - Includes support to defeat brute froce attacks



**How to Get a Bcrypt Password:**

- **Option 1:** Use a website utility to perform encryption
- **Option 2:** Write Java code to perform the encryption



**Development Process:**

- **Step 1: **Run SQL Script that contains encrypted passwords
  - Modify DDL for passwords field, length should be 6
- **Step 2:** Modify database properties file to point to new database schema 



**Spring Security Password Storage:**

- In Spring Security 5, passwords are stored using a specific format

- {id}encodedPassword

  ![](https://githubpictures.000webhostapp.com/pictures/bcrypt.png)



**Modify DDL for Password Filed**

![](https://githubpictures.000webhostapp.com/pictures/ddl.png)





**Step 1: Run SQL Script that contains encrypted passwords**

![](https://githubpictures.000webhostapp.com/pictures/bcrypt-database.png)

> sql queries
>
> ```mysql
> DROP DATABASE  IF EXISTS `spring_security_demo_bcrypt`;
> 
> CREATE DATABASE  IF NOT EXISTS `spring_security_demo_bcrypt`;
> USE `spring_security_demo_bcrypt`;
> 
> --
> -- Table structure for table `users`
> --
> 
> DROP TABLE IF EXISTS `users`;
> CREATE TABLE `users` (
>   `username` varchar(50) NOT NULL,
>   `password` char(68) NOT NULL,
>   `enabled` tinyint(1) NOT NULL,
>   PRIMARY KEY (`username`)
> ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
> 
> --
> -- Dumping data for table `users`
> --
> -- NOTE: The passwords are encrypted using BCrypt
> --
> -- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
> --
> -- Default passwords here are: fun123
> --
> 
> INSERT INTO `users` 
> VALUES 
> ('john','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
> ('mary','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
> ('susan','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1);
> 
> 
> --
> -- Table structure for table `authorities`
> --
> 
> DROP TABLE IF EXISTS `authorities`;
> CREATE TABLE `authorities` (
>   `username` varchar(50) NOT NULL,
>   `authority` varchar(50) NOT NULL,
>   UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
>   CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
> ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
> 
> --
> -- Dumping data for table `authorities`
> --
> 
> INSERT INTO `authorities` 
> VALUES 
> ('john','ROLE_EMPLOYEE'),
> ('mary','ROLE_EMPLOYEE'),
> ('mary','ROLE_MANAGER'),
> ('susan','ROLE_EMPLOYEE'),
> ('susan','ROLE_ADMIN');
> 
> 
> ```
>
> ****



**Step 2:** **Modify database properties file to point to new database schema** 

![](https://githubpictures.000webhostapp.com/pictures/bcrypt-properties.png)





**Spring Security Login Process:**

![](https://githubpictures.000webhostapp.com/pictures/bcrypt-login-process.png)