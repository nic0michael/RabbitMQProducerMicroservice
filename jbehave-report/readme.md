#  Behaviour Driven  Development BDD using JBehave


In this project JBehave version 5.0-SNAPSHOT is used. (This was built from a git clone of [the jbehave project in GitHub](https://github.com/jbehave))

## 1 The JBehave Story File

**This file describes the Test Acceptance Criteria** using English statements that the Business can understand.   
The Business Analyst or Product Owner will provide this as a text file (Sometimes referred to as a Gherkin File).   
This becomes test Specifications for the developers to build their projects.   
This file is placed in the **/src/test/resources/** folder.    
We chose to name it **JBhaveStoryFile.story** .   

~~~
Narrative: While Sending messages to the RabbitMQ Server
As a external service
I want to receive requests and send messages.
  
Scenario: 1 the sent request received from another service is valid send a message to the rabbitmq server
Given the request has all fields populated with needed values
When I receive a valid request from another service
Then I should send a message to the rabbitmq server
    
     
Scenario: 2 the sent requests received from another service has missing fields dont send a message to the rabbitmq server
Given the request does not have all needed fields populated
When I receive an invalid request from another service
Then I should not send a message to the rabbitmq server
    
    
Scenario: 3 the sent request received from another service is valid sending a message to the rabbitmq server fails
Given the request has all fields populated with valid values
When I receive a valid request from the other service
Then I trying to send the message fails I should receive a suitable response 

~~~

## 2 The Runner Class
This class is located in the **/src/test/java/za/co/nico/rabbitmq/poc/bdd/** folder.    
We chose to name it **JBehaveRunner_Test.java** .    
This class will run the tests and generate a report.    

**To launch the BDD tests**
In STS or Eclipse you right click on this class and select **"Run As Unit Test"**.    
By running the Runner Class jbehave will generate a test report in the **/target/jbehave-report/** folder.    

## 3 The Steps class
This class is located in the **/src/test/java/za/co/nico/rabbitmq/poc/bdd/** folder.    
We chose to name it **SendMessageSteps** .    

## 4 Generating the code for the Steps class

**If we write the minimum code for this class**
~~~
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

public class SendMessageSteps extends Steps {

}
~~~

**and then run the Runner Class as a unit test.    
Jbehave will generate the skeleton code for all test scenarios that you will need for this class in the Console**.    

~~~

Scenario: 1 the sent request received from another service is valid send a message to the rabbitmq server
Given the request has all fields populated with needed values (PENDING)
When I receive a valid request from another service (PENDING)
Then I should send a message to the rabbitmq server (PENDING)
@Given("the request has all fields populated with needed values")
@Pending
public void givenTheRequestHasAllFieldsPopulatedWithNeededValues() {
  // PENDING
}

@When("I receive a valid request from another service")
@Pending
public void whenIReceiveAValidRequestFromAnotherService() {
  // PENDING
}

@Then("I should send a message to the rabbitmq server")
@Pending
public void thenIShouldSendAMessageToTheRabbitmqServer() {
  // PENDING
}

~~~
