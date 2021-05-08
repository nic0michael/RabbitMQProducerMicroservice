#!/bin/bash
# startRabbitMqPoc.sh
echo ---------------------------------------- >> start.log
echo date >> start.log
echo start.sh started >> start.log
echo =================== >> start.log
date >> start.log
java -Xmx500M -jar RabbitMqPoc.war
