updated in 2022.06.27

# Inversion Of Control
### Description
1. Design principle, independent of language, which does not actually create the objects 
   but describes the way in which object is being created.
2. IoC is the principle where the control flow of a program is inverted: instead of the programmer controlling the flow of a program,
   the framework or service takes control of the program flow.
3. Dependency Injection is the pattern through which IoC is achieved.
4. Through Dependency Injection, the responsibility of creating object is shifted from the application to the Spring IoC container.
   It reduces coupling between multiple objects as it is dynamically injected by the framework.
* Factory Pattern 
  - using parameter of getObj("") method to get different object, and create object for each parameter.
  - it returns factorypatter class which was implemented each specific object
  - drawback: still we have to change the parameters from each code where it is used.
  
### Advantages
1. Lose coupling btw the components > whenever the dependency changes, I don't have to change all the codes where
   the dependency is being used.
2. minimizes the amount of codes.
3. makes unit testing easy with different mocks. (if you didn't decide the dependency, you can make a temp dependency and adapt it in a test)
4. Increases system maintainability, module reusability.
5. replacing modules(dependencies) has no side effect to other modules.

# Spring Core
### Description
1. the heart of entire Spring. It contains some base framework classes, principles and mechanisms.
2. The entire Srping Framework are developed on top of the Spring Core
3. It contains following components
   1. IoC(Inversion of Control)
   2. Dependency Injection
   3. Beans
   4. Context
   5. SpEL(Spring Expression Language)
   6. SpringIoCContainer
### Spring Beans
   1. Nothing but any POJO(Plain old java object) class that is managed by Spring IoC Controller
   2. Beans are created with the configuration metadata in the form of XML configs and Annotations.
   3. SpringIoCContainer manages the lifecycle of SpringBeanScope, and injecting any required dependencies in the Bean.
### Context
   1. like a memory location of app where we add all the object instances that we want the framework to manage.
   2. To enable Spring to see your objects(beans), you need to add them to the CONTEXT
### SpEL(Spring Expression Language)
   1. provides a powerful expression language for querying and manipulating an object at runtime
   2. (like getting/setting property values, property assignment, method invocation.)
### SpringIoCContainer
   1. All lifecycle of SpringBeans is maintained by this. (creating Beans, Configuring object, assemble the dependencies btw objects)
   2. 2Types
      1. org.springframework.beans.factory.BeanFactory(I/F) - basic(creating, autowire, configure beans)
      2. org.springframework.beans.ApplicationContext(I/F)

# Maven
1. helps projects to build, download dependencies
2. Maven Repository
3. .m2 : created by Maven, try to maintain all the libraries in here. 
4. defines dependencies on pom.xml