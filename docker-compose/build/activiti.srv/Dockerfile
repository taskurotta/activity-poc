FROM dhr.thprom.ru:5000/java-oracle-perf:8u66

RUN mkdir /opt/igrr
WORKDIR /opt/igrr

RUN locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

EXPOSE 8081

CMD ["java", "-jar", "/opt/app/activiti-srv.jar"]

COPY activiti-srv.jar /opt/app/activiti-srv.jar

