# This project is archived!

## Introduction


The main purpose of this micro-service is to ease the process of searching from the command line. 
You can search for any arbitrary word/expression as long as it follows the standard with will be explained later.

### Structure of the request
First of all, it must be in a **json** format.

The body included two main parts :
   - searchWords
   - filters

**searchWords** is a list of words that you are interested, and **filters** is an another list that 
you want to refine/filter the results to have a more accurate precision. 
As I mentioned before, both of them should follow the JSON format. 

Here is a sample request : 
```sh
$ curl -X POST -d '{"searchWords":["pizza","kebab"],"filters":["companyInfo","address"]}' --header "Content-Type: application/json" http://localhost:8080/
```
As you can see, "pizza" and "kebab" are the items to search, and "companyInfo" and "address" are some filters to refine the output..

**Filter** names are general, yet limited words. This limitations should be respected to have an accurate result. 
Here are the list of filters that can be used :
  - eniroId
  - companyInfo
  - address
  - location
  - phoneNumbers
  - homepage
  - facebook
  - companyReviews
  - infoPageLink



## Building and running the service

To build and run the service, follow these steps :

### Step zero
set the `PROFILE` and `KEY` constants in `SearchSerivce.java` file to your customized entities;
This should be set in the `configuration.yml`, but this software is in a very beta mode and needs some refactorings.. 

### Step one
Open a terminal and just issue the following command:
```sh
$ mvn clean install

```


### Step two
After finishing the first step, issue this command:
```sh
$ ./run_service.sh server configuration.yml
```
This command starts the server and moves it to a listening mode.


### Step three
Open **another terminal** and send your request. For this example, I'm using **cURL**.
```sh
$ curl -X POST -d '{"searchWords":["pizza","kebab"],"filters":["companyInfo","facebook"]}' --header "Content-Type: application/json" http://localhost:8080/
```

 
