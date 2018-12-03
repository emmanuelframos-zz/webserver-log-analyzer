FROM java:8-jre

ENV HOME=/home/webserver-log-analyzer

WORKDIR $HOME

ADD wait-for-it.sh wait-for-it.sh

ADD build/libs/webserver-log-analyzer.jar webserver-log-analyzer.jar

CMD ["java", "-jar", "webserver-log-analyzer.jar"]