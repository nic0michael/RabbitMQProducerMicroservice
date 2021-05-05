# Our Microservice Design Pattern
This design pattern have matured over four years of its use to write Microservices with focus given to TDD (Test Driven Development) and BDD (Behaviour Driven Development)

## How this design pattern evolved 
4 years ago Saurabh Agrawal had recommended that one should decouple the Controller Classes from the Service Classes

Nico Michael introduced the use of Mock Classes for Unit Testing to facilitate TDD (Test Driven Development) and BDD (Behaviour Driven Development)

The Class that decouples the  Controller Classes from the Service Classes was given the name "ServiceManager"

## Testing Strategy
Our Testing strategy is to only write unit tests for Classes with methods that have data changing logic

### Getting SonarQube to ignore Classes we don't want to test

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
It is not uncommon to find the Controller Classes tightly coupled to the Service Classes this makes unit testing a challenge

### We introduce a Service Manager Class between the Controller Classes from the Service Classes
This decouples the Controller Classes 

## We use "Pass-through Methods"
In the Controller Classes all the methods that call the Service Manager Class do not "Modify Data" what they receive in their  parameters is passed directly to the methods of the Service Manager Class where we prefer to even have the methods with the same names as in the Controller Classes

As we do not have any logic and are not changing anything in the Controller Classes we don’t need to write unit tests for the Controller Classes

We write positive and Negative Unit tests for the Service Manager Class

## We decouple the Service Manager Class from the Service Classes by making the Service Classes implement an Interface
These Service Interfaces are @Autowired to the Service Manager Class

## We mock the Service Interfaces by implementing Mock Service Implementation classes in the Test Folder for Unit testing

By Mocking the Service classes we can control their behaviour to give us  Positive and Negative Unit tests  
  
### Positive Tests
For Positive tests all the public methods of the Mock Service Classes return the Expected values so we can see that our code behaves

### Negative Tests
For Negative tests all the public methods of the Mock Service Classes return values representing failure so we can see that our code behaves with failures

### Destructive Negative Tests
For Destructive Negative Tests we make all the methods in the Mock Classes throw the expected Exceptions for serious  failure

This way we can test how our code handles crashing conditions

### Giving our Mock Classes multiple behaviours (Schizophrenic Classes)
In Order to give our Mock Classes multiple behaviours We make the default constructor private  
And provide an overloaded Constructor that receives an Enum called TestType

    public enum TestType {
	   PASSING_TEST,FAILING_TEST,THROWS_EXCEPTION;
    }
 
And write all our Mock Class methods to have three behaviours based on how the classes are instantiated

## The benefits of using Mock Service Implementation classes
  * We write unit tests that are not fragile 
  * Using Mockito any changes in the Requests and responses will require debugging to fix broken unit tests
  * Any changes in the Requests and responses will be easy to change in the Mock Service methods
  
**We can use the same Mock classes for doing BDD Tasting simplifying the testing **
