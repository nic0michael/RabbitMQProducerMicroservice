#!/bin/bash

sudo docker run -d --hostname RabbitMQPoc --name rabbitmq-poc-docker -p 9080:9080 rabbitmq-poc-docker:v1
