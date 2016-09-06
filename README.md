GoEuro Java Developer Test
===========================

This code for goeuro dev test.

The application is using Spring-boot, spring-unittest, AOP, RestTemplates.

How to run the application directly:
--------------------------------------
1- Download the GoEuroTest.jar found in the root directory of the project.
2- issue command ==> java -jar GoEuroTest.jar Berlin
3- the above command will request the ReST api for locations that are near to Berlin.
4- Outout will be dumped into a file with this format "<CITY_NAME>_<FULL_DATETIME> e.g. Berlin_2016-09-06T18:34:21.159.csv" in the location you are running the jar in.


How to build the application yourself:
---------------------------------------
1- clone the repository
2- cd to the project
3- mvn install
4- the above will compile, run test, package and install the application.  

at the end you should see logs like the below:

```javascript
Results :

Tests run: 9, Failures: 0, Errors: 0, Skipped: 2

[INFO] 
[INFO] --- maven-jar-plugin:2.6:jar (default-jar) @ GoEuroTest ---
[INFO] Building jar: /Users/ktawfik/Desktop/test-2/target/GoEuroTest-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.4.0.RELEASE:repackage (default) @ GoEuroTest ---
[INFO] 
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ GoEuroTest ---
[INFO] Installing /Users/ktawfik/Desktop/test-2/target/GoEuroTest-0.0.1-SNAPSHOT.jar to /Users/ktawfik/.m2/repository/com/goeuro/GoEuroTest/0.0.1-SNAPSHOT/GoEuroTest-0.0.1-SNAPSHOT.jar
[INFO] Installing /Users/ktawfik/Desktop/test-2/pom.xml to /Users/ktawfik/.m2/repository/com/goeuro/GoEuroTest/0.0.1-SNAPSHOT/GoEuroTest-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 5.596 s
[INFO] Finished at: 2016-09-06T18:37:47+02:00
[INFO] Final Memory: 29M/325M
[INFO] ------------------------------------------------------------------------
```

The application handle various types of exceptions