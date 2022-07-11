# RabbitMQ Queue Producer SpringBoot Microservice

## This project is an example of a Microservice built from the Industry Best Practices.   
This includes TDD, BDD, and Unique Design Pattern for Microservices, as well as the installation of various Dockerized servers used to work with this project.   
What makes this Microservice unique is the Design Pattern we developed for Spring Boot microservices is used here. For more information please read the Project Documentation.   
[To access the Project Documentation use this link.](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/ProjectDocumentation.md).   

This Project offers a sample SpringBoot project to send messages to a RabbitMQ servers Queue using code built using best practices

Here a Cloud Ready project is provided to run on Amazon (AWS),Microsoft Azure, Google Cloud Platform (GCP).  
This project is delivered as a WAR file, so that it can also be run on Tomcat

After reading this page have a look at the project documentation page in section 3 below

## 1. Features 
  * This Project is built using a unique Design Pattern optimized for better Microservices
  * Support is given to Tomcat by project is delivered As a WAR file.
  * Support for Docker is provided by this project.   
  * This Microservice can be Customized to support the Database of your choice

## 2. Servers and Technologies supported
### 2.1 RabbitMQ
We use the RabbitMQ server to host the Queues to which this project sends Transactional messages.   
We provide installation instructions to install:   
  * This in a Ubuntu Linux Server
  * As well as Docker instance 
  * Special instructions are provided to run this as a Docker instance creating:
 1. the vhosts(Virtual Hosts)
 2. The admin user.
 3. Giving the admin user access to the vhosts.  
    
### 2.2 Tomcat
This project uses Maven to generate a WAR file that can be deployed on tomcat.

### 2.3 Docker
A set of scripts are  provided to "Dockerize" this Microservice.   
As well as [instructions accessible from the Project Documentation are provided
](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/ProjectDocumentation.md)

### 2.4 Portainer
Portainer is used to Start the Docker instances for this project if you are using Docker.
  
### 2.5 Database Support
This project is customizable for you to chose your own database technology.   
An Interface is provided and an empty Database Service class for you to customize for your requirements. 
## 3. Project Documentation
Explanations on how Our design pattern decouples this Microservice internally. 
How it facilitates doing Test Driven Development(TDD) and Behaviour Driven Development(BDD) using Mock Service classes.
Installation instructions for all servers and Docker containers are provided here

**[To access the Project Documentation use this link.](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/ProjectDocumentation.md)**
**[To learn more about our Design Pattern n use this link.](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/DesignPattern.md)**
 
## 4. Building cloud ready microservices using industry best practices DVT White Paper 
I have published this white paper : 
[white-paper-building-cloud-ready-microservices-using-industry-best-practices](https://www.dvt.co.za/white-paper-building-cloud-ready-microservices-using-industry-best-practices)  
 
## 5. Free Open Source FOS with the License based on GPL Version 3

This project is Free Open Source code FOS.   
We provide this Project as free of charge and subject to the terms and license of this project is GPL Version 3.   
**As this is Free Open Source Project you are welcome to make a fork of this project for commercial use.**   
In return for that please give the author of this project credit in your MD keeping the next line:   
[The Source of this project if Forked from GitHub and is Free for Commercial use.](https://github.com/nic0michael/RabbitMQProducerMicroservice)

## 6. No Warranty is provided or implied
This software is provided in terms of South African law as : "VOETS TOETS" "AS IS" (in English) with no warranty and no Guarantee provided or implied That the user shall agree to comply with these terms when using this software.

