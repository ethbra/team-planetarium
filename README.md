# Team Planetarium - Bugs Busted
_A fully integrated testing suite_

_**Team Bug Busters**: [Alex](https://github.com/alexylv), [Divyaj](https://github.com/DivyajR), [Ethan](https://github.com/ethbra-revature), [Venjie](https://venjiebanks.github.io/)_


Our project is a demo of our use of learned technologies throughout SDET training at 
Revature. We implemented common testing practices throughout the testing lifecycle

This is a continuation of our previous Planetarium test suite, which focused on system and acceptance
testing. 

## Completed Objectives

**Software Testing Lifecycle**: Completed 3 sprints that evolved through the STLC, following its 
process by utilizing a requirements traceability matrix, creating test data, developing
the test suite, and generating an end report. Generally, we completed sprint MVPs early and made
unexpected progress on stretch goals

**End-to-End Testing**: Created test data with black box testing to check for possible misalignment 
between the written requirements and the functions in the application  

**Unit & Integration Testing**:  With access to the code, we employed multiple testing strategies
for generating tests and created an even more comprehensive battery of tests to check all levels of
the application

**Defects**: We reported over 60 defects, and fixed the large majority of these defects ahead of 
schedule in Sprint 2 as a stretch goal, and completely fixed 

**Added Features**: As another stretch goal for Sprint 3, we added UI improvements to the Planetarium, 
with dynamic sizing for each planet list, a new "galaxy" field for each body, and improvements to the 
selection menu for deleting/creating planets, or logging out

**DevOps Pipeline**: We developed an almost complete Continuous Deployment pipeline for our test 
suite, which could only be further automated by having a production environment to push to. After pushing 
code to GitHub, it is automatically tested (unit, integration, and API tests) and, if successful,
deployed to the simulated production environment on our AWS EC2 instance. 

## Technologies

### Testing
- **Cucumber** implements methods and test data in natural language to make the testing understandable,
including for non-technical people 
- **Selenium** allows us to develop browser interactions with Java, so we can test the application 
end to end, like a user would
- **JUnit** is the standard testing library. JUnit uses decoration-based configuration for doing
unit and integration testing
- **Mockito** simulates interactions with a component that some method depends on. This way, we can
reliably test only one level of the application and test with the assumption that any deeper calls will
  behave deterministically
- **Postman/Newman** sends API calls to the controller and checks for the correct response, then 
generates an importable report
### DevOps
- **GitHub** is our shared codebase, which allows us to make rulesets and webhooks to automatically
implement branch rules and integrate our code into a CI/CD pipeline
- **Jenkins** uses specialized agents to run our pipeline jobs, like deploying a new jar file to the AWS
environment, or running newman and unit/integration tests
### Agile
- **Jira** and AIO let us structure our work into epics/sub-tasks and embed defects into them for a more
intuitive and visually comprehensive format
### Cloud
- **AWS** provided a cloud-hosted VM (**EC2**) and database (**RDS**) to remotely deploy our application as part
of the test environment CI/CD pipeline