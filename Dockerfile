#FROM ubuntu
FROM jboss/wildfly

MAINTAINER Jure Frank version: 1.0

ADD build/praktikumEAR.ear /opt/jboss/wildfly/standalone/deployments/
ADD WebContent/ /opt/jboss/wildfly/standalone/deployments/praktikumEAR.ear/praktikum-II.war/
ADD build/classes /opt/jboss/wildfly/standalone/deployments/praktikumEAR.ear/praktikum-II.war/WEB-INF/
ADD /build/praktikumEAR.ear.deployed /opt/jboss/wildfly/standalone/deployments/

#RUN /opt/jboss/wildfly/bin/standalone.sh --silent
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
#Install java into fresh ubuntu

#RUN apt-get update && \
 #   apt-get upgrade -y && \
  #  apt-get install -y software-properties-common && \
   # add-apt-repository ppa:webupd8team/java -y && \
   # apt-get update && \
   # echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
   # apt-get install -y oracle-java8-installer && \
   # apt-get clean

#Adding default wildfly server


