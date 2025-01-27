## Outline
### Covered Topics
- More Selenium/Cucumber
- [Types of Testing](https://github.com/EricTrainingRev/241209-JWA/blob/main/Study%20Guides/Levels%20of%20Testing%20Study%20Guide.md#types-of-tests-revisited--updated)
- [System Testing](https://github.com/EricTrainingRev/241209-JWA/blob/main/Study%20Guides/Levels%20of%20Testing%20Study%20Guide.md#system-testing)
- [JUnit](https://github.com/EricTrainingRev/241209-JWA/blob/fe8940dad3b6c366269a915444d6426bd8b75a59/Study%20Guides/Levels%20of%20Testing%20Study%20Guide.md#junit)
- [Mockito](https://github.com/EricTrainingRev/241209-JWA/blob/fe8940dad3b6c366269a915444d6426bd8b75a59/Study%20Guides/Levels%20of%20Testing%20Study%20Guide.md#mockito)
- Sprint 2 Presentations
### Documents
- [Levels of Testing](https://github.com/EricTrainingRev/241209-JWA/blob/main/Study%20Guides/Levels%20of%20Testing%20Study%20Guide.md)
### Review Topics
- Performance Testing
- Smoke vs. Sanity Testing
- Terminology
## Performance Testing
**Performance testing** is a [part of non-functional testing](https://github.com/EricTrainingRev/241209-JWA/blob/fe8940dad3b6c366269a915444d6426bd8b75a59/Study%20Guides/Test%20Team%20Organization%20Study%20Guide.md#software-requirements-specification) that measures the responsiveness of the application. There are various types of performance testing that test the system under different conditions
- **Endurance Testing** - measuring the maximum duration the application can remain stable; useful for finding memory leaks or bad configurations
- **Performance Testing** - measuring latency under normal loads; the standard type of performance testing
- **Load Testing** - analyzes performance by levels of traffic
- **Stress Testing** - Progressively increasing traffic to find when the application breaks down
## Smoke & Sanity Testing
A **smoke test** makes sure the application is live and available for testing, whereas **sanity tests** ensure the new feature or patch is working as intended. 
**Smoke tests** are early, preliminary tests to make sure the basic functioning of the application is working. **Sanity tests** make sure the update does what it's supposed to before testing the rest of the application.
## Terminology
- **State Transition Diagram** - a visual representation of how you can go from one state to another. An example could be the defect lifecycle, which shows how you go from one phase to the next![[firefox_cc0Og73z4R.png]]
- **Decision Tables** - a table of possible user inputs and expected outputs