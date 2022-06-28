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
2. The entire Spring Framework are developed on top of the Spring Core
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
   4. How to Create Spring Beans?
       1. with simply using "new A()" SpringContext/SpringIoCContainer will not have any clue of the object
       2. but, **@Bean** lets Spring know that Spring needs to call the annotated method when it initializes its context
          and adds the returned object/value to the SpringContext/SpringIoCContainer
       3. method name will be the Bean name inside the springContext, so it is highly recommended to be a **Noun**.
<details><summary>code Example</summary>

```java
      @Bean
      Vehicle vehicle1(){
           var veh = new Vehicle();
           veh.setName("Soo's SuperCar");
           return veh;
      }
      @Bean
      Vehicle vehicle2(){
            var veh = new Vehicle();
            veh.setName("New CAR2");
            return veh;
          }
      @Bean
      String hello(){
           return "hello";
      }
      @Bean
      int a(){
          return 12;
      }
      var context = new AnnotationConfigApplicationContext(ProjectConfig.class); //act as a IoCContainer

      Vehicle vehicle1 = context.getBean("vehicle1", Vehicle.class);
      String hello = context.getBean(String.class);
      Integer num = context.getBean(Integer.class);
      System.out.println(String.format("the result would be vehicle1.getName()[%s], hello[%s], num[%d]", vehicle1.getName(), hello, num));
       //the result would be vehicle1.getName()[sexyVEH], hello[hello], num[12]   
```
</details>

   5. @Configuration, @Bean
       1. @Configuration: let framework knows that the class has Bean definitions and processes this class while starting the application.
       2. @Bean: Whatever object returned by method annotated with this, will become a bean and maintained by the SpringIoCContainer
          By default, the bean name is an annotated method name. so method name is recommended to be Noun.
          However, you can put name in the value parameter of @Bean (eg. @Bean("name"), @Bean(name="name"), @Bean(value="name"))
   6. NonUniqueBeanDefinitionException
      1. While we create multiple objects of same data type and try to fetch the bean from context by type, then Spring cannot specify which instance is referred.
   7. @Primary
      1. When you have multiple beans of the same kind inside the Spring context, you can make one of them primary by using @Primary. 
      2. Primary bean is the default bean that Spring Context will consider in case of confusion due to multiple beans present of same type.
      3. multiple primary bean will return "NoUniqueBeanDefinitionException"
   8. @Component, @ComponentScan(basePackages = "com.example.abc") 
      1. @Component: stereotype annotation, put on top of the POJO java class to add it to the Spring context.
      2. @ComponentScan: it instructs Spring on where to find the classes you marked with stereotype annotations.
      * 차이점. @Bean으로 생성하면 Context에 빈생성할 때 뭔가 조작을 할 수 있지만 ComponentScan은 생성자 등으로 세팅하지 않는 이상 기본값(객체는 null 등)으로 초기화된다.
   9. Stereotype Annotations
      1. help to create the Spring beans automatically in the application context.
      2. @Component: is used as general on top of any Java class. base for other annotations
      3. @Service: service layer where we write business logic and make external API calls
      4. @Repository: handles the code related to DB access operations like Insert, Delete, Update
      5. @Controller: controller layer of MVC applications.
         * 
### Context
   1. like a memory location of app where we add all the object instances that we want the framework to manage.
   2. To enable Spring to see your objects(beans), you need to add them to the CONTEXT
### SpEL(Spring Expression Language)
   1. provides a powerful expression language for querying and manipulating an object at runtime
   2. (like getting/setting property values, property assignment, method invocation.)
### SpringIoCContainer
   1. All lifecycle of SpringBeans is maintained by this. (instantiate the application class, Configuring object, assemble the dependencies btw objects)
   2. 2Types
      1. org.springframework.beans.factory.BeanFactory(I/F) - basic(creating, autowire, configure beans)
      2. org.springframework.beans.ApplicationContext(I/F) - more advanced IoCContainer than BeanFactory

# Maven
1. helps projects to build, download dependencies
2. Maven Repository
3. .m2 : created by Maven, try to maintain all the libraries in here. 
4. defines dependencies on pom.xml