# Yfwould - Good Weather4Party
Good Weather4Party is an application for playlist suggestion based on your city for your party time!

This application was built on Spring Cloud Netflix stack and it is composed by following applications: Eureka Service Discovery, Zuul Gateway, Good Weather4Party Microservice, Open Weather Integration Microservice and Spotify Integration Microservice.

<br>

### Architecture

![](https://image.ibb.co/dO3OJz/architecture_diagram_1.png)

<br>

### Good Weater4Party Service Discovery (Eureka)
Service Discovery Application built on Spring Cloud Eureka. Eureka instances can be registered and clients can discover the instances using Spring-managed beans. An Eureka server can be created with declarative Java configuration.
#### Eureka Dashboard
It can be accessed on:
[http://localhost:8761](http://localhost:8761)

<br>

### Good Time4Party Gateway (Zuul)
Zuul is an edge service that provides dynamic routing, monitoring, resiliency, security, and more. Automatic regsitration of Zuul filters, and a simple convention over configuration approach to reverse proxy creation.

#### Zuul Routes
It can be accessed on:
[http://localhost:8762/routes](http://localhost:8762/routes)

<br>

### Good Weather4Party Microservice (Spring Boot)
Microservice on top of Spring Boot, it searches for your city temperature and provides you a best playlist for the moment. Uses Hystrix as Circuit Breaker, this makes the application resilient and fault tolerance. Redis is used to avoid unnecessary calls to providers.
#### Hystrix Dashboard
It can be accesed on:
[http://localhost:8080/hystrix](http://localhost:8080/hystrix)

#### Hystrix Monitor
Used to check fallbacks, it can be accesed on:
[http://localhost:8080/hystrix.stream](http://localhost:8080/hystrix.stream)

#### Swagger Documentation
Rest documentation for microservice, it can be accesed on:
[http://localhost:8080](http://localhost:8080)

<br>

### Good Weather4Party - Open Weather Microservice (Spring Boot)
Microservice on top of Spring Boot, it searches for your city temperature based on city name or geographical coordinates. Uses Hystrix as Circuit Breaker, this makes the application resilient and fault tolerance.

#### Hystrix Dashboard
It can be accesed on:
[http://localhost:8081/hystrix](http://localhost:8081/hystrix)

#### Hystrix Monitor
Used to check fallbacks, it can be accesed on:
[http://localhost:8081/hystrix.stream](http://localhost:8081/hystrix.stream)

#### Swagger Documentation
Rest documentation for microservice, it can be accesed on:
[http://localhost:8081](http://localhost:8081/hystrix)

<br>

### Good Weather4Party - Spotify Microservice (Spring Boot)
Microservice on top of Spring Boot, it finds playlists based on Spotify's account. Uses Hystrix as Circuit Breaker, this makes the application resilient and fault tolerance.

#### Hystrix Dashboard 
It can be accesed on:
[http://localhost:8082/hystrix](http://localhost:8082/hystrix)

#### Hystrix Monitor
Used to check fallbacks, it can be accesed on:
[http://localhost:8082/hystrix.stream](http://localhost:8082/hystrix.stream)

#### Swagger Documentation
Rest documentation for microservice, it can be accesed on:
[http://localhost:8082](http://localhost:8082)

<br>

## Running Application
1. Access application root directory
2. Execute run-all.sh file


## Usage
Find a playlist using city name:
~~~
curl -X GET 'http://localhost:8762/ms-goodweather-4party/api/v1/playlist/city?cityName=Curitiba' -v
~~~
Find a playlist using geographical coordinates (latitude and longitude):
~~~     
curl -X GET 'http://localhost:8762/ms-goodweather-4party/api/v1/playlist/coordinates?latitude=-25&longitude=-49' -v
~~~

## Load Test 
Load test performed using Apache Bench for 50 000 request having 100 concurrent. <br>
Ubuntu 16.04
CPU i5-7600K 16GB RAM
![](https://image.ibb.co/ewMKae/Screenshot_from_2018_08_04_03_05_37.png)


## Tool Versions
- Gradle 4.9
- Java Open JDK 1.8.0_171
- Spring Boot 1.5.14.RELEASE
- Spring Cloud 1.4.5.RELEASE
- Docker 18.06.0
- Docker Compose 1.22.0
- Redis 4.0.10


## Dependencies
- Unix Based OS
- Java >= 8
- Gradle >= 4.0
- Docker >= 18.06.0
- Docker Compose >= 1.22.0


## License
Apache License, Version 2.0, January 2004.


