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
