# OCS API testing
This project was created to provide the aproach to testing the API for the OCS technical task. 

# Required software
* Java JDK 8+
* Maven installed and in your classpath
* [IDE with enabled Lombok](https://www.baeldung.com/lombok-ide)

# How to execute the tests
The tests could be executed directly from `src\test\java` but the framework also provides the
command line support. It allows running the tests by levels and provides the results in reporting format.

## Running the test suites

To run the suites provide the property `-Dsuite` to the command line with the suite name:

| run | command |
|-----|---------|
| health check tests | `mvn test -Dsuite=health` |
| contract tests | `mvn test -Dsuite=contract` |
| functional tests | `mvn test -Dsuite=functional` |
| e2e tests | `mvn test -Dsuite=e2e` |
| all tests | `mvn test -Dsuite=all` |

Once the tests are executed the results could be found in `test-output/report/ocs_api_report.html`.
The execution logs could be found in `test-output/log4j2/`.

# Project Structure

## src/main/java

### api
Classes that support the interaction with API.

### dto
Classes used to deserialize the result of API response.

### db
Database connection and interaction.

### utils
Helper classes 

## src/main/resources
Contains the properties file to provide data to API and setup the environment (system.properties).
The log4j2.xml contains the configuration for logging setup.
athletes.db is the copy of the testing Database.

## src/test/java

### api
Contains the existing tests suites divided by level of testing.

## src/test/resources

### schemas
JSON Schemas to ensure Contract Testing

### suites
TestNG suites divided by levels of testing

### report
extent.properties for reporting configuration

# Libraries
* [TestNG](https://testng.org/doc/) test creation and execution library
* [RestAssured](http://rest-assured.io/) standard library to test REST APIs
* [Log4J2](https://logging.apache.org/log4j/2.x/) logging support
* [ExtentReport](https://extentreports.com/) library for report generation 
* [AssertJ](https://assertj.github.io/doc/) rich set of assertions
* [Sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) SQL Lite database interaction
* [Lombok](https://projectlombok.org) bolierplate code generation

# TODO
* Enable multi threading support
* Add CI pipeline
* Generate DTO on the fly from Swagger of any other API description tool
