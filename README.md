spring-data
==========================

This project demonstrates Spring Data, Spring Web MVC, Spring Security and Thymeleaf. It accompanies blog posts at
http://akorsa.ru which are listed below in their accompanying implementation version.

##Implemented##

*Implementations listed below by version are found in their corresponding branch, v0.0.1, v0.0.2, etc. Recent branches may not
contain features found in prior versions.*

##v0.2.1 -- Spring MVC and JavaScript Samplings##

- Bootstrap Dialog Box with requirement to be logged-in to view
- JQuery Bootstrap Multiselect Plugin with Spring MVC JSON-populated Options and postback
- SpringLoaded to Hot Reload Java Class updates w/o app restart

##v0.2.0 -- Spring Data JPA Auditing##
- Spring data JPA Auditing

##v0.1.9 -- More Child Object Handling in MVC and Thymeleaf

- Hobbies added to Contact Update Form w/ Custom Data Validation
- Demo of Spring Boot CommandLineRunner
- Contact Form tests added
- [Post: Using Spring Boot CommandLineRunner](http://nixmash.com/java/using-spring-boot-commandlinerunner/)
- [Post: Testing Custom Exception Handling Classes in Spring MVC](http://nixmash.com/java/testing-custom-exception-handling-classes-in-spring-mvc/)
- [Post: Handling Null Radio Button Data in Thymeleaf and Spring MVC](http://nixmash.com/java/handling-null-radio-button-data-in-thymeleaf-and-spring-mvc/)

![Radio Button Child Hobby Objects in v0.1.9](http://nixmash.com/x/pics/github/spring-data-0.1.9.png)


##v0.1.8 -- Child Object Processing, MVC Method Level Security##

- Add, Update, Remove Contact Phones added to Contact Update form
- User Profile Page with @PreAuthorize to enforce owner-only plus administrator profile view
- [Post: Spring MVC Method Security with @PreAuthorize and Sp-EL](http://nixmash.com/java/spring-mvc-method-security-with-preauthorize-and-sp-el/)
- [Post: Consistent Fonts with Bootstrap Glyphicon and Thymeleaf](http://nixmash.com/java/consistent-fonts-with-bootstrap-glyphicon-and-thymeleaf/)

##v0.1.7 -- Full MySQL Support, WAR Deployment##

- MySQL Spring Security Support (mysql.setup file now located in /install folder)
- Deployed as a WAR at http://nixmashspring.daveburkevt.com
- @ControllerAdvice firing issue with AddViewControllers() fix
- User Login and Registration Tests
- [Post: Why Your @ControllerAdvice May Not Be Firing](http://nixmash.com/java/why-your-controlleradvice-may-not-be-firing/)
- [Post: Deploying Your Spring Boot WAR Application](http://nixmash.com/java/deploying-your-spring-boot-war-application/)
- [Post: Adding MySQL Spring Security to Existing H2 App](http://nixmash.com/java/adding-mysql-spring-security-to-existing-h2-app/)

##v0.1.6 -- Initial Release w/ Spring Security##

- Spring Security with Login and User Registration. Supports Multiple Role Assignment with USERS, USER_AUTHORITIES and AUTHORITIES table storage
- H2 Console support at http://site/console. USER Role Access Denied to Console
- User must be authenticated to view Contact Details and Search pages
- External Properties for configuring options based on site public status (is demo site) _**To Configure Location of Properties File:** Set in Jpa/ApplicationSettings.class annotation_
- Create Contact Update/Create Submit restricted to ADMIN Role
- Tests for New User Registration and Form Validation
- _**Note:** v0.1.6 supports H2Database only. MySql support to be added._
- [Post: Using the H2 Console in Spring and IntelliJ](http://nixmash.com/java/using-the-h2-console-in-spring-and-intellij/)
- [Post: Beware Your Spring Security Principal’s Authorities Format](http://nixmash.com/java/beware-your-spring-security-principals-granted-authorities-list-format/)
- [Post: Spring Security in NixMash Spring App: The Parts You Can See](http://nixmash.com/java/spring-security-in-nixmash-spring-app-the-parts-you-can-see/)
- [Post: Welcome, User! in Thymeleaf and Spring](http://nixmash.com/java/welcome-user-in-thymeleaf-and-spring/)
- [Post: Enabling Submit on User Role in Thymeleaf and Spring](http://nixmash.com/java/enabling-submit-on-user-role-in-thymeleaf-and-spring/)
- [Post: The Old InstanceAlreadyExistsException JUnit Trick](http://nixmash.com/java/the-old-instancealreadyexistsexception-junit-trick/)
- [Post: External Properties File for Spring Web Site Configuration](http://nixmash.com/java/external-properties-file-for-spring-web-site-configuration/)
- [Post: Saving Multiple Authorities to Database on New User in Spring](http://nixmash.com/java/saving-multiple-authorities-to-database-on-new-user-in-spring/)
- [Post: User Registration Validation in Spring](http://nixmash.com/java/user-registration-validation-in-spring/)

![Registration form validation in v0.1.6](http://nixmash.com/x/pics/github/spring-data-0.1.6.png)

##v0.1.5 -- Contact Add/Update Form, Dandelion Asset Bundling##

- Multi-use Contact Add/Update Form
- Fadeout Feedback Messages on Contact Add/Update with jQuery and Handlebars
- Using Dandelion with Asset Bundling to load jQuery-UI datePicker and theme
- Custom Favicon
- Custom Exception Handling and all supporting Tests
- [Post: Fadeout Feedback Messages in Spring MVC](http://nixmash.com/java/fadeout-feedback-messages-in-spring-mvc/)
- [Post: Java Dates, Dandelion, Thymeleaf, Hibernate and Spring](http://nixmash.com/java/java-dates-dandelion-thymeleaf-hibernate-and-spring/)
- [Post: Using Dandelion with Spring Boot and Thymeleaf](http://nixmash.com/java/using-dandelion-with-spring-boot-and-thymeleaf/)
- [Post: Testing for 404 and Custom Exceptions in Spring MVC](http://nixmash.com/java/testing-for-404-and-custom-exceptions-in-spring-mvc/)
- [Post: Custom 404 Exception Handling in Spring MVC](http://nixmash.com/java/custom-404-exception-handling-in-spring-mvc/)
- [Post: Including Webjars in IntelliJ Tomcat WAR Artifact](http://nixmash.com/java/including-webjars-in-intellij-tomcat-war-artifact/)
- [Post: Using Your Favorite Favicon in Spring MVC](http://nixmash.com/java/using-for-favorite-favicon-in-spring-mvc/)

##v0.1.4 -- Contact Display, List and Search##

- jQuery, Bootstrap and Bootswatch Webjars
- Using Bootswatch Webjar with Spring
- Contact Display, List, and Search Form with all supporting Tests
- [Post: Adding Bootswatch Webjar in Spring and Thymeleaf](http://nixmash.com/java/adding-bootswatch-webjar-in-spring-and-thymeleaf/)
- [Post: Bootstrap Navbar Highlighting in Thymeleaf](http://nixmash.com/java/bootstrap-navbar-highlighting-in-thymeleaf/)
- [Post: Testing a Spring MVC Search Form](http://nixmash.com/java/testing-a-spring-mvc-search-form/)

![Bootstrap with All Contacts Display in Spring-Data MVC with Thymeleaf v0.1.4](http://nixmash.com/x/pics/github/spring-data-0.1.4.png)

##v0.1.3 -- Initial release w/ Thymeleaf##

- Thymeleaf support added
- [Post: Thymeleaf Configuration with Spring Boot](http://nixmash.com/java/thymeleaf-configuration-with-spring-boot/)

![Spring-Data MVC with Thymeleaf in v0.1.3](http://nixmash.com/x/pics/github/spring-data-0.1.3.png)

##v0.1.2 -- Multi-Module with JPA and MVC Modules##

- MVC Module Added
- JPA and MVC Modules wired as single Spring Application
- Referencing Test Source from JPA module in MVC module tests
- Initial HomeController and ContactController classes
- Pretty Formatted JSON output in Web Browser
- [Post: Deployable WARs in Spring Boot, IntelliJ and Gradle](http://nixmash.com/java/deployable-wars-in-spring-boot-intellij-and-gradle/)
- [Post: An IntelliJ Multi Module Spring Boot MVC Configuration](http://nixmash.com/java/an-intellij-multi-module-spring-boot-mvc-configuration/)
- [Post: Referencing Test Classes in Another IntelliJ Module](http://nixmash.com/java/referencing-test-classes-in-another-intellij-module/)
- [Post: Pretty Formatted JSON in Spring Web MVC](http://nixmash.com/java/pretty-formatted-json-in-spring-web-mvc/)

![Spring-Data Does MVC in v0.1.2](http://nixmash.com/x/pics/github/spring-data-0.1.2.png)

##v0.1.1 -- @ManyToMany Hobbies added to Contact Model##

- Addition of Hobbies, a @ManyToMany Entity with Contacts
- Adding Hobbies dynamically on new and updated Contacts, and as separate process
- Adding and removing Hobbies from Contact
- ContactDTO with Set<HobbyDTO>
- Tests covering above Hobby processes
- [Post: Working with the Many in a JPA @ManyToMany](http://nixmash.com/java/working-with-the-many-in-a-jpa-manytomany/)


##v0.1.0 -- @OneToMany Contact Phones added to Contact Model##

- Examples of Updating, Deleting and Adding records with focus on @OneToMany relationship handling
- ContactDTO now containing Contact and ContactPhone Set
- Builder pattern added to ContactPhone Entity class
- Tests covering Contact updates, deletions and additions, on both contact and multiple contact phones
- [Post: Flexible H2 Database Persistence For Testing With Gradle](http://nixmash.com/java/flexible-h2-database-persistence-for-testing-with-gradle/)
- [Post: Adding a JPA Entity and its @OneToMany Children](http://nixmash.com/java/adding-a-jpa-entity-and-its-onetomany-children/)
- [Post: Updating the Many in a JPA @OneToMany](http://nixmash.com/java/updating-the-many-in-a-jpa-onetomany/)

##v0.0.9 -- Lazy Loading examples##

- Several examples of Lazy Loading. See NixMash post [Approaches to JPA Lazy Loading](http://nixmash.com/java/approaches-to-jpa-lazy-loading/) for details
- First pass at creating schema DTO objects for use in updating and creating data
- Modified database schema to better adhere to database relationship best practices. Updates are reflected in Entities.

##v0.0.8 -- Multi-database configuration w/ H2Console and MySQL##

- h2database and mySQL JPA Data Configuration
- Profiles based on Enumerator value (ex: JpaDataConfig.MYSQL)

##v0.0.7 -- Multi-profile configuration##

- ApplicationContext and Data Retrieval Tests
- "Production" and "Dev" Spring Profiles
- Re-architected main() to load context, and a single bean to startup application
- Broke out Production and Dev Profile Configurations, extend base SpringConfiguration class. Plan to redesign further
- Spring Profile set in Gradle.build bootRun task

##v0.0.6 -- GUI Generated Entities and Diagram Examples##

- Generated supplemental Contact, ContactTelDetail and Hobby Entities using the IntelliJ Persistence View GUI tools. (Contain "Entity" classname suffix.)
- Output based on new Entities, shown below
- Updated to _Spring 4.1.6, Spring-Boot 1.2.3_

![Image of Spring Data JPA Output v0.0.6](http://nixmash.com/x/pics/github/spring-data-0.0.6.png)


##v0.0.5 -- Hibernate and JPA Module structure##

- Broke-up the application into two separate IntelliJ Modules
- Root and child Gradle project configuration

##v0.0.4 -- Initial Release with JPA##

- Spring Data JPA Implementation
- Dual SpringJPAConfiguration and SpringHbnConfiguration classes
- Simultaneous use of both Spring Data JPA and Hibernate with Transaction Isolation
- Updated MySQL Schema with additional data
- Shared Model Entities between Hibernate and Spring Data JPA
- _Spring 4.0.6, Spring-Boot 1.1.4_

![Image of Spring Data JPA Output v0.0.4](http://nixmash.com/x/pics/github/spring-data-0.0.4.png)


##v0.0.3 -- DAO Implementation##

- Generic DAO Interface Implementation
- Added Service Facade
- Persistence Tier broken out to Domain Model, Data Access Object and Service Layers
- Data and Output identical to v0.0.1
- _Spring 4.0.6, Spring-Boot 1.1.4_

##v0.0.2  -- Using Spring Properties##

- Populating DataSource connection from Property File
- Using both @Value and @Autowired Environment properties
- Annotations with @ImportResource of app-context.xml file
- Custom SpringPropertiesUtil Class to retrieve overriden "username" System Property
- Populating @Autowired PropertyClass class properties in @Configuration class and retrieving the properties in another class
- _Spring 4.0.6, Spring-Boot 1.1.4_

![Image of Output v0.0.2](http://nixmash.com/x/pics/github/spring-hibernate-properties.png)

##v0.0.1 -- Base Configuration in Spring and Hibernate##

- Changed XML Spring Configuration to Annotations
- Changed datasource from embedded H2 to MySql
- Lazy Fetching
- Named Queries
- Logging Configuration
- Gradle Build Configuration
- Inserting data
- _**Note:** v0.0.1 was created with Spring Boot v1.1.4. Using v1.2.x generates a compile error regarding JPA Entity handling._

[Image of Output v0.0.1](http://nixmash.com/x/pics/github/spring-hibernate0411.png)

##Installation##

The application supports an H2 Profile (default) and a MySQL Profile. To run JPA Console app use **$gradle jpa:bootRun.** To run
MVC Web app use **$gradle mvc:bootRun.** The Tomcat Server Port is set for **8084** (set in the MVC _application.properties_ file)
so go to **http://localhost:8084** to view the app in your browser.

##Database Configuration##

To use MySQL run _setup.mysql_ script in the /doc/install directory to populate the database. Update Datasource connection
properties in _/resources/META-INF/spring/mysql.properties_ file. The H2 create-data script for the tests is located in
_/resources/db._

##References##

 **Version 0.0.1** of the app was based on Chapter #7 of Pro Spring, Fourth Edition from Apress, "Using Hibernate."
 (An excellent book, btw.) Here is [the book's listing on Apress](http://goo.gl/q2w50H). For Safari users, here is the
 online version of it [on Safari](http://goo.gl/TD6nuO).

 The Generic DAO structure in **Version 0.0.3** was based on Chapter #2 of Spring in Practice by Willie Wheeler and
 Joshua White, "Data persistence, ORM, and transactions." Here is the [Chapter on Safari](http://goo.gl/Q9uoTl).

 Multiple database JPA Configuration implemented in **Version 0.0.8** based largely on the work of Gordon Dickens' [Spring-Data-
 Demos "Profiles" project](https://goo.gl/IuaWoR).

 Several components related to Thymeleaf and Security in **Version 0.1.x** from Arnaldo Piccnelli's [Enhanced Pet Clinic.]
 (https://github.com/arnaldop/enhanced-pet-clinic)

 Petri Kainulainen’s excellent book [Spring Data](https://www.packtpub.com/application-development/spring-data) from Packt
 Publishing and accompanying source code was a reference for Model Attribute handling and feedback messaging in **Version 0.1.5**
 as well as other features.

 Two excellent source references for Spring Security which initially appeared in **Version 0.1.6** were Rob Winch's [gs-spring-
 security-3.2](https://github.com/rwinch/gs-spring-security-3.2) something and Bartosz Kielczewski's [example-spring-boot-security]
 (https://github.com/bkielczewski/example-spring-boot-security).