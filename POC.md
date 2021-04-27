# RabbitMQ Queue Producer SpringBoot Microservice

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
