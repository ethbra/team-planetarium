## Presentation Requirements
- **10 minutes**
- have your test report ready to go
 (*create final report Thursday*)
    - You should have your **pre-fix and post-fix reports** as part of your presentation
- all team members should participate in the presentation
	- write down what you want to say
	- organize beforehand so everything is ready
	- Ethan will share and prepare visuals

## Presentation Order of Operations
1. Present Test Summary Report/s
	(*Make the summary Thursday*)
    - describe **how test suite evolved** moving from a solo to group project
	    (*tests needed reorganization, comprehensive categorization. Reused most test data but required refactoring. We split work first for Divyaj, Alex, and Venjie to do Postman while I set up structure, then switch*)
    - describe **how you combined automated** tests
	    (*Classes were split into integration/unit, then further into planet/moon/user, and finally into positive/negative. Methods each have their own file for negative tests, using @Parameters for each. Positive tests are all in one file use equivalence partitioning*)
    - describe **what new information was discovered** by your automation test suite
    - conversely, **describe defects** your automated test suite missed that you **discovered during manual testing**
	    (*Images don't scale with window size*) 
2. Run your Automated Tests
    - give a **mini retrospective** on the process of developing the automation suite while the test suite is running
		(*Only Integration/Unit test suite*)
		*Creating a functional test suite was like adding layers of paint. I would create a test, notice there are no errors, then use the test to represent structural issues like bad return type because it would otherwise exit early and not represent errors that need defect reports*
        - what **new defects** were discovered in your testing?
			(*Unimplemented business rules*)
        - how did your understanding of the underlying causes of the defects discovered in your E2E testing change based on the testing done this sprint?
	        (*E2E failed, but unit/integration succeeds in some cases*)
        - what parts of the Planetarium source code were the most difficult to test?
3. Perform Sprint retrospective
    - what went well in the Sprint?
        - you had a good understanding of the work
        - you were quick to implement your test strategy to streamline work
        - you came up with a way to automate much of the setup/tear down for resetting your test environment between tests
        (*recompiled source code to include a reset db endpoint*)
        - etc.
    - what can be improved for next Sprint?
        - did you underestimate how long a phase would take to complete?
        - did you do a poor job of implementing your chosen testing techniques?
        - did you fall behind your schedule and have to rush to finish your work?
        - etc.
    - what work needs to be done next Sprint?
        - do you have more tests to execute?
        - are there edge cases you discovered that you want to test?
        - are there new testing strategies you can implement next sprint?
        - do you need to update your Story Points / Velocity estimates?
        - etc.
4. Field questions

## Recommendations
- visual aids are welcome but not necessary for this presentation
- make sure your team does a couple practice runs for your presentation
- write down what you are going to say for your part of the presentation
- create your test summaries the day before your presentation: if something goes wrong with the live demo you have the pre-made results to fall back on
    - **if something goes wrong** in the live demo don't spend much time trying to debug: **move on** if you can't fix it quickly
- all team members are expected to have their **cameras on** during the presentation: make sure you are somewhere distraction free
- remember to **be proud** of the work you have accomplished
