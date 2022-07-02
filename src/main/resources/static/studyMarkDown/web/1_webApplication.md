#How Spring Web application works?
###1. Overview
1. The Web clients sends a request using protocols like HTTP to Web Application and the Web server where the web app is deployed receives the requests and process the response.
2. In Java web apps, *Servlet Container(Web Server)* takes care of translating the HTTP messages for Java code to understand. 
   * the commonly used ServletContainer is *Apache Tomcat*.
3. Servlet Container converts the HTTP messages into **ServletRequest** and hand over to Servlet Method as a parameter. Similarly, **ServletResponse** returns as an output to Servlet Container from Servlet.

###2. Role of Servlets inside web apps
1. Before Spring
    1. Before Spring, developer has to create a new servlet instance, configure it in the servlet container, 
       and assign it to a specific URL path
    2. When the client sends a request, Tomcat calls a method of the servlet associated with the path the client requested.
       The servlet gets the values on the request and builds the response that Tomcat sends back to the client.
    
2. After Spring
   1. Programmers don't have to create and configure servlet.
   2. After Spring, it defines a servlet called **Dispatcher Servlet** which maintain all the URL mapping inside a web application.
   3. The servlet Container calls the DispatcherServlet for any client request, allowing the servlet to manage the request and the response.
   4. This way Srping internally does all the magic without the need of defining the servlets inside a Web app

###3. SpringBoot
1. Before SpringBoot
    1. Configure a Maven/Gradle project with all the dependencies needed
    2. Understand how servlets work and configure the DispatcherServlet inside "web.xml"
    3. Package the app into a WAR file. Deploy it into a server

2.After SpringBoot
   1. Before: Developer need to configure a servlet container, establish link btw Tomcat and Dispatcher servlet, deploy into a server, define all dependencies.
   2. automatically configure components
   3. SpringBoot app embed a webserver so that we do not require an external application server.

###4. SpringBootFeatures
1. SpringBootStarters
   - relates dependencies used for a specific purpose as starter projects. (provides Embedded Tomcat as well)
   - example: spring-boot-starter-web

2. AutoConfiguration
   - set configuration for certain dependencies by default. 
   - SpringBoot follows the convention-over-configuration principles. (developer can override and customize it)
   - ex) if we add MySql dependencies > create connection object to DB(username, password, port 3306 etc)
   - How to Override default configuration? > application.properties
     - server.port=8081  (0 -> random port number which is not duplicated)
     - server.servlet.context-path=/sexySchool
     - debug=true
     - spring.thymeleaf.cache=false  (make thymeleaf do not cache the template, so developer do not have to restart server but only build it to see the updated template)
3. Actuator&DevTools
   - Actuator : Spring boot provides a pre-defined list of actuator endpoints. with this, we can monitor app health, metrics etc
   - DevTools : automatic detection of code changes, liveReload server to automatically refresh any HTML changes to browser without server restart
     - by maintaining 2 ClassLoaders, it can provide automatic restart, and liveReload.
     - automatic restart: only with build after certain changes on code, devtool also restart server automatically
     - live Reload: automatically reload the page
     - However, it will disable all the caching. so it is not appropriate for prod env.
     - Luckily devtool is smart enough not to be packaged in jar or war
### Inside Spring Boot Application
1. @SpringBootApplication
   1. @EnableAutoConfiguration : enable Spring Boot's auto-configuration mechanism
   2. @ComponentScan : enable @Component scan on the package where the application is located
   3. @SpringBootConfiguration : enable registration of extra beans in the context or the import of additional configuration classes. An alternative to Spring's standard @Configuration annotation.

# 2. FrontEnd
### ViewControllerRegistry
In order to direct mapping between the URL and the view name, we can use the ViewControllerRegistry. With this, there is
no need for any Controller btw the two.
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/courses").setViewName("courses");
        registry.addViewController("/abouts").setViewName("abouts");
    }
}
```

