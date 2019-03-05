FROM openjdk:8-jre

# RUN ln -fs /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && dpkg-reconfigure -f noninteractive tzdata

WORKDIR /home/webserver-log-analyzer

ADD build/libs/webserver-log-analyzer.jar .

ADD access.log .