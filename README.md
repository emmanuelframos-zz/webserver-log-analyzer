# Web Server Log Analyzer
The goal of this application is parse a web server access log file, loads the log to MySQL and checks if a given IP makes more than a certain number of requests for the given duration.

This application was built on Spring Boot and it is deployed on Docker Compose.

<br>

### Architecture

![](https://image.ibb.co/dO3OJz/architecture_diagram_1.png)


## Running Application
1. Access application root directory
2. Execute run-all.sh file


## Usage
Load a log file and get IPs:
~~~
curl -v -X POST -H "Content-Type: application/json" 'http://localhost:9000/api/v1/analyze' -d '{"filePath":"PUT_FILE_PATH_HERE", "startDate":"2017-01-01.13:00:00", "duration":"DAILY", "threshold":250}'
~~~


## Load Test 
Load test performed using Apache Bench for 50 000 request having 100 concurrent. <br>
Ubuntu 16.04
CPU i5-7600K 16GB RAM
![](https://image.ibb.co/ewMKae/Screenshot_from_2018_08_04_03_05_37.png)


## Tool Versions
- Gradle 4.10.2
- Java Open JDK 1.8.0_191
- Spring Boot 1.5.18.RELEASE
- Docker 18.09.0
- Docker Compose 1.23.1
- MySQL 8.0.13
- HikariCP 3.2.0


## Dependencies
- Unix Based OS
- Java >= 8
- Gradle >= 4.0
- Docker >= 18.06.0
- Docker Compose >= 1.22.0


## License
Apache License, Version 2.0, January 2004.


