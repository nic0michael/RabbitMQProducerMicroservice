# RabbitMQ Queue Producer SpringBoot Microservice POC

## What will be Demonstrated

### Running on a Linux Server
We will be demonstrating This Microservice running on a Linux server

Connecting to a RabbitMQ Server running running on the same Linux server

We will Look at the RabbitMQ Management Interface

Postman will be used to send Messages to the Queues of this RabbitMQ Server 

### Running the RabbitMQ Server in a Docker Container
Then we will stop this RabbitMQ Server and start a Docker Instance with a  RabbitMQ Server

We will Look at the RabbitMQ Management Interface

Postman will be used to send Messages to the Queues of this RabbitMQ Server 

**Please note a different command is used to create and start this Docker Instance with a  RabbitMQ Server and not what you fine on the Internet**

## After that we will look at the Testing strategy and Design Pattern for this Microservice 

## Demo of RabbitMQ Docker instance
```
ssh nico@10.154.2.88

stop-rabbitmqServer

start-portainer

```

[http://10.154.2.88:9000/#!/home](http://10.154.2.88:9000/#!/homeL)

// now start  RabbitMQ Docker instance in portainer

[http://10.154.2.88:15672/#/](http://10.154.2.88:15672/#/)

// start the micro service instance
```
cd /system/projects_folder/STS_Projects/NewWorkSpace/RabbitMqPoc/
./runPoc.sh &
```

### set-up Postman
```
http://10.154.2.88:9080/rabbitmq/rlpty/qproducer/send
```

**Headers :**
```
Accept application/json

Content-Type application/json
```

**Bad Body:**
```
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
```
**Good Body:**

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
```
start-rabbitmqServer
```
Run Postman



## Now show the following documents:
[Microservice Recomendations](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/MicroserviceRecomendations.md)
[Microservice Design Pattern](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/DesignPattern.md) 
If I have time we will look at the code in the IDE
  
