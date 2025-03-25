# Airalo API Automation

This project is designed to automate API testing for the [Airalo API](https://partners-doc.airalo.com/) using REST-assured, TestNG, and a modular testing framework. The tests validate various API endpoints, ensuring correct responses and data integrity.

---

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Setup](#project-setup)
- [Configuration](#configuration)
- [Running the Tests](#running-the-tests)
  - [Locally](#locally)
  - [Using CICD GitHub Actions](#using-cicd-github-actions)
- [Report Generation](#report-generation)
- [Test Case Implementation Overview](#test-case-implementation-overview)
  - [Approach](#approach)
    - [REST-Assured & TestNG Integration](#rest-assured--testng-integration)
    - [Modular Design](#modular-design)
    - [Test Execution Flow](#test-execution-flow)
- [Project Structure](#project-structure)

---

## Prerequisites

Ensure you have the following installed:

- Java 17 
- Maven 3.8+ installed and in classpath
- [IDE with enabled Lombok](https://www.baeldung.com/lombok-ide)

---

## Project Setup

### Clone the repository:
```bash
git clone https://github.com/Muntesu/airalo-api-automation.git
````

### Install Dependencies:
The project uses Maven to manage dependencies. In order to install them run: <code>mvn clean compile</code>

## Configuration

### System Properties:
The project uses a configuration file to load properties from `system.properties` located in `src/main/resources` to set up the API environment.
These properties configure:

- The base URI for the sandbox API.
- Endpoints for SIMs, orders, and token retrieval.
- The content type and port used for HTTP requests.
- Client credentials needed for authentication.

---

## Running the Tests

### Locally
To run the tests locally, execute:
<code>mvn test -DsuiteXmlFile=src/test/resources/suites/all.xml</code>


This command will:
1. Compile the project.
2. Use TestNG to run the test suite defined in `all.xml`.
3. Use the configuration specified in `system.properties` (e.g., base URI, client credentials).

### Using CI/CD (GitHub Actions)
The project is integrated with GitHub Actions for CI/CD. The workflow file is located at `.github/workflows/test.yaml`. 
This workflow:

- Automatically triggers on pushes to the main branch and on pull requests.
- Supports manual triggering via workflow_dispatch, allowing you to run tests on-demand directly from the GitHub Actions tab.
- Sets up JDK 17 (using the Temurin distribution), compiles the project, runs the API tests, and uploads test reports as artifacts.

---

#### Report Generation

After the tests complete, the project generates detailed test reports and artifacts:

- **HTML Reports:**  
  An HTML report (e.g., `ExtentReport.html`) is generated and saved in the `test-output/` directory. This report includes a summary of the test run, logs, and test results.

## Test Case Implementation Overview

### Approach

#### REST-Assured & TestNG Integration:
- REST-assured is used to construct and send HTTP requests to the Airalo API endpoints.
- TestNG organizes and executes the tests, supporting data-driven testing via data providers.

#### Modular Design:
- APIClient: Centralizes REST-assured configuration (base URI, default headers, authentication).
- JsonValidator: Provides helper methods to validate JSON responses against expected schemas.
- ConfigManager: Loads environment-specific properties from system.properties.
- Data Providers: Supply test data for multiple API endpoints to ensure comprehensive coverage.

#### Test Execution Flow:
- Setup: The API client is initialized with the base URI and authentication details.
- Execution: A request is sent to an API endpoint, and the response is captured.
- Validation: The response is validated for HTTP status codes, response times, and JSON content.
- Teardown: Any necessary cleanup is performed, and detailed logs are recorded.

---

## Project Structure
```
airalo-api-automation/
├── .github/                      # CI/CD workflows
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── airalo/
│   │   │           └── api/
│   │   │               ├── client/          # API client logic (RestClient, etc.)
│   │   │               ├── configuration/   # Property loading, environment setup
│   │   │               ├── dto/             # Data Transfer Objects
│   │   │               ├── logger/          # Logging utilities
│   │   │               ├── pojo/            # Plain Java Objects representing JSON data
│   │   │               └── utils/           # Utility/helper methods
│   │   └── resources/                       # Contains system.properties and logs configuration
│
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── airalo/
│       │           └── api/                 # Test classes
│       └── resources/
│           ├── schemas/                    # JSON schema validators
│           └── suites/                     # TestNG suite XML files
│
├── test-output/                    # TestNG reports, screenshots, ExtentReport.html
│   └── ExtentReport.html
├── .gitignore
├── pom.xml                         # Maven configuration
└── README.md

```


