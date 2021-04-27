# RabbitMQ Queue Producer SpringBoot Microservice POC

## Demo of RabbitMQ Docker instance
ssh nickm@10.154.2.88

stop-rabbitmqServer

start-portainer

http://10.154.2.88:9000/#!/home

// now start  RabbitMQ Docker instance in portainer

http://10.154.2.88:15672/#/

// start the micro service instance

cd /system/projects_folder/STS_Projects/NewWorkSpace/RabbitMqPoc/
./runPoc.sh &


### setup Postman
http://10.154.2.88:9080/rabbitmq/rlpty/qproducer/send

Headers :

Accept application/json

Content-Type application/json

Body:

{
    "messageId":"NOT REQUIRED",
	"messageType":"dummy_messageType",
	"messageDescription":"dummy_messageDescription",
	"transactionId":"dummy_transactionId",
	"transactionType":"dummy_transactionType",
	"transaction":"",
	"targetSystemId":"dummy_targetSystemId",
	"senderSystemId":"dummy_senderSystemId",
	"senderId":"dummy_senderId"
}



{
    "messageId":"NOT REQUIRED",
	"messageType":"dummy_messageType",
	"messageDescription":"dummy_messageDescription",
	"transactionId":"dummy_transactionId",
	"transactionType":"dummy_transactionType",
	"transaction":"dummy_transaction",
	"targetSystemId":"dummy_targetSystemId",
	"senderSystemId":"dummy_senderSystemId",
	"senderId":"dummy_senderId"
}

 
## Demo of RabbitMQ running on Ubuntu Server

//  RabbitMQ Docker instance and stop portainer

start-rabbitmqServer

Run Postman

# Open IDE and show these files

Our Testing stratergy is to only write init tests for Classes with methods that have data changing logic

We do not test the traditional getter and setter methods 


## Open the POM
and look at the <sonar.exclusions> inside the <properties> 

## looking at code samples to show our testing stratergy

MessageQueueController  This has only pass thorugh methods (No data values change here) so we dont need to unit test it 

We will unit test ServiceManager

ServiceManager 

This class is decoulped from MessageQueueService implementations  
  
We create two implementations 
  * MessageQueueServiceImpl
  * MockMessageQueueServiceImpl (a mock implementation in the test packages)

The second class will be passed to the ServiceManager during unit testing  

This mock class has 3 behaviours for all its methods
 1. return good values
 2. return failing values
 3. throw exception

By doing unit testing this way you dont have the brittle tests Mockito gives you 
Everytime you make a small change in you requests or responces you waste a lot of time fixing broken unit tests
  
Also this way you dont just do positive testing , you test using bad data and test trying to crash the class under test  
It provides more rubust and non fragile tests

This same methodology is carried out in other classes

## Open the POM
and look at the <sonar.exclusions> inside the <properties> 
  
