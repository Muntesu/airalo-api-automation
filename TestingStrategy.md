# Testing strategy for OCS API
The best approach before starting the testing is to provide the strategy which would be followed.
It should include the items which will or not be covered as well as the different types of testing to be conducted.
The common strategy used in most of the cases is a so called `Testing Pyramid`, a term introduced by Martin Fowler (https://martinfowler.com/articles/practical-test-pyramid.html) 

# API testing strategy
There could be different approaches to testing the backend (API in our case). Acording to the above link it could be divided as follows:
* Unit testing (done by developers)
* Health check (assure the viability of endpoints)
* Contract test (compare the responses structure conforms with the provided json schema)
* Functional test (ensure the data is properly propagated from lower levels OR check a single functionality. In our case we checked that the API properly transforms the data from the DB. This level should also include checking the negative scenarios)
* E2E either Acceptance test (check interactions between different endpoints, data integrity, following end user business flows)
* Manual test (having postman collections will allow to maintain all the changes and test them before properly automating them)
Note: at some levels to not depend on third-party libraries mocking and stubing is recommended 

# UI testing strategy
The API will be used as the backend to creating the mobile app either the webpage.
The approaches to testing them could differ. They should be based on:
* QA team experience with the given technologies
* Time required for testing
* The company size and business (big companies tend to use the proven and enterprise sollutions (Java + Selenium), small companies are looking for less heavyweight ones (Cypress))

# Performance and Security testing
These types of testing are often ignored but are quite important to:
* Avoid money loss
* Provide user satisfaction (nobody will be watching a match if the stream is getting broken all the times)
* Ensure third party malicious individuals and tools could not break the system

In order to have the tests help in improving the quality of product they should be run within CI/CD pipeline:
* For every commit in PR (both devs and QAs PRs)
* Before merging to the next stage to ensure nothing is broken

Other important notes to improve the QA:
* Have a single code style and enable linting
* Define a single git strategy per project (company)
* Involve cooperation with devs in writing the tests and reviewing the automation PRs
* Have a mechanism to measure the coverage

The number of tests could be increased and in order to not polute the PRs and not increase the merge time we propose:
* Run all regression pack nightly on a dedicated environment identical with production
* Run the tests by grouping them (i.e. in case devs changed payment and didn't affect putting the items to cart, it makes sense to run only the `payment` group)

# Overral testing
* CI/CD integration
* Separate environments for testing purposes
* Data isolation (Dockerized solutions like Selenoid for running the tests)
* Take the best from cloud based solution (AWS, Azure, Google cloud)

# Suggestions:
* Mobile frontend should covere both Android and iOS
* BE devs to provide swagger or yaml file formats with the description of API
* API should also use the authorization (i.e. via token)
* API to use pagination for big ammount of data
* Solutions to be provided with flags (to enable or disable a certain feature) or versioning
* Always handle wrong input (proper error codes and messages)
* Data consistency (if a specific id in one endpoint is of type int, it cannot be of type string in another)

