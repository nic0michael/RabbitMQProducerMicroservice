# How we meet Dave Farley's Microservice Recommendations

The talks of Dave Farley from the Continuous Delivery Channel provides valuable information on Microservices

Here we show how we met his recommendations for this project

## Small
This is a small Microservice that could be replaced in about 3 days

## Focused on a single task
This Microservice only delivers on task and that is to publish messages to a RabbitMQ server

## Aligned to a bounded context (Domain Driven Design)
this Microservice provides a boundary for sending Transactions (Our Domain Object) to be transmitted to a Queue

Our Port is the Controller Class : MessageQueueController

Our Adapter is the QueueProducerManager class : QueueProducerManagerImpl which converts the SendToQueueRequest to JSON for sending to the Queue

## Loosly coupled
This Microservice does not depend on the previous or next Microservice  

It can be deploys interdependently
Additional decoupling to the next Microserviceis provided by a RabbitMQ Queue

## Autonomy
this Microservice's implementation can be changed at any time provided we dont change its Interface

## Fast Tests
Please refer to our Microservice Design Pattern where we make Unit Testing TDD as well as BDD Testing simpler and faster 

## For more information

Have a look at our Design Pattern   

[Dave Farley's Continuous Delivery YouTube Channel](https://www.youtube.com/channel/UCCfqyGl3nq_V0bo64CjZh8g)