#!/bin/bash
echo running poc
# make next line point to the folder with the POM file
cd /system/projects_folder/STS_Projects/NewWorkSpace/RabbitMqPoc
mvn spring-boot:run -Dspring-boot.run.profiles=dev
