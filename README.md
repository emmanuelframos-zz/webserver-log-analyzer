# Web Server Log Analyzer
The goal of this application is parse a web server access log file, load the log to MySQL and check if a given IP makes more than a certain number of requests for the given duration.

This application was built on Spring Boot and deployed on Docker Compose.

<br>

## Running Application
1. Access application root directory
2. Execute run-all.sh file


## Usage
After application has started, copy your log files to "/tmp" directory.

On the sequence, submit a request using any HTTP client, as *curl* for example:
~~~
curl -v -X POST -H "Content-Type: application/json" 'http://localhost:9000/api/v1/analyze' -d '{"filePath":"/tmp/PUT_FILE_NAME_HERE", "startDate":"2017-01-01.13:00:00", "duration":"DAILY", "threshold":250}'
~~~


## Load Test
Load test performed using a file with 116.484 rows. <br>
Ubuntu 18.04.1 LTS
CPU i5-7600K 16GB RAM
![](https://i.ibb.co/WWCDzYT/load-test.png)


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


