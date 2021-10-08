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
[Install Docker Engine on Ubuntu](https://docs.docker.com/engine/install/ubuntu/). 
[How To Install and Use Docker on Ubuntu 20.04](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04). 

Check what version of Ubuntu you have installed:
```
lsb_release -a
```

```
sudo apt-get install \
apt-transport-https \
ca-certificates \
curl \
software-properties-common

sudo apt update

sudo apt install apt-transport-https ca-certificates curl software-properties-common

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

apt-cache policy docker-ce

sudo apt install docker-ce
# if the above fails
sudo apt install docker.io
sudo apt install docker-compose


 
```

[Install WSL - Windows Subsystem for Linux](https://docs.microsoft.com/en-us/windows/wsl/install)

###  Installing Docker on windows 10
It is much easier to install WSL (Windows Subsystem for Linux).   
open browser : [WSL (Windows Subsystem for Linux)](https://www.microsoft.com/en-za/windows/windows-10-apps). 
[Search for Ubuntu ](https://www.microsoft.com/en-za/p/ubuntu/9nblggh4msv6?activetab=pivot:overviewtab). 
After installing WSL you need to reboot Windows 10. 
Search and Start Ubuntu. 
You will need to set the Linux UserId and password make a note of this. 

Check what version of Ubuntu you have installed:
```
lsb_release -a
```
#### The WSL file System is located here :
C:\Users\YourWindowsName\AppData\Local\Packages\CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\LocalState\rootfs  
Your Home folder is located here: 
C:\Users\YourWindowsName\AppData\Local\Packages\CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\LocalState\rootfs\home\LinuxUserId  


[1. How to install Cygwin on Windows 10](https://www.youtube.com/watch?v=QonIPpKodCw).   

[Download Cygwin here](https://cygwin.com/install.html)

[2. Install docker on Ubuntu 16.04 and Windows with Cygwin command line working](https://www.youtube.com/watch?v=L1fwHM9agIw)

[3. Docker Tutorial for Beginners 2 - Install Docker on Windows 10](https://www.youtube.com/watch?v=_9AWYlt86B8)

## 12. Installing Docker-Compose
[Download and Install Docker-compose here:](https://github.com/docker/compose/releases/)

