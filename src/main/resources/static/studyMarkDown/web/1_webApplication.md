#How Srping Web application works?
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
   
3. fdas