# Quote API

This Web API show information about quotes

### Terminology
* Symbol: The identifier for something that can be traded.
* Bid: The price a trader offers to buy a symbol.
* Ask: The price a trader offers to sell a symbol.
* Quote: The highest bid and lowest ask.

## Instruction

### Build and Test
```shell script
mvn clean package
```

### Run spring-boot Web container
```shell script
mvn clean spring-boot:run
```

### Curl commands [link](https://curl.se) 
```shell script
curl http://localhost:8080/api/symbols/GOOG/quotes/lastest
curl http://localhost:8080/api/symbols/highestAsk?day01-01-2020
```

## API
Access the [Swagger UI](http://localhost:8080/swagger-ui.html) for API documentation

`The application must to be running!!`