# Bookstore

BookStore is an online application that users can use to search and buy books from. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

```
1) Maven should be installed.
```

### Installing

Script to Build and Install Project:
```
1) Open command propmt, then switch to directory {dir}/bookstore. Please verify that the pom.xml is present in this directory.
2) Run the command "mvn package"
3) Check the target folder for the generated jar file named "BookStore.jar"
4) Place this jar file in any directory.
5) Switch to the directory in which the jar file is placed.
6) Run the command "java -jar BookStore.jar"
```

### APIs Details
```
Add Book  
	endPoint: http://{host}:{port}/bookstore/addbook 
	requestType: POST  
	headers: Content-Type:application/json  
	requestBody: [isbn: STRING, quantity: INTEGER, price: DOUBLE, title: STRING, author: STRING] 

Get Book  
	endPoint: http://{host}:{port}/bookstore/getbook/{searchField:isbn|title|author}/{searchValue}
	requestType: GET  
	headers: Content-Type:application/json  
  
Get Media Coverage  
	endPoint: http://{host}:{port}/bookstore/mediacoverage/{searchField:isbn}/{searchValue}
	requestType: GET  
	headers: Content-Type:application/json 
  
 Purchase Book  
	endPoint: http://{host}:{port}/bookstore/purchase 
	requestType: POST  
	headers: Content-Type:application/json  
	requestBody: [isbn: STRING, quantity: INTEGER, title: STRING, author: STRING]
  
```

## Running Test Cases

```
1) Open command propmt. Then switch the directory to {localPath}/bookstore. Please verify that the pom.xml is present in this directory.  
2) To run the unit tests, issue this command :  
	mvn test  
 ```
 
## NOTE
1) server port can be changed in the application properties file in resource folder. Presently 9000 is set.
2) Java 8 should be installed
3) Embedded database H2 is used. So, no need to configure the db.
