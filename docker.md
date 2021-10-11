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

## 10. Installing Docker

### Installing Docker Engine on Ubuntu
[How To Install and Use Docker on Ubuntu 20.04](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04). 
This instalation can be used on a Ubuntu Machine as wellas on WSL(Windows Subsystem for Linux). 

Check what version of Ubuntu you have installed: 
```
lsb_release -a
```

```
sudo apt update

sudo apt install apt-transport-https ca-certificates curl software-properties-common

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

apt-cache policy docker-ce

sudo apt install docker-ce
# if the above fails (in WSL)
sudo apt install docker.io
sudo apt install docker-compose

sudo apt update

sudo apt upgrade

docker --version
docker-compose --version
 
```

[Install WSL - Windows Subsystem for Linux](https://docs.microsoft.com/en-us/windows/wsl/install)

###  Installing Docker on windows 10 (Dont use WSL)
[https://nickjanetakis.com/blog/setting-up-docker-for-windows-and-wsl-to-work-flawlessly](https://nickjanetakis.com/blog/setting-up-docker-for-windows-and-wsl-to-work-flawlessly) . 

#### The WSL file System is located here :
C:\Users\YourWindowsName\AppData\Local\Packages\CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\LocalState\rootfs  
Your Home folder is located here: 
C:\Users\YourWindowsName\AppData\Local\Packages\CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\LocalState\rootfs\home\LinuxUserId  

#### To create a Desktop Shortcut the Executable Binary is located here:
C:\Windows\System32\wsl.exe 
right click thia file and select : Send to Desktop (create shortcut)


## 12. Installing Docker-Compose
[Download and Install Docker-compose here:](https://github.com/docker/compose/releases/)

