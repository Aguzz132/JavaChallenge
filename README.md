# JavaUniversityProject

This is a Spring Boot project that uses:

- Java 8
- MySql
- Eclipse IDE (https://www.eclipse.org/downloads/)
- Spring Boot v2.2.11
- Spring Security
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf

This is a simple project that recreates a University Management System.

Actions based on the role of the user:
  
  Admin User:
    1) Login.
    2) Add new courses.
    3) Add new professors.
  
  Student User:
    1) Register/Login
    2) Enroll/Unenrolled in courses.
    3) See in which courses are enrolled.

To import this project in Eclipse IDE: File/Import/Maven ---> Existing Maven Projects

Before running the program you need to create a database called "university" in port:3306.
(See the file application.properties if you wish to configure the database)

To run the program: 
  1) Right click in src/main/src/com.university/AlkemyJavaChallengeApplication.java
  2) Run as
  3) Spring Boot App

When you run the program for the first time it will create all the tables necessary and a Admin User with username: "admin" and password: "admin".

To use the program: Open a browser --> http://localhost:8080/
  
