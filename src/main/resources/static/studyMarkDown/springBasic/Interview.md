# Q1. What is Spring boot? and What problem does Spring Boot Solves?
extension of SpringFramework

# Q2. Explain a few important Spring Boot Key features?
1. SpringBootStarters
2. autoconfiguration
3. externalized configuration
4. spring boot actuator
5. embedded servlet container support

# Q3. What is Spring Boot Auto Configuration?
1. it automatically configures SpringApplication based on the jar dependencies that you have added in classpath.
2. For example
   1. To use SpringMVC, we need to configure Dispatcher servlet, ComponentScan, viewResolver
   2. To use Hibernate, we have to configure datasource, session factory, transaction manager **as beans**
3. However, with Spring boot, it auto-configure all those beans 

# Q4. How Spring boot internally works or Explain the run() method in Spring boot?
1. SpringbootApplication Execution process, **SpringApplication.run(AA.class, args)** is called through main method.
2. run() is responsible to execute autoConfiguration classes.
   * BootStrapping : load all necessary components for booting
3. Steps
   1) Starts StopWatch to identify time taken to bootstrap the spring boot application.
   2) Prepare environment to run spring boot application (dev, prod, qa, uat)
   3) print banner
   4) Start IoCContainer(ApplicationContext) based on classpath (default, Webservlet/ Reactive)
   5) Refresh ApplicationContext
   6) Trigger Runners (ApplicationRunner or CommandLineRunner)
   7) Return "ApplicationContext(IoC Container)" object
   
# Q5. What are different ways to create a Spring boot application?
1. Spring initializr
2. Spring Starter Project in STS(Eclipse)
3. Spring Boot CLI

# Q6. Explain @SpringBootApplication,@Configuration, and @ComponentScan annotations
1. @SpringBootApplication
   1. @SpringBootConfiguration: 
      1. @Configuration: Java-based configuration(not xml, annotation based config)
   2. @EnableAutoConfiguration:
      1. ex) DispatcherServlet, ViewResolver ...
   3. @ComponentScan
      1. Scan SpringComponent in a certain package(in this case, base package) and its subpackages
      2. Any class annotated with @Component

# Q7. What is Spring boot starters and name a few important Spring boot starter dependencies?
1. Without it, we have to add every dependency we need for web application and match each other's compatible version.
2. List
   1. spring-web
   2. spring-webmvc
   3. internal tomcat
   4. jackson dependency
* spring-boot-start-  (data-jpa, web, test, security... more than 30s)

# Q8. What is Spring Boot Starter Parent?
  1. It provides default configuration which are, for example, java version1.8, Maven Plugin, default version of dependencies
  2. You can override it by mentioning it below SpringBootStarter parent
  3. Its version is automatically set for other SpringBootStarter dependency

# Q9. How many types of projects we can create using Spring boot?
There are 3 types of project based on 3 of each dependency below.
1. Spring-boot-starter
 develop utilities, stand along projects, desktop based projects.
2. Spring-boot-starter-web
 spring MVC web application, RestAPI Web application
3. Spring-boot-starter-webflux
 SpringBoot Reactive Web Applications

# Q10. Explain types of Embedded Servers in Spring boot? How to change the default server port?
1. Default Embedded web server: Tomcat, with this, we can only install java and deploy jar file.
2. 3 Types: Tomcat, jetty, Undertow, 
Ex) Using Jetty instead of embedded Tomcat
```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
```
# Q11. How to run Spring boot application from the command line?
1. Using 'java -jar' command
2. Using SpringBoot Maven plugin '-mvn spring-boot:run'

# Q12. How to Add Security to Spring Boot and Explain Security Auto Configuration?
1. Add Dependency
2. Logging.level.org.springframework.security=DEBUG 
3. ...

# Q15. Explain profiles in your Spring Boot project
1. spring.profiles.active = dev;