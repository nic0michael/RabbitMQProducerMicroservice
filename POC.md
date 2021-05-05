# RabbitMQ Queue Producer SpringBoot Microservice POC

## What will be Demonstrated

### 1. Running on a Linux Server
We will be demonstrating This Microservice running on a Linux server

Connecting to a RabbitMQ Server running running on the same Linux server

We will Look at the RabbitMQ Management Interface

Postman will be used to send Messages to the Queues of this RabbitMQ Server 

### 2. Running the RabbitMQ Server in a Docker Container
Then we will stop this RabbitMQ Server and start a Docker Instance with a  RabbitMQ Server

We will Look at the RabbitMQ Management Interface

Postman will be used to send Messages to the Queues of this RabbitMQ Server 

**Please note a different command is used to create and start this Docker Instance with a  RabbitMQ Server and not what you fine on the Internet**

## After that we will look at the Testing strategy and Design Pattern for this Microservice 


## 1. Demo of RabbitMQ running on Ubuntu Server
Start  the Microserver Instance
```
ssh nico@10.154.2.88

cd /system/projects_folder/STS_Projects/NewWorkSpace/RabbitMqPoc/

./runPoc.sh &

```

Open the [RabbitMQ Server Admin Console](http://10.154.2.88:15672/#/)

After this demo stop the  RabbitMQ Server 

```
ssh nico@10.154.2.88

cd /system/projects_folder/STS_Projects/NewWorkSpace/RabbitMqPoc/

./runPoc.sh &

sudo ls

stop-rabbitmqServer
```
Run Postman

## 2. Demo of RabbitMQ Docker instance

```
ssh nico@10.154.2.88

sudo ls

stop-rabbitmqServer

start-portainer

```
Open Portainer in the browser: [http://10.154.2.88:9000/#!/home](http://10.154.2.88:9000/#!/homeL)

// now start  RabbitMQ Docker instance in portainer

Open the RabbitMQ Server Admin Console in the browser : [http://10.154.2.88:15672/#/](http://10.154.2.88:15672/#/)

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

 



## Now show the following documents:
[Microservice Recomendations](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/MicroserviceRecomendations.md)

[Microservice Design Pattern](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/DesignPattern.md) 

If I have time we will look at the code in the IDE
  
