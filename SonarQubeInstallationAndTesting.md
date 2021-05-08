# SonarQube installation Procedures

## Add SonarQube to the Project POM
In the POM of the Project add the following entries.   
```
<properties>
		. . .
		. . .
		<sonar.exclusions>
			file:**/rabbitmq/poc/controllers/**,
			file:**/rabbitmq/poc/dtos/**,
			file:**/rabbitmq/poc/exceptions/**
		</sonar.exclusions>
	</properties>



	<build>
		<!-- <finalName>${project.artifactId}_${project.version}</finalName> -->
		<finalName>${project.artifactId}</finalName>

		<pluginManagement>
			<plugins>
				<plugin>
					<groupId>org.sonarsource.scanner.maven</groupId>
					<artifactId>sonar-maven-plugin</artifactId>
					<version>3.7.0.1746</version>
				</plugin>
			</plugins>
		</pluginManagement>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

```

## Install SonarQube in Docker
**Run the following commands in the Linux server**

```
sudo docker pull sonarqube

sudo docker run -d --name sonarqube -p 9001:9000 -p 9092:9092 sonarqube

This now runs under http://localhost:9001   
```
This will create a docker Instance.   
You will need to open this instance in Portainer and stop it in Portainer so that Portainer will remember this instance.   

### Open Portainer in Browser
http://localhost:9000/

Use Portainer To Start the SonarQube Instance.   

### Open SonarQube in Browser
http://localhost:9001

## Creating an Admin Token 
[Open SonarQube in Browser.](http://localhost:9001)   
```
click -> Administration (in top menu).   
Select    -> Security.   
Select        -> Users.   

Select -> Update Tokens Icon Type Token Name.   
Select    -> Generate (button).   
Select        -> done (button).   
```

This generates token : 63ff9f6e91cb99a16598ffc52050ebc4d533a4ec   
Save this token for future use.   

## To run the Sonar Test
Open the Java Project folder in terminal.   
Run this command.   

```
mvn sonar:sonar -Dsonar.host.url=http://localhost:9001 -Dsonar.login=63ff9f6e91cb99a16598ffc52050ebc4d533a4ec
```


