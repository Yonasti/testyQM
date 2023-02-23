#project
This is a Selenium test framework based on Maven/Java with Allure reports for test results.
It contains 3 Testcases.

#setup
Make sure you have Java installed on your local machine. This project needs Java 11.
In addition to java, you need also:

-Chrome and Firefox browser
-Allure 2.21.0
-Maven 3.9.0


#build and download dependencies
-open Maven window and click refresh icon to reload Maven project at first
-build the project for making sure that everything is fine

#running tests
You can launch tests in different ways, but I recommnd to use testng.xml file for that.
testng.xml file is under /src/test/resources. It contains the entire test suite. 
To start tests just click with right-click on it and choose "Run .../src/test/resources/testng.xml" from options menu.

Tests run parallel on two threads by default - if you need to change the number of threads, just open testng.xml and edit
threads-count value.


#reports
After executing tests, you can view some reports generated with allure.
Go to main folder of the project and make sure you see folder "allure-results" in it.
Type "cmd" in the adress bar. Now type "allure serve allure-results" in the console and press enter. 
A report will be generated in couple of seconds so that you will be able to view it in a browser.


