# Postman and Newman for automated Integration Tests
Postman is used to create Test Requests. 

Then you place Test Scripts using the Javascript language inside the Requests. 

When all your tests pass then you export your collection as a TestSuite (a json file)


## 1. Install Postman

https://www.postman.com/downloads/

## 2. Install Newman

### 2.1 Install Node JS

install Node JS : https://nodejs.org/en

### 2.2 Install Newman

 npm install -g newman
 
 Once you have an exported TestSuite (JSON file) then you write a Newman shell script which will run all the tests and create a Test Results file.
 