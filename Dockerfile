FROM java:8-jre

RUN ln -fs /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && dpkg-reconfigure -f noninteractive tzdata

ENV HOME=/home/webserver-log-analyzer

WORKDIR $HOME

ADD wait-for-it.sh wait-for-it.sh

ADD build/libs/webserver-log-analyzer.jar webserver-log-analyzer.jar