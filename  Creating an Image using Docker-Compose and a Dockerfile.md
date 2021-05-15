#  Creating an Image using Docker-Compose and a Dockerfile

## 1. First steps
create a folder called **DockerCompose** in your project.  

## 2. create The Docker Compose YAML File
in this folder create a file called docker-compose.yml  

**Add this content to the YAML file:**

```
version: '2'
services:
  prod:
    image: rabbitmqq-producer
    build: 
      context: prod
      dockerfile: Dockerfile
    ports:
      - 9080:9080     
    restart: on-failure
```


## 3. Create a docker compose build shell script to build the project
In this folder create a file called step1_docker-compose_build.sh  

Add this content to the shell script file:  

```
#!/bin/bash
sudo docker-compose build
```

In the terminal run this command to make the script executable in linux
```
sudo chmod 775 step1_docker-compose_build.sh
```

## 4. Create a docker run shell script to list the docker images
In this folder create a file called step2_docker_list_images.sh  

Add this content to the shell script file:  

```
#!/bin/bash
sudo docker images
echo to delete an image:
echo sudo docker rmi rabbitmqq-producer
```

## 5. Create a docker run shell script to run the Docker Image
In this folder create a file called step3_Docker_run_image.sh  

Add this content to the shell script file:  

```
#!/bin/bash
sudo docker run -p 9080:9080 -it rabbitmqq-producer
```
In the terminal run this command to make the script executable in linux:  

```
sudo chmod 775 step1_docker-compose_build.sh
```
## 6. create the Production Environment Folder : prod
in this folder create a folder called **prod**.  

## 7 Add the WAR file to this folder
Add the WAR file that was built by Maven in the project Target folder to the prod folder.  
It was built running this command:  

```
mvn clean package
```

## 8. Add the Dockerfile file to this folder
In the previous Docker document we describe how the Docker file is made.

[follow this link to see how to create a Docker file for this project](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/docker.md)

