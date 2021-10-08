# Making a docker Image of this project
If you have not installed Docker have a look at the instructions below this document.

## 1. Build the WAR file
Open the project folder in the terminal.   
Run the following command.   
```
mvn clean package
```
## 2. Create a folder called Docker
Create a folder called Docker in your project.   

## 3. Create a file called  Dockerfile in this folder with this content

```
FROM alpine:latest
WORKDIR /
VOLUME /tmp
ADD RabbitMqPoc.war RabbitMqPoc.jar
RUN apk --update add openjdk11-jre
EXPOSE 9080
CMD java -Xmx500M -jar RabbitMqPoc.jar
```
## 4. copy the WAR file RabbitMqPoc.war from the Target Folder to the docker folder
```
cp -p target/RabbitMqPoc.war docker/
```

## 5. Pull down the Alpine Docker Container
Open your docker folder in a terminal and run this command.   

```
sudo docker pull alpine:latest

```
If you Don't like Alpine you could use Ubuntu.   

## 6. build a docker image from the Docker File
Now run this command in the terminal.   
```
sudo docker build -t rabbitmq-poc-docker:v1 .

```

## 7. Start the docker image 
Run this command in the terminal.   
```
sudo docker run -d --hostname RabbitMQPoc --name rabbitmq-poc-docker -p 9080:9080 rabbitmq-poc-docker:v1

```
## 8. Open Portainer and stop your Docker instance
[Portainer instance](http://localhost:9000/)
Now Portainer will have your Docker Instance ready for starting in the future.   

## 9. Creating an Image using Docker-Compose and the above Dockerfile
[This information is published in a separate document use this link to access it](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/%20Creating%20an%20Image%20using%20Docker-Compose%20and%20a%20Dockerfile.md)

## Installing Docker

### Installing Docker Engine on Ubuntu
[Install Docker Engine on Ubuntu](https://docs.docker.com/engine/install/ubuntu/)

###  Installing Docker on windows 10
[1. How to install Cygwin on Windows 10](https://www.youtube.com/watch?v=QonIPpKodCw)

(2. Docker Tutorial for Beginners 2 - Install Docker on Windows 10)[https://www.youtube.com/watch?v=_9AWYlt86B8]

## Installing Docker-Compose
[Download and Install Docker-compose here:](https://github.com/docker/compose/releases/)

