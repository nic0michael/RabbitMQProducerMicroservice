# Our Microservice Design Pattern
This design pattern has matured over four years of its use to write Microservices   

Here focus given to TDD (Test Driven Development) and BDD (Behaviour Driven Development).

[To access our POC documentation use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/POC.md).   
[To access  Recommendations for Microservices use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice).   

## The Metamorphosis of this design pattern for building better Microservices
### 2018 
Perceive Chuchu and Nico Michael were working in the Best Practices Team at Wesbank writing Spring Boot Microservices.  
As many developers of Spring Boot Microservices we had tightly coupled or Controller classes from the Service classes.  
This introduces disadvantages to your code like, the effort needed for testing, and time to maintain the code. 

**Decoupling the Controller Classes from the Service Classes**   
Our Practice Lead (Open Source Technologies, Java & Cloud Solutions) at DVT Saurabh Agrawal recommended that we decouple the Controller Classes from the Service Classes.  This is in-line with the Microservice Codeing Best Practices.   
Saurabh named the class we would use for decoupling the **"Module class"**.   
 
**Using Nico's "Schizophrenic" Mock Classes to simplify Unit testing**       
The concept of using Stubs and Drivers cam from the Mainframe developers and from that we got the Mock class. However Nico wanted something more.     

Nico came up with a variation that he named the "Schizophrenic" Mock Class that we would use to Mock the behaviour of the Service Class. This class would have multiple personalities or in our case behaviours.     
He wanted, Unit Tests to use these classes, to simplify their code.  
Instead of using many lines of complicated, Mockito code, that would make the unit tests "Brittle", theses Mock classes would be used instead, as Nico had learned not to write Brittle Unit Tests.    
He also figured out that he could not only use these Mock Classes for TDD (Test Driven Development), but that he could also reuse the same Mock classes for BDD (Behaviour Driven Development).       
Thus simplifying all his tests.

**"Schizophrenic behaviour achieved by Overloading the Constructors of the Mock classes**.       
By making the Service classes Implement Interfaces, Nico could make his Mock classes implement the same Interfaces, thus making them pluggable into his unit tests.       
By overloading the Constructors and passing an ENUM as a parameter he could make the Mock Class behave like the Service class but with programmable behaviour.       

When the value of the ENUM was "GOOD" these Mock classes would return the expected responses from all their public methods.   
But when the ENUM value was "FAILING" they would return the expected failure response.       
When the value of the ENUM was "THROW_EXCEPTIONS" this would make all its public methods in the Mock class throw the expected exceptions.       

### Dec 2020       
**Renaming the Decoupling class or Module Class**      
John Daratos an ex Senior Architect from IBM advised Nico to rename the **Module Class** used for Decoupling the Controller Classes from the Service classes to be called the **ServiceManager class** as it better described its purpose.    

### 2021     
**Renaming the Decoupling class or ServiceManager class**      
Nico had figured out that if you move all the Business Logic to the decoupling class and adding the same public methods of the Controller classes that he would not need to test the Controller classes as all the data-changing logic and business logic would be in the **ServiceManager classes**.   
He realized that the Service Manager class was now processing the Business Logic and that its name was now not descriptive of its purpose so he renamed the ServiceManager class to the **BusinessProcessor class**. This is his first project using this name

