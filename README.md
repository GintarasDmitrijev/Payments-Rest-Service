# Getting Started

### How to build and run this application

This project has everything that is needed to run the application. Just issue the following command
from the project root:

```shell script
mvnw clean install
```
This builds the project and runs all the tests. After project is built, issue the following command to
run the application:
```shell script
mvnw spring-boot:run
```

The directory ```postman_collection``` contains the collection of Postman requests that are needed to 
explore the functionality. 

### Preloaded data
When application starts, the ```LoadDatabase``` class preloads in-memmory database with some ammount of payments. Most
of them have ```creationDate``` value of "now". However, if you want to examine the calculation of cancellation fee,
try cancel the payment with id 4, which is preloaded to database with value of ```creationDate``` as one hour in the past.

### Rest endpoints provided by the application
This API provides the following endpoints to control the flow:

|Method|Endpoint URL|Parameter  |Description|Example|
|------------|-----------|-----------|-------|-------|
|GET|/payments    |    |et all payments|http://localhost:8080/payments   |
|GET|/payments/{id} | -  |Get one payment|http://localhost:8080/payments/1|
|PUT|/payments/{id}/cancel  | |Cancel the payment|http://localhost:8080/payments/1/cancel  |

### Example responses
http://localhost:8080/payments
```json
{
    "_embedded": {
        "type2PaymentTypes": [
            {
                "id": 3,
                "cancellationFee": {
                    "amount": 0.00,
                    "currency": "EUR"
                },
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/payments/3"
                    },
                    "payments": {
                        "href": "http://localhost:8080/payments"
                    }
                }
            }
        ],
        ...
         "_links": {
                "self": {
                    "href": "http://localhost:8080/payments"
                }
    }
```
http://localhost:8080/payments/3
```json
{
    "id": 3,
    "cancellationFee": {
        "amount": 0.00,
        "currency": "EUR"
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/payments/3"
        },
        "payments": {
            "href": "http://localhost:8080/payments"
        }
    }
}
```
http://localhost:8080/payments/3/cancel
```json
{
    "id": 4,
    "cancellationFee": {
        "amount": 0.05,
        "currency": "EUR"
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/payments/4/cancel"
        },
        "one": {
            "href": "http://localhost:8080/payments/4"
        },
        "payments": {
            "href": "http://localhost:8080/payments"
        }
    }
}
```
### How to run end-to-end Cucumber acceptance tests
Open the project in the Intellij IDEA, navigate to ```CucumberTest.java``` class and select "Run 'CucumberTest'" from right click menu.

### Known issues 
 - The field "cancellationFee" is calculated and displayed each time the payment is requested, as opposed to 
 calculate this value only on cancellation.
 - Cucumber tests are not running on command line while ```maven``` goals are executed (Maven and Spring Boot configuration issue).