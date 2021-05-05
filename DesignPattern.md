# Our Microservice Design Pattern
This design pattern has matured over four years of its use to write Microservices   

Here focus given to TDD (Test Driven Development) and BDD (Behaviour Driven Development).

## The problem with writing Microservices that have tightly coupled classes
Many developers do not decouple their Controller Classes from the Service Classes this has a few disadvantages:

  * The design becomes rigid not giving the developer flexibility in the future.
  * These Microservices become a challenge for rapid unit testing (Google achieves 3 million tests in a few minutes).
  * This does not facilitate the use of Mock Service classes for TDD and BDD testing. 

## How this design pattern evolved 
In 2017 Saurabh Agrawal(DVT) had recommended that one should decouple the Controller Classes from the Service Classes

Later Nico Michael(DVT) introduced the use of Mock Classes for Unit Testing to facilitate TDD and BDD.

Recently the Class that decouples the  Controller Classes from the Service Classes was given the name "ServiceManager".

## Testing Strategy
We only write unit tests for Classes with methods, that are data changing, or have other logic.

**So POJOs , DTOs, Entity Classes, Custom Exceptions, and Controller Classes are not Unit tested tested**.

If we use only Pass-Through methods in our Controller Classes then there is no logic to test so we don't test them.
(this is explained below)

### Getting SonarQube to ignore Classes we don't want to test
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

## Decoupling the Controller Classes from the Service Classes
It is not uncommon to find the Controller Classes tightly coupled to the Service Classes this makes unit testing a challenge.

### We introduced a Service Manager Class between the Controller Classes and the Service Classes
This decouples the Controller Classes from the Service Classes.   
We also made the Service Classes implement Interfaces this further decouples the ServiceManager Class from the Service Classes

![MicroserviceDesignPattern](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/MicroserviceDesignPattern.JPG)

## We use "Pass-through Methods"
In the Controller Classes all the methods that call the Service Manager Class do not "Modify Data".  
  
What they receive in their  parameters is passed directly to the methods of the Service Manager Class   
  
We prefer to have the methods there with the same names as in the Controller Classes.

As we do not have any logic and are not changing anything in the Controller Classes we don’t need to write unit tests for the Controller Classes.

## We decouple the Service Manager Class from the Service Classes by making the Service Classes implement an Interface
These Service Interfaces are @Autowired to the Service Manager Class.

## We mock the Service classes by implementing Mock Service classes in the Test Folder for Unit testing

![MicroserviceDesignPatternTDDandBDD](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/MicroserviceDesignPatternTDDandBDD.JPG)

By Mocking the Service classes we can control their behaviour to give us  Positive and Negative Unit tests.
  
### Positive Tests
For Positive tests all the public methods of the Mock Service Classes will return the Expected values .

### Negative Tests
For Negative tests all the public methods of the Mock Service Classes will return values representing failure  
  
Now we can see that our code behaves with failures.

### Destructive Negative Tests
For Destructive Negative Tests we make all the methods in the Mock Classes throw the expected Exceptions for serious  failure.

This way we can test how our code handles crashing conditions.

### Giving our Mock Classes multiple behaviours (Schizophrenic Classes)
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

## The benefits of using Mock Service Implementation classes
  * We write unit tests that are not fragile 
  * Using Mockito produces brittle unit tests as any changes in the Requests and responses will require debugging to fix broken unit tests
  * Any changes in the Requests and responses will be easy to change in the Mock Service methods
  * We are able to do TDD and BDD tests with the infrastructure down this way Jenkins can still build and deploy   
  * This is achieved using Mock Classes and not making network calls to resources
  
### We can now use the same Mock classes for also doing BDD Testing and so simplifying the testing

```
    Scenario    : Trying to send a Transaction to the Message Queue
    Given       : An invalid transaction message is received
    Then        : A response is received with an Error Message with a corresponding Error code
```

```
    Scenario    : Trying to send a Transaction to the Message Queue
    Given       : A valid transaction message is received but cant be sent to the Message Queue
    Then        : A response is received with an Error Message with a corresponding Error code
```

```
    Scenario    : Trying to send a Transaction to the Message Queue
    Given       : A valid transaction message is received and it was successfully sent to the Message Queue
    Then        : A response is received with a Success Message with a corresponding Success code
```
All these Scenarios can be tested with the same Mock classes used for UNIT Testing
