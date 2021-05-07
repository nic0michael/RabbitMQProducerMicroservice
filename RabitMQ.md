# RabbitMQ Server Installation

## RabbitMQ Server Linux Installation
You need to look at the following links to first install Erlang.   
https://computingforgeeks.com/how-to-install-latest-erlang-on-ubuntu-linux/

Now to install RabbitMQ in your Linux server.   
https://computingforgeeks.com/how-to-install-latest-rabbitmq-server-on-ubuntu-linux/

### You will be running these commands :
systemctl status  rabbitmq-server.service   
sudo systemctl enable rabbitmq-server   
sudo rabbitmq-plugins enable rabbitmq_management   

If you are using the UFW Firewall   
**sudo ufw allow proto tcp from any to any port 5672,15672**

```
sudo rabbitmqctl add_user admin YourPassword   
sudo rabbitmqctl set_user_tags admin administrator   

sudo rabbitmqctl change_password user strongpassword   
sudo rabbitmqctl add_vhost /nico_vhost   
sudo rabbitmqctl list_vhosts   
sudo rabbitmqctl delete_vhost /myvhost   

sudo rabbitmqctl set_permissions -p /nico_vhost admin ".*" ".*" ".*"   
sudo rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"   

sudo rabbitmqctl list_user_permissions admin   

sudo rabbitmqctl clear_permissions -p /myvhost user

```

**When configuring RabbitMQ, at least one vhost is needed, which in default is just a slash “/”**

 
## Installing RabbitMQ as a Docker Container

### Run the following commands in a Terminal
```
sudo docker run -d --hostname RabbitMQSvr --name rabbit-mq-mgr2 -p 15672:15672 -p 5672:5672 rabbitmq:3-management

```
## Open the Docker instance in Portainer

[http://localhost:9000](http://localhost:9000)

### Adminnistrate the Docker Instance
**You May need to open this Docker Container in Portainer and open the Portainer Terrminal for this Docker Container and run these commands:**
```
sudo rabbitmqctl add_vhost /nico_vhost
sudo rabbitmqctl add_user admin YourPassword   
sudo rabbitmqctl set_user_tags admin administrator   
sudo rabbitmqctl set_permissions -p /nico_vhost admin ".*" ".*" ".*"   
sudo rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"   

```
This will will create the Virtual Host and the admin user giving it access rights to the Queues in all the vhosts

## RabbitMQ  Administration Console
### Open the  RabbitMQ  Administration Console in the Browser

You should receive the following response after a few seconds

[http://localhost:15672/](http://localhost:15672/)

### Login credentials:
## Creating Exchange the Queues and Bindings using Exchange and Routing keys

```
Delete Default GUEST Credentials  and use the admin credentials that were set in the above instructions 
User Id : guest
Password : guest
```

## Creating Exchange the Queues and Bindings using Exchange and Routing keys


## You create The Exchange/s here
Click on the Exchanges Tab.   
Expand the : Add a new exchange link.   
Fill in the form and press the Add Exchange Button.   

## You create The Queues here
Click on the Queues Tab.   
Expand the : Add a new queue link.   
Fill in the form and press the Add Queue Button.   


## Creating Exchange the Queues and Bindings using Exchange and Routing keys
```
Exchange : rabbitmq.in.x

Queue : rabbitmq.in.rlpty.q

Deadletter Queue : rabbitmq.dead.in.rlpty.q

Queue Routing Key : rabbitmq.rlpty.r

Deadletter Queue Routing Key : rabbitmq.dead.rlpty.r
```

