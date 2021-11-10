# Postman and Newman for automated Integration tests
Postnam is used to create Test Requests 
Then placing Test Scripts using the Javascript language inside the Requests 
When all your tests pass then you export you collection as a TestSuite (a json file)


## 1. Install Postman

https://www.postman.com/downloads/

## 2. Install Newman

### 2.1 Install Node JS

install Node JS : https://nodejs.org/en

### 2.2 Install Newman

 npm install -g newman
 
 Once you have an exported TestSuite JSON file you then write a Newman shell script whic will runn all the test and create a Test Results file.
 