**Renaming the service Classes and "Schizophrenic" Mock Classess**      
After Nico have viewed this video from Dave Farley of "Continuous Delivery" fame, ["When TDD is Difficult - Try This!"](https://www.youtube.com/watch?v=ESHn53myB88&list=WL&index=10&t=7s) , Nico liked Dave's use of adaptors he decided that the Service classes would implement interfaces named Adaptors.       

**For this project the interfaces would be named :**

  * DatabaseAdaptor 
  * MessageQueueAdaptor.       

**The service classes implementing these interfaces in this project would be named :**

  * DatabaseServiceAdaptorImpl
  * MessageQueueServiceAdaptorImpl.   

Now Nico's "Schitzophrenic" Mock Classes would implement the interfaces above and be mocking the above Service classes

   
   



## 1. Why do we want Unit testing
Cyberpunk 2077 Was Supposed to Be the Biggest Video Game of the Year.   
CD Projekt Red, the Polish studio behind the video game, announced the title in 2012.   
**[Bad Software Engineering KILLED Cyberpunk 2077???s Release](https://www.youtube.com/watch?v=E-jGEtqB4wU).**   

Unit testing facilitates Continuous Integration.   
At WesBank we spent 3 years writing more than 30 Microservices using an early version of this Design Pattern.   
We only had one production issue with one Microservice which was quickly fixed that was before we used this Design Pattern.    

The approach of this Design Pattern is to simplify Unit Testing doing TDD (Test Driven Development) and BDD (Behaviour Driven Development) 

## 2. The problem with writing Microservices that have tightly coupled classes 
**Many developers do not decouple their Controller Classes from the Service Classes this has a following disadvantages:**

  * They make Unit testing a challenge
  * The design becomes rigid not giving the developer flexibility in the future.
  * These Microservices become a challenge for rapid unit testing (Google achieves 3 million tests in a few minutes).
  * They do not facilitate the use of Mock Service classes for TDD and BDD testing. 

## 3. How this design pattern evolved to solve the above problems
In 2018 Saurabh Agrawal(DVT) had recommended that one should decouple the Controller Classes from the Service Classes.   
Later Nico Michael(DVT) introduced the use of Mock Classes for Unit Testing to facilitate TDD and BDD.   
Recently the Class that decouples the  Controller Classes from the Service Classes was given the name the "BusinessLogicProcessor" class.

## 4. Testing Strategy
**We only write unit tests where we need them.**   
For Classes with methods, that are data changing, or have other logic.

### 4.1 What not to Test
  * Fa??ades of just wrapping other frameworks or libraries or Classes (We make the Controller Classes Fa??ades in this Design Pattern).  
  * If we use only Pass-Through or wrapper methods in our Controller Classes then there is no logic to test so there is no need to test them.
  * POJOs , DTOs, Entity Classes, Custom Exceptions, and Controller Classes are not Unit tested tested.
  * Constructors or properties (if they just return variables). Test them only if they contain validations.
  * Configurations like constants, read only fields, configs, enumerations, etc.
  * Container service registrations
  * Exception messages
  * Private methods directly
  * Complex SQL Queries (more than 3 joins or grouping, etc.). Better to test it with manual or some kind of system test against real DB.
  * Complex multi-threading code (it is better to be tested with integration tests)
  * Methods that call another public method

### 4.2 Getting SonarQube to ignore Classes we don't want to test
This is done in the projects POM file (pom.xml) by adding the SonarQube Exclusions to folders or classes :

```
	<properties>
		. . . 
		. . .
		<sonar.exclusions>
			file:**/rabbitmq/poc/controllers/**,
			file:**/rabbitmq/poc/dtos/**,
			file:**/rabbitmq/poc/exceptions/**
		</sonar.exclusions>
	</properties>
```

### 4.3 What to Test
  * Utility methods
  * Core business logic methods
  * Collections passed as parameter not changed in the method
  * Algorithm Engines
  * Simple DB queries checking predicates
  * Services that are high-risk

## 5. This Design Pattern Decouples the Controller Classes from the Service Classes
It is not uncommon to find the Controller Classes tightly coupled to the Service Classes this makes unit testing a challenge.

### 5.1 We introduced a BusinessLogicProcessor Class to decouple the Controller Classes from the Service Classes
This decouples the Controller Classes from the Service Class.  

### 5.2 We decouple the BusinessLogicProcessor Class from the Service Class using an Interface
We also made the Service Classes implement Interfaces this decouples the BusinessLogicProcessor Class from the Service Class.   

### 5.3 We also decouple the Service Class from the Adaptor Classes using Interfaces
We also made the Adaptor Classes implement Interfaces that decouples them from the Service Class.   

### 5.4 Overloading Constructors of classes assists in unit testing
We add overloaded Constructors to the classes we want to test so that we can inject what was @Autowired fields and Objects as well as mock instances

![MicroserviceDesignPattern](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/MicroserviceDesignPattern.png)   
Fig 1 : The design Pattern Class Diagram.   

## 6. We use "Pass-through or Wrapper Methods" Making Fa??ades of the Controllers
We moved all the logic from the Controller classes to the Business Logic Processor Class so that we don't need to test them.   
In the Controller Classes all the methods that call the Business Logic Processor Class direct and do not "Modify Data".   
What they receive in their  parameters is passed directly to the methods of the Service Processor Class.   
We prefer to have the methods there with the same names as in the Controller Classes.   
As we do not have any logic and are not changing anything in the Controller Classes we don???t need to write unit tests for the Controller Classes.


## 7. Using Test Driven Development to find the code for this project
We Use the First Write a Test Methodology.   
Here we don't write the code and then do the unit tests. **This is only done when developers are forced to have high code coverage**.   
We would rather want **enough good unit tests**.   

[For more information on how we did the TDD it has been documented here](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/TDD.md)

## 8. We mock the Adaptor classes by implementing Mock Adaptor classes in the Test Folder for Unit testing
According to **Dave Farley's** video : [When TDD is Difficult - Try This!](https://www.youtube.com/watch?v=ESHn53myB88&t=2s&pp=sAQA)  
One should not be testing qaccross the network he recomended using Adaptors. Based on a similar idea we were using Mock classes 4 years ago to do unit testing.   
 

## 8. We mock the Adaptor classes by implementing Mock Adaptor classes in the Test Folder for Unit testing
By Mocking the Adaptor classes we can control their behaviour to give us  Positive and Negative Unit tests.   
We add overloaded Constructors to the classes we want to test so that we can inject @Autowired fields and Objects as well as instances of the mocked Service classs.   
One improvement we had in our design was the use of an Enum to be passed to the overridden constructors of the Mocked Adapror classes to make the mocked instance of the Adaptor Pass for fail a test or throw exceptions thus facilitating Negative tests

![MicroserviceDesignPatternTDDandBDD](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/MicroserviceDesignPatternTDDandBDD.png)   
Fig 2 : The Class diagram showing the Mock Classes used for Unit Testing.   

### 8.1 Positive Tests
For Positive tests all the public methods of the Mock Service Classes will return the Expected values .

### 8.2 Negative Tests
It is sad that many developers do not write negative tests to test how their code works in adverse conditions expecially when Exceptions are deliberatly thrown to test how the code survives in extreem conditions.    
For Negative tests all the public methods of the Mock Service Classes will return values representing failure  
  
Now we can see that our code behaves with failures.

### 8.3 Destructive Negative Tests
  
For Destructive Negative Tests we make all the methods in the Mock Classes throw the expected Exceptions for serious  failure.

This way we can test how our code handles crashing conditions.

### 8.4 Giving our Mock Classes multiple behaviours (Schizophrenic like Classes)
In Order to give our Mock Classes multiple behaviours this is done by :

  * Making the default constructor private.  
  * Providing an overloaded Constructor that receives an Enum (called TestType).
  
```
    public enum TestType {
	   PASSING_TEST,FAILING_TEST,THROWS_EXCEPTION;
    }

```
 
We write all our Mock Class methods in such a way that they have three behaviours 

This is based on how these classes are instantiated.


## 9. The benefits of using Mock Service Implementation classes
  * We write unit tests that are not fragile 
  * Using Mockito produces brittle unit tests as any changes in the Requests and responses will require debugging to fix broken unit tests
  * Any changes in the Requests and responses will be easy to change in the Mock Service methods
  * We are able to do TDD and BDD tests with the infrastructure down this way Jenkins can still build and deploy   
  * This is achieved using Mock Classes and not making network calls to resources.   

  One of the companies I worked at would have Jenkins build failures in one of the services was down.   
**We never had that where we used this technique of using Mock Classes.**
  
## 10. We can now reuse the same Mock classes for simplifying BDD Testing

### 10.1 Positive Test
```
    Scenario    : Trying to send a valid Transaction successfully to the Message Queue for processing

    Given       : A valid transaction message is sent which is successfully sent to the Message Queue
    Then        : A response is received with a Success Message
    And         : The response has a corresponding Success code
```

### 10.2 Negative Test
```
    Scenario    : Trying to send a invalid Transaction to the Message Queue for processing

    Given       : An invalid transaction message is sent
    Then        : A response is received with an Error Message
    And         : The response has a corresponding Error code
```

### 10.3 Destructive Negative Test
```
    Scenario    : Trying to send a valid Transaction unsuccessfully to the Message Queue for processing

    Given       : A valid transaction message is sent but it is not successfully sent to the Message Queue
    Then        : A response is received with an Error Message
    And         : The response has a corresponding Error code
```

**All these Scenarios can be tested with the same Mock classes used for UNIT Testing**
