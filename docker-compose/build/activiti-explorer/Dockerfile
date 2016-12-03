FROM openjdk:8

EXPOSE 8080

ENV TOMCAT_VERSION 8.0.38
ENV ACTIVITI_VERSION 5.22.0

# Tomcat
RUN wget http://archive.apache.org/dist/tomcat/tomcat-8/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/catalina.tar.gz && \
	tar xzf /tmp/catalina.tar.gz -C /opt && \
	ln -s /opt/apache-tomcat-${TOMCAT_VERSION} /opt/tomcat && \
	rm /tmp/catalina.tar.gz && \
	rm -rf /opt/tomcat/webapps/examples && \
	rm -rf /opt/tomcat/webapps/docs

# To install jar files first we need to deploy war files manually
RUN wget https://github.com/Activiti/Activiti/releases/download/activiti-${ACTIVITI_VERSION}/activiti-${ACTIVITI_VERSION}.zip -O /tmp/activiti.zip && \
 	unzip /tmp/activiti.zip -d /opt/activiti && \
	unzip /opt/activiti/activiti-${ACTIVITI_VERSION}/wars/activiti-explorer.war -d /opt/tomcat/webapps/activiti-explorer && \
	unzip /opt/activiti/activiti-${ACTIVITI_VERSION}/wars/activiti-rest.war -d /opt/tomcat/webapps/activiti-rest && \
	rm -f /tmp/activiti.zip \
	rm -rf /opt/activiti

#Add postrge jdbc driver
COPY assets/postgresql-9.4.1212.jar /opt/tomcat/webapps/activiti-explorer/WEB-INF/lib/postgresql-9.4.1212.jar

# Add roles
COPY assets/tomcat-users.xml /opt/apache-tomcat-${TOMCAT_VERSION}/conf/

# Add properties
COPY assets/db.properties /opt/tomcat/webapps/activiti-explorer/WEB-INF/classes/db.properties
COPY assets/engine.properties /opt/tomcat/webapps/activiti-explorer/WEB-INF/classes/engine.properties

CMD ["/opt/tomcat/bin/catalina.sh", "run"]
