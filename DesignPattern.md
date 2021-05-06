# Our Microservice Design Pattern
This design pattern has matured over four years of its use to write Microservices   

Here focus given to TDD (Test Driven Development) and BDD (Behaviour Driven Development).

## 1. Why do we want Unit testing
Cyberpunk 2077 Was Supposed to Be the Biggest Video Game of the Year.   
CD Projekt Red, the Polish studio behind the video game, announced the title in 2012.   
[Bad Software Engineering KILLED Cyberpunk 2077’s Release](https://www.youtube.com/watch?v=E-jGEtqB4wU).   

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
In 2017 Saurabh Agrawal(DVT) had recommended that one should decouple the Controller Classes from the Service Classes.   
Later Nico Michael(DVT) introduced the use of Mock Classes for Unit Testing to facilitate TDD and BDD.   
Recently the Class that decouples the  Controller Classes from the Service Classes was given the name "ServiceManager".

## 4. Testing Strategy
**We only write unit tests where we need them.**   
For Classes with methods, that are data changing, or have other logic.

### 4.1 What not to Test
  * Façades of just wrapping other frameworks or libraries or Classes (We make the Controller Classes Façades in this Design Pattern).  
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

### 5.1 We introduced a Service Manager Class to decouple the Controller Classes from the Service Classes
This decouples the Controller Classes from the Service Classes.   
We also made the Service Classes implement Interfaces this decouples the ServiceManager Class from the Service Classes.   
We add overloaded Constructors to the classes we want to test so that we can inject @Autowired fields and Objects as well as mock instances

![MicroserviceDesignPattern](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/MicroserviceDesignPattern.JPG)

## 6. We use "Pass-through or Wrapper Methods" Making Façades of the Controllers
In the Controller Classes all the methods that call the Service Manager Class do not "Modify Data".   
What they receive in their  parameters is passed directly to the methods of the Service Manager Class.   
We prefer to have the methods there with the same names as in the Controller Classes.   
As we do not have any logic and are not changing anything in the Controller Classes we don’t need to write unit tests for the Controller Classes.

## 7. We decouple the Service Manager Class from the Service Classes by making the Service Classes implement an Interface
These Service Interfaces are @Autowired to the Service Manager Class.


## 8. Using Test Driven Development to find the code for this project
We don't write the code and then do the unit tests. **This is only done when developers are forced to have high code coverage**.   
We would rather want **enough good unit tests**.   

The Purpose of the Service Manager Class is to be called by the Controllers and to call one or more Service Class.   
This class will Orchestrate the process **keeping the Service Classes small and simple**, each having one primary function.

In our design pattern we know that Micro Services have Controller Classes  and a few Service Classes.   
We also know that we are introducing a Service Manager Class, and one or more Interfaces as well as one or more Mock Service Classes.   

### 8.1 Step One - Create Empty Classes and Interfaces
We start by creating Empty Classes and empty Interfaces for the above.   
We will use Test Driven Development to find the code in these empty Classes and Interfaces 

### 8.2 Step Two - Find the first method for the first Empty Classes and Interfaces 
**Here we are using a valid request in our test**

As we know in this project that we are expected to receive a request from another Microservice.   
Now we start by writing the first unit test to test the Service Manager Class receiving a valid Transaction in the request.     
This will find the code for the Controllers first method and the Service Managers first method as well as our Mock Service Classes first method.   
In order for this unit test to pass you will have written code to pass the first methods

### 8.3 Step - Three Find more code for first method for the first Empty Classes and Interfaces 
**Here we are using an invalid request and writing a Negative Unit Test**

Now we start by writing the second unit test to test the Service Manager Class receiving an invalid Transaction in the request.   
This will find more code for the Service Managers first method as well as our Mock Service Classes first method.   
In order for this unit test to pass you will have written code to pass the first methods with invalid requests

**In this project this found the Validator Class called by the Service Manager Class**

**You repeat this process until you have found all the code for your project making sure you have done both Positive and negative tests and passed them all**

## 9. We mock the Service classes by implementing Mock Service classes in the Test Folder for Unit testing
By Mocking the Service classes we can control their behaviour to give us  Positive and Negative Unit tests.   
We add overloaded Constructors to the classes we want to test so that we can inject @Autowired fields and Objects as well as instances of the mocked Service classs 

![MicroserviceDesignPatternTDDandBDD](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/MicroserviceDesignPatternTDDandBDD.JPG)

### 9.1 Positive Tests
For Positive tests all the public methods of the Mock Service Classes will return the Expected values .

### 9.2 Negative Tests
For Negative tests all the public methods of the Mock Service Classes will return values representing failure  
  
Now we can see that our code behaves with failures.

### 9.3 Destructive Negative Tests
For Destructive Negative Tests we make all the methods in the Mock Classes throw the expected Exceptions for serious  failure.

This way we can test how our code handles crashing conditions.

### 9.4 Giving our Mock Classes multiple behaviours (Schizophrenic Classes)
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


## 10. The benefits of using Mock Service Implementation classes
  * We write unit tests that are not fragile 
  * Using Mockito produces brittle unit tests as any changes in the Requests and responses will require debugging to fix broken unit tests
  * Any changes in the Requests and responses will be easy to change in the Mock Service methods
  * We are able to do TDD and BDD tests with the infrastructure down this way Jenkins can still build and deploy   
  * This is achieved using Mock Classes and not making network calls to resources.   

  One of the companies I worked at would have Jenkins build failures in one of the services was down.   
**We never had that where we used this technique of using Mock Classes.**
  
## 11. We can now reuse the same Mock classes for simplifying BDD Testing

### 11.1 Negative Test
```
    Scenario    : Trying to send a invalid Transaction to the Message Queue for processing

    Given       : An invalid transaction message is sent
    Then        : A response is received with an Error Message
    And         : The response has a corresponding Error code
```

### 11.2 Destructive Negative Test
```
    Scenario    : Trying to send a valid Transaction unsuccessfully to the Message Queue for processing

    Given       : A valid transaction message is sent but it is not successfully sent to the Message Queue
    Then        : A response is received with an Error Message
    And         : The response has a corresponding Error code
```

### 11.3 Positive Test
```
    Scenario    : Trying to send a valid Transaction successfully to the Message Queue for processing

    Given       : A valid transaction message is sent which is successfully sent to the Message Queue
    Then        : A response is received with a Success Message
    And         : The response has a corresponding Success code
```
**All these Scenarios can be tested with the same Mock classes used for UNIT Testing**
