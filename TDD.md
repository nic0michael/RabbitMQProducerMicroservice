# Test Driven Development

## We Use the First Write a Test Methodology
[For Recomendations about Microservices use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice)

The Purpose of the Service Manager Class is to be called by the Controllers and to call one or more Service Class.   
This class will Orchestrate the process **keeping the Service Classes small and simple**, each having one primary function.

In our design pattern we know that Micro Services have Controller Classes  and a few Service Classes.   
We also know that we are introducing a Service Manager Class, and one or more Interfaces as well as one or more Mock Service Classes.   


## Having the following Business Rules 

We have the business rules as Cucumber files (In text format and can be generated using a suitable BDD Tool)
### First Scenario (This will be used for a Positive Unit Test)
```
    Scenario    : Trying to send a valid Transaction successfully to the Message Queue for processing

    Given       : A valid transaction message is sent which is successfully sent to the Message Queue
    Then        : A response is received with a Success Message
    And         : The response has a corresponding Success code
```

###  Second Scenario (This will be used for a Negative Unit Test)
```
    Scenario    : Trying to send a invalid Transaction to the Message Queue for processing

    Given       : An invalid transaction message is sent
    Then        : A response is received with an Error Message
    And         : The response has a corresponding Error code
```

###  Third Scenario (This will be used for a Destructive Negative Test)
```
    Scenario    : Trying to send a valid Transaction unsuccessfully to the Message Queue for processing

    Given       : A valid transaction message is sent but it is not successfully sent to the Message Queue
    Then        : A response is received with an Error Message
    And         : The response has a corresponding Error code


### Step One - Create Empty Classes and Interfaces
We start by creating Empty Classes and empty Interfaces for the above.   
We will use Test Driven Development to find the code in these empty Classes and Interfaces 

### Step Two - Find the first method for the first Empty Classes and Interfaces 
**Here we are using a valid request in our test**

As we know in this project that we are expected to receive a request from another Microservice.   
Now we start by writing the first unit test to test the Service Manager Class receiving a valid Transaction in the request.     
This will find the code for the Controllers first method and the Service Managers first method as well as our Mock Service Classes first method.   
In order for this unit test to pass you will have written code to pass the first methods

### Step - Three Find more code for first method for the first Empty Classes and Interfaces 
**Here we are using an invalid request and writing a Negative Unit Test**

Now we start by writing the second unit test to test the Service Manager Class receiving an invalid Transaction in the request.   
This will find more code for the Service Managers first method as well as our Mock Service Classes first method.   
In order for this unit test to pass you will have written code to pass the first methods with invalid requests

**In this project this found the Validator Class called by the Service Manager Class**

**You repeat this process until you have found all the code for your project making sure you have done both Positive and Negative tests and passed them all**

```