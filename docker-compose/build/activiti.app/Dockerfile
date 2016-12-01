FROM dhr.thprom.ru:5000/java-oracle-perf:8u66

RUN mkdir /opt/igrr
WORKDIR /opt/igrr

RUN locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

EXPOSE 8080

CMD ["java", "-jar", "/opt/app/activiti-poc.jar"]

COPY activiti-poc-1.0-SNAPSHOT.jar /opt/app/activiti-poc.jar